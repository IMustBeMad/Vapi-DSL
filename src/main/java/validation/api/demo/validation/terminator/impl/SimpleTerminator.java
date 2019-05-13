package validation.api.demo.validation.terminator.impl;

import validation.api.demo.exception.SystemMessage;
import validation.api.demo.validation.common.Condition;
import validation.api.demo.validation.common.ConditionCluster;
import validation.api.demo.validation.result.ValidationResult;
import validation.api.demo.validation.terminator.Terminator;
import validation.api.demo.validation.tester.impl.TesterFacade;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public enum SimpleTerminator implements Terminator {
    INSTANCE;

    @Override
    public <T> List<SystemMessage> failFast(List<Condition<T>> conditions, T obj) {
        TesterFacade tester = TesterFacade.INSTANCE;

        for (Condition<T> condition : conditions) {
            ValidationResult result = tester.test(condition, obj);

            if (!result.isValid()) {
                return Collections.singletonList(result.getReason());
            }
        }

        return Collections.emptyList();
    }

    @Override
    public <T> List<SystemMessage> failSafe(List<Condition<T>> conditions, T obj) {
        TesterFacade tester = TesterFacade.INSTANCE;

        return conditions.stream()
                         .map(condition -> tester.test(condition, obj))
                         .filter(result -> !result.isValid())
                         .map(ValidationResult::getReason)
                         .collect(Collectors.toList());
    }

    @Override
    public <T> List<SystemMessage> failIfNoneGroupMatch(List<ConditionCluster<T>> conditionClusters, T obj) {
        return this.failFast(conditionClusters.get(0).getConditions(), obj);
    }
}
