package vapidsl.domain.number.bigdecimal;

import vapidsl.common.SingleCondition;
import vapidsl.domain.ConditionBinder;
import vapidsl.domain.number.bigdecimal.impl.BigDecimalValidation;

import java.math.BigDecimal;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

public abstract class BigDecimalConditionBinder extends ConditionBinder<BigDecimal, BigDecimalValidation> {

    BigDecimalConditionBinder(Class<?> selfType) {
        super(selfType);
    }

    public BigDecimalValidation isGt(BigDecimal otherBigDecimal) {
        this.registrar.registerCondition(BigDecimalConditions.isGt(otherBigDecimal));

        return self;
    }

    public BigDecimalValidation isGte(BigDecimal otherBigDecimal) {
        this.registrar.registerCondition(BigDecimalConditions.isGte(otherBigDecimal));

        return self;
    }

    public BigDecimalValidation isLt(BigDecimal otherBigDecimal) {
        this.registrar.registerCondition(BigDecimalConditions.isLt(otherBigDecimal));

        return self;
    }

    public BigDecimalValidation isLte(BigDecimal otherBigDecimal) {
        this.registrar.registerCondition(BigDecimalConditions.isLte(otherBigDecimal));

        return self;
    }

    @Override
    public BigDecimalValidation isEqualTo(BigDecimal otherBigDecimal) {
        this.registrar.registerCondition(BigDecimalConditions.isEqualTo(otherBigDecimal));

        return self;
    }

    @Override
    public BigDecimalValidation isNotEqualTo(BigDecimal otherBigDecimal) {
        this.registrar.registerCondition(BigDecimalConditions.isNotEqualTo(otherBigDecimal));

        return self;
    }

    @Override
    public BigDecimalValidation isNull() {
        return super.isNull();
    }

    @Override
    public BigDecimalValidation isNotNull() {
        return super.isNotNull();
    }

    @Override
    public BigDecimalValidation withTerm(Predicate<BigDecimal> predicate) {
        return super.withTerm(predicate);
    }

    @Override
    public BigDecimalValidation withTerm(Supplier<Boolean> supplier) {
        return super.withTerm(supplier);
    }

    @Override
    @SafeVarargs
    public final BigDecimalValidation satisfiesAny(SingleCondition<BigDecimal>... conditions) {
        return super.satisfiesAny(conditions);
    }

    @Override
    @SafeVarargs
    public final BigDecimalValidation satisfiesEvery(SingleCondition<BigDecimal>... conditions) {
        return super.satisfiesEvery(conditions);
    }

    @Override
    public BigDecimalValidation log(String msg, Object... values) {
        return super.log(msg, values);
    }

    @Override
    public <R> BigDecimalValidation inspecting(Function<BigDecimal, R> mapper, Predicate<R> predicate) {
        return super.inspecting(mapper, predicate);
    }

    @Override
    public <R> BigDecimalValidation inspecting(Function<BigDecimal, R> mapper, Supplier<SingleCondition<R>> condition) {
        return super.inspecting(mapper, condition);
    }

    @Override
    public <R, OTHER extends ConditionBinder<R, OTHER>> BigDecimalValidation inspectingDeeply(Function<BigDecimal, R> mapper, Function<R, ConditionBinder<R, OTHER>> validator) {
        return super.inspectingDeeply(mapper, validator);
    }
}
