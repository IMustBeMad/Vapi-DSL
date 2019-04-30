package validation.api.demo.validation.domain.array;

import validation.api.demo.validation.common.SingleCondition;
import validation.api.demo.validation.domain.AbstractBaseValidation;

import java.util.function.Function;
import java.util.function.Predicate;

public abstract class AbstractArrayCondition<T> extends AbstractBaseValidation<T[]> {

    public AbstractArrayCondition<T> contains(T element, String onError) {
        registerCondition(ArrayConditions.contains(element), onError);

        return this;
    }

    public AbstractArrayCondition<T> ofSize(int size, String onError) {
        registerCondition(ArrayConditions.ofSize(size), onError);

        return this;
    }

    public AbstractArrayCondition<T> isEmpty(String onError) {
        registerCondition(ArrayConditions.isEmpty(), onError);

        return this;
    }

    public AbstractArrayCondition<T> isNotEmpty(String onError) {
        registerCondition(ArrayConditions.isNotEmpty(), onError);

        return this;
    }

    @Override
    public AbstractArrayCondition<T> isNull(String onError) {
        return (AbstractArrayCondition<T>) super.isNull(onError);
    }

    @Override
    public AbstractArrayCondition<T> isNotNull(String onError) {
        return (AbstractArrayCondition<T>) super.isNotNull(onError);
    }

    @Override
    public AbstractArrayCondition<T> isEqualTo(T[] otherObj, String onError) {
        return (AbstractArrayCondition<T>) super.isEqualTo(otherObj, onError);
    }

    @Override
    public AbstractArrayCondition<T> isNotEqualTo(T[] otherObj, String onError) {
        return (AbstractArrayCondition<T>) super.isNotEqualTo(otherObj, onError);
    }

    @Override
    public AbstractArrayCondition<T> withTerm(Predicate<T[]> predicate, String onError) {
        return (AbstractArrayCondition<T>) super.withTerm(predicate, onError);
    }

    @Override
    public AbstractArrayCondition<T> isAnyOf(SingleCondition<T[]> condition1, SingleCondition<T[]> condition2, String onError) {
        return ((AbstractArrayCondition<T>) super.isAnyOf(condition1, condition2, onError));
    }

    @Override
    public AbstractArrayCondition<T> isAllOf(SingleCondition<T[]> condition1, SingleCondition<T[]> condition2, String onError) {
        return ((AbstractArrayCondition<T>) super.isAllOf(condition1, condition2, onError));
    }

    @Override
    public AbstractArrayCondition<T> log(String msg, Object... values) {
        return (AbstractArrayCondition<T>) super.log(msg, values);
    }

    @Override
    public <R> AbstractArrayCondition<T> inspecting(Function<T[], R> mapper, Predicate<R> predicate, String onError) {
        return (AbstractArrayCondition<T>) super.inspecting(mapper, predicate, onError);
    }

    @Override
    public <R> AbstractArrayCondition<T> inspecting(Function<T[], R> mapper, Function<R, AbstractBaseValidation<R>> validator) {
        return (AbstractArrayCondition<T>) super.inspecting(mapper, validator);
    }
}
