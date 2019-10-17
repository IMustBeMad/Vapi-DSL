package vapidsl.domain.array;

import vapidsl.common.Condition;
import vapidsl.common.LinkedCondition;
import vapidsl.common.SingleCondition;
import vapidsl.dict.Clause;
import vapidsl.domain.AbstractBaseValidation;
import vapidsl.domain.array.impl.ArrayValidation;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public abstract class AbstractArrayCondition<T> extends AbstractBaseValidation<T[]> {

    public ArrayValidation<T> contains(T element) {
        this.registerCondition(ArrayConditions.contains(element));

        return self();
    }

    public ArrayValidation<T> ofSize(int size) {
        this.registerCondition(ArrayConditions.ofSize(size));

        return self();
    }

    public ArrayValidation<T> isEmpty() {
        this.registerCondition(ArrayConditions.isEmpty());

        return self();
    }

    public ArrayValidation<T> isNotEmpty() {
        this.registerCondition(ArrayConditions.isNotEmpty());

        return self();
    }

    public ArrayValidation<T> each(SingleCondition<T> condition) {
        Optional.of(condition)
                .map(SingleCondition::getPredicate)
                .map(this::toSerialCondition)
                .map(serial -> new LinkedCondition<>(serial, Clause.AND))
                .ifPresent(this::registerCondition);

        return self();
    }

    public ArrayValidation<T> each(Function<T, AbstractBaseValidation<T>> validator) {
        Optional.of(validator)
                .map(this::toSerialCondition)
                .map(serial -> new LinkedCondition<>(serial, Clause.AND))
                .ifPresent(this::registerCondition);

        return self();
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

    @Override
    @SafeVarargs
    public final ArrayValidation<T> satisfiesAny(SingleCondition<T[]>... conditions) {
        return (ArrayValidation<T>) super.satisfiesAny(conditions);
    }

    @Override
    @SafeVarargs
    public final ArrayValidation<T> satisfiesAll(SingleCondition<T[]>... conditions) {
        return (ArrayValidation<T>) super.satisfiesAll(conditions);
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
    public <R> ArrayValidation<T> inspecting(Function<T[], R> mapper, Supplier<SingleCondition<R>> condition) {
        return (ArrayValidation<T>) super.inspecting(mapper, condition);
    }

    @Override
    public <R> ArrayValidation<T> deepInspecting(Function<T[], R> mapper, Function<R, AbstractBaseValidation<R>> validator) {
        return (ArrayValidation<T>) super.deepInspecting(mapper, validator);
    }

    private List<Condition<T[]>> toSerialCondition(Function<T, AbstractBaseValidation<T>> validator) {
        return Arrays.stream(this.obj)
                     .map(it -> this.toCondition(it, validator))
                     .collect(Collectors.toList());
    }

    private List<Condition<T[]>> toSerialCondition(Predicate<T> predicate) {
        return Arrays.stream(this.obj)
                     .map(it -> this.toCondition(it, predicate))
                     .collect(Collectors.toList());
    }

    private ArrayValidation<T> self() {
        return (ArrayValidation<T>) this;
    }
}
