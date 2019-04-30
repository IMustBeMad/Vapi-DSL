package validation.api.demo.validation.terminator.impl;

import validation.api.demo.exception.SystemMessage;
import validation.api.demo.exception.ValidationException;
import validation.api.demo.validation.common.Condition;
import validation.api.demo.validation.common.ConditionCluster;
import validation.api.demo.validation.result.ValidationResult;
import validation.api.demo.validation.terminator.Terminator;
import validation.api.demo.validation.tester.impl.TesterFacade;

import java.util.List;
import java.util.stream.Collectors;

public enum SimpleTerminator implements Terminator {
    INSTANCE;

    @Override
    public <T> void failFast(List<Condition<T>> conditions, T obj) {
        TesterFacade tester = TesterFacade.INSTANCE;

        for (Condition<T> condition : conditions) {
            ValidationResult result = tester.test(condition, obj);

            if (!result.isValid()) {
                throw ValidationException.withError(result.getReason());
            }
        }
    }

    @Override
    public <T> void failSafe(List<Condition<T>> conditions, T obj) {
        List<SystemMessage> systemMessages = this.examine(conditions, obj);

        if (!systemMessages.isEmpty()) {
            throw ValidationException.withError(systemMessages);
        }
    }

    @Override
    public <T> void failIfNoneGroupMatch(List<ConditionCluster<T>> conditionClusters, T obj) {
        List<Condition<T>> conditions = conditionClusters.get(0).getConditions();

        this.failFast(conditions, obj);
    }

    @Override
    public <T> List<SystemMessage> examine(List<Condition<T>> conditions, T obj) {
        TesterFacade tester = TesterFacade.INSTANCE;

        return conditions.stream()
                         .map(condition -> tester.test(condition, obj))
                         .filter(it -> !it.isValid())
                         .map(ValidationResult::getReason)
                         .collect(Collectors.toList());
    }
}
