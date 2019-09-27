package validation.exception;

import validation.common.ValidationError;

import java.util.List;
import java.util.stream.Collectors;

public class ValidationException extends RuntimeException {

    private ValidationException(String message) {
        super(message);
    }

    public static ValidationException withError(ValidationError message) {
        return new ValidationException(message.getReasonCode());
    }

    public static ValidationException withError(List<ValidationError> messages) {
        return new ValidationException(joinCodes(messages));
    }

    private static String joinMessages(List<List<ValidationError>> messages) {
        return messages.stream()
                       .map(ValidationException::joinCodes)
                       .collect(Collectors.joining("\n<OR>\n"));
    }

    private static String joinCodes(List<ValidationError> messages) {
        return messages.stream()
                       .map(ValidationError::getReasonCode)
                       .collect(Collectors.joining("\n"));
    }
}
