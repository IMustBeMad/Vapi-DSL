package vapidsl.domain.number.integer;

import vapidsl.common.SingleCondition;
import vapidsl.domain.AbstractBaseValidation;
import vapidsl.domain.number.integer.impl.IntValidation;

import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

public abstract class AbstractIntCondition extends AbstractBaseValidation<Integer, IntValidation> {

    protected AbstractIntCondition(Class<?> selfType) {
        super(selfType);
    }

    public IntValidation isGt(Integer otherInteger) {
        this.registerCondition(IntConditions.isGt(otherInteger));

        return self;
    }

    public IntValidation isGte(Integer otherInteger) {
        this.registerCondition(IntConditions.isGte(otherInteger));

        return self;
    }

    public IntValidation isLt(Integer otherInteger) {
        this.registerCondition(IntConditions.isLt(otherInteger));

        return self;
    }

    public IntValidation isLte(Integer otherInteger) {
        this.registerCondition(IntConditions.isLte(otherInteger));

        return self;
    }

    @Override
    protected IntValidation isNull() {
        return super.isNull();
    }

    @Override
    protected IntValidation isNotNull() {
        return super.isNotNull();
    }

    @Override
    protected IntValidation isEqualTo(Integer otherObj) {
        return super.isEqualTo(otherObj);
    }

    @Override
    protected IntValidation isNotEqualTo(Integer otherObj) {
        return super.isNotEqualTo(otherObj);
    }

    @Override
    protected IntValidation withTerm(Predicate<Integer> predicate) {
        return super.withTerm(predicate);
    }

    @Override
    protected IntValidation withTerm(Supplier<Boolean> supplier) {
        return super.withTerm(supplier);
    }

    @Override
    protected IntValidation satisfiesAny(SingleCondition<Integer>... conditions) {
        return super.satisfiesAny(conditions);
    }

    @Override
    protected IntValidation satisfiesAll(SingleCondition<Integer>... conditions) {
        return super.satisfiesAll(conditions);
    }

    @Override
    protected IntValidation log(String msg, Object... values) {
        return super.log(msg, values);
    }

    @Override
    protected <R> IntValidation inspecting(Function<Integer, R> mapper, Predicate<R> predicate) {
        return super.inspecting(mapper, predicate);
    }

    @Override
    protected <R> IntValidation inspecting(Function<Integer, R> mapper, Supplier<SingleCondition<R>> condition) {
        return super.inspecting(mapper, condition);
    }

    @Override
    protected <R, OTHER extends AbstractBaseValidation<R, OTHER>> IntValidation deepInspecting(Function<Integer, R> mapper, Function<R, AbstractBaseValidation<R, OTHER>> validator) {
        return super.deepInspecting(mapper, validator);
    }
}
