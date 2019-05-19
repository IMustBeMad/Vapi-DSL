package validation.api.demo.validation.domain.string;

import validation.api.demo.validation.common.SingleCondition;
import validation.api.demo.validation.dict.ErrorMode;
import validation.api.demo.validation.dict.TerminationMode;
import validation.api.demo.validation.domain.AbstractBaseValidation;
import validation.api.demo.validation.domain.string.impl.StringValidation;

import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

public abstract class AbstractStringCondition extends AbstractBaseValidation<String> {

    public StringValidation matches(String pattern) {
        registerCondition(StringConditions.matches(pattern));

        return (StringValidation) this;
    }

    public StringValidation isEmpty() {
        registerCondition(StringConditions.isEmpty());

        return (StringValidation) this;
    }

    public StringValidation isNotEmpty() {
        registerCondition(StringConditions.isNotEmpty());

        return (StringValidation) this;
    }

    @Override
    public StringValidation isNull() {
        return (StringValidation) super.isNull();
    }

    @Override
    public StringValidation isNotNull() {
        return (StringValidation) super.isNotNull();
    }

    @Override
    public StringValidation isEqualTo(String otherString) {
        return (StringValidation) super.isEqualTo(otherString);
    }

    @Override
    public StringValidation isNotEqualTo(String otherString) {
        return (StringValidation) super.isNotEqualTo(otherString);
    }

    @Override
    public StringValidation withTerm(Predicate<String> predicate) {
        return (StringValidation) super.withTerm(predicate);
    }

    @Override
    public StringValidation withTerm(Supplier<Boolean> supplier) {
        return (StringValidation) super.withTerm(supplier);
    }

    @Override
    @SafeVarargs
    public final StringValidation isAnyOf(SingleCondition<String>... conditions) {
        return (StringValidation) super.isAnyOf(conditions);
    }

    @Override
    @SafeVarargs
    public final StringValidation isAllOf(SingleCondition<String>... conditions) {
        return (StringValidation) super.isAllOf(conditions);
    }

    @Override
    public StringValidation log(String msg, Object... values) {
        return (StringValidation) super.log(msg, values);
    }

    @Override
    public <R> StringValidation inspecting(Function<String, R> mapper, Predicate<R> predicate) {
        return (StringValidation) super.inspecting(mapper, predicate);
    }

    @Override
    public <R> StringValidation inspecting(Function<String, R> mapper, Supplier<SingleCondition<R>> condition) {
        return (StringValidation) super.inspecting(mapper, condition);
    }

    @Override
    public <R> StringValidation deepInspecting(Function<String, R> mapper, Function<R, AbstractBaseValidation<R>> validator) {
        return (StringValidation) super.deepInspecting(mapper, validator);
    }

    @Override
    public StringValidation failOn(TerminationMode terminationMode) {
        return (StringValidation) super.failOn(terminationMode);
    }

    @Override
    public StringValidation failOn(TerminationMode terminationMode, ErrorMode errorMode) {
        return (StringValidation) super.failOn(terminationMode, errorMode);
    }
}
