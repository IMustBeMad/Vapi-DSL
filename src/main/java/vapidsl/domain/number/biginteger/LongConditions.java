package vapidsl.domain.number.biginteger;

import lombok.experimental.UtilityClass;
import vapidsl.common.SingleCondition;

@UtilityClass
public class LongConditions {

    public static SingleCondition<Long> isEqualTo(Long otherLong) {
        return new SingleCondition<>(it -> it.compareTo(otherLong) == 0);
    }

    public static SingleCondition<Long> isNotEqualTo(Long otherLong) {
        return new SingleCondition<>(it -> it.compareTo(otherLong) != 0);
    }

    public static SingleCondition<Long> isGt(Long otherLong) {
        return new SingleCondition<>(it -> it.compareTo(otherLong) > 0);
    }

    public static SingleCondition<Long> isGte(Long otherLong) {
        return new SingleCondition<>(it -> it.compareTo(otherLong) >= 0);
    }

    public static SingleCondition<Long> isLt(Long otherLong) {
        return new SingleCondition<>(it -> it.compareTo(otherLong) < 0);
    }

    public static SingleCondition<Long> isLte(Long otherLong) {
        return new SingleCondition<>(it -> it.compareTo(otherLong) <= 0);
    }
}
