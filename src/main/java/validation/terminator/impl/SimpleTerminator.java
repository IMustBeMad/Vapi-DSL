package validation.terminator.impl;

import validation.common.Condition;
import validation.common.ConditionCluster;
import validation.dict.FlowType;
import validation.common.ValidationError;
import validation.result.ValidationResult;
import validation.terminator.Terminator;
import validation.tester.impl.TesterFacade;

import java.util.*;

public enum SimpleTerminator implements Terminator {
    INSTANCE;

    @Override
    public <T> List<ValidationError> failFast(ConditionCluster<T> conditionCluster, T obj) {
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
    public <T> List<ValidationError> failSafe(ConditionCluster<T> conditionCluster, T obj) {
        TesterFacade tester = TesterFacade.INSTANCE;
        List<Condition<T>> conditions = conditionCluster.getConditions();
        Set<ValidationError> errors = new HashSet<>();

        for (Condition<T> condition : conditions) {
            ValidationResult result = tester.test(condition, obj);

            if (!result.isValid()) {
                ValidationError reason = result.getReason();

                if (condition.getFlowType() == FlowType.EARLY_EXIT) {
                    return Collections.singletonList(reason);
                }

                errors.addAll(getErrorReason(Collections.singletonList(result), conditionCluster.getOnError()));
            }
        }

        return new ArrayList<>(errors);
    }

    @Override
    public <T> List<ValidationError> failOnNoneGroupMatched(List<ConditionCluster<T>> conditionClusters, T obj) {
        throw new UnsupportedOperationException();
    }

    @Override
    public <T> List<ValidationError> failOnFirstGroupMatched(List<ConditionCluster<T>> conditionClusters, T obj) {
        ConditionCluster<T> conditionCluster = conditionClusters.get(0);
        List<ValidationError> validationErrors = this.failFast(conditionCluster, obj);

        if (validationErrors.isEmpty()) {
            return getErrorReason(conditionCluster);
        }

        return Collections.emptyList();
    }
}
