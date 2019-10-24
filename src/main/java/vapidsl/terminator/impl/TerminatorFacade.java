package vapidsl.terminator.impl;

import vapidsl.common.ConditionCluster;
import vapidsl.dict.MatchMode;
import vapidsl.dict.PurposeMode;
import vapidsl.dict.TerminationMode;
import vapidsl.domain.BaseDataHolder;
import vapidsl.result.ValidationResult;
import vapidsl.terminator.Terminator;

import java.util.List;

public enum TerminatorFacade {
    INSTANCE;

    public <T> ValidationResult terminate(BaseDataHolder.ModeManager modeManager, List<ConditionCluster<T>> conditionClusters, T obj) {
        TerminationMode terminationMode = getTerminationMode(modeManager, conditionClusters);

        return getValidationResult(terminationMode, conditionClusters, obj);
    }

    private <T> ValidationResult getValidationResult(TerminationMode terminationMode, List<ConditionCluster<T>> conditionClusters, T obj) {
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

        return ValidationResult.ok();
    }

    private <T> ValidationResult failFast(List<ConditionCluster<T>> conditionClusters, T obj) {
        ConditionCluster<T> firstConditionCluster = getFirstConditionCluster(conditionClusters);

        return getTerminator(conditionClusters).failFast(firstConditionCluster, obj);
    }

    private <T> ValidationResult failSafe(List<ConditionCluster<T>> conditionClusters, T obj) {
        ConditionCluster<T> firstConditionCluster = getFirstConditionCluster(conditionClusters);

        return getTerminator(conditionClusters).failSafe(firstConditionCluster, obj);
    }

    private <T> ValidationResult failOnFirstGroupMatch(List<ConditionCluster<T>> conditionClusters, T obj) {
        return getTerminator(conditionClusters).failOnFirstGroupMatched(conditionClusters, obj);
    }

    private <T> ValidationResult failOnNoneGroupMatch(List<ConditionCluster<T>> conditionClusters, T obj) {
        return getTerminator(conditionClusters).failOnNoneGroupMatched(conditionClusters, obj);
    }

    private <T> ConditionCluster<T> getFirstConditionCluster(List<ConditionCluster<T>> conditionClusters) {
        return conditionClusters.get(0);
    }

    private <T> Terminator getTerminator(List<ConditionCluster<T>> conditionClusters) {
        return isSingleGroup(conditionClusters) ? SimpleTerminator.INSTANCE : TernaryTerminator.INSTANCE;
    }

    private <T> boolean isSingleGroup(List<ConditionCluster<T>> clusters) {
        return clusters.size() == 1;
    }

    private <T> TerminationMode getTerminationMode(BaseDataHolder.ModeManager modeManager, List<ConditionCluster<T>> conditionClusters) {
        boolean singleGroup = isSingleGroup(conditionClusters);
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
}
