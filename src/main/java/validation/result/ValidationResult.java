package validation.result;


import validation.common.ValidationError;

public interface ValidationResult {

    boolean isValid();

    ValidationError getReason();
}
