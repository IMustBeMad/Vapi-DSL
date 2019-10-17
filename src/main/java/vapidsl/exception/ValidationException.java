package vapidsl.exception;

import vapidsl.common.ValidationError;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.util.StringUtils.isEmpty;

public class ValidationException extends RuntimeException {

    private ValidationException(String message) {
        super(message);
    }

    public static ValidationException withError(List<ValidationError> messages) {
        return new ValidationException(joinCodes(messages));
    }

    private static String joinCodes(List<ValidationError> messages) {
        return messages.stream()
                       .map(ValidationException::formatValidationError)
                       .collect(Collectors.joining("\n"));
    }

    private static String formatValidationError(ValidationError error) {
        if (isEmpty(error.getField())) {
            return error.getReasonCode();
        }

        return String.format("%s:%s", error.getField(), error.getReasonCode());
    }
}
