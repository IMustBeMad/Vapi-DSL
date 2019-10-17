package vapidsl.domain.number.biginteger;

import vapidsl.common.SingleCondition;
import vapidsl.domain.AbstractBaseValidation;
import vapidsl.domain.number.biginteger.impl.LongValidation;

import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

public abstract class AbstractLongCondition extends AbstractBaseValidation<Long, LongValidation> {

    protected AbstractLongCondition(Class<?> selfType) {
        super(selfType);
    }

    public LongValidation isGt(Long otherLong) {
        this.registerCondition(LongConditions.isGt(otherLong));

        return self;
    }

    public LongValidation isGte(Long otherLong) {
        this.registerCondition(LongConditions.isGte(otherLong));

        return self;
    }

    public LongValidation isLt(Long otherLong) {
        this.registerCondition(LongConditions.isLt(otherLong));

        return self;
    }

    public LongValidation isLte(Long otherLong) {
        this.registerCondition(LongConditions.isLte(otherLong));

        return self;
    }

    @Override
    public LongValidation isEqualTo(Long otherLong) {
        this.registerCondition(LongConditions.isEqualTo(otherLong));

        return self;
    }

    @Override
    public LongValidation isNotEqualTo(Long otherLong) {
        registerCondition(LongConditions.isNotEqualTo(otherLong));

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
    public LongValidation withTerm(Supplier<Boolean> supplier) {
        return super.withTerm(supplier);
    }

    @Override
    @SafeVarargs
    public final LongValidation satisfiesAny(SingleCondition<Long>... conditions) {
        return super.satisfiesAny(conditions);
    }

    @Override
    @SafeVarargs
    public final LongValidation satisfiesAll(SingleCondition<Long>... conditions) {
        return super.satisfiesAll(conditions);
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
    public <R, OTHER extends AbstractBaseValidation<R, OTHER>> LongValidation deepInspecting(Function<Long, R> mapper, Function<R, AbstractBaseValidation<R, OTHER>> validator) {
        return super.deepInspecting(mapper, validator);
    }
}
