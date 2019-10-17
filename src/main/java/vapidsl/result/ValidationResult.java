package vapidsl.result;


import vapidsl.common.ValidationError;

import java.util.List;

public interface ValidationResult {

    boolean isValid();

    List<ValidationError> getReason();
}
