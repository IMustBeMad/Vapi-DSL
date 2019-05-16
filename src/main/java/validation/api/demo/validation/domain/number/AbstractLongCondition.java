package validation.api.demo.validation.domain.number;

import validation.api.demo.validation.common.SingleCondition;
import validation.api.demo.validation.dict.ErrorMode;
import validation.api.demo.validation.dict.TerminationMode;
import validation.api.demo.validation.domain.AbstractBaseValidation;
import validation.api.demo.validation.domain.number.impl.LongValidation;

import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

public abstract class AbstractLongCondition extends AbstractBaseValidation<Long> {

    public LongValidation isGt(Long otherLong) {
        registerCondition(LongConditions.isGt(otherLong));

        return (LongValidation) this;
    }

    public LongValidation isGte(Long otherLong) {
        registerCondition(LongConditions.isGte(otherLong));

        return (LongValidation) this;
    }

    public LongValidation isLt(Long otherLong) {
        registerCondition(LongConditions.isLt(otherLong));

        return (LongValidation) this;
    }

    public LongValidation isLte(Long otherLong) {
        registerCondition(LongConditions.isLte(otherLong));

        return (LongValidation) this;
    }

    @Override
    public LongValidation isEqualTo(Long otherLong) {
        registerCondition(LongConditions.isEqualTo(otherLong));

        return (LongValidation) this;
    }

    @Override
    public LongValidation isNotEqualTo(Long otherLong) {
        registerCondition(LongConditions.isNotEqualTo(otherLong));

        return (LongValidation) this;
    }

    @Override
    public LongValidation isNull() {
        return (LongValidation) super.isNull();
    }

    @Override
    public LongValidation isNotNull() {
        return (LongValidation) super.isNotNull();
    }

    @Override
    public LongValidation withTerm(Predicate<Long> predicate) {
        return (LongValidation) super.withTerm(predicate);
    }

    @Override
    public LongValidation withTerm(Supplier<Boolean> supplier) {
        return (LongValidation) super.withTerm(supplier);
    }

    @Override
    public LongValidation isAnyOf(SingleCondition<Long> condition1, SingleCondition<Long> condition2) {
        return (LongValidation) super.isAnyOf(condition1, condition2);
    }

    @Override
    public LongValidation isAllOf(SingleCondition<Long> condition1, SingleCondition<Long> condition2) {
        return (LongValidation) super.isAllOf(condition1, condition2);
    }

    @Override
    public LongValidation log(String msg, Object... values) {
        return (LongValidation) super.log(msg, values);
    }

    @Override
    public <R> LongValidation inspecting(Function<Long, R> mapper, Predicate<R> predicate) {
        return (LongValidation) super.inspecting(mapper, predicate);
    }

    @Override
    public <R> LongValidation inspecting(Function<Long, R> mapper, Supplier<SingleCondition<R>> condition) {
        return (LongValidation) super.inspecting(mapper, condition);
    }

    @Override
    public <R> LongValidation deepInspecting(Function<Long, R> mapper, Function<R, AbstractBaseValidation<R>> validator) {
        return (LongValidation) super.deepInspecting(mapper, validator);
    }

    @Override
    public LongValidation failOn(TerminationMode terminationMode) {
        return (LongValidation) super.failOn(terminationMode);
    }

    @Override
    public LongValidation failOn(TerminationMode terminationMode, ErrorMode errorMode) {
        return (LongValidation) super.failOn(terminationMode, errorMode);
    }
}
