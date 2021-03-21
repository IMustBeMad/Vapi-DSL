package vapidsl.domain.string;

import vapidsl.common.SingleCondition;
import vapidsl.domain.ConditionBinder;
import vapidsl.domain.string.impl.StringValidation;

import java.util.function.BooleanSupplier;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

public abstract class StringConditionBinder extends ConditionBinder<String, StringValidation> {

    StringConditionBinder(Class<?> selfType) {
        super(selfType);
    }

    public StringValidation matches(String pattern) {
        this.registrar.registerCondition(StringConditions.matches(pattern));

        return self;
    }

    public StringValidation isEmpty() {
        this.registrar.registerCondition(StringConditions.isEmpty());

        return self;
    }

    public StringValidation isNotEmpty() {
        this.registrar.registerCondition(StringConditions.isNotEmpty());

        return self;
    }

    public StringValidation startsWith(String prefix) {
        this.registrar.registerCondition(StringConditions.startsWith(prefix));

        return self;
    }

    public StringValidation hasLength(int length) {
        this.registrar.registerCondition(StringConditions.hasLength(length));

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
    public StringValidation withTerm(BooleanSupplier supplier) {
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
    public <R, OTHER extends ConditionBinder<R, OTHER>> StringValidation inspectingDeeply(Function<String, R> mapper, Function<R, ConditionBinder<R, OTHER>> validator) {
        return super.inspectingDeeply(mapper, validator);
    }
}
