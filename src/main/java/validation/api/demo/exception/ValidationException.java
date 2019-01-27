package validation.api.demo.exception;

public class ValidationException extends RuntimeException {

    public ValidationException(SystemMessage message) {
        super(message.getReasonCode());
    }
}
