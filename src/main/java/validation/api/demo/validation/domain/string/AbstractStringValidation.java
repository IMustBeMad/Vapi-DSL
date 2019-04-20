package validation.api.demo.validation.domain.string;

import validation.api.demo.validation.common.Condition;
import validation.api.demo.validation.domain.object.AbstractObjectValidation;

import java.util.function.Function;
import java.util.function.Predicate;

public abstract class AbstractStringValidation extends AbstractObjectValidation<String> {

    public AbstractStringValidation matches(String pattern, String onError) {
        memoize(new Condition<>(it -> it.matches(pattern), onError));

        return this;
    }

    @Override
    public AbstractStringValidation isNull(String onError) {
        return (AbstractStringValidation) super.isNull(onError);
    }

    @Override
    public AbstractStringValidation isNotNull(String onError) {
        return (AbstractStringValidation) super.isNotNull(onError);
    }

    @Override
    public AbstractStringValidation isEqualTo(String otherString, String onError) {
        return (AbstractStringValidation) super.isEqualTo(otherString, onError);
    }

    @Override
    public AbstractStringValidation isNotEqualTo(String otherString, String onError) {
        return (AbstractStringValidation) super.isNotEqualTo(otherString, onError);
    }

    @Override
    public AbstractStringValidation withTerm(Predicate<String> predicate, String onError) {
        return (AbstractStringValidation) super.withTerm(predicate, onError);
    }

    @Override
    public AbstractStringValidation log(String msg, Object... values) {
        return (AbstractStringValidation) super.log(msg, values);
    }

    @Override
    public <R> AbstractStringValidation inspecting(Function<String, R> mapper, Predicate<R> predicate, String onError) {
        return (AbstractStringValidation) super.inspecting(mapper, predicate, onError);
    }

    @Override
    public <R> AbstractStringValidation inspecting(Function<String, R> mapper, Function<R, AbstractObjectValidation<R>> validator) {
        return (AbstractStringValidation) super.inspecting(mapper, validator);
    }
}
