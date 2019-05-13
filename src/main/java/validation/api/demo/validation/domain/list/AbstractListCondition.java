package validation.api.demo.validation.domain.list;

import validation.api.demo.validation.common.SingleCondition;
import validation.api.demo.validation.dict.TerminationMode;
import validation.api.demo.validation.domain.AbstractBaseValidation;
import validation.api.demo.validation.domain.list.impl.ListValidation;

import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

public abstract class AbstractListCondition<T> extends AbstractBaseValidation<List<T>> {

    public ListValidation<T> contains(T element, String onError) {
        registerCondition(ListConditions.contains(element), onError);

        return (ListValidation<T>) this;
    }

    public ListValidation<T> ofSize(int size, String onError) {
        registerCondition(ListConditions.ofSize(size), onError);

        return (ListValidation<T>) this;
    }

    public ListValidation<T> hasNoDuplicates(String onError) {
        registerCondition(ListConditions.hasNoDuplicates(), onError);

        return (ListValidation<T>) this;
    }

    public ListValidation<T> isEmpty(String onError) {
        registerCondition(ListConditions.isEmpty(), onError);

        return (ListValidation<T>) this;
    }

    public ListValidation<T> isNotEmpty(String onError) {
        registerCondition(ListConditions.isNotEmpty(), onError);

        return (ListValidation<T>) this;
    }

    public ListValidation<T> each(SingleCondition<T> condition, String onError) {
        this.obj.forEach(it -> this.inspect(it, condition.getPredicate(), onError));

        return (ListValidation<T>) this;
    }

    public ListValidation<T> each(TerminationMode terminationMode, Function<T, AbstractBaseValidation<T>> validator) {
       this.obj.forEach(it -> this.inspect(it, terminationMode, validator));

        return (ListValidation<T>) this;
    }

    @Override
    public ListValidation<T> isNull(String onError) {
        return (ListValidation<T>) super.isNull(onError);
    }

    @Override
    public ListValidation<T> isNotNull(String onError) {
        return (ListValidation<T>) super.isNotNull(onError);
    }

    @Override
    public ListValidation<T> isEqualTo(List<T> otherList, String onError) {
        return (ListValidation<T>) super.isEqualTo(otherList, onError);
    }

    @Override
    public ListValidation<T> isNotEqualTo(List<T> otherList, String onError) {
        return (ListValidation<T>) super.isNotEqualTo(otherList, onError);
    }

    @Override
    public ListValidation<T> withTerm(Predicate<List<T>> predicate, String onError) {
        return (ListValidation<T>) super.withTerm(predicate, onError);
    }

    @Override
    public ListValidation<T> withTerm(Supplier<Boolean> supplier, String onError) {
        return (ListValidation<T>) super.withTerm(supplier, onError);
    }

    @Override
    public ListValidation<T> isAnyOf(SingleCondition<List<T>> condition1, SingleCondition<List<T>> condition2, String onError) {
        return (ListValidation<T>) super.isAnyOf(condition1, condition2, onError);
    }

    @Override
    public ListValidation<T> isAllOf(SingleCondition<List<T>> condition1, SingleCondition<List<T>> condition2, String onError) {
        return (ListValidation<T>) super.isAllOf(condition1, condition2, onError);
    }

    @Override
    public ListValidation<T> log(String msg, Object... values) {
        return (ListValidation<T>) super.log(msg, values);
    }

    @Override
    public <R> ListValidation<T> inspecting(Function<List<T>, R> mapper, Predicate<R> predicate, String onError) {
        return (ListValidation<T>) super.inspecting(mapper, predicate, onError);
    }

    @Override
    public <R> ListValidation<T> inspecting(Function<List<T>, R> mapper, TerminationMode terminationMode, Function<R, AbstractBaseValidation<R>> validator) {
        return (ListValidation<T>) super.inspecting(mapper, terminationMode, validator);
    }

    @Override
    public <R> ListValidation<T> inspecting(Function<List<T>, R> mapper, Supplier<SingleCondition<R>> condition) {
        return (ListValidation<T>) super.inspecting(mapper, condition);
    }
}
