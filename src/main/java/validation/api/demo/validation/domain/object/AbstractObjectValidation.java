package validation.api.demo.validation.domain.object;

import lombok.AllArgsConstructor;
import validation.api.demo.common.Condition;
import validation.api.demo.validation.domain.ValidationHolder;

import java.util.Objects;

@AllArgsConstructor
public abstract class AbstractObjectValidation extends ValidationHolder<Object> {

    public AbstractObjectValidation isNull(String onError) {
        memoize(new Condition<>(Objects::isNull, onError));

        return this;
    }

    public AbstractObjectValidation isNotNull(String onError) {
        memoize(new Condition<>(Objects::nonNull, onError));

        return this;
    }

    public AbstractObjectValidation isEqualTo(Object otherObj, String onError) {
        memoize(new Condition<>(it -> it.equals(otherObj), onError));

        return this;
    }

    public AbstractObjectValidation isNotEqualTo(Object otherObj, String onError) {
        memoize(new Condition<>(it -> !it.equals(otherObj), onError));

        return this;
    }
}
