package validation.api.demo.validation.domain.array;

import validation.api.demo.validation.common.SingleCondition;
import validation.api.demo.validation.dict.ErrorMode;
import validation.api.demo.validation.dict.TerminationMode;
import validation.api.demo.validation.domain.AbstractBaseValidation;
import validation.api.demo.validation.domain.array.impl.ArrayValidation;

import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Stream;

public abstract class AbstractArrayCondition<T> extends AbstractBaseValidation<T[]> {

    public ArrayValidation<T> contains(T element) {
        registerCondition(ArrayConditions.contains(element));

        return (ArrayValidation<T>) this;
    }

    public ArrayValidation<T> ofSize(int size) {
        registerCondition(ArrayConditions.ofSize(size));

        return (ArrayValidation<T>) this;
    }

    public ArrayValidation<T> isEmpty() {
        registerCondition(ArrayConditions.isEmpty());

        return (ArrayValidation<T>) this;
    }

    public ArrayValidation<T> isNotEmpty() {
        registerCondition(ArrayConditions.isNotEmpty());

        return (ArrayValidation<T>) this;
    }

    @Override
    public ArrayValidation<T> isNull() {
        return (ArrayValidation<T>) super.isNull();
    }

    @Override
    public ArrayValidation<T> isNotNull() {
        return (ArrayValidation<T>) super.isNotNull();
    }

    @Override
    public ArrayValidation<T> isEqualTo(T[] otherObj) {
        return (ArrayValidation<T>) super.isEqualTo(otherObj);
    }

    @Override
    public ArrayValidation<T> isNotEqualTo(T[] otherObj) {
        return (ArrayValidation<T>) super.isNotEqualTo(otherObj);
    }

    @Override
    public ArrayValidation<T> withTerm(Predicate<T[]> predicate) {
        return (ArrayValidation<T>) super.withTerm(predicate);
    }

    @Override
    public ArrayValidation<T> withTerm(Supplier<Boolean> supplier) {
        return (ArrayValidation<T>) super.withTerm(supplier);
    }

    public ArrayValidation<T> each(SingleCondition<T> condition) {
        SingleCondition<T[]> listCondition = new SingleCondition<>(array -> Stream.of(array).allMatch(el -> condition.getPredicate().test(el)));
        registerCondition(listCondition);

        return (ArrayValidation<T>) this;
    }

    public ArrayValidation<T> each(Function<T, AbstractBaseValidation<T>> validator) {
        Stream.of(this.obj).forEach(el -> this.inspect(el, validator));

        return (ArrayValidation<T>) this;
    }

    @Override
    public ArrayValidation<T> isAnyOf(SingleCondition<T[]> condition1, SingleCondition<T[]> condition2) {
        return (ArrayValidation<T>) super.isAnyOf(condition1, condition2);
    }

    @Override
    public ArrayValidation<T> isAllOf(SingleCondition<T[]> condition1, SingleCondition<T[]> condition2) {
        return (ArrayValidation<T>) super.isAllOf(condition1, condition2);
    }

    @Override
    public ArrayValidation<T> log(String msg, Object... values) {
        return (ArrayValidation<T>) super.log(msg, values);
    }

    @Override
    public <R> ArrayValidation<T> inspecting(Function<T[], R> mapper, Predicate<R> predicate) {
        return (ArrayValidation<T>) super.inspecting(mapper, predicate);
    }

    @Override
    public <R> ArrayValidation<T> innerValidation(Function<T[], R> mapper, Function<R, AbstractBaseValidation<R>> validator) {
        return (ArrayValidation<T>) super.innerValidation(mapper, validator);
    }

    @Override
    public <R> ArrayValidation<T> inspecting(Function<T[], R> mapper, Supplier<SingleCondition<R>> condition) {
        return (ArrayValidation<T>) super.inspecting(mapper, condition);
    }

    @Override
    public ArrayValidation<T> failOn(TerminationMode terminationMode) {
        return (ArrayValidation<T>) super.failOn(terminationMode);
    }

    @Override
    public ArrayValidation<T> failOn(TerminationMode terminationMode, ErrorMode errorMode) {
        return (ArrayValidation<T>) super.failOn(terminationMode, errorMode);
    }
}
