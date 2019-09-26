package validation.result;

import validation.exception.SystemMessage;

public interface ValidationResult {

    boolean isValid();

    SystemMessage getReason();
}
