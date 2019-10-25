package vapidsl.terminator.impl;

import vapidsl.common.Condition;
import vapidsl.common.ConditionCluster;
import vapidsl.dict.FlowType;
import vapidsl.result.ValidationResult;
import vapidsl.terminator.Terminator;
import vapidsl.tester.impl.TesterFacade;

import java.util.ArrayList;
import java.util.List;

public enum SimpleTerminator implements Terminator {
    INSTANCE;

    @Override
    public <T> ValidationResult failFast(ConditionCluster<T> conditionCluster, T obj) {
        TesterFacade tester = TesterFacade.INSTANCE;
        List<Condition<T>> conditions = conditionCluster.getConditions();

        for (Condition<T> condition : conditions) {
            ValidationResult result = tester.test(condition, obj);

            if (!result.isValid()) {
                return getResult(result, conditionCluster);
            }
        }

        return ValidationResult.ok();
    }

    @Override
    public <T> ValidationResult failSafe(ConditionCluster<T> conditionCluster, T obj) {
        TesterFacade tester = TesterFacade.INSTANCE;
        List<Condition<T>> conditions = conditionCluster.getConditions();
        List<ValidationResult> validationResults = new ArrayList<>();

        for (Condition<T> condition : conditions) {
            ValidationResult result = tester.test(condition, obj);

            if (!result.isValid()) {
                if (condition.getFlowType() == FlowType.EARLY_EXIT) {
                    return result;
                }

                validationResults.add(getResult(result, conditionCluster));
            }
        }

        return getResult(validationResults);
    }

    @Override
    public <T> ValidationResult failOnNoneGroupMatched(List<ConditionCluster<T>> conditionClusters, T obj) {
        throw new UnsupportedOperationException();
    }

    @Override
    public <T> ValidationResult failOnFirstGroupMatched(List<ConditionCluster<T>> conditionClusters, T obj) {
        ConditionCluster<T> conditionCluster = conditionClusters.get(0);
        ValidationResult validationResult = this.failFast(conditionCluster, obj);

        if (validationResult.isValid()) {
            return this.getResult(conditionCluster);
        }

        return ValidationResult.ok();
    }
}
