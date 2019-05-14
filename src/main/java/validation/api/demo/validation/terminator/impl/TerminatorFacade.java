package validation.api.demo.validation.terminator.impl;

import validation.api.demo.exception.SystemMessage;
import validation.api.demo.exception.ValidationException;
import validation.api.demo.validation.common.Condition;
import validation.api.demo.validation.common.ConditionCluster;
import validation.api.demo.validation.dict.ErrorMode;
import validation.api.demo.validation.dict.TerminationMode;
import validation.api.demo.validation.terminator.Terminator;

import java.util.Collections;
import java.util.List;

public enum TerminatorFacade {
    INSTANCE;

    public <T> List<SystemMessage> terminate(TerminationMode terminationMode, ErrorMode errorMode, List<ConditionCluster<T>> conditionClusters, T obj) {
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
        List<Condition<T>> conditions = getFirstClusterConditions(conditionClusters);

        return getTerminator(conditionClusters).failOnNoErrors(conditions, obj);
    }

    private <T> List<SystemMessage> failOnFirstError(List<ConditionCluster<T>> conditionClusters, T obj) {
        List<Condition<T>> conditions = getFirstClusterConditions(conditionClusters);

        return getTerminator(conditionClusters).failOnFirstError(conditions, obj);
    }

    private <T> List<SystemMessage> failOnLastError(List<ConditionCluster<T>> conditionClusters, T obj) {
        List<Condition<T>> conditions = getFirstClusterConditions(conditionClusters);

        return getTerminator(conditionClusters).failOnLastError(conditions, obj);
    }

    private <T> List<SystemMessage> failOnFirstGroupMatch(List<ConditionCluster<T>> conditionClusters, T obj) {
        return getTerminator(conditionClusters).failOnFirstGroupMatch(conditionClusters, obj);
    }

    private <T> List<SystemMessage> failOnNoneGroupMatch(List<ConditionCluster<T>> conditionClusters, T obj) {
        return getTerminator(conditionClusters).failOnNoneGroupMatch(conditionClusters, obj);
    }

    private <T> List<Condition<T>> getFirstClusterConditions(List<ConditionCluster<T>> conditionClusters) {
        return conditionClusters.get(0).getConditions();
    }

    private <T> Terminator getTerminator(List<ConditionCluster<T>> conditionClusters) {
        return isSingleCluster(conditionClusters) ? SimpleTerminator.INSTANCE
                                                  : TernaryTerminator.INSTANCE;
    }

    private <T> boolean isSingleCluster(List<ConditionCluster<T>> clusters) {
        return clusters.size() == 1;
    }
}
