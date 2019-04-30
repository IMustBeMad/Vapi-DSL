package validation.api.demo.validation.terminator.impl;

import validation.api.demo.exception.SystemMessage;
import validation.api.demo.exception.ValidationException;
import validation.api.demo.validation.common.Condition;
import validation.api.demo.validation.common.ConditionCluster;
import validation.api.demo.validation.result.ValidationResult;
import validation.api.demo.validation.terminator.Terminator;
import validation.api.demo.validation.tester.impl.TesterFacade;

import java.util.ArrayList;
import java.util.List;

public enum TernaryTerminator implements Terminator {
    INSTANCE;

    @Override
    public <T> void failFast(List<Condition<T>> conditions, T obj) {
        throw new UnsupportedOperationException();
    }

    @Override
    public <T> void failSafe(List<Condition<T>> conditions, T obj) {
        throw new UnsupportedOperationException();
    }

    @Override
    public <T> void failIfNoneGroupMatch(List<ConditionCluster<T>> conditionClusters, T obj) {
        TesterFacade tester = TesterFacade.INSTANCE;
        List<SystemMessage> systemMessages = new ArrayList<>();

        for (ConditionCluster<T> conditionCluster : conditionClusters) {
            SystemMessage systemMessage = null;
            List<Condition<T>> conditions = conditionCluster.getConditions();

            for (Condition<T> condition : conditions) {
                ValidationResult result = tester.test(condition, obj);

                if (!result.isValid()) {
                    systemMessage = result.getReason();
                    systemMessages.add(systemMessage);
                    break;
                }
            }

            if (systemMessage == null) {
                return;
            }
        }
        if (!systemMessages.isEmpty()) {
            throw ValidationException.withError(systemMessages);
        }
    }

    @Override
    public <T> List<SystemMessage> examine(List<Condition<T>> conditions, T obj) {
        return null;
    }
}
