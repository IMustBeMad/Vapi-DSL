package validation.api.demo.validation.terminator.impl;

import validation.api.demo.validation.exception.SystemMessage;
import validation.api.demo.validation.common.ConditionCluster;
import validation.api.demo.validation.result.ValidationResult;
import validation.api.demo.validation.terminator.Terminator;
import validation.api.demo.validation.tester.impl.TesterFacade;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public enum TernaryTerminator implements Terminator {
    INSTANCE;

    @Override
    public <T> List<SystemMessage> failOnFirstError(ConditionCluster<T> conditionCluster, T obj) {
        throw new UnsupportedOperationException();
    }

    @Override
    public <T> List<SystemMessage> failOnLastError(ConditionCluster<T> conditionCluster, T obj) {
        throw new UnsupportedOperationException();
    }

    @Override
    public <T> List<SystemMessage> failOnNoErrors(ConditionCluster<T> conditionCluster, T obj) {
        throw new UnsupportedOperationException();
    }

    @Override
    public <T> List<SystemMessage> failOnNoneGroupMatch(List<ConditionCluster<T>> conditionClusters, T obj) {
        TesterFacade tester = TesterFacade.INSTANCE;
        List<SystemMessage> systemMessages = new ArrayList<>();

        for (ConditionCluster<T> conditionCluster : conditionClusters) {
            ValidationResult result = getFirstError(tester, conditionCluster, obj);

            if (result == null) {
                return Collections.emptyList();
            }
            systemMessages.add(getErrorReason(result, conditionCluster.getOnError()));
        }

        return systemMessages;
    }

    @Override
    public <T> List<SystemMessage> failOnFirstGroupMatch(List<ConditionCluster<T>> conditionClusters, T obj) {
        TesterFacade tester = TesterFacade.INSTANCE;

        for (ConditionCluster<T> conditionCluster : conditionClusters) {
            List<ValidationResult> result = getGroupErrors(tester, conditionCluster, obj);

            if (!result.isEmpty()) {
                return result.stream()
                             .map(ValidationResult::getReason)
                             .collect(Collectors.toList());
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

    private <T> List<ValidationResult> getGroupErrors(TesterFacade tester, ConditionCluster<T> conditionCluster, T obj) {
        return conditionCluster.getConditions().stream()
                               .map(condition -> tester.invertedTest(condition, obj))
                               .filter(result -> !result.isValid())
                               .collect(Collectors.toList());
    }
}
