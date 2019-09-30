package vapidsl.result.impl;

import vapidsl.common.ValidationError;
import vapidsl.result.ValidationResult;

public class ValidationResultImpl implements ValidationResult {

    private boolean valid;
    private String codeOnError;

    public ValidationResultImpl(boolean valid) {
        this.valid = valid;
    }

    public ValidationResultImpl(boolean valid, String codeOnError) {
        this.valid = valid;
        this.codeOnError = codeOnError;
    }

    @Override
    public boolean isValid() {
        return valid;
    }

    @Override
    public ValidationError getReason() {
        return this.valid ? null : ValidationError.withCode(codeOnError);
    }
}
