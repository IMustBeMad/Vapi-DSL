package validation.api.demo.validation.domain.number;

import validation.api.demo.validation.common.SingleCondition;
import validation.api.demo.validation.domain.AbstractBaseValidation;

import java.util.function.Function;
import java.util.function.Predicate;

public abstract class AbstractLongCondition extends AbstractBaseValidation<Long> {

    public AbstractLongCondition isGt(Long otherLong, String onError) {
        registerCondition(LongConditions.isGt(otherLong), onError);

        return this;
    }

    public AbstractLongCondition isGte(Long otherLong, String onError) {
        registerCondition(LongConditions.isGte(otherLong), onError);

        return this;
    }

    public AbstractLongCondition isLt(Long otherLong, String onError) {
        registerCondition(LongConditions.isLt(otherLong), onError);

        return this;
    }

    public AbstractLongCondition isLte(Long otherLong, String onError) {
        registerCondition(LongConditions.isLte(otherLong), onError);

        return this;
    }

    @Override
    public AbstractLongCondition isEqualTo(Long otherLong, String onError) {
        registerCondition(LongConditions.isEqualTo(otherLong), onError);

        return this;
    }

    @Override
    public AbstractLongCondition isNotEqualTo(Long otherLong, String onError) {
        registerCondition(LongConditions.isNotEqualTo(otherLong), onError);

        return this;
    }

    @Override
    public AbstractLongCondition isNull(String onError) {
        return (AbstractLongCondition) super.isNull(onError);
    }

    @Override
    public AbstractLongCondition isNotNull(String onError) {
        return (AbstractLongCondition) super.isNotNull(onError);
    }

    @Override
    public AbstractLongCondition withTerm(Predicate<Long> predicate, String onError) {
        return (AbstractLongCondition) super.withTerm(predicate, onError);
    }

    @Override
    public AbstractLongCondition isAnyOf(SingleCondition<Long> condition1, SingleCondition<Long> condition2, String onError) {
       return ((AbstractLongCondition) super.isAnyOf(condition1, condition2, onError));
    }

    @Override
    public AbstractLongCondition isAllOf(SingleCondition<Long> condition1, SingleCondition<Long> condition2, String onError) {
        return ((AbstractLongCondition) super.isAllOf(condition1, condition2, onError));
    }

    @Override
    public AbstractLongCondition log(String msg, Object... values) {
        return (AbstractLongCondition) super.log(msg, values);
    }

    @Override
    public <R> AbstractLongCondition inspecting(Function<Long, R> mapper, Predicate<R> predicate, String onError) {
        return (AbstractLongCondition) super.inspecting(mapper, predicate, onError);
    }

    @Override
    public <R> AbstractLongCondition inspecting(Function<Long, R> mapper, Function<R, AbstractBaseValidation<R>> validator) {
        return (AbstractLongCondition) super.inspecting(mapper, validator);
    }
}
