package validation.api.demo.validation.domain.string;

import validation.api.demo.validation.common.SingleCondition;
import validation.api.demo.validation.domain.AbstractBaseValidation;
import validation.api.demo.validation.domain.string.impl.StringValidation;

import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

public abstract class AbstractStringCondition extends AbstractBaseValidation<String> {

    public StringValidation matches(String pattern) {
        this.registerCondition(StringConditions.matches(pattern));

        return self();
    }

    public StringValidation isEmpty() {
        this.registerCondition(StringConditions.isEmpty());

        return self();
    }

    public StringValidation isNotEmpty() {
        this.registerCondition(StringConditions.isNotEmpty());

        return self();
    }

    public StringValidation startsWith(String prefix) {
        this.registerCondition(StringConditions.startsWith(prefix));

        return self();
    }

    public StringValidation ofLength(int length) {
        this.registerCondition(StringConditions.ofLength(length));

        return self();
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

    private StringValidation self() {
        return (StringValidation) this;
    }
}
