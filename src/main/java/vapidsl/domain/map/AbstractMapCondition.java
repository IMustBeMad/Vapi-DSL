package vapidsl.domain.map;

import vapidsl.common.Condition;
import vapidsl.common.LinkedCondition;
import vapidsl.common.SingleCondition;
import vapidsl.dict.Clause;
import vapidsl.domain.AbstractBaseValidation;
import vapidsl.domain.map.impl.MapValidation;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public abstract class AbstractMapCondition<K, V> extends AbstractBaseValidation<Map<K, V>> {

    public <R> MapValidation<K, V> each(Function<Map.Entry<K, V>, AbstractBaseValidation<R>> validator) {
        Optional.of(validator)
                .map(this::toSerialCondition)
                .map(serial -> new LinkedCondition<>(serial, Clause.AND))
                .ifPresent(this::registerCondition);

        return self();
    }

    @Override
    public MapValidation<K, V> isNull() {
        return (MapValidation<K, V>) super.isNull();
    }

    @Override
    public MapValidation<K, V> isNotNull() {
        return (MapValidation<K, V>) super.isNotNull();
    }

    @Override
    public MapValidation<K, V> isEqualTo(Map<K, V> otherMap) {
        return (MapValidation<K, V>) super.isEqualTo(otherMap);
    }

    @Override
    public MapValidation<K, V> isNotEqualTo(Map<K, V> otherMap) {
        return (MapValidation<K, V>) super.isNotEqualTo(otherMap);
    }

    @Override
    public MapValidation<K, V> withTerm(Predicate<Map<K, V>> predicate) {
        return (MapValidation<K, V>) super.withTerm(predicate);
    }

    @Override
    public MapValidation<K, V> withTerm(Supplier<Boolean> supplier) {
        return (MapValidation<K, V>) super.withTerm(supplier);
    }

    @Override
    @SafeVarargs
    public final MapValidation<K, V> isAnyOf(SingleCondition<Map<K, V>>... conditions) {
        return (MapValidation<K, V>) super.isAnyOf(conditions);
    }

    @Override
    @SafeVarargs
    public final MapValidation<K, V> isAllOf(SingleCondition<Map<K, V>>... conditions) {
        return (MapValidation<K, V>) super.isAllOf(conditions);
    }

    @Override
    public MapValidation<K, V> log(String msg, Object... values) {
        return (MapValidation<K, V>) super.log(msg, values);
    }

    @Override
    public <R> MapValidation<K, V> inspecting(Function<Map<K, V>, R> mapper, Predicate<R> predicate) {
        return (MapValidation<K, V>) super.inspecting(mapper, predicate);
    }

    @Override
    public <R> MapValidation<K, V> inspecting(Function<Map<K, V>, R> mapper, Supplier<SingleCondition<R>> condition) {
        return (MapValidation<K, V>) super.inspecting(mapper, condition);
    }

    @Override
    public <R> MapValidation<K, V> deepInspecting(Function<Map<K, V>, R> mapper, Function<R, AbstractBaseValidation<R>> validator) {
        return (MapValidation<K, V>) super.deepInspecting(mapper, validator);
    }

    private MapValidation<K, V> self() {
        return (MapValidation<K, V>) this;
    }

    private <R> List<Condition<Map<K, V>>> toSerialCondition(Function<Map.Entry<K, V>, AbstractBaseValidation<R>> validator) {
        return this.obj.entrySet()
                       .stream()
                       .map(it -> this.toCondition(it, validator))
                       .collect(Collectors.toList());
    }
}
