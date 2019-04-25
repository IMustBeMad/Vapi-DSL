package validation.api.demo.validation.domain.object;

import validation.api.demo.validation.common.SingleCondition;

import java.util.Objects;

public class ObjectConditions {

    public static <T> SingleCondition<T> isNull() {
        return new SingleCondition<>(Objects::isNull);
    }

    public static <T> SingleCondition<T> isNotNull() {
        return new SingleCondition<>(Objects::nonNull);
    }

    public static <T> SingleCondition<T> isEqualTo(T otherObj) {
        return new SingleCondition<>(it -> it.equals(otherObj));
    }

    public static <T> SingleCondition<T> isNotEqualTo(T otherObj) {
        return new SingleCondition<>(it -> !it.equals(otherObj));
    }
}