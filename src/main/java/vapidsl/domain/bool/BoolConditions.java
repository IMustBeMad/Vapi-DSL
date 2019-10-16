package vapidsl.domain.bool;

import vapidsl.common.SingleCondition;

public class BoolConditions {

    public static SingleCondition<Boolean> isTrue() {
        return new SingleCondition<>(it -> it);
    }

    public static SingleCondition<Boolean> isFalse() {
        return new SingleCondition<>(it -> !it);
    }
}
