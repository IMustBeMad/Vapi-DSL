package vapidsl.domain.bool;

import lombok.experimental.UtilityClass;
import vapidsl.common.SingleCondition;

@UtilityClass
public class BoolConditions {

    public static SingleCondition<Boolean> isTrue() {
        return new SingleCondition<>(it -> it);
    }

    public static SingleCondition<Boolean> isFalse() {
        return new SingleCondition<>(it -> !it);
    }
}
