package vapidsl.result;


import vapidsl.common.ValidationError;
import vapidsl.result.impl.ValidationResultImpl;

import java.util.List;

public interface ValidationResult {

    boolean isValid();

    List<ValidationError> getReason();

    static ValidationResult ok() {
        return new ValidationResultImpl(true);
    }

    static ValidationResult failed(List<ValidationError> validationError) {
        return new ValidationResultImpl(false, validationError);
    }
}
