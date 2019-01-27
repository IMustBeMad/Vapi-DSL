package validation.api.demo.exception;

import java.util.List;
import java.util.stream.Collectors;

public class ValidationException extends RuntimeException {

    private ValidationException(String message) {
        super(message);
    }

    public static ValidationException withError(SystemMessage message) {
        return new ValidationException(message.getReasonCode());
    }

    public static ValidationException withError(List<SystemMessage> messages) {
        return new ValidationException(joinCodes(messages));
    }

    private static String joinCodes(List<SystemMessage> messages) {
        return messages.stream().map(SystemMessage::getReasonCode).collect(Collectors.joining("\n"));
    }
}
