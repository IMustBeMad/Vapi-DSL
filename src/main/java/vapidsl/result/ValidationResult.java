package vapidsl.result;


import vapidsl.common.ValidationError;

public interface ValidationResult {

    boolean isValid();

    ValidationError getReason();
}
