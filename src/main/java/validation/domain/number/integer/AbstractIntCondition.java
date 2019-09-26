package validation.domain.number.integer;

import validation.common.SingleCondition;
import validation.domain.AbstractBaseValidation;
import validation.domain.number.integer.impl.IntValidation;

import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

public abstract class AbstractIntCondition extends AbstractBaseValidation<Integer> {

    public IntValidation isGt(Integer otherInteger) {
        this.registerCondition(IntConditions.isGt(otherInteger));

        return self();
    }

    public IntValidation isGte(Integer otherInteger) {
        this.registerCondition(IntConditions.isGte(otherInteger));

        return self();
    }

    public IntValidation isLt(Integer otherInteger) {
        this.registerCondition(IntConditions.isLt(otherInteger));

        return self();
    }

    public IntValidation isLte(Integer otherInteger) {
        this.registerCondition(IntConditions.isLte(otherInteger));

        return self();
    }

    @Override
    protected IntValidation isNull() {
        return (IntValidation) super.isNull();
    }

    @Override
    protected IntValidation isNotNull() {
        return (IntValidation) super.isNotNull();
    }

    @Override
    protected IntValidation isEqualTo(Integer otherObj) {
        return (IntValidation) super.isEqualTo(otherObj);
    }

    @Override
    protected IntValidation isNotEqualTo(Integer otherObj) {
        return (IntValidation) super.isNotEqualTo(otherObj);
    }

    @Override
    protected IntValidation withTerm(Predicate<Integer> predicate) {
        return (IntValidation) super.withTerm(predicate);
    }

    @Override
    protected IntValidation withTerm(Supplier<Boolean> supplier) {
        return (IntValidation) super.withTerm(supplier);
    }

    @Override
    protected IntValidation withTerm(Function<Integer, AbstractBaseValidation<Integer>> validator) {
        return (IntValidation) super.withTerm(validator);
    }

    @Override
    protected IntValidation isAnyOf(SingleCondition<Integer>... conditions) {
        return (IntValidation) super.isAnyOf(conditions);
    }

    @Override
    protected IntValidation isAllOf(SingleCondition<Integer>... conditions) {
        return (IntValidation) super.isAllOf(conditions);
    }

    @Override
    protected IntValidation log(String msg, Object... values) {
        return (IntValidation) super.log(msg, values);
    }

    @Override
    protected <R> IntValidation inspecting(Function<Integer, R> mapper, Predicate<R> predicate) {
        return (IntValidation) super.inspecting(mapper, predicate);
    }

    @Override
    protected <R> IntValidation inspecting(Function<Integer, R> mapper, Supplier<SingleCondition<R>> condition) {
        return (IntValidation) super.inspecting(mapper, condition);
    }

    @Override
    protected <R> IntValidation deepInspecting(Function<Integer, R> mapper, Function<R, AbstractBaseValidation<R>> validator) {
        return (IntValidation) super.deepInspecting(mapper, validator);
    }

    private IntValidation self() {
        return (IntValidation) this;
    }
}
