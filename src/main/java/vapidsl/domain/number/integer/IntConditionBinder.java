package vapidsl.domain.number.integer;

import vapidsl.common.SingleCondition;
import vapidsl.domain.ConditionBinder;
import vapidsl.domain.number.integer.impl.IntValidation;

import java.util.function.BooleanSupplier;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

public abstract class IntConditionBinder extends ConditionBinder<Integer, IntValidation> {

    IntConditionBinder(Class<?> selfType) {
        super(selfType);
    }

    public IntValidation isGt(Integer otherInteger) {
        this.registrar.registerCondition(IntConditions.isGt(otherInteger));

        return self;
    }

    public IntValidation isGte(Integer otherInteger) {
        this.registrar.registerCondition(IntConditions.isGte(otherInteger));

        return self;
    }

    public IntValidation isLt(Integer otherInteger) {
        this.registrar.registerCondition(IntConditions.isLt(otherInteger));

        return self;
    }

    public IntValidation isLte(Integer otherInteger) {
        this.registrar.registerCondition(IntConditions.isLte(otherInteger));

        return self;
    }

    @Override
    public IntValidation isNull() {
        return super.isNull();
    }

    @Override
    public IntValidation isNotNull() {
        return super.isNotNull();
    }

    @Override
    public IntValidation isEqualTo(Integer otherObj) {
        return super.isEqualTo(otherObj);
    }

    @Override
    public IntValidation isNotEqualTo(Integer otherObj) {
        return super.isNotEqualTo(otherObj);
    }

    @Override
    public IntValidation withTerm(Predicate<Integer> predicate) {
        return super.withTerm(predicate);
    }

    @Override
    public IntValidation withTerm(BooleanSupplier supplier) {
        return super.withTerm(supplier);
    }

    @Override
    @SafeVarargs
    public final IntValidation satisfiesAny(SingleCondition<Integer>... conditions) {
        return super.satisfiesAny(conditions);
    }

    @Override
    @SafeVarargs
    public final IntValidation satisfiesEvery(SingleCondition<Integer>... conditions) {
        return super.satisfiesEvery(conditions);
    }

    @Override
    public IntValidation log(String msg, Object... values) {
        return super.log(msg, values);
    }

    @Override
    public  <R> IntValidation inspecting(Function<Integer, R> mapper, Predicate<R> predicate) {
        return super.inspecting(mapper, predicate);
    }

    @Override
    public  <R> IntValidation inspecting(Function<Integer, R> mapper, Supplier<SingleCondition<R>> condition) {
        return super.inspecting(mapper, condition);
    }

    @Override
    public  <R, OTHER extends ConditionBinder<R, OTHER>> IntValidation inspectingDeeply(Function<Integer, R> mapper, Function<R, ConditionBinder<R, OTHER>> validator) {
        return super.inspectingDeeply(mapper, validator);
    }
}
