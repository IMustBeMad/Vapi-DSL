package validation.api.demo.validation.terminator.impl;

import validation.api.demo.validation.common.ConditionCluster;
import validation.api.demo.validation.dict.ErrorMode;
import validation.api.demo.validation.dict.MatchMode;
import validation.api.demo.validation.dict.PurposeMode;
import validation.api.demo.validation.dict.TerminationMode;
import validation.api.demo.validation.domain.BaseDataHolder;
import validation.api.demo.validation.exception.SystemMessage;
import validation.api.demo.validation.exception.ValidationException;
import validation.api.demo.validation.terminator.Terminator;

import java.util.Collections;
import java.util.List;

public enum TerminatorFacade {
    INSTANCE;

    public <T> List<SystemMessage> terminate(BaseDataHolder.ModeManager modeManager, List<ConditionCluster<T>> conditionClusters, T obj) {
        TerminationMode terminationMode = getTerminationMode(modeManager, conditionClusters);
        List<SystemMessage> errors = getErrors(terminationMode, conditionClusters, obj);

        if (shouldThrowError(modeManager, errors)) {
            throw ValidationException.withError(errors);
        }

        return errors;
    }

    private <T> List<SystemMessage> getErrors(TerminationMode terminationMode, List<ConditionCluster<T>> conditionClusters, T obj) {
        switch (terminationMode) {
            case FAIL_FAST:
                return failFast(conditionClusters, obj);
            case FAIL_SAFE:
                return failSafe(conditionClusters, obj);
            case NONE_GROUP_MATCH:
                return failOnNoneGroupMatch(conditionClusters, obj);
            case LAZY_GROUP_MATCH:
                return failOnFirstGroupMatch(conditionClusters, obj);
        }

        return Collections.emptyList();
    }

    private <T> List<SystemMessage> failFast(List<ConditionCluster<T>> conditionClusters, T obj) {
        ConditionCluster<T> firstConditionCluster = getFirstConditionCluster(conditionClusters);

        return getTerminator(conditionClusters).failFast(firstConditionCluster, obj);
    }

    private <T> List<SystemMessage> failSafe(List<ConditionCluster<T>> conditionClusters, T obj) {
        ConditionCluster<T> firstConditionCluster = getFirstConditionCluster(conditionClusters);

        return getTerminator(conditionClusters).failSafe(firstConditionCluster, obj);
    }

    private <T> List<SystemMessage> failOnFirstGroupMatch(List<ConditionCluster<T>> conditionClusters, T obj) {
        return getTerminator(conditionClusters).failOnFirstGroupMatched(conditionClusters, obj);
    }

    private <T> List<SystemMessage> failOnNoneGroupMatch(List<ConditionCluster<T>> conditionClusters, T obj) {
        return getTerminator(conditionClusters).failOnNoneGroupMatched(conditionClusters, obj);
    }

    private <T> ConditionCluster<T> getFirstConditionCluster(List<ConditionCluster<T>> conditionClusters) {
        return conditionClusters.get(0);
    }

    private <T> Terminator getTerminator(List<ConditionCluster<T>> conditionClusters) {
        return isSingleCluster(conditionClusters) ? SimpleTerminator.INSTANCE : TernaryTerminator.INSTANCE;
    }

    private <T> boolean isSingleCluster(List<ConditionCluster<T>> clusters) {
        return clusters.size() == 1;
    }

    private <T> TerminationMode getTerminationMode(BaseDataHolder.ModeManager modeManager, List<ConditionCluster<T>> conditionClusters) {
        boolean singleGroup = conditionClusters.size() == 1;
        PurposeMode purposeMode = modeManager.getPurposeMode();

        if (purposeMode == PurposeMode.FAIL) {
            return TerminationMode.LAZY_GROUP_MATCH;
        }
        if (purposeMode == PurposeMode.SUCCESS) {
            if (modeManager.getMatchMode() == MatchMode.LAZY) {
                return singleGroup ? TerminationMode.FAIL_FAST
                                   : TerminationMode.NONE_GROUP_MATCH;
            } else {
                if (singleGroup) {
                    return TerminationMode.FAIL_SAFE;
                }
            }
        }

        throw new UnsupportedOperationException();
    }

    private boolean shouldThrowError(BaseDataHolder.ModeManager modeManager, List<SystemMessage> errors) {
        return modeManager.getErrorMode() == ErrorMode.THROW && !errors.isEmpty();
    }
}
