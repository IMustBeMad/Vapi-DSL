package validation.api.demo.validation.result;

import validation.api.demo.exception.SystemMessage;

public interface ValidationResult {

    boolean isValid();

    SystemMessage getReason();
}
