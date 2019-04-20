package validation.api.demo.validation.domain.list;

import validation.api.demo.validation.common.Condition;
import validation.api.demo.validation.domain.object.AbstractObjectValidation;

import java.util.HashSet;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

public abstract class AbstractListValidation<T> extends AbstractObjectValidation<List<T>> {

    public AbstractListValidation<T> contains(T element, String onError) {
        memoize(new Condition<>(it -> it.contains(element)));

        return this;
    }

    public AbstractListValidation<T> ofSize(int size, String onError) {
        memoize(new Condition<>(it -> it.size() == size, onError));

        return this;
    }

    public AbstractListValidation<T> hasNoDuplicates(String onError) {
        memoize(new Condition<>(it -> new HashSet<>(it).size() == it.size(), onError));

        return this;
    }

    public AbstractListValidation<T> isEmpty(String onError) {
        memoize(new Condition<>(List::isEmpty, onError));

        return this;
    }

    public AbstractListValidation<T> isNotEmpty(String onError) {
        memoize(new Condition<>(it -> !it.isEmpty(), onError));

        return this;
    }

    @Override
    public AbstractListValidation<T> isNull(String onError) {
        return (AbstractListValidation<T>) super.isNull(onError);
    }

    @Override
    public AbstractListValidation<T> isNotNull(String onError) {
        return (AbstractListValidation<T>) super.isNotNull(onError);
    }

    @Override
    public AbstractListValidation<T> isEqualTo(List<T> otherList, String onError) {
        return (AbstractListValidation<T>) super.isEqualTo(otherList, onError);
    }

    @Override
    public AbstractListValidation<T> isNotEqualTo(List<T> otherList, String onError) {
        return (AbstractListValidation<T>) super.isNotEqualTo(otherList, onError);
    }

    @Override
    public AbstractListValidation<T> withTerm(Predicate<List<T>> predicate, String onError) {
        return (AbstractListValidation<T>) super.withTerm(predicate, onError);
    }

    @Override
    public AbstractListValidation<T> log(String msg, Object... values) {
        return (AbstractListValidation<T>) super.log(msg, values);
    }

    @Override
    public <R> AbstractListValidation<T> inspecting(Function<List<T>, R> mapper, Predicate<R> predicate, String onError) {
        return (AbstractListValidation<T>) super.inspecting(mapper, predicate, onError);
    }

    @Override
    public <R> AbstractListValidation<T> inspecting(Function<List<T>, R> mapper, Function<R, AbstractObjectValidation<R>> validator) {
        return (AbstractListValidation<T>) super.inspecting(mapper, validator);
    }
}
