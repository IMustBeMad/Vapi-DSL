package validation.api.demo.validation;

import validation.api.demo.validation.domain.array.impl.ArrayValidation;
import validation.api.demo.validation.domain.date.impl.DateValidation;
import validation.api.demo.validation.domain.list.impl.ListValidation;
import validation.api.demo.validation.domain.number.impl.LongValidation;
import validation.api.demo.validation.domain.object.impl.ObjectValidation;
import validation.api.demo.validation.domain.string.impl.StringValidation;
import validation.api.demo.validation.result.ValidationResult;
import validation.api.demo.validation.result.impl.ValidationResultImpl;

import java.time.LocalDate;
import java.util.List;

public class Validation {

    public static DateValidation verifyIf(LocalDate date) {
        return new DateValidation(date);
    }

    public static StringValidation verifyIf(String value) {
        return new StringValidation(value);
    }

    public static LongValidation verifyIf(Long aLong) {
        return new LongValidation(aLong);
    }

    public static <T> ListValidation<T> verifyIf(List<T> list) {
        return new ListValidation<>(list);
    }

    public static <T> ObjectValidation<T> verifyIf(T obj) {
        return new ObjectValidation<>(obj);
    }

    public static <T> ArrayValidation<T> verifyIf(T[] array) {
        return new ArrayValidation<>(array);
    }

    public static ValidationResult ok() {
        return new ValidationResultImpl(true);
    }

    public static ValidationResult failed(String codeOnError) {
        return new ValidationResultImpl(false, codeOnError);
    }
}
