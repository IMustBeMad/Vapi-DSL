package validation;

import validation.dict.MatchMode;
import validation.dict.PurposeMode;
import validation.domain.array.AbstractArrayCondition;
import validation.domain.array.impl.ArrayValidation;
import validation.domain.date.AbstractDateCondition;
import validation.domain.date.impl.DateValidation;
import validation.domain.list.AbstractListCondition;
import validation.domain.list.impl.ListValidation;
import validation.domain.number.biginteger.AbstractLongCondition;
import validation.domain.number.biginteger.impl.LongValidation;
import validation.domain.number.integer.AbstractIntCondition;
import validation.domain.number.integer.impl.IntValidation;
import validation.domain.object.AbstractObjectCondition;
import validation.domain.object.impl.ObjectValidation;
import validation.domain.string.AbstractStringCondition;
import validation.domain.string.impl.StringValidation;
import validation.result.ValidationResult;
import validation.result.impl.ValidationResultImpl;

import java.time.LocalDate;
import java.util.List;

public class Validation {

    public static <T> AbstractObjectCondition<T> failIf(T obj) {
        return new ObjectValidation<>(obj, null, PurposeMode.FAIL);
    }

    public static <T> AbstractObjectCondition<T> succeedIf(T obj) {
        return new ObjectValidation<>(obj, MatchMode.LAZY, PurposeMode.SUCCESS);
    }

    public static <T> AbstractObjectCondition<T> succeedIf(T obj, MatchMode matchMode) {
        return new ObjectValidation<>(obj, matchMode, PurposeMode.SUCCESS);
    }

    public static AbstractStringCondition failIf(String value) {
        return new StringValidation(value, null, PurposeMode.FAIL);
    }

    public static AbstractStringCondition succeedIf(String value) {
        return new StringValidation(value, MatchMode.LAZY, PurposeMode.SUCCESS);
    }

    public static AbstractStringCondition succeedIf(String value, MatchMode matchMode) {
        return new StringValidation(value, matchMode, PurposeMode.SUCCESS);
    }

    public static AbstractIntCondition failIf(Integer integer) {
        return new IntValidation(integer, null, PurposeMode.FAIL);
    }

    public static AbstractIntCondition succeedIf(Integer integer) {
        return new IntValidation(integer, MatchMode.LAZY, PurposeMode.SUCCESS);
    }

    public static AbstractIntCondition succeedIf(Integer integer, MatchMode matchMode) {
        return new IntValidation(integer, matchMode, PurposeMode.SUCCESS);
    }

    public static AbstractLongCondition failIf(Long aLong) {
        return new LongValidation(aLong, null, PurposeMode.FAIL);
    }

    public static AbstractLongCondition succeedIf(Long aLong) {
        return new LongValidation(aLong, MatchMode.LAZY, PurposeMode.SUCCESS);
    }

    public static AbstractLongCondition succeedIf(Long aLong, MatchMode matchMode) {
        return new LongValidation(aLong, matchMode, PurposeMode.SUCCESS);
    }

    public static AbstractDateCondition failIf(LocalDate date) {
        return new DateValidation(date, null, PurposeMode.FAIL);
    }

    public static AbstractDateCondition succeedIf(LocalDate date) {
        return new DateValidation(date, MatchMode.LAZY, PurposeMode.SUCCESS);
    }

    public static AbstractDateCondition succeedIf(LocalDate date, MatchMode matchMode) {
        return new DateValidation(date, matchMode, PurposeMode.SUCCESS);
    }

    public static <T> AbstractListCondition<T> failIf(List<T> list) {
        return new ListValidation<>(list, null, PurposeMode.FAIL);
    }

    public static <T> AbstractListCondition<T> succeedIf(List<T> list) {
        return new ListValidation<>(list, MatchMode.LAZY, PurposeMode.SUCCESS);
    }

    public static <T> AbstractListCondition<T> succeedIf(List<T> list, MatchMode matchMode) {
        return new ListValidation<>(list, matchMode, PurposeMode.SUCCESS);
    }

    public static <T> AbstractArrayCondition<T> failIf(T[] array) {
        return new ArrayValidation<>(array, null, PurposeMode.FAIL);
    }

    public static <T> AbstractArrayCondition<T> succeedIf(T[] array) {
        return new ArrayValidation<>(array, MatchMode.LAZY, PurposeMode.SUCCESS);
    }

    public static <T> AbstractArrayCondition<T> succeedIf(T[] array, MatchMode matchMode) {
        return new ArrayValidation<>(array, matchMode, PurposeMode.SUCCESS);
    }

    public static ValidationResult ok() {
        return new ValidationResultImpl(true);
    }

    public static ValidationResult failed(String codeOnError) {
        return new ValidationResultImpl(false, codeOnError);
    }
}
