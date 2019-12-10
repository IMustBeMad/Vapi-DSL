package vapidsl.domain.array;

import vapidsl.common.Condition;
import vapidsl.common.LinkedCondition;
import vapidsl.common.SingleCondition;
import vapidsl.dict.Clause;
import vapidsl.domain.ConditionBinder;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public abstract class ArrayConditionBinder<T, SELF extends ArrayConditionBinder<T, SELF>> extends ConditionBinder<T[], SELF> {

    ArrayConditionBinder(Class<?> selfType) {
        super(selfType);
    }

    @Override
    public SELF isEqualTo(T[] array) {
        this.registerCondition(ArrayConditions.isEqualTo(array));

        return self;
    }

    @Override
    public SELF isNotEqualTo(T[] array) {
        this.registerCondition(ArrayConditions.isNotEqualTo(array));

        return self;
    }

    public SELF contains(T element) {
        this.registerCondition(ArrayConditions.contains(element));

        return self;
    }

    public SELF hasSize(int size) {
        this.registerCondition(ArrayConditions.hasSize(size));

        return self;
    }

    public SELF isEmpty() {
        this.registerCondition(ArrayConditions.isEmpty());

        return self;
    }

    public SELF isNotEmpty() {
        this.registerCondition(ArrayConditions.isNotEmpty());

        return self;
    }

    public SELF every(Supplier<SingleCondition<T>> condition) {
        LinkedCondition<T[]> linkedCondition = new LinkedCondition<>(() -> this.toSerialCondition(condition.get().getPredicate()), Clause.AND);
        this.registerCondition(linkedCondition);

        return self;
    }

    public SELF every(Predicate<T> predicate) {
        LinkedCondition<T[]> linkedCondition = new LinkedCondition<>(() -> this.toSerialCondition(predicate), Clause.AND);
        this.registerCondition(linkedCondition);

        return self;
    }

    public <OTHER extends ConditionBinder<T, OTHER>> SELF everyDeeply(Function<T, ConditionBinder<T, OTHER>> validator) {
        LinkedCondition<T[]> linkedCondition = new LinkedCondition<>(() -> this.toSerialCondition(validator), Clause.AND);
        this.registerCondition(linkedCondition);

        return self;
    }

    @Override
    public SELF isNull() {
        return super.isNull();
    }

    @Override
    public SELF isNotNull() {
        return super.isNotNull();
    }

    @Override
    public SELF withTerm(Predicate<T[]> predicate) {
        return super.withTerm(predicate);
    }

    @Override
    public SELF withTerm(Supplier<Boolean> supplier) {
        return super.withTerm(supplier);
    }

    @Override
    public SELF withTermDeeply(Function<T[], ConditionBinder<T[], SELF>> validator) {
        return super.withTermDeeply(validator);
    }

    @Override
    @SafeVarargs
    public final SELF satisfiesAny(SingleCondition<T[]>... conditions) {
        return super.satisfiesAny(conditions);
    }

    @Override
    @SafeVarargs
    public final SELF satisfiesEvery(SingleCondition<T[]>... conditions) {
        return super.satisfiesEvery(conditions);
    }

    @Override
    public SELF log(String msg, Object... values) {
        return super.log(msg, values);
    }

    @Override
    public <R> SELF inspecting(Function<T[], R> mapper, Predicate<R> predicate) {
        return super.inspecting(mapper, predicate);
    }

    @Override
    public <R> SELF inspecting(Function<T[], R> mapper, Supplier<SingleCondition<R>> condition) {
        return super.inspecting(mapper, condition);
    }

    @Override
    public <R, OTHER extends ConditionBinder<R, OTHER>> SELF inspectingDeeply(Function<T[], R> mapper, Function<R, ConditionBinder<R, OTHER>> validator) {
        return super.inspectingDeeply(mapper, validator);
    }

    private <OTHER extends ConditionBinder<T, OTHER>> List<Condition<T[]>> toSerialCondition(Function<T, ConditionBinder<T, OTHER>> validator) {
        return Arrays.stream(this.obj)
                     .map(it -> MAPPER.toCondition(() -> validator.apply(it)))
                     .collect(Collectors.toList());
    }

    private List<Condition<T[]>> toSerialCondition(Predicate<T> predicate) {
        return Arrays.stream(this.obj)
                     .map(it -> MAPPER.toCondition(it, predicate))
                     .collect(Collectors.toList());
    }
}
