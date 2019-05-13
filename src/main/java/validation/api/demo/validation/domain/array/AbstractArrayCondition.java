package validation.api.demo.validation.domain.array;

import validation.api.demo.validation.common.SingleCondition;
import validation.api.demo.validation.dict.TerminationMode;
import validation.api.demo.validation.domain.AbstractBaseValidation;
import validation.api.demo.validation.domain.array.impl.ArrayValidation;

import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Stream;

public abstract class AbstractArrayCondition<T> extends AbstractBaseValidation<T[]> {

    public ArrayValidation<T> contains(T element, String onError) {
        registerCondition(ArrayConditions.contains(element), onError);

        return (ArrayValidation<T>) this;
    }

    public ArrayValidation<T> ofSize(int size, String onError) {
        registerCondition(ArrayConditions.ofSize(size), onError);

        return (ArrayValidation<T>) this;
    }

    public ArrayValidation<T> isEmpty(String onError) {
        registerCondition(ArrayConditions.isEmpty(), onError);

        return (ArrayValidation<T>) this;
    }

    public ArrayValidation<T> isNotEmpty(String onError) {
        registerCondition(ArrayConditions.isNotEmpty(), onError);

        return (ArrayValidation<T>) this;
    }

    @Override
    public ArrayValidation<T> isNull(String onError) {
        return (ArrayValidation<T>) super.isNull(onError);
    }

    @Override
    public ArrayValidation<T> isNotNull(String onError) {
        return (ArrayValidation<T>) super.isNotNull(onError);
    }

    @Override
    public ArrayValidation<T> isEqualTo(T[] otherObj, String onError) {
        return (ArrayValidation<T>) super.isEqualTo(otherObj, onError);
    }

    @Override
    public ArrayValidation<T> isNotEqualTo(T[] otherObj, String onError) {
        return (ArrayValidation<T>) super.isNotEqualTo(otherObj, onError);
    }

    @Override
    public ArrayValidation<T> withTerm(Predicate<T[]> predicate, String onError) {
        return (ArrayValidation<T>) super.withTerm(predicate, onError);
    }

    @Override
    public ArrayValidation<T> withTerm(Supplier<Boolean> supplier, String onError) {
        return (ArrayValidation<T>) super.withTerm(supplier, onError);
    }

    public ArrayValidation<T> each(SingleCondition<T> condition, String onError) {
        SingleCondition<T[]> listCondition = new SingleCondition<>(array -> Stream.of(array).allMatch(el -> condition.getPredicate().test(el)));
        registerCondition(listCondition, onError);

        return (ArrayValidation<T>) this;
    }

    public ArrayValidation<T> each(TerminationMode terminationMode, Function<T, AbstractBaseValidation<T>> validator) {
        Stream.of(this.obj).forEach(el -> this.inspect(el, terminationMode, validator));

        return (ArrayValidation<T>) this;
    }

    @Override
    public ArrayValidation<T> isAnyOf(SingleCondition<T[]> condition1, SingleCondition<T[]> condition2, String onError) {
        return (ArrayValidation<T>) super.isAnyOf(condition1, condition2, onError);
    }

    @Override
    public ArrayValidation<T> isAllOf(SingleCondition<T[]> condition1, SingleCondition<T[]> condition2, String onError) {
        return (ArrayValidation<T>) super.isAllOf(condition1, condition2, onError);
    }

    @Override
    public ArrayValidation<T> log(String msg, Object... values) {
        return (ArrayValidation<T>) super.log(msg, values);
    }

    @Override
    public <R> ArrayValidation<T> inspecting(Function<T[], R> mapper, Predicate<R> predicate, String onError) {
        return (ArrayValidation<T>) super.inspecting(mapper, predicate, onError);
    }

    @Override
    public <R> ArrayValidation<T> inspecting(Function<T[], R> mapper, TerminationMode terminationMode, Function<R, AbstractBaseValidation<R>> validator) {
        return (ArrayValidation<T>) super.inspecting(mapper, terminationMode, validator);
    }
}
