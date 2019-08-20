package validation.api.demo.validation.domain.number.integer;

import validation.api.demo.validation.common.SingleCondition;
import validation.api.demo.validation.dict.ErrorMode;
import validation.api.demo.validation.dict.TerminationMode;
import validation.api.demo.validation.domain.AbstractBaseValidation;
import validation.api.demo.validation.domain.number.integer.impl.IntValidation;

import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

public abstract class AbstractIntCondition extends AbstractBaseValidation<Integer> {

    public IntValidation isGt(Integer otherInteger) {
        registerCondition(IntConditions.isGt(otherInteger));

        return (IntValidation) this;
    }

    public IntValidation isGte(Integer otherInteger) {
        registerCondition(IntConditions.isGte(otherInteger));

        return (IntValidation) this;
    }

    public IntValidation isLt(Integer otherInteger) {
        registerCondition(IntConditions.isLt(otherInteger));

        return (IntValidation) this;
    }

    public IntValidation isLte(Integer otherInteger) {
        registerCondition(IntConditions.isLte(otherInteger));

        return (IntValidation) this;
    }

    @Override
    protected AbstractIntCondition isNull() {
        return (AbstractIntCondition) super.isNull();
    }

    @Override
    protected AbstractIntCondition isNotNull() {
        return (AbstractIntCondition) super.isNotNull();
    }

    @Override
    protected AbstractIntCondition isEqualTo(Integer otherObj) {
        return (AbstractIntCondition) super.isEqualTo(otherObj);
    }

    @Override
    protected AbstractIntCondition isNotEqualTo(Integer otherObj) {
        return (AbstractIntCondition) super.isNotEqualTo(otherObj);
    }

    @Override
    protected AbstractIntCondition withTerm(Predicate<Integer> predicate) {
        return (AbstractIntCondition) super.withTerm(predicate);
    }

    @Override
    protected AbstractIntCondition withTerm(Supplier<Boolean> supplier) {
        return (AbstractIntCondition) super.withTerm(supplier);
    }

    @Override
    protected AbstractIntCondition withTerm(Function<Integer, AbstractBaseValidation<Integer>> validator) {
        return (AbstractIntCondition) super.withTerm(validator);
    }

    @Override
    protected AbstractIntCondition isAnyOf(SingleCondition<Integer>... conditions) {
        return (AbstractIntCondition) super.isAnyOf(conditions);
    }

    @Override
    protected AbstractIntCondition isAllOf(SingleCondition<Integer>... conditions) {
        return (AbstractIntCondition) super.isAllOf(conditions);
    }

    @Override
    protected AbstractIntCondition log(String msg, Object... values) {
        return (AbstractIntCondition) super.log(msg, values);
    }

    @Override
    protected <R> AbstractIntCondition inspecting(Function<Integer, R> mapper, Predicate<R> predicate) {
        return (AbstractIntCondition) super.inspecting(mapper, predicate);
    }

    @Override
    protected <R> AbstractIntCondition inspecting(Function<Integer, R> mapper, Supplier<SingleCondition<R>> condition) {
        return (AbstractIntCondition) super.inspecting(mapper, condition);
    }

    @Override
    protected <R> AbstractIntCondition deepInspecting(Function<Integer, R> mapper, Function<R, AbstractBaseValidation<R>> validator) {
        return (AbstractIntCondition) super.deepInspecting(mapper, validator);
    }

    @Override
    protected AbstractIntCondition failOn(TerminationMode terminationMode) {
        return (AbstractIntCondition) super.failOn(terminationMode);
    }

    @Override
    protected AbstractIntCondition failOn(TerminationMode terminationMode, ErrorMode errorMode) {
        return (AbstractIntCondition) super.failOn(terminationMode, errorMode);
    }
}
