package validation.api.demo.validation.domain;

import validation.api.demo.exception.SystemMessage;
import validation.api.demo.exception.ValidationException;
import validation.api.demo.validation.common.Condition;
import validation.api.demo.validation.result.ValidationResult;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class TerminatorClass<T> implements SimpleTerminator, BinaryTerminator {

    @Override
    public void failFast() {
        List<Condition<T>> conditions = this.currentCluster.getConditions();

        for (Condition<T> condition : conditions) {
            ValidationResult result = this.test(condition);

            if (!result.isValid()) {
                throw ValidationException.withError(result.getReason());
            }
        }
    }

    @Override
    public void failSafe() {
        List<SystemMessage> systemMessages = this.examine();

        if (!systemMessages.isEmpty()) {
            throw ValidationException.withError(systemMessages);
        }
    }

    @Override
    public List<SystemMessage> examine() {
        List<Condition<T>> conditions = this.currentCluster.getConditions();

        return conditions.stream()
                         .map(this::test)
                         .filter(it -> !it.isValid())
                         .map(ValidationResult::getReason)
                         .collect(Collectors.toList());
    }

    @Override
    public List<SystemMessage> matched() {
        return Collections.emptyList();
    }
}
