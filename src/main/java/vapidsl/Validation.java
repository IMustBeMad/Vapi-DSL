package vapidsl;

import vapidsl.dict.MatchMode;
import vapidsl.dict.PurposeMode;
import vapidsl.domain.array.ArrayConditionBinder;
import vapidsl.domain.array.impl.ArrayValidation;
import vapidsl.domain.bool.BoolConditionBinder;
import vapidsl.domain.bool.impl.BoolValidation;
import vapidsl.domain.date.localdate.LocalDateConditionBinder;
import vapidsl.domain.date.localdate.impl.LocalDateValidation;
import vapidsl.domain.list.ListConditionBinder;
import vapidsl.domain.list.impl.ListValidation;
import vapidsl.domain.map.MapConditionBinder;
import vapidsl.domain.map.impl.MapValidation;
import vapidsl.domain.number.bigdecimal.BigDecimalConditionBinder;
import vapidsl.domain.number.bigdecimal.impl.BigDecimalValidation;
import vapidsl.domain.number.biginteger.LongConditionBinder;
import vapidsl.domain.number.biginteger.impl.LongValidation;
import vapidsl.domain.number.integer.IntConditionBinder;
import vapidsl.domain.number.integer.impl.IntValidation;
import vapidsl.domain.object.ObjectConditionBinder;
import vapidsl.domain.object.impl.ObjectValidation;
import vapidsl.domain.string.StringConditionBinder;
import vapidsl.domain.string.impl.StringValidation;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public class Validation {

    public static <T> ObjectConditionBinder<T, ObjectValidation<T>> failIf(T obj) {
        return new ObjectValidation<>(obj, null, PurposeMode.FAIL);
    }

    public static <T> ObjectConditionBinder<T, ObjectValidation<T>> succeedIf(T obj) {
        return new ObjectValidation<>(obj, MatchMode.LAZY, PurposeMode.SUCCESS);
    }

    public static <T> ObjectConditionBinder<T, ObjectValidation<T>> succeedIf(T obj, MatchMode matchMode) {
        return new ObjectValidation<>(obj, matchMode, PurposeMode.SUCCESS);
    }

    public static StringConditionBinder failIf(String value) {
        return new StringValidation(value, null, PurposeMode.FAIL);
    }

    public static StringConditionBinder succeedIf(String value) {
        return new StringValidation(value, MatchMode.LAZY, PurposeMode.SUCCESS);
    }

    public static StringConditionBinder succeedIf(String value, MatchMode matchMode) {
        return new StringValidation(value, matchMode, PurposeMode.SUCCESS);
    }

    public static IntConditionBinder failIf(Integer integer) {
        return new IntValidation(integer, null, PurposeMode.FAIL);
    }

    public static IntConditionBinder succeedIf(Integer integer) {
        return new IntValidation(integer, MatchMode.LAZY, PurposeMode.SUCCESS);
    }

    public static IntConditionBinder succeedIf(Integer integer, MatchMode matchMode) {
        return new IntValidation(integer, matchMode, PurposeMode.SUCCESS);
    }

    public static LongConditionBinder failIf(Long aLong) {
        return new LongValidation(aLong, null, PurposeMode.FAIL);
    }

    public static LongConditionBinder succeedIf(Long aLong) {
        return new LongValidation(aLong, MatchMode.LAZY, PurposeMode.SUCCESS);
    }

    public static LongConditionBinder succeedIf(Long aLong, MatchMode matchMode) {
        return new LongValidation(aLong, matchMode, PurposeMode.SUCCESS);
    }

    public static BigDecimalConditionBinder failIf(BigDecimal bigDecimal) {
        return new BigDecimalValidation(bigDecimal, null, PurposeMode.FAIL);
    }

    public static BigDecimalConditionBinder succeedIf(BigDecimal bigDecimal) {
        return new BigDecimalValidation(bigDecimal, MatchMode.LAZY, PurposeMode.SUCCESS);
    }

    public static BigDecimalConditionBinder succeedIf(BigDecimal bigDecimal, MatchMode matchMode) {
        return new BigDecimalValidation(bigDecimal, matchMode, PurposeMode.SUCCESS);
    }

    public static BoolConditionBinder failIf(Boolean bool) {
        return new BoolValidation(bool, null, PurposeMode.FAIL);
    }

    public static BoolConditionBinder succeedIf(Boolean bool) {
        return new BoolValidation(bool, MatchMode.LAZY, PurposeMode.SUCCESS);
    }

    public static BoolConditionBinder succeedIf(Boolean bool, MatchMode matchMode) {
        return new BoolValidation(bool, matchMode, PurposeMode.SUCCESS);
    }

    public static LocalDateConditionBinder failIf(LocalDate date) {
        return new LocalDateValidation(date, null, PurposeMode.FAIL);
    }

    public static LocalDateConditionBinder succeedIf(LocalDate date) {
        return new LocalDateValidation(date, MatchMode.LAZY, PurposeMode.SUCCESS);
    }

    public static LocalDateConditionBinder succeedIf(LocalDate date, MatchMode matchMode) {
        return new LocalDateValidation(date, matchMode, PurposeMode.SUCCESS);
    }

    public static <T> ListConditionBinder<T, ListValidation<T>> failIf(List<T> list) {
        return new ListValidation<>(list, null, PurposeMode.FAIL);
    }

    public static <T> ListConditionBinder<T, ListValidation<T>> succeedIf(List<T> list) {
        return new ListValidation<>(list, MatchMode.LAZY, PurposeMode.SUCCESS);
    }

    public static <T> ListConditionBinder<T, ListValidation<T>> succeedIf(List<T> list, MatchMode matchMode) {
        return new ListValidation<>(list, matchMode, PurposeMode.SUCCESS);
    }

    public static <K, V> MapConditionBinder<K, V, MapValidation<K, V>> failIf(Map<K, V> map) {
        return new MapValidation<>(map, null, PurposeMode.FAIL);
    }

    public static <K, V> MapConditionBinder<K, V, MapValidation<K, V>> succeedIf(Map<K, V> map) {
        return new MapValidation<>(map, MatchMode.LAZY, PurposeMode.SUCCESS);
    }

    public static <K, V> MapConditionBinder<K, V, MapValidation<K, V>> succeedIf(Map<K, V> map, MatchMode matchMode) {
        return new MapValidation<>(map, matchMode, PurposeMode.SUCCESS);
    }

    public static <T> ArrayConditionBinder<T, ArrayValidation<T>> failIf(T[] array) {
        return new ArrayValidation<>(array, null, PurposeMode.FAIL);
    }

    public static <T> ArrayConditionBinder<T, ArrayValidation<T>> succeedIf(T[] array) {
        return new ArrayValidation<>(array, MatchMode.LAZY, PurposeMode.SUCCESS);
    }

    public static <T> ArrayConditionBinder<T, ArrayValidation<T>> succeedIf(T[] array, MatchMode matchMode) {
        return new ArrayValidation<>(array, matchMode, PurposeMode.SUCCESS);
    }
}
