package validation.api.demo.validation.domain.number;

import validation.api.demo.validation.common.SingleCondition;
import validation.api.demo.validation.domain.object.AbstractObjectValidation;

import java.util.function.Function;
import java.util.function.Predicate;

public abstract class AbstractLongValidation extends AbstractObjectValidation<Long> {

    public AbstractLongValidation isGt(Long otherLong, String onError) {
        registerCondition(LongConditions.isGt(otherLong), onError);

        return this;
    }

    public AbstractLongValidation isGte(Long otherLong, String onError) {
        registerCondition(LongConditions.isGte(otherLong), onError);

        return this;
    }

    public AbstractLongValidation isLt(Long otherLong, String onError) {
        registerCondition(LongConditions.isLt(otherLong), onError);

        return this;
    }

    public AbstractLongValidation isLte(Long otherLong, String onError) {
        registerCondition(LongConditions.isLte(otherLong), onError);

        return this;
    }

    @Override
    public AbstractLongValidation isEqualTo(Long otherLong, String onError) {
        registerCondition(LongConditions.isEqualTo(otherLong), onError);

        return this;
    }

    @Override
    public AbstractLongValidation isNotEqualTo(Long otherLong, String onError) {
        registerCondition(LongConditions.isNotEqualTo(otherLong), onError);

        return this;
    }

    @Override
    public AbstractLongValidation isNull(String onError) {
        return (AbstractLongValidation) super.isNull(onError);
    }

    @Override
    public AbstractLongValidation isNotNull(String onError) {
        return (AbstractLongValidation) super.isNotNull(onError);
    }

    @Override
    public AbstractLongValidation withTerm(Predicate<Long> predicate, String onError) {
        return (AbstractLongValidation) super.withTerm(predicate, onError);
    }

    @Override
    public AbstractLongValidation isAnyOf(SingleCondition<Long> condition1, SingleCondition<Long> condition2, String onError) {
       return ((AbstractLongValidation) super.isAnyOf(condition1, condition2, onError));
    }

    @Override
    public AbstractLongValidation isAllOf(SingleCondition<Long> condition1, SingleCondition<Long> condition2, String onError) {
        return ((AbstractLongValidation) super.isAllOf(condition1, condition2, onError));
    }

    @Override
    public AbstractLongValidation or() {
        return ((AbstractLongValidation) super.or());
    }

    @Override
    public AbstractLongValidation log(String msg, Object... values) {
        return (AbstractLongValidation) super.log(msg, values);
    }

    @Override
    public <R> AbstractLongValidation inspecting(Function<Long, R> mapper, Predicate<R> predicate, String onError) {
        return (AbstractLongValidation) super.inspecting(mapper, predicate, onError);
    }

    @Override
    public <R> AbstractLongValidation inspecting(Function<Long, R> mapper, Function<R, AbstractObjectValidation<R>> validator) {
        return (AbstractLongValidation) super.inspecting(mapper, validator);
    }
}
