package vapidsl.domain.string;

import vapidsl.common.SingleCondition;
import vapidsl.domain.AbstractBaseValidation;
import vapidsl.domain.string.impl.StringValidation;

import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

public abstract class AbstractStringCondition extends AbstractBaseValidation<String, StringValidation> {

    AbstractStringCondition(Class<?> selfType) {
        super(selfType);
    }

    public StringValidation matches(String pattern) {
        this.registerCondition(StringConditions.matches(pattern));

        return self;
    }

    public StringValidation isEmpty() {
        this.registerCondition(StringConditions.isEmpty());

        return self;
    }

    public StringValidation isNotEmpty() {
        this.registerCondition(StringConditions.isNotEmpty());

        return self;
    }

    public StringValidation startsWith(String prefix) {
        this.registerCondition(StringConditions.startsWith(prefix));

        return self;
    }

    public StringValidation hasLength(int length) {
        this.registerCondition(StringConditions.hasLength(length));

        return self;
    }

    @Override
    public StringValidation isNull() {
        return super.isNull();
    }

    @Override
    public StringValidation isNotNull() {
        return super.isNotNull();
    }

    @Override
    public StringValidation isEqualTo(String otherString) {
        return super.isEqualTo(otherString);
    }

    @Override
    public StringValidation isNotEqualTo(String otherString) {
        return super.isNotEqualTo(otherString);
    }

    @Override
    public StringValidation withTerm(Predicate<String> predicate) {
        return super.withTerm(predicate);
    }

    @Override
    public StringValidation withTerm(Supplier<Boolean> supplier) {
        return super.withTerm(supplier);
    }

    @Override
    @SafeVarargs
    public final StringValidation satisfiesAny(SingleCondition<String>... conditions) {
        return super.satisfiesAny(conditions);
    }

    @Override
    @SafeVarargs
    public final StringValidation satisfiesEvery(SingleCondition<String>... conditions) {
        return super.satisfiesEvery(conditions);
    }

    @Override
    public StringValidation log(String msg, Object... values) {
        return super.log(msg, values);
    }

    @Override
    public <R> StringValidation inspecting(Function<String, R> mapper, Predicate<R> predicate) {
        return super.inspecting(mapper, predicate);
    }

    @Override
    public <R> StringValidation inspecting(Function<String, R> mapper, Supplier<SingleCondition<R>> condition) {
        return super.inspecting(mapper, condition);
    }

    @Override
    public <R, OTHER extends AbstractBaseValidation<R, OTHER>> StringValidation inspectingDeeply(Function<String, R> mapper, Function<R, AbstractBaseValidation<R, OTHER>> validator) {
        return super.inspectingDeeply(mapper, validator);
    }
}
