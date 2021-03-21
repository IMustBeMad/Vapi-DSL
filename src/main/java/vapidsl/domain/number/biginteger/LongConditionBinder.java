package vapidsl.domain.number.biginteger;

import vapidsl.common.SingleCondition;
import vapidsl.domain.ConditionBinder;
import vapidsl.domain.number.biginteger.impl.LongValidation;

import java.util.function.BooleanSupplier;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

public abstract class LongConditionBinder extends ConditionBinder<Long, LongValidation> {

    LongConditionBinder(Class<?> selfType) {
        super(selfType);
    }

    public LongValidation isGt(Long otherLong) {
        this.registrar.registerCondition(LongConditions.isGt(otherLong));

        return self;
    }

    public LongValidation isGte(Long otherLong) {
        this.registrar.registerCondition(LongConditions.isGte(otherLong));

        return self;
    }

    public LongValidation isLt(Long otherLong) {
        this.registrar.registerCondition(LongConditions.isLt(otherLong));

        return self;
    }

    public LongValidation isLte(Long otherLong) {
        this.registrar.registerCondition(LongConditions.isLte(otherLong));

        return self;
    }

    @Override
    public LongValidation isEqualTo(Long otherLong) {
        this.registrar.registerCondition(LongConditions.isEqualTo(otherLong));

        return self;
    }

    @Override
    public LongValidation isNotEqualTo(Long otherLong) {
        this.registrar.registerCondition(LongConditions.isNotEqualTo(otherLong));

        return self;
    }

    @Override
    public LongValidation isNull() {
        return super.isNull();
    }

    @Override
    public LongValidation isNotNull() {
        return super.isNotNull();
    }

    @Override
    public LongValidation withTerm(Predicate<Long> predicate) {
        return super.withTerm(predicate);
    }

    @Override
    public LongValidation withTerm(BooleanSupplier supplier) {
        return super.withTerm(supplier);
    }

    @Override
    @SafeVarargs
    public final LongValidation satisfiesAny(SingleCondition<Long>... conditions) {
        return super.satisfiesAny(conditions);
    }

    @Override
    @SafeVarargs
    public final LongValidation satisfiesEvery(SingleCondition<Long>... conditions) {
        return super.satisfiesEvery(conditions);
    }

    @Override
    public LongValidation log(String msg, Object... values) {
        return super.log(msg, values);
    }

    @Override
    public <R> LongValidation inspecting(Function<Long, R> mapper, Predicate<R> predicate) {
        return super.inspecting(mapper, predicate);
    }

    @Override
    public <R> LongValidation inspecting(Function<Long, R> mapper, Supplier<SingleCondition<R>> condition) {
        return super.inspecting(mapper, condition);
    }

    @Override
    public <R, OTHER extends ConditionBinder<R, OTHER>> LongValidation inspectingDeeply(Function<Long, R> mapper, Function<R, ConditionBinder<R, OTHER>> validator) {
        return super.inspectingDeeply(mapper, validator);
    }
}
