package validation.api.demo.validation.domain.list;

import validation.api.demo.validation.common.SingleCondition;
import validation.api.demo.validation.dict.ErrorMode;
import validation.api.demo.validation.dict.TerminationMode;
import validation.api.demo.validation.domain.AbstractBaseValidation;
import validation.api.demo.validation.domain.list.impl.ListValidation;

import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

public abstract class AbstractListCondition<T> extends AbstractBaseValidation<List<T>> {

    public ListValidation<T> contains(T element) {
        registerCondition(ListConditions.contains(element));

        return (ListValidation<T>) this;
    }

    public ListValidation<T> ofSize(int size) {
        registerCondition(ListConditions.ofSize(size));

        return (ListValidation<T>) this;
    }

    public ListValidation<T> hasNoDuplicates() {
        registerCondition(ListConditions.hasNoDuplicates());

        return (ListValidation<T>) this;
    }

    public ListValidation<T> isEmpty() {
        registerCondition(ListConditions.isEmpty());

        return (ListValidation<T>) this;
    }

    public ListValidation<T> isNotEmpty() {
        registerCondition(ListConditions.isNotEmpty());

        return (ListValidation<T>) this;
    }

    public ListValidation<T> each(SingleCondition<T> condition) {
        this.obj.forEach(it -> this.inspect(it, condition.getPredicate()));

        return (ListValidation<T>) this;
    }

    public ListValidation<T> each(Function<T, AbstractBaseValidation<T>> validator) {
       this.obj.forEach(it -> this.inspect(it, validator));

        return (ListValidation<T>) this;
    }

    @Override
    public ListValidation<T> isNull() {
        return (ListValidation<T>) super.isNull();
    }

    @Override
    public ListValidation<T> isNotNull() {
        return (ListValidation<T>) super.isNotNull();
    }

    @Override
    public ListValidation<T> isEqualTo(List<T> otherList) {
        return (ListValidation<T>) super.isEqualTo(otherList);
    }

    @Override
    public ListValidation<T> isNotEqualTo(List<T> otherList) {
        return (ListValidation<T>) super.isNotEqualTo(otherList);
    }

    @Override
    public ListValidation<T> withTerm(Predicate<List<T>> predicate) {
        return (ListValidation<T>) super.withTerm(predicate);
    }

    @Override
    public ListValidation<T> withTerm(Supplier<Boolean> supplier) {
        return (ListValidation<T>) super.withTerm(supplier);
    }

    @Override
    public ListValidation<T> isAnyOf(SingleCondition<List<T>> condition1, SingleCondition<List<T>> condition2) {
        return (ListValidation<T>) super.isAnyOf(condition1, condition2);
    }

    @Override
    public ListValidation<T> isAllOf(SingleCondition<List<T>> condition1, SingleCondition<List<T>> condition2) {
        return (ListValidation<T>) super.isAllOf(condition1, condition2);
    }

    @Override
    public ListValidation<T> log(String msg, Object... values) {
        return (ListValidation<T>) super.log(msg, values);
    }

    @Override
    public <R> ListValidation<T> inspecting(Function<List<T>, R> mapper, Predicate<R> predicate) {
        return (ListValidation<T>) super.inspecting(mapper, predicate);
    }

    @Override
    public <R> ListValidation<T> innerValidation(Function<List<T>, R> mapper, Function<R, AbstractBaseValidation<R>> validator) {
        return (ListValidation<T>) super.innerValidation(mapper, validator);
    }

    @Override
    public <R> ListValidation<T> inspecting(Function<List<T>, R> mapper, Supplier<SingleCondition<R>> condition) {
        return (ListValidation<T>) super.inspecting(mapper, condition);
    }

    @Override
    public ListValidation<T> failOn(TerminationMode terminationMode) {
        return (ListValidation<T>) super.failOn(terminationMode);
    }

    @Override
    public ListValidation<T> failOn(TerminationMode terminationMode, ErrorMode errorMode) {
        return (ListValidation<T>) super.failOn(terminationMode, errorMode);
    }
}
