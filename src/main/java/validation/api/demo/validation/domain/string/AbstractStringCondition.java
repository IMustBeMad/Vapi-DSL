package validation.api.demo.validation.domain.string;

import validation.api.demo.validation.common.SingleCondition;
import validation.api.demo.validation.domain.AbstractBaseValidation;

import java.util.function.Function;
import java.util.function.Predicate;

public abstract class AbstractStringCondition extends AbstractBaseValidation<String> {

    public AbstractStringCondition matches(String pattern, String onError) {
        registerCondition(StringConditions.matches(pattern), onError);

        return this;
    }

    public AbstractStringCondition isEmpty(String onError) {
        registerCondition(StringConditions.isEmpty(), onError);

        return this;
    }

    public AbstractStringCondition isNotEmpty(String onError) {
        registerCondition(StringConditions.isNotEmpty(), onError);

        return this;
    }

    @Override
    public AbstractStringCondition isNull(String onError) {
        return (AbstractStringCondition) super.isNull(onError);
    }

    @Override
    public AbstractStringCondition isNotNull(String onError) {
        return (AbstractStringCondition) super.isNotNull(onError);
    }

    @Override
    public AbstractStringCondition isEqualTo(String otherString, String onError) {
        return (AbstractStringCondition) super.isEqualTo(otherString, onError);
    }

    @Override
    public AbstractStringCondition isNotEqualTo(String otherString, String onError) {
        return (AbstractStringCondition) super.isNotEqualTo(otherString, onError);
    }

    @Override
    public AbstractStringCondition withTerm(Predicate<String> predicate, String onError) {
        return (AbstractStringCondition) super.withTerm(predicate, onError);
    }

    @Override
    public AbstractStringCondition isAnyOf(SingleCondition<String> condition1, SingleCondition<String> condition2, String onError) {
        return ((AbstractStringCondition) super.isAnyOf(condition1, condition2, onError));
    }

    @Override
    public AbstractStringCondition isAllOf(SingleCondition<String> condition1, SingleCondition<String> condition2, String onError) {
        return ((AbstractStringCondition) super.isAllOf(condition1, condition2, onError));
    }

    @Override
    public AbstractStringCondition log(String msg, Object... values) {
        return (AbstractStringCondition) super.log(msg, values);
    }

    @Override
    public <R> AbstractStringCondition inspecting(Function<String, R> mapper, Predicate<R> predicate, String onError) {
        return (AbstractStringCondition) super.inspecting(mapper, predicate, onError);
    }

    @Override
    public <R> AbstractStringCondition inspecting(Function<String, R> mapper, Function<R, AbstractBaseValidation<R>> validator) {
        return (AbstractStringCondition) super.inspecting(mapper, validator);
    }
}
