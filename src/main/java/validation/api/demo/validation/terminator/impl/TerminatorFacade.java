package validation.api.demo.validation.terminator.impl;

import validation.api.demo.validation.common.ConditionCluster;
import validation.api.demo.validation.dict.ErrorMode;
import validation.api.demo.validation.dict.FailMode;
import validation.api.demo.validation.dict.SucceedMode;
import validation.api.demo.validation.dict.TerminationMode;
import validation.api.demo.validation.exception.SystemMessage;
import validation.api.demo.validation.exception.ValidationException;
import validation.api.demo.validation.terminator.Terminator;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

public enum TerminatorFacade {
    INSTANCE;

    public <T> List<SystemMessage> terminate(FailMode failMode, SucceedMode succeedMode, ErrorMode errorMode, List<ConditionCluster<T>> conditionClusters, T obj) {
        TerminationMode terminationMode = getTerminationMode(failMode, succeedMode, conditionClusters);
        List<SystemMessage> errors = getErrors(terminationMode, conditionClusters, obj);

        if (errorMode == ErrorMode.THROW && !errors.isEmpty()) {
            throw ValidationException.withError(errors);
        }

        return errors;
    }

    private <T> List<SystemMessage> getErrors(TerminationMode terminationMode, List<ConditionCluster<T>> conditionClusters, T obj) {
        switch (terminationMode) {
            case FIRST_ERROR_ENCOUNTERED:
                return failOnFirstError(conditionClusters, obj);
            case LAST_ERROR_ENCOUNTERED:
                return failOnLastError(conditionClusters, obj);
            case NONE_GROUP_MATCH:
                return failOnNoneGroupMatch(conditionClusters, obj);
            case FIRST_GROUP_MATCH:
                return failOnFirstGroupMatch(conditionClusters, obj);
            case NO_ERROR_ENCOUNTERED:
                return failOnNoErrors(conditionClusters, obj);
        }

        return Collections.emptyList();
    }

    private <T> List<SystemMessage> failOnNoErrors(List<ConditionCluster<T>> conditionClusters, T obj) {
        ConditionCluster<T> firstConditionCluster = getFirstConditionCluster(conditionClusters);

        return getTerminator(conditionClusters).failOnNoErrors(firstConditionCluster, obj);
    }

    private <T> List<SystemMessage> failOnFirstError(List<ConditionCluster<T>> conditionClusters, T obj) {
        ConditionCluster<T> firstConditionCluster = getFirstConditionCluster(conditionClusters);

        return getTerminator(conditionClusters).failOnFirstError(firstConditionCluster, obj);
    }

    private <T> List<SystemMessage> failOnLastError(List<ConditionCluster<T>> conditionClusters, T obj) {
        ConditionCluster<T> firstConditionCluster = getFirstConditionCluster(conditionClusters);

        return getTerminator(conditionClusters).failOnLastError(firstConditionCluster, obj);
    }

    private <T> List<SystemMessage> failOnFirstGroupMatch(List<ConditionCluster<T>> conditionClusters, T obj) {
        return getTerminator(conditionClusters).failOnFirstGroupMatch(conditionClusters, obj);
    }

    private <T> List<SystemMessage> failOnNoneGroupMatch(List<ConditionCluster<T>> conditionClusters, T obj) {
        return getTerminator(conditionClusters).failOnNoneGroupMatch(conditionClusters, obj);
    }

    private <T> ConditionCluster<T> getFirstConditionCluster(List<ConditionCluster<T>> conditionClusters) {
        return conditionClusters.get(0);
    }

    private <T> Terminator getTerminator(List<ConditionCluster<T>> conditionClusters) {
        return isSingleCluster(conditionClusters) ? SimpleTerminator.INSTANCE
                                                  : TernaryTerminator.INSTANCE;
    }

    private <T> boolean isSingleCluster(List<ConditionCluster<T>> clusters) {
        return clusters.size() == 1;
    }

    private <T> TerminationMode getTerminationMode(FailMode failMode, SucceedMode succeedMode, List<ConditionCluster<T>> conditionClusters) {
        boolean singleGroup = conditionClusters.size() == 1;

        if (Objects.nonNull(failMode)) {
            if (FailMode.FAST == failMode) {
                return singleGroup ? TerminationMode.NO_ERROR_ENCOUNTERED
                                   : TerminationMode.FIRST_GROUP_MATCH;
            } else {
                return TerminationMode.LAST_ERROR_ENCOUNTERED;
            }
        }
        if (Objects.nonNull(succeedMode)) {
            return singleGroup ? TerminationMode.FIRST_ERROR_ENCOUNTERED
                               : TerminationMode.NONE_GROUP_MATCH;
        }

        return TerminationMode.FIRST_ERROR_ENCOUNTERED;
    }
}
