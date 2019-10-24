package vapidsl.domain.map;

import vapidsl.common.Condition;
import vapidsl.common.LinkedCondition;
import vapidsl.common.SingleCondition;
import vapidsl.dict.Clause;
import vapidsl.domain.AbstractBaseValidation;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public abstract class AbstractMapCondition<K, V, SELF extends AbstractMapCondition<K, V, SELF>> extends AbstractBaseValidation<Map<K, V>, SELF> {

    AbstractMapCondition(Class<?> selfType) {
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

    public <R, OTHER extends AbstractBaseValidation<R, OTHER>> SELF every(BiFunction<? super K, ? super V, AbstractBaseValidation<R, OTHER>> validator) {
        LinkedCondition<Map<K, V>> linkedCondition = new LinkedCondition<>(toSerialCondition(validator), Clause.AND);
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
    public final SELF satisfiesAll(SingleCondition<Map<K, V>>... conditions) {
        return super.satisfiesAll(conditions);
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
    public <R, OTHER extends AbstractBaseValidation<R, OTHER>> SELF deepInspecting(Function<Map<K, V>, R> mapper, Function<R, AbstractBaseValidation<R, OTHER>> validator) {
        return super.deepInspecting(mapper, validator);
    }

    private <R, OTHER extends AbstractBaseValidation<R, OTHER>> List<Condition<Map<K, V>>> toSerialCondition(BiFunction<? super K, ? super V, AbstractBaseValidation<R, OTHER>> validator) {
        if (this.obj == null) {
            return Collections.singletonList(this.toCondition(null, validator));
        }

        return this.obj.entrySet()
                       .stream()
                       .map(it -> this.toCondition(it, validator))
                       .collect(Collectors.toList());
    }
}
