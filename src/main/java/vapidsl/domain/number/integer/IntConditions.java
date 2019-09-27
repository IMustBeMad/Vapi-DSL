package vapidsl.domain.number.integer;

import vapidsl.common.SingleCondition;

public class IntConditions {

    public static SingleCondition<Integer> isEqualTo(Integer otherInt) {
        return new SingleCondition<>(it -> it.compareTo(otherInt) == 0);
    }

    public static SingleCondition<Integer> isNotEqualTo(Integer otherInt) {
        return new SingleCondition<>(it -> it.compareTo(otherInt) != 0);
    }

    public static SingleCondition<Integer> isGt(Integer otherInt) {
        return new SingleCondition<>(it -> it.compareTo(otherInt) > 0);
    }

    public static SingleCondition<Integer> isGte(Integer otherInt) {
        return new SingleCondition<>(it -> it.compareTo(otherInt) >= 0);
    }

    public static SingleCondition<Integer> isLt(Integer otherInt) {
        return new SingleCondition<>(it -> it.compareTo(otherInt) < 0);
    }

    public static SingleCondition<Integer> isLte(Integer otherInt) {
        return new SingleCondition<>(it -> it.compareTo(otherInt) <= 0);
    }
}
