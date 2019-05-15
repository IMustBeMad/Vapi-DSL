package validation.api.demo.validation.terminator.impl;

import validation.api.demo.exception.SystemMessage;
import validation.api.demo.validation.common.Condition;
import validation.api.demo.validation.common.ConditionCluster;
import validation.api.demo.validation.result.ValidationResult;
import validation.api.demo.validation.terminator.Terminator;
import validation.api.demo.validation.tester.impl.TesterFacade;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public enum TernaryTerminator implements Terminator {
    INSTANCE;

    @Override
    public <T> List<SystemMessage> failOnFirstError(List<Condition<T>> conditions, T obj) {
        throw new UnsupportedOperationException();
    }

    @Override
    public <T> List<SystemMessage> failOnLastError(List<Condition<T>> conditions, T obj) {
        throw new UnsupportedOperationException();
    }

    @Override
    public <T> List<SystemMessage> failOnNoErrors(List<Condition<T>> conditions, T obj) {
        throw new UnsupportedOperationException();
    }

    @Override
    public <T> List<SystemMessage> failOnNoneGroupMatch(List<ConditionCluster<T>> conditionClusters, T obj) {
        TesterFacade tester = TesterFacade.INSTANCE;
        List<SystemMessage> systemMessages = new ArrayList<>();

        for (ConditionCluster<T> conditionCluster : conditionClusters) {
            SystemMessage systemMessage = getFirstError(tester, conditionCluster, obj);

            if (systemMessage == null) {
                return Collections.emptyList();
            }
            systemMessages.add(systemMessage);
        }

        return systemMessages;
    }

    @Override
    public <T> List<SystemMessage> failOnFirstGroupMatch(List<ConditionCluster<T>> conditionClusters, T obj) {
        TesterFacade tester = TesterFacade.INSTANCE;

        for (ConditionCluster<T> conditionCluster : conditionClusters) {
            SystemMessage systemMessage = getFirstError(tester, conditionCluster, obj);

            if (systemMessage == null) {
                return Collections.singletonList(SystemMessage.withError("group", "fail.on.first.group.match"));
            }
        }

        return Collections.emptyList();
    }

    private <T> SystemMessage getFirstError(TesterFacade tester, ConditionCluster<T> conditionCluster, T obj) {
        return conditionCluster.getConditions().stream()
                               .map(condition -> tester.test(condition, obj))
                               .filter(result -> !result.isValid())
                               .findFirst()
                               .map(ValidationResult::getReason)
                               .orElse(null);
    }
}
