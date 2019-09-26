package validation.api.demo.validation.result;

import validation.api.demo.validation.common.ValidationError;

public interface ValidationResult {

    boolean isValid();

    ValidationError getReason();
}
