package validation.api.demo.validation.domain.list;

import validation.api.demo.validation.common.Condition;
import validation.api.demo.validation.common.LinkedCondition;
import validation.api.demo.validation.common.SingleCondition;
import validation.api.demo.validation.dict.Clause;
import validation.api.demo.validation.dict.ErrorMode;
import validation.api.demo.validation.dict.TerminationMode;
import validation.api.demo.validation.domain.AbstractBaseValidation;
import validation.api.demo.validation.domain.list.impl.ListValidation;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;

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
        Optional.of(condition)
                .map(SingleCondition::getPredicate)
                .map(this::toSerialCondition)
                .map(serial -> new LinkedCondition<>(serial, Clause.AND))
                .ifPresent(this::registerCondition);

        return (ListValidation<T>) this;
    }

    public ListValidation<T> each(Function<T, AbstractBaseValidation<T>> validator) {
        Optional.of(validator)
                .map(this::toSerialCondition)
                .map(serial -> new LinkedCondition<>(serial, Clause.AND))
                .ifPresent(this::registerCondition);

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
    @SafeVarargs
    public final ListValidation<T> isAnyOf(SingleCondition<List<T>>... conditions) {
        return (ListValidation<T>) super.isAnyOf(conditions);
    }

    @Override
    @SafeVarargs
    public final ListValidation<T> isAllOf(SingleCondition<List<T>>... conditions) {
        return (ListValidation<T>) super.isAllOf(conditions);
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
    public <R> ListValidation<T> inspecting(Function<List<T>, R> mapper, Supplier<SingleCondition<R>> condition) {
        return (ListValidation<T>) super.inspecting(mapper, condition);
    }

    @Override
    public <R> ListValidation<T> deepInspecting(Function<List<T>, R> mapper, Function<R, AbstractBaseValidation<R>> validator) {
        return (ListValidation<T>) super.deepInspecting(mapper, validator);
    }

    @Override
    public ListValidation<T> failOn(TerminationMode terminationMode) {
        return (ListValidation<T>) super.failOn(terminationMode);
    }

    @Override
    public ListValidation<T> failOn(TerminationMode terminationMode, ErrorMode errorMode) {
        return (ListValidation<T>) super.failOn(terminationMode, errorMode);
    }

    private List<Condition<List<T>>> toSerialCondition(Function<T, AbstractBaseValidation<T>> validator) {
        return this.obj.stream()
                       .map(it -> this.toCondition(it, validator))
                       .collect(Collectors.toList());
    }

    private List<Condition<List<T>>> toSerialCondition(Predicate<T> predicate) {
        return this.obj.stream()
                       .map(it -> this.toCondition(it, predicate))
                       .collect(Collectors.toList());
    }
}
