package validation.api.demo.validation.domain.object;

import validation.api.demo.common.Condition;
import validation.api.demo.validation.domain.ValidationHolder;

import java.util.Objects;
import java.util.function.Function;
import java.util.function.Predicate;

public abstract class AbstractObjectValidation<T> extends ValidationHolder<T> {

    public AbstractObjectValidation<T> isNull(String onError) {
        memoize(new Condition<>(Objects::isNull, onError));

        return this;
    }

    public AbstractObjectValidation<T> isNotNull(String onError) {
        memoize(new Condition<>(Objects::nonNull, onError));

        return this;
    }

    public AbstractObjectValidation<T> isEqualTo(T otherObj, String onError) {
        memoize(new Condition<>(it -> it.equals(otherObj), onError));

        return this;
    }

    public AbstractObjectValidation<T> isNotEqualTo(T otherObj, String onError) {
        memoize(new Condition<>(it -> !it.equals(otherObj), onError));

        return this;
    }

    public AbstractObjectValidation<T> withTerm(Predicate<T> predicate, String onError) {
        memoize(new Condition<>(predicate, onError));

        return this;
    }

    public <R> AbstractObjectValidation<T> inspecting(Function<T, R> mapper, Predicate<R> predicate, String onError) {
        memoize(new Condition<>(it -> predicate.test(mapper.apply((T) it)), onError));

        return this;
    }
}
