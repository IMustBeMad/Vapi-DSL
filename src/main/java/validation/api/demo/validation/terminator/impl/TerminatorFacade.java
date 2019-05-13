package validation.api.demo.validation.terminator.impl;

import validation.api.demo.exception.SystemMessage;
import validation.api.demo.exception.ValidationException;
import validation.api.demo.validation.common.Condition;
import validation.api.demo.validation.common.ConditionCluster;
import validation.api.demo.validation.dict.ErrorMode;
import validation.api.demo.validation.dict.TerminationMode;
import validation.api.demo.validation.terminator.Terminator;

import java.util.ArrayList;
import java.util.List;

public enum TerminatorFacade {
    INSTANCE;

    public <T> List<SystemMessage> terminate(TerminationMode terminationMode, ErrorMode errorMode, List<ConditionCluster<T>> conditionClusters, T obj) {
        List<SystemMessage> errors = new ArrayList<>();

        switch (terminationMode) {
            case FIRST_ERROR:
            case FIRST_GROUP_MATCH:
                errors = failFast(conditionClusters, obj);
                break;
            case ERRORS_COMPUTED:
                errors = failSafe(conditionClusters, obj);
                break;
            case NONE_GROUP_MATCH:
                errors = failIfNoneGroupMatch(conditionClusters, obj);
                break;
        }

        if (errorMode == ErrorMode.THROW && !errors.isEmpty()) {
            throw ValidationException.withError(errors);
        }

        return errors;
    }

    private <T> List<SystemMessage> failFast(List<ConditionCluster<T>> conditionClusters, T obj) {
        List<Condition<T>> conditions = getFirstClusterConditions(conditionClusters);

        return getTerminator(conditionClusters).failFast(conditions, obj);
    }

    private <T> List<SystemMessage> failSafe(List<ConditionCluster<T>> conditionClusters, T obj) {
        List<Condition<T>> conditions = getFirstClusterConditions(conditionClusters);

        return getTerminator(conditionClusters).failSafe(conditions, obj);
    }

    private <T> List<SystemMessage> failIfNoneGroupMatch(List<ConditionCluster<T>> conditionClusters, T obj) {
        return getTerminator(conditionClusters).failIfNoneGroupMatch(conditionClusters, obj);
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
