package vapidsl;

import vapidsl.dict.MatchMode;
import vapidsl.dict.PurposeMode;
import vapidsl.domain.array.AbstractArrayCondition;
import vapidsl.domain.array.impl.ArrayValidation;
import vapidsl.domain.bool.AbstractBoolCondition;
import vapidsl.domain.bool.impl.BoolValidation;
import vapidsl.domain.date.AbstractDateCondition;
import vapidsl.domain.date.impl.DateValidation;
import vapidsl.domain.list.AbstractListCondition;
import vapidsl.domain.list.impl.ListValidation;
import vapidsl.domain.map.AbstractMapCondition;
import vapidsl.domain.map.impl.MapValidation;
import vapidsl.domain.number.biginteger.AbstractLongCondition;
import vapidsl.domain.number.biginteger.impl.LongValidation;
import vapidsl.domain.number.integer.AbstractIntCondition;
import vapidsl.domain.number.integer.impl.IntValidation;
import vapidsl.domain.object.AbstractObjectCondition;
import vapidsl.domain.object.impl.ObjectValidation;
import vapidsl.domain.string.AbstractStringCondition;
import vapidsl.domain.string.impl.StringValidation;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public class Validation {

    public static <T> AbstractObjectCondition<T, ObjectValidation<T>> failIf(T obj) {
        return new ObjectValidation<>(obj, null, PurposeMode.FAIL);
    }

    public static <T> AbstractObjectCondition<T, ObjectValidation<T>> succeedIf(T obj) {
        return new ObjectValidation<>(obj, MatchMode.LAZY, PurposeMode.SUCCESS);
    }

    public static <T> AbstractObjectCondition<T, ObjectValidation<T>> succeedIf(T obj, MatchMode matchMode) {
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

    public static AbstractBoolCondition failIf(Boolean bool) {
        return new BoolValidation(bool, null, PurposeMode.FAIL);
    }

    public static AbstractBoolCondition succeedIf(Boolean bool) {
        return new BoolValidation(bool, MatchMode.LAZY, PurposeMode.SUCCESS);
    }

    public static AbstractBoolCondition succeedIf(Boolean bool, MatchMode matchMode) {
        return new BoolValidation(bool, matchMode, PurposeMode.SUCCESS);
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

    public static <T> AbstractListCondition<T, ListValidation<T>> failIf(List<T> list) {
        return new ListValidation<>(list, null, PurposeMode.FAIL);
    }

    public static <T> AbstractListCondition<T, ListValidation<T>> succeedIf(List<T> list) {
        return new ListValidation<>(list, MatchMode.LAZY, PurposeMode.SUCCESS);
    }

    public static <T> AbstractListCondition<T, ListValidation<T>> succeedIf(List<T> list, MatchMode matchMode) {
        return new ListValidation<>(list, matchMode, PurposeMode.SUCCESS);
    }

    public static <K, V> AbstractMapCondition<K, V, MapValidation<K, V>> failIf(Map<K, V> map) {
        return new MapValidation<>(map, null, PurposeMode.FAIL);
    }

    public static <K, V> AbstractMapCondition<K, V, MapValidation<K, V>> succeedIf(Map<K, V> map) {
        return new MapValidation<>(map, MatchMode.LAZY, PurposeMode.SUCCESS);
    }

    public static <K, V> AbstractMapCondition<K, V, MapValidation<K, V>> succeedIf(Map<K, V> map, MatchMode matchMode) {
        return new MapValidation<>(map, matchMode, PurposeMode.SUCCESS);
    }

    public static <T> AbstractArrayCondition<T, ArrayValidation<T>> failIf(T[] array) {
        return new ArrayValidation<>(array, null, PurposeMode.FAIL);
    }

    public static <T> AbstractArrayCondition<T, ArrayValidation<T>> succeedIf(T[] array) {
        return new ArrayValidation<>(array, MatchMode.LAZY, PurposeMode.SUCCESS);
    }

    public static <T> AbstractArrayCondition<T, ArrayValidation<T>> succeedIf(T[] array, MatchMode matchMode) {
        return new ArrayValidation<>(array, matchMode, PurposeMode.SUCCESS);
    }
}
