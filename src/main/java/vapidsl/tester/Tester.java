package vapidsl.tester;

import vapidsl.common.Condition;
import vapidsl.common.ValidationError;
import vapidsl.result.ValidationResult;

import java.util.List;
import java.util.function.Supplier;

public interface Tester {

    <T> ValidationResult test(Condition<T> condition, T obj);

    default ValidationResult getResult(boolean predicatePassed, Supplier<List<ValidationError>> errorSupplier) {
        return predicatePassed ? ValidationResult.ok() : ValidationResult.failed(errorSupplier.get());
    }
}
