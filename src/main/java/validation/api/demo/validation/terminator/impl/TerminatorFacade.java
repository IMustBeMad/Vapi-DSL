package validation.api.demo.validation.terminator.impl;

import validation.api.demo.exception.SystemMessage;
import validation.api.demo.validation.common.Condition;
import validation.api.demo.validation.common.ConditionCluster;
import validation.api.demo.validation.terminator.Terminator;

import java.util.List;

public enum TerminatorFacade {
    INSTANCE;

    public <T> void failFast(List<ConditionCluster<T>> conditionClusters, T obj) {
        List<Condition<T>> conditions = getFirstClusterConditions(conditionClusters);

        getTerminator(conditionClusters).failFast(conditions, obj);
    }

    public <T> void failSafe(List<ConditionCluster<T>> conditionClusters, T obj) {
        List<Condition<T>> conditions = getFirstClusterConditions(conditionClusters);

        getTerminator(conditionClusters).failSafe(conditions, obj);
    }

    public <T> void failIfNoneGroupMatch(List<ConditionCluster<T>> conditionClusters, T obj) {
        getTerminator(conditionClusters).failIfNoneGroupMatch(conditionClusters, obj);
    }

    public <T> List<SystemMessage> examine(List<ConditionCluster<T>> conditionClusters, T obj) {
        List<Condition<T>> conditions = getFirstClusterConditions(conditionClusters);

        return getTerminator(conditionClusters).examine(conditions, obj);
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
