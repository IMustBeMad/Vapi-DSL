package vapidsl.domain.object;

import lombok.experimental.UtilityClass;
import vapidsl.common.SingleCondition;
import vapidsl.dict.FlowType;

import java.util.Objects;

@UtilityClass
public class ObjectConditions {

    public static <T> SingleCondition<T> isNull() {
        return new SingleCondition<>(Objects::isNull);
    }

    public static <T> SingleCondition<T> isNotNull() {
        return new SingleCondition<>(Objects::nonNull, FlowType.EARLY_EXIT);
    }

    public static <T> SingleCondition<T> isEqualTo(T otherObj) {
        return new SingleCondition<>(it -> it.equals(otherObj));
    }

    public static <T> SingleCondition<T> isNotEqualTo(T otherObj) {
        return new SingleCondition<>(it -> !it.equals(otherObj));
    }
}
