package validation.api.demo.validation.terminator.impl;

import validation.api.demo.validation.common.Condition;
import validation.api.demo.validation.common.ConditionCluster;
import validation.api.demo.validation.dict.FlowType;
import validation.api.demo.validation.exception.SystemMessage;
import validation.api.demo.validation.result.ValidationResult;
import validation.api.demo.validation.terminator.Terminator;
import validation.api.demo.validation.tester.impl.TesterFacade;

import java.util.*;

public enum SimpleTerminator implements Terminator {
    INSTANCE;

    @Override
    public <T> List<SystemMessage> failFast(ConditionCluster<T> conditionCluster, T obj) {
        TesterFacade tester = TesterFacade.INSTANCE;
        List<Condition<T>> conditions = conditionCluster.getConditions();

        for (Condition<T> condition : conditions) {
            ValidationResult result = tester.test(condition, obj);

            if (!result.isValid()) {
                return getErrorReason(Collections.singletonList(result), conditionCluster.getOnError());
            }
        }

        return Collections.emptyList();
    }

    @Override
    public <T> List<SystemMessage> failSafe(ConditionCluster<T> conditionCluster, T obj) {
        TesterFacade tester = TesterFacade.INSTANCE;
        List<Condition<T>> conditions = conditionCluster.getConditions();
        Set<SystemMessage> errors = new HashSet<>();

        for (Condition<T> condition : conditions) {
            ValidationResult result = tester.test(condition, obj);

            if (!result.isValid()) {
                SystemMessage reason = result.getReason();

                if (condition.getFlowType() == FlowType.EARLY_EXIT) {
                    return Collections.singletonList(reason);
                }

                errors.addAll(getErrorReason(Collections.singletonList(result), conditionCluster.getOnError()));
            }
        }

        return new ArrayList<>(errors);
    }

    @Override
    public <T> List<SystemMessage> failOnNoneGroupMatched(List<ConditionCluster<T>> conditionClusters, T obj) {
        throw new UnsupportedOperationException();
    }

    @Override
    public <T> List<SystemMessage> failOnFirstGroupMatched(List<ConditionCluster<T>> conditionClusters, T obj) {
        ConditionCluster<T> conditionCluster = conditionClusters.get(0);
        List<SystemMessage> systemMessages = this.failFast(conditionCluster, obj);

        if (systemMessages.isEmpty()) {
            return getErrorReason(conditionCluster);
        }

        return Collections.emptyList();
    }
}
