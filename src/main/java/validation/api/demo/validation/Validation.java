package validation.api.demo.validation;

import validation.api.demo.validation.domain.array.AbstractArrayCondition;
import validation.api.demo.validation.domain.array.impl.ArrayValidation;
import validation.api.demo.validation.domain.date.AbstractDateCondition;
import validation.api.demo.validation.domain.date.impl.DateValidation;
import validation.api.demo.validation.domain.list.AbstractListCondition;
import validation.api.demo.validation.domain.list.impl.ListValidation;
import validation.api.demo.validation.domain.number.biginteger.AbstractLongCondition;
import validation.api.demo.validation.domain.number.biginteger.impl.LongValidation;
import validation.api.demo.validation.domain.number.integer.AbstractIntCondition;
import validation.api.demo.validation.domain.number.integer.impl.IntValidation;
import validation.api.demo.validation.domain.object.AbstractObjectCondition;
import validation.api.demo.validation.domain.object.impl.ObjectValidation;
import validation.api.demo.validation.domain.string.AbstractStringCondition;
import validation.api.demo.validation.domain.string.impl.StringValidation;
import validation.api.demo.validation.result.ValidationResult;
import validation.api.demo.validation.result.impl.ValidationResultImpl;

import java.time.LocalDate;
import java.util.List;

public class Validation {

    public static <T> AbstractObjectCondition<T> verifyIf(T obj) {
        return new ObjectValidation<>(obj);
    }

    public static AbstractStringCondition verifyIf(String value) {
        return new StringValidation(value);
    }

    public static AbstractIntCondition verifyIf(Integer integer) {
        return new IntValidation(integer);
    }

    public static AbstractLongCondition verifyIf(Long aLong) {
        return new LongValidation(aLong);
    }

    public static AbstractDateCondition verifyIf(LocalDate date) {
        return new DateValidation(date);
    }

    public static <T> AbstractListCondition<T> verifyIf(List<T> list) {
        return new ListValidation<>(list);
    }

    public static <T> AbstractArrayCondition<T> verifyIf(T[] array) {
        return new ArrayValidation<>(array);
    }

    public static ValidationResult ok() {
        return new ValidationResultImpl(true);
    }

    public static ValidationResult failed(String codeOnError) {
        return new ValidationResultImpl(false, codeOnError);
    }
}
