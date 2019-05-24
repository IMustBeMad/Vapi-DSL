package validation.api.demo.validation.result;

import validation.api.demo.validation.exception.SystemMessage;

public interface ValidationResult {

    boolean isValid();

    SystemMessage getReason();
}
