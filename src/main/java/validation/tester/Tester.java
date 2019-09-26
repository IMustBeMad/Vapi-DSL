package validation.tester;

import validation.Validation;
import validation.common.Condition;
import validation.result.ValidationResult;

public interface Tester {

    <T> ValidationResult test(Condition<T> condition, T obj);

    default ValidationResult getResult(boolean predicatePassed, String onError) {
        return predicatePassed ? Validation.ok() : Validation.failed(onError);
    }
}
