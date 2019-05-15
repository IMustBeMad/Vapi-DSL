package validation.api.demo.validation.terminator.impl;

import validation.api.demo.exception.SystemMessage;
import validation.api.demo.validation.common.Condition;
import validation.api.demo.validation.common.ConditionCluster;
import validation.api.demo.validation.dict.FailureMode;
import validation.api.demo.validation.result.ValidationResult;
import validation.api.demo.validation.terminator.Terminator;
import validation.api.demo.validation.tester.impl.TesterFacade;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public enum SimpleTerminator implements Terminator {
    INSTANCE;

    @Override
    public <T> List<SystemMessage> failOnFirstError(List<Condition<T>> conditions, T obj) {
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
    public <T> List<SystemMessage> failOnLastError(List<Condition<T>> conditions, T obj) {
        TesterFacade tester = TesterFacade.INSTANCE;
        List<SystemMessage> errors = new ArrayList<>();

        for (Condition<T> condition : conditions) {
            ValidationResult result = tester.test(condition, obj);

            if (!result.isValid()) {
                SystemMessage reason = result.getReason();

                if (condition.getFailureMode() == FailureMode.EARLY_EXIT) {
                    return Collections.singletonList(reason);
                }

                errors.add(reason);
            }
        }

        return errors;
    }

    @Override
    public <T> List<SystemMessage> failOnNoErrors(List<Condition<T>> conditions, T obj) {
        List<SystemMessage> systemMessages = this.failOnLastError(conditions, obj);

        if (systemMessages.isEmpty()) {
            return Collections.singletonList(SystemMessage.withError("group", "fail.on.no.errors"));
        }

        return Collections.emptyList();
    }

    @Override
    public <T> List<SystemMessage> failOnNoneGroupMatch(List<ConditionCluster<T>> conditionClusters, T obj) {
        return this.failOnFirstError(conditionClusters.get(0).getConditions(), obj);
    }

    @Override
    public <T> List<SystemMessage> failOnFirstGroupMatch(List<ConditionCluster<T>> conditionClusters, T obj) {
        return this.failOnNoErrors(conditionClusters.get(0).getConditions(), obj);
    }
}
