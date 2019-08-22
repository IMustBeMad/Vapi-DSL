package validation.api.demo.validation.tester;

import validation.api.demo.validation.Validation;
import validation.api.demo.validation.common.Condition;
import validation.api.demo.validation.result.ValidationResult;

public interface Tester {

    <T> ValidationResult test(Condition<T> condition, T obj, TestMode testMode);

    default ValidationResult getResult(boolean predicatePassed, String onError, TestMode testMode) {
        if (testMode == TestMode.INVERTED) {
            return predicatePassed ? Validation.failed(onError) : Validation.ok();
        }

        return predicatePassed ? Validation.ok() : Validation.failed(onError);
    }

    enum TestMode {
        STRAIGHT, INVERTED
    }
}
