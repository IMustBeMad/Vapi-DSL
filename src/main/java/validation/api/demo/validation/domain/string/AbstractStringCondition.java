package validation.api.demo.validation.domain.string;

import validation.api.demo.validation.common.SingleCondition;
import validation.api.demo.validation.domain.AbstractBaseValidation;
import validation.api.demo.validation.domain.string.impl.StringValidation;

import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

public abstract class AbstractStringCondition extends AbstractBaseValidation<String> {

    public StringValidation matches(String pattern, String onError) {
        registerCondition(StringConditions.matches(pattern), onError);

        return (StringValidation) this;
    }

    public StringValidation isEmpty(String onError) {
        registerCondition(StringConditions.isEmpty(), onError);

        return (StringValidation) this;
    }

    public StringValidation isNotEmpty(String onError) {
        registerCondition(StringConditions.isNotEmpty(), onError);

        return (StringValidation) this;
    }

    @Override
    public StringValidation isNull(String onError) {
        return (StringValidation) super.isNull(onError);
    }

    @Override
    public StringValidation isNotNull(String onError) {
        return (StringValidation) super.isNotNull(onError);
    }

    @Override
    public StringValidation isEqualTo(String otherString, String onError) {
        return (StringValidation) super.isEqualTo(otherString, onError);
    }

    @Override
    public StringValidation isNotEqualTo(String otherString, String onError) {
        return (StringValidation) super.isNotEqualTo(otherString, onError);
    }

    @Override
    public StringValidation withTerm(Predicate<String> predicate, String onError) {
        return (StringValidation) super.withTerm(predicate, onError);
    }

    @Override
    public StringValidation withTerm(Supplier<Boolean> supplier, String onError) {
        return (StringValidation) super.withTerm(supplier, onError);
    }

    @Override
    public StringValidation isAnyOf(SingleCondition<String> condition1, SingleCondition<String> condition2, String onError) {
        return (StringValidation) super.isAnyOf(condition1, condition2, onError);
    }

    @Override
    public StringValidation isAllOf(SingleCondition<String> condition1, SingleCondition<String> condition2, String onError) {
        return (StringValidation) super.isAllOf(condition1, condition2, onError);
    }

    @Override
    public StringValidation log(String msg, Object... values) {
        return (StringValidation) super.log(msg, values);
    }

    @Override
    public <R> StringValidation inspecting(Function<String, R> mapper, Predicate<R> predicate, String onError) {
        return (StringValidation) super.inspecting(mapper, predicate, onError);
    }

    @Override
    public <R> StringValidation inspecting(Function<String, R> mapper, Function<R, AbstractBaseValidation<R>> validator) {
        return (StringValidation) super.inspecting(mapper, validator);
    }

    @Override
    public <R> StringValidation inspecting(Function<String, R> mapper, Supplier<SingleCondition<R>> condition) {
        return (StringValidation) super.inspecting(mapper, condition);
    }
}
