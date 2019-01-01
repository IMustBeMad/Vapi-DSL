package validation.api.demo.validation.result;

import validation.api.demo.SystemMessage;

public interface ValidationResult {

    boolean isValid();

    SystemMessage getReason();
}
