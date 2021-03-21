package vapidsl.domain.object;

import vapidsl.common.SingleCondition;
import vapidsl.domain.ConditionBinder;

import java.util.function.BooleanSupplier;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

public abstract class ObjectConditionBinder<T, SELF extends ObjectConditionBinder<T, SELF>> extends ConditionBinder<T, SELF> {

    ObjectConditionBinder(Class<?> selfType) {
        super(selfType);
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
    public SELF isEqualTo(T otherObj) {
        return super.isEqualTo(otherObj);
    }

    @Override
    public SELF isNotEqualTo(T otherObj) {
        return super.isNotEqualTo(otherObj);
    }

    @Override
    public SELF withTerm(Predicate<T> predicate) {
        return super.withTerm(predicate);
    }

    @Override
    public SELF withTerm(BooleanSupplier supplier) {
        return super.withTerm(supplier);
    }

    @Override
    public SELF withTermDeeply(Function<T, ConditionBinder<T, SELF>> validator) {
        return super.withTermDeeply(validator);
    }

    @Override
    @SafeVarargs
    public final SELF satisfiesAny(SingleCondition<T>... conditions) {
        return super.satisfiesAny(conditions);
    }

    @Override
    @SafeVarargs
    public final SELF satisfiesEvery(SingleCondition<T>... conditions) {
        return super.satisfiesEvery(conditions);
    }

    @Override
    public SELF log(String msg, Object... values) {
        return super.log(msg, values);
    }

    @Override
    public <R> SELF inspecting(Function<T, R> mapper, Predicate<R> predicate) {
        return super.inspecting(mapper, predicate);
    }

    @Override
    public <R> SELF inspecting(Function<T, R> mapper, Supplier<SingleCondition<R>> condition) {
        return super.inspecting(mapper, condition);
    }

    @Override
    public <R, OTHER extends ConditionBinder<R, OTHER>> SELF inspectingDeeply(Function<T, R> mapper, Function<R, ConditionBinder<R, OTHER>> validator) {
        return super.inspectingDeeply(mapper, validator);
    }
}
