package vapidsl.domain.map;

import vapidsl.common.Condition;
import vapidsl.common.LinkedCondition;
import vapidsl.common.SingleCondition;
import vapidsl.dict.Clause;
import vapidsl.domain.ConditionBinder;

import java.util.List;
import java.util.Map;
import java.util.function.*;
import java.util.stream.Collectors;

public abstract class MapConditionBinder<K, V, SELF extends MapConditionBinder<K, V, SELF>> extends ConditionBinder<Map<K, V>, SELF> {

    MapConditionBinder(Class<?> selfType) {
        super(selfType);
    }

    public SELF isEmpty() {
        this.registerCondition(MapConditions.isEmpty());

        return self;
    }

    public SELF isNotEmpty() {
        this.registerCondition(MapConditions.isNotEmpty());

        return self;
    }

    public SELF hasSize(int size) {
        this.registerCondition(MapConditions.hasSize(size));

        return self;
    }

    public SELF containsKey(K keyValue) {
        this.registerCondition(MapConditions.containsKey(keyValue));

        return self;
    }

    public SELF every(BiPredicate<? super K, ? super V> predicate) {
        LinkedCondition<Map<K, V>> linkedCondition = new LinkedCondition<>(() -> toSerialCondition(predicate), Clause.AND);
        registerCondition(linkedCondition);

        return self;
    }

    public <R, OTHER extends ConditionBinder<R, OTHER>> SELF everyDeeply(BiFunction<? super K, ? super V, ConditionBinder<R, OTHER>> validator) {
        LinkedCondition<Map<K, V>> linkedCondition = new LinkedCondition<>(() -> toSerialCondition(validator), Clause.AND);
        registerCondition(linkedCondition);

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
    public SELF isEqualTo(Map<K, V> otherMap) {
        return super.isEqualTo(otherMap);
    }

    @Override
    public SELF isNotEqualTo(Map<K, V> otherMap) {
        return super.isNotEqualTo(otherMap);
    }

    @Override
    public SELF withTerm(Predicate<Map<K, V>> predicate) {
        return super.withTerm(predicate);
    }

    @Override
    public SELF withTerm(Supplier<Boolean> supplier) {
        return super.withTerm(supplier);
    }

    @Override
    @SafeVarargs
    public final SELF satisfiesAny(SingleCondition<Map<K, V>>... conditions) {
        return super.satisfiesAny(conditions);
    }

    @Override
    @SafeVarargs
    public final SELF satisfiesEvery(SingleCondition<Map<K, V>>... conditions) {
        return super.satisfiesEvery(conditions);
    }

    @Override
    public SELF log(String msg, Object... values) {
        return super.log(msg, values);
    }

    @Override
    public <R> SELF inspecting(Function<Map<K, V>, R> mapper, Predicate<R> predicate) {
        return super.inspecting(mapper, predicate);
    }

    @Override
    public <R> SELF inspecting(Function<Map<K, V>, R> mapper, Supplier<SingleCondition<R>> condition) {
        return super.inspecting(mapper, condition);
    }

    @Override
    public <R, OTHER extends ConditionBinder<R, OTHER>> SELF inspectingDeeply(Function<Map<K, V>, R> mapper, Function<R, ConditionBinder<R, OTHER>> validator) {
        return super.inspectingDeeply(mapper, validator);
    }

    private <R, OTHER extends ConditionBinder<R, OTHER>> List<Condition<Map<K, V>>> toSerialCondition(BiFunction<? super K, ? super V, ConditionBinder<R, OTHER>> validator) {
        return this.obj.entrySet()
                       .stream()
                       .map(it -> MAPPER.toCondition(() -> validator.apply(it.getKey(), it.getValue())))
                       .collect(Collectors.toList());
    }

    private List<Condition<Map<K, V>>> toSerialCondition(BiPredicate<? super K, ? super V> predicate) {
        return this.obj.entrySet()
                       .stream()
                       .map(it -> MAPPER.toCondition(it, predicate))
                       .collect(Collectors.toList());
    }
}
