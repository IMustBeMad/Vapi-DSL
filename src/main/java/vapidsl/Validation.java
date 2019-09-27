package vapidsl;

import vapidsl.dict.MatchMode;
import vapidsl.dict.PurposeMode;
import vapidsl.domain.array.AbstractArrayCondition;
import vapidsl.domain.array.impl.ArrayValidation;
import vapidsl.domain.date.AbstractDateCondition;
import vapidsl.domain.date.impl.DateValidation;
import vapidsl.domain.list.AbstractListCondition;
import vapidsl.domain.list.impl.ListValidation;
import vapidsl.domain.number.biginteger.AbstractLongCondition;
import vapidsl.domain.number.biginteger.impl.LongValidation;
import vapidsl.domain.number.integer.AbstractIntCondition;
import vapidsl.domain.number.integer.impl.IntValidation;
import vapidsl.domain.object.AbstractObjectCondition;
import vapidsl.domain.object.impl.ObjectValidation;
import vapidsl.domain.string.AbstractStringCondition;
import vapidsl.domain.string.impl.StringValidation;
import vapidsl.result.ValidationResult;
import vapidsl.result.impl.ValidationResultImpl;

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
