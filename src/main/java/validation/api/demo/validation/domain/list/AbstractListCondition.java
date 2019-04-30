package validation.api.demo.validation.domain.list;

import validation.api.demo.validation.common.SingleCondition;
import validation.api.demo.validation.domain.AbstractBaseValidation;

import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

public abstract class AbstractListCondition<T> extends AbstractBaseValidation<List<T>> {

    public AbstractListCondition<T> contains(T element, String onError) {
        registerCondition(ListConditions.contains(element), onError);

        return this;
    }

    public AbstractListCondition<T> ofSize(int size, String onError) {
        registerCondition(ListConditions.ofSize(size), onError);

        return this;
    }

    public AbstractListCondition<T> hasNoDuplicates(String onError) {
        registerCondition(ListConditions.hasNoDuplicates(), onError);

        return this;
    }

    public AbstractListCondition<T> isEmpty(String onError) {
        registerCondition(ListConditions.isEmpty(), onError);

        return this;
    }

    public AbstractListCondition<T> isNotEmpty(String onError) {
        registerCondition(ListConditions.isNotEmpty(), onError);

        return this;
    }

    @Override
    public AbstractListCondition<T> isNull(String onError) {
        return (AbstractListCondition<T>) super.isNull(onError);
    }

    @Override
    public AbstractListCondition<T> isNotNull(String onError) {
        return (AbstractListCondition<T>) super.isNotNull(onError);
    }

    @Override
    public AbstractListCondition<T> isEqualTo(List<T> otherList, String onError) {
        return (AbstractListCondition<T>) super.isEqualTo(otherList, onError);
    }

    @Override
    public AbstractListCondition<T> isNotEqualTo(List<T> otherList, String onError) {
        return (AbstractListCondition<T>) super.isNotEqualTo(otherList, onError);
    }

    @Override
    public AbstractListCondition<T> withTerm(Predicate<List<T>> predicate, String onError) {
        return (AbstractListCondition<T>) super.withTerm(predicate, onError);
    }


    @Override
    public AbstractListCondition<T> isAnyOf(SingleCondition<List<T>> condition1, SingleCondition<List<T>> condition2, String onError) {
        return ((AbstractListCondition<T>) super.isAnyOf(condition1, condition2, onError));
    }

    @Override
    public AbstractListCondition<T> isAllOf(SingleCondition<List<T>> condition1, SingleCondition<List<T>> condition2, String onError) {
        return ((AbstractListCondition<T>) super.isAllOf(condition1, condition2, onError));
    }

    @Override
    public AbstractListCondition<T> log(String msg, Object... values) {
        return (AbstractListCondition<T>) super.log(msg, values);
    }

    @Override
    public <R> AbstractListCondition<T> inspecting(Function<List<T>, R> mapper, Predicate<R> predicate, String onError) {
        return (AbstractListCondition<T>) super.inspecting(mapper, predicate, onError);
    }

    @Override
    public <R> AbstractListCondition<T> inspecting(Function<List<T>, R> mapper, Function<R, AbstractBaseValidation<R>> validator) {
        return (AbstractListCondition<T>) super.inspecting(mapper, validator);
    }
}
