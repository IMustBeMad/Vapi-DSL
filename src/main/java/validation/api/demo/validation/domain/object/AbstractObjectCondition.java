package validation.api.demo.validation.domain.object;

import validation.api.demo.validation.common.SingleCondition;
import validation.api.demo.validation.dict.ErrorMode;
import validation.api.demo.validation.dict.TerminationMode;
import validation.api.demo.validation.domain.AbstractBaseValidation;
import validation.api.demo.validation.domain.object.impl.ObjectValidation;

import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

public abstract class AbstractObjectCondition<T> extends AbstractBaseValidation<T> {

    @Override
    public ObjectValidation<T> isNull() {
        return (ObjectValidation<T>) super.isNull();
    }

    @Override
    public ObjectValidation<T> isNotNull() {
        return (ObjectValidation<T>) super.isNotNull();
    }

    @Override
    public ObjectValidation<T> isEqualTo(T otherObj) {
        return (ObjectValidation<T>) super.isEqualTo(otherObj);
    }

    @Override
    public ObjectValidation<T> isNotEqualTo(T otherObj) {
        return (ObjectValidation<T>) super.isNotEqualTo(otherObj);
    }

    @Override
    public ObjectValidation<T> withTerm(Predicate<T> predicate) {
        return (ObjectValidation<T>) super.withTerm(predicate);
    }

    @Override
    public ObjectValidation<T> withTerm(Supplier<Boolean> supplier) {
        return (ObjectValidation<T>) super.withTerm(supplier);
    }

    @Override
    public ObjectValidation<T> withTerm(Function<T, AbstractBaseValidation<T>> validator) {
        return (ObjectValidation<T>) super.withTerm(validator);
    }

    @Override
    public ObjectValidation<T> isAnyOf(SingleCondition<T> condition1, SingleCondition<T> condition2) {
        return (ObjectValidation<T>) super.isAnyOf(condition1, condition2);
    }

    @Override
    public ObjectValidation<T> isAllOf(SingleCondition<T> condition1, SingleCondition<T> condition2) {
        return (ObjectValidation<T>) super.isAllOf(condition1, condition2);
    }

    @Override
    public ObjectValidation<T> log(String msg, Object... values) {
        return (ObjectValidation<T>) super.log(msg, values);
    }

    @Override
    public <R> ObjectValidation<T> inspecting(Function<T, R> mapper, Predicate<R> predicate) {
        return (ObjectValidation<T>) super.inspecting(mapper, predicate);
    }

    @Override
    public <R> ObjectValidation<T> inspecting(Function<T, R> mapper, Supplier<SingleCondition<R>> condition) {
        return (ObjectValidation<T>) super.inspecting(mapper, condition);
    }

    @Override
    public <R> ObjectValidation<T> deepInspecting(Function<T, R> mapper, Function<R, AbstractBaseValidation<R>> validator) {
        return (ObjectValidation<T>) super.deepInspecting(mapper, validator);
    }

    @Override
    public ObjectValidation<T> failOn(TerminationMode terminationMode) {
        return (ObjectValidation<T>) super.failOn(terminationMode);
    }

    @Override
    public ObjectValidation<T> failOn(TerminationMode terminationMode, ErrorMode errorMode) {
        return (ObjectValidation<T>) super.failOn(terminationMode, errorMode);
    }
}
