package validation.api.demo.validation.tester;

import validation.api.demo.validation.Validation;
import validation.api.demo.validation.common.Condition;
import validation.api.demo.validation.result.ValidationResult;

public interface Tester {

    <T> ValidationResult test(Condition<T> condition, T obj);

    default <T> ValidationResult getResult(boolean isValid, Condition<T> condition) {
        return isValid ? Validation.ok() : Validation.failed(condition.getOnError());
    }
}
