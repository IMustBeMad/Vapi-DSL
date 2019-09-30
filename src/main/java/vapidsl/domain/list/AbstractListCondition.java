package vapidsl.domain.list;

import vapidsl.common.Condition;
import vapidsl.common.LinkedCondition;
import vapidsl.common.SingleCondition;
import vapidsl.dict.Clause;
import vapidsl.domain.AbstractBaseValidation;
import vapidsl.domain.list.impl.ListValidation;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public abstract class AbstractListCondition<T> extends AbstractBaseValidation<List<T>> {

    public ListValidation<T> contains(T element) {
        this.registerCondition(ListConditions.contains(element));

        return self();
    }

    public ListValidation<T> ofSize(int size) {
        this.registerCondition(ListConditions.ofSize(size));

        return self();
    }

    public ListValidation<T> hasNoDuplicates() {
        this.registerCondition(ListConditions.hasNoDuplicates());

        return self();
    }

    public ListValidation<T> isEmpty() {
        this.registerCondition(ListConditions.isEmpty());

        return self();
    }

    public ListValidation<T> isNotEmpty() {
        this.registerCondition(ListConditions.isNotEmpty());

        return self();
    }

    public ListValidation<T> each(SingleCondition<T> condition) {
        Optional.of(condition)
                .map(SingleCondition::getPredicate)
                .map(this::toSerialCondition)
                .map(serial -> new LinkedCondition<>(serial, Clause.AND))
                .ifPresent(this::registerCondition);

        return self();
    }

    public ListValidation<T> each(Function<T, AbstractBaseValidation<T>> validator) {
        Optional.of(validator)
                .map(this::toSerialCondition)
                .map(serial -> new LinkedCondition<>(serial, Clause.AND))
                .ifPresent(this::registerCondition);

        return self();
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

    private ListValidation<T> self() {
        return (ListValidation<T>) this;
    }
}
