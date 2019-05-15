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

    public LongValidation isGt(Long otherLong, String onError) {
        registerCondition(LongConditions.isGt(otherLong), onError);

        return (LongValidation) this;
    }

    public LongValidation isGte(Long otherLong, String onError) {
        registerCondition(LongConditions.isGte(otherLong), onError);

        return (LongValidation) this;
    }

    public LongValidation isLt(Long otherLong, String onError) {
        registerCondition(LongConditions.isLt(otherLong), onError);

        return (LongValidation) this;
    }

    public LongValidation isLte(Long otherLong, String onError) {
        registerCondition(LongConditions.isLte(otherLong), onError);

        return (LongValidation) this;
    }

    @Override
    public LongValidation isEqualTo(Long otherLong, String onError) {
        registerCondition(LongConditions.isEqualTo(otherLong), onError);

        return (LongValidation) this;
    }

    @Override
    public LongValidation isNotEqualTo(Long otherLong, String onError) {
        registerCondition(LongConditions.isNotEqualTo(otherLong), onError);

        return (LongValidation) this;
    }

    @Override
    public LongValidation isNull(String onError) {
        return (LongValidation) super.isNull(onError);
    }

    @Override
    public LongValidation isNotNull(String onError) {
        return (LongValidation) super.isNotNull(onError);
    }

    @Override
    public LongValidation withTerm(Predicate<Long> predicate, String onError) {
        return (LongValidation) super.withTerm(predicate, onError);
    }

    @Override
    public LongValidation withTerm(Supplier<Boolean> supplier, String onError) {
        return (LongValidation) super.withTerm(supplier, onError);
    }

    @Override
    public LongValidation isAnyOf(SingleCondition<Long> condition1, SingleCondition<Long> condition2, String onError) {
        return (LongValidation) super.isAnyOf(condition1, condition2, onError);
    }

    @Override
    public LongValidation isAllOf(SingleCondition<Long> condition1, SingleCondition<Long> condition2, String onError) {
        return (LongValidation) super.isAllOf(condition1, condition2, onError);
    }

    @Override
    public LongValidation log(String msg, Object... values) {
        return (LongValidation) super.log(msg, values);
    }

    @Override
    public <R> LongValidation inspecting(Function<Long, R> mapper, Predicate<R> predicate, String onError) {
        return (LongValidation) super.inspecting(mapper, predicate, onError);
    }

    @Override
    public <R> LongValidation inspecting(Function<Long, R> mapper, Function<R, AbstractBaseValidation<R>> validator) {
        return (LongValidation) super.inspecting(mapper, validator);
    }

    @Override
    public <R> LongValidation inspecting(Function<Long, R> mapper, Supplier<SingleCondition<R>> condition, String onError) {
        return (LongValidation) super.inspecting(mapper, condition, onError);
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
