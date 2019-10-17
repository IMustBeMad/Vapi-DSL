package vapidsl.tester;

import vapidsl.Validation;
import vapidsl.common.Condition;
import vapidsl.common.ValidationError;
import vapidsl.result.ValidationResult;

import java.util.List;

public interface Tester {

    <T> ValidationResult test(Condition<T> condition, T obj);

    default ValidationResult getResult(boolean predicatePassed, List<ValidationError> onError) {
        return predicatePassed ? Validation.ok() : Validation.failed(onError);
    }
}
