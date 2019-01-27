package validation.api.demo.validation.domain.object;

import lombok.AllArgsConstructor;
import validation.api.demo.common.Condition;
import validation.api.demo.validation.domain.ValidationHolder;

import java.util.Objects;

@AllArgsConstructor
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
}
