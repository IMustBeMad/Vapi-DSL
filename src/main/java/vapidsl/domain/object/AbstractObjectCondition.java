package vapidsl.domain.object;

import vapidsl.common.SingleCondition;
import vapidsl.domain.AbstractBaseValidation;
import vapidsl.domain.object.impl.ObjectValidation;

import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

public abstract class AbstractObjectCondition<T, SELF extends ObjectValidation<T, SELF>> extends AbstractBaseValidation<T, SELF> {

    protected AbstractObjectCondition(Class<?> selfType) {
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
    public SELF withTerm(Supplier<Boolean> supplier) {
        return super.withTerm(supplier);
    }

    @Override
    public SELF withTerm(Function<T, AbstractBaseValidation<T, SELF>> validator) {
        return super.withTerm(validator);
    }

    @Override
    @SafeVarargs
    public final SELF satisfiesAny(SingleCondition<T>... conditions) {
        return super.satisfiesAny(conditions);
    }

    @Override
    @SafeVarargs
    public final SELF satisfiesAll(SingleCondition<T>... conditions) {
        return super.satisfiesAll(conditions);
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
    public <R, OTHER extends AbstractBaseValidation<R, OTHER>> SELF deepInspecting(Function<T, R> mapper, Function<R, AbstractBaseValidation<R, OTHER>> validator) {
        return super.deepInspecting(mapper, validator);
    }
}
