package validation.api.demo.validation.domain.object;

import validation.api.demo.validation.common.Condition;

import java.util.Objects;

public class ObjectConditions {

    public static <T> Condition<T> isNull() {
        return new Condition<>(Objects::isNull);
    }

    public static <T> Condition<T> isNotNull() {
        return new Condition<>(Objects::nonNull);
    }

    public static <T> Condition<T> isEqualTo(T otherObj) {
        return new Condition<>(it -> it.equals(otherObj));
    }

    public static <T> Condition<T> isNotEqualTo(T otherObj) {
        return new Condition<>(it -> !it.equals(otherObj));
    }
}
