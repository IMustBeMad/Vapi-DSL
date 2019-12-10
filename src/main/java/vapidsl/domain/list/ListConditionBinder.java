package vapidsl.domain.list;

import vapidsl.common.Condition;
import vapidsl.common.LinkedCondition;
import vapidsl.common.SingleCondition;
import vapidsl.dict.Clause;
import vapidsl.domain.ConditionBinder;

import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public abstract class ListConditionBinder<T, SELF extends ListConditionBinder<T, SELF>> extends ConditionBinder<List<T>, SELF> {

    ListConditionBinder(Class<?> selfType) {
        super(selfType);
    }

    public SELF contains(T element) {
        this.registerCondition(ListConditions.contains(element));

        return self;
    }

    public SELF hasSize(int size) {
        this.registerCondition(ListConditions.hasSize(size));

        return self;
    }

    public SELF hasNoDuplicates() {
        this.registerCondition(ListConditions.hasNoDuplicates());

        return self;
    }

    public SELF isEmpty() {
        this.registerCondition(ListConditions.isEmpty());

        return self;
    }

    public SELF isNotEmpty() {
        this.registerCondition(ListConditions.isNotEmpty());

        return self;
    }

    public SELF every(Supplier<SingleCondition<T>> condition) {
        LinkedCondition<List<T>> linkedCondition = new LinkedCondition<>(() -> this.toSerialCondition(condition.get().getPredicate()), Clause.AND);
        this.registerCondition(linkedCondition);

        return self;
    }

    public SELF every(Predicate<T> predicate) {
        LinkedCondition<List<T>> linkedCondition = new LinkedCondition<>(() -> this.toSerialCondition(predicate), Clause.AND);
        this.registerCondition(linkedCondition);

        return self;
    }

    public <OTHER extends ConditionBinder<T, OTHER>> SELF everyDeeply(Function<T, ConditionBinder<T, OTHER>> validator) {
        LinkedCondition<List<T>> linkedCondition = new LinkedCondition<>(() -> this.toSerialCondition(validator), Clause.AND);
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
    public SELF isEqualTo(List<T> otherList) {
        this.registerCondition(ListConditions.isEqualTo(otherList));

        return self;
    }

    @Override
    public SELF isNotEqualTo(List<T> otherList) {
        this.registerCondition(ListConditions.isNotEqualTo(otherList));

        return self;
    }

    @Override
    public SELF withTerm(Predicate<List<T>> predicate) {
        return super.withTerm(predicate);
    }

    @Override
    public SELF withTerm(Supplier<Boolean> supplier) {
        return super.withTerm(supplier);
    }

    @Override
    @SafeVarargs
    public final SELF satisfiesAny(SingleCondition<List<T>>... conditions) {
        return super.satisfiesAny(conditions);
    }

    @Override
    @SafeVarargs
    public final SELF satisfiesEvery(SingleCondition<List<T>>... conditions) {
        return super.satisfiesEvery(conditions);
    }

    @Override
    public SELF log(String msg, Object... values) {
        return super.log(msg, values);
    }

    @Override
    public <R> SELF inspecting(Function<List<T>, R> mapper, Predicate<R> predicate) {
        return super.inspecting(mapper, predicate);
    }

    @Override
    public <R> SELF inspecting(Function<List<T>, R> mapper, Supplier<SingleCondition<R>> condition) {
        return super.inspecting(mapper, condition);
    }

    @Override
    public <R, OTHER extends ConditionBinder<R, OTHER>> SELF inspectingDeeply(Function<List<T>, R> mapper, Function<R, ConditionBinder<R, OTHER>> validator) {
        return super.inspectingDeeply(mapper, validator);
    }

    private <OTHER extends ConditionBinder<T, OTHER>> List<Condition<List<T>>> toSerialCondition(Function<T, ConditionBinder<T, OTHER>> validator) {
        return this.obj.stream()
                       .map(it -> MAPPER.toCondition(() -> validator.apply(it)))
                       .collect(Collectors.toList());
    }

    private List<Condition<List<T>>> toSerialCondition(Predicate<T> predicate) {
        return this.obj.stream()
                       .map(it -> MAPPER.toCondition(it, predicate))
                       .collect(Collectors.toList());
    }
}
