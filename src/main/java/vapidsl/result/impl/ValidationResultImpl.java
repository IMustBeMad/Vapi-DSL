package vapidsl.result.impl;

import vapidsl.common.ValidationError;
import vapidsl.result.ValidationResult;

import java.util.Collections;
import java.util.List;

public class ValidationResultImpl implements ValidationResult {

    private boolean valid;
    private List<ValidationError> validationError;

    public ValidationResultImpl(boolean valid) {
        this.valid = valid;
    }

    public ValidationResultImpl(boolean valid, List<ValidationError> validationError) {
        this.valid = valid;
        this.validationError = validationError;
    }

    @Override
    public boolean isValid() {
        return valid;
    }

    @Override
    public List<ValidationError> getReason() {
        return this.valid ? Collections.emptyList() : validationError;
    }
}
