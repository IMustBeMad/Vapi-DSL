package validation.api.demo.validation;

import validation.api.demo.validation.domain.number.AbstractLongValidation;
import validation.api.demo.validation.domain.number.impl.LongValidation;
import validation.api.demo.validation.domain.object.AbstractObjectValidation;
import validation.api.demo.validation.domain.object.impl.ObjectValidation;
import validation.api.demo.validation.domain.string.AbstractStringValidation;
import validation.api.demo.validation.domain.string.impl.StringValidation;
import validation.api.demo.validation.result.ValidationResult;
import validation.api.demo.validation.result.impl.ValidationResultImpl;

public class Validation {

    public static AbstractStringValidation verifyIf(String value) {
        return new StringValidation(value);
    }

    public static AbstractLongValidation verifyIf(Long aLong) {
        return new LongValidation(aLong);
    }

    public static AbstractObjectValidation verifyIf(Object obj) {
        return new ObjectValidation(obj);
    }

    public static ValidationResult ok() {
        return new ValidationResultImpl(true);
    }

    public static ValidationResult failed(String codeOnError) {
        return new ValidationResultImpl(false, codeOnError);
    }
}
