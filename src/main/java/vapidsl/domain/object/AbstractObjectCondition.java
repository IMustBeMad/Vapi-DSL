package vapidsl.domain.object;

import vapidsl.common.SingleCondition;
import vapidsl.domain.AbstractBaseValidation;
import vapidsl.domain.object.impl.ObjectValidation;

import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

public abstract class AbstractObjectCondition<T> extends AbstractBaseValidation<T> {

    @Override
    public ObjectValidation<T> isNull() {
        return (ObjectValidation<T>) super.isNull();
    }

    @Override
    public ObjectValidation<T> isNotNull() {
        return (ObjectValidation<T>) super.isNotNull();
    }

    @Override
    public ObjectValidation<T> isEqualTo(T otherObj) {
        return (ObjectValidation<T>) super.isEqualTo(otherObj);
    }

    @Override
    public ObjectValidation<T> isNotEqualTo(T otherObj) {
        return (ObjectValidation<T>) super.isNotEqualTo(otherObj);
    }

    @Override
    public ObjectValidation<T> withTerm(Predicate<T> predicate) {
        return (ObjectValidation<T>) super.withTerm(predicate);
    }

    @Override
    public ObjectValidation<T> withTerm(Supplier<Boolean> supplier) {
        return (ObjectValidation<T>) super.withTerm(supplier);
    }

    @Override
    public ObjectValidation<T> withTerm(Function<T, AbstractBaseValidation<T>> validator) {
        return (ObjectValidation<T>) super.withTerm(validator);
    }

    @Override
    @SafeVarargs
    public final ObjectValidation<T> satisfiesAny(SingleCondition<T>... conditions) {
        return (ObjectValidation<T>) super.satisfiesAny(conditions);
    }

    @Override
    @SafeVarargs
    public final ObjectValidation<T> satisfiesAll(SingleCondition<T>... conditions) {
        return (ObjectValidation<T>) super.satisfiesAll(conditions);
    }

    @Override
    public ObjectValidation<T> log(String msg, Object... values) {
        return (ObjectValidation<T>) super.log(msg, values);
    }

    @Override
    public <R> ObjectValidation<T> inspecting(Function<T, R> mapper, Predicate<R> predicate) {
        return (ObjectValidation<T>) super.inspecting(mapper, predicate);
    }

    @Override
    public <R> ObjectValidation<T> inspecting(Function<T, R> mapper, Supplier<SingleCondition<R>> condition) {
        return (ObjectValidation<T>) super.inspecting(mapper, condition);
    }

    @Override
    public <R> ObjectValidation<T> deepInspecting(Function<T, R> mapper, Function<R, AbstractBaseValidation<R>> validator) {
        return (ObjectValidation<T>) super.deepInspecting(mapper, validator);
    }
}
