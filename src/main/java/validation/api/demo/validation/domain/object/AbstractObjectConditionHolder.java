package validation.api.demo.validation.domain.object;

import validation.api.demo.validation.common.SingleCondition;
import validation.api.demo.validation.domain.Terminator;
import validation.api.demo.validation.domain.object.impl.ObjectValidation;

import java.util.function.Function;
import java.util.function.Predicate;

public abstract class AbstractObjectConditionHolder<T> extends AbstractObjectValidation<T> implements Terminator {

    @Override
    public ObjectValidation<T> isNull(String onError) {
        return (ObjectValidation<T>) super.isNull(onError);
    }

    @Override
    public ObjectValidation<T> isNotNull(String onError) {
        return (ObjectValidation<T>) super.isNotNull(onError);
    }

    @Override
    public ObjectValidation<T> isEqualTo(T otherObj, String onError) {
        return (ObjectValidation<T>) super.isEqualTo(otherObj, onError);
    }

    @Override
    public ObjectValidation<T> isNotEqualTo(T otherObj, String onError) {
        return (ObjectValidation<T>) super.isNotEqualTo(otherObj, onError);
    }

    @Override
    public ObjectValidation<T> withTerm(Predicate<T> predicate, String onError) {
        return (ObjectValidation<T>) super.withTerm(predicate, onError);
    }

    @Override
    public ObjectValidation<T> isAnyOf(SingleCondition<T> condition1, SingleCondition<T> condition2, String onError) {
        return (ObjectValidation<T>) super.isAnyOf(condition1, condition2, onError);
    }

    @Override
    public ObjectValidation<T> isAllOf(SingleCondition<T> condition1, SingleCondition<T> condition2, String onError) {
        return (ObjectValidation<T>) super.isAllOf(condition1, condition2, onError);
    }

    @Override
    public ObjectValidation<T> log(String msg, Object... values) {
        return (ObjectValidation<T>) super.log(msg, values);
    }

    @Override
    public <R> ObjectValidation<T> inspecting(Function<T, R> mapper, Predicate<R> predicate, String onError) {
        return (ObjectValidation<T>) super.inspecting(mapper, predicate, onError);
    }

    @Override
    public <R> ObjectValidation<T> inspecting(Function<T, R> mapper, Function<R, AbstractObjectValidation<R>> validator) {
        return (ObjectValidation<T>) super.inspecting(mapper, validator);
    }
}
