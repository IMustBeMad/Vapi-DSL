package vapidsl.tester;

import vapidsl.Validation;
import vapidsl.common.Condition;
import vapidsl.result.ValidationResult;

public interface Tester {

    <T> ValidationResult test(Condition<T> condition, T obj);

    default ValidationResult getResult(boolean predicatePassed, String onError) {
        return predicatePassed ? Validation.ok() : Validation.failed(onError);
    }
}
