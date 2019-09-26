package validation.terminator.impl;

import validation.common.ConditionCluster;
import validation.exception.SystemMessage;
import validation.result.ValidationResult;
import validation.terminator.Terminator;
import validation.tester.impl.TesterFacade;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public enum TernaryTerminator implements Terminator {
    INSTANCE;

    @Override
    public <T> List<SystemMessage> failFast(ConditionCluster<T> conditionCluster, T obj) {
        throw new UnsupportedOperationException();
    }

    @Override
    public <T> List<SystemMessage> failSafe(ConditionCluster<T> conditionCluster, T obj) {
        throw new UnsupportedOperationException();
    }

    @Override
    public <T> List<SystemMessage> failOnNoneGroupMatched(List<ConditionCluster<T>> conditionClusters, T obj) {
        TesterFacade tester = TesterFacade.INSTANCE;
        List<SystemMessage> systemMessages = new ArrayList<>();

        for (ConditionCluster<T> conditionCluster : conditionClusters) {
            ValidationResult result = getFirstError(tester, conditionCluster, obj);

            if (result == null) {
                return Collections.emptyList();
            }
            systemMessages.addAll(getErrorReason(Collections.singletonList(result), conditionCluster.getOnError()));
        }

        return systemMessages;
    }

    @Override
    public <T> List<SystemMessage> failOnFirstGroupMatched(List<ConditionCluster<T>> conditionClusters, T obj) {
        TesterFacade tester = TesterFacade.INSTANCE;

        for (ConditionCluster<T> conditionCluster : conditionClusters) {
            ValidationResult result = getFirstError(tester, conditionCluster, obj);

            if (result == null) {
                return getErrorReason(conditionCluster);
            }
        }

        return Collections.emptyList();
    }

    private <T> ValidationResult getFirstError(TesterFacade tester, ConditionCluster<T> conditionCluster, T obj) {
        return conditionCluster.getConditions().stream()
                               .map(condition -> tester.test(condition, obj))
                               .filter(result -> !result.isValid())
                               .findFirst()
                               .orElse(null);
    }
}