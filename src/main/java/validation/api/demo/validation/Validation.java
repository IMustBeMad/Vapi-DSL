package validation.api.demo.validation;

import lombok.AllArgsConstructor;
import validation.api.demo.validation.result.ValidationResult;
import validation.api.demo.validation.result.impl.ValidationResultImpl;

import java.util.function.Predicate;

@AllArgsConstructor
public class Validation<P> {

    private Predicate<P> predicate;
    private String codeOnError;

    public static ValidationResult ok() {
        return new ValidationResultImpl(true);
    }

    public static ValidationResult fail(String codeOnError) {
        return new ValidationResultImpl(false, codeOnError);
    }

    public ValidationResult test(P param) {
        return predicate.test(param) ? Validation.ok() : Validation.fail(this.codeOnError) ;
    }
}
