package validation.api.demo.validation.tester;

import validation.api.demo.validation.Validation;
import validation.api.demo.validation.common.Condition;
import validation.api.demo.validation.result.ValidationResult;

public interface Tester {

    <T> ValidationResult test(Condition<T> condition, T obj);

    default ValidationResult getResult(boolean isValid, String onError) {
        return isValid ? Validation.ok() : Validation.failed(onError);
    }
}
