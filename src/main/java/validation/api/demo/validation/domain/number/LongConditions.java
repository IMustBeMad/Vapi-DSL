package validation.api.demo.validation.domain.number;

import validation.api.demo.validation.common.Condition;
import validation.api.demo.validation.domain.object.ObjectConditions;

public class LongConditions extends ObjectConditions {

    public static Condition<Long> isEqualTo(Long otherLong) {
        return new Condition<>(it -> it.compareTo(otherLong) == 0);
    }

    public static Condition<Long> isNotEqualTo(Long otherLong) {
        return new Condition<>(it -> it.compareTo(otherLong) != 0);
    }

    public static Condition<Long> isGt(Long otherLong) {
        return new Condition<>(it -> it.compareTo(otherLong) > 0);
    }

    public static Condition<Long> isGte(Long otherLong) {
        return new Condition<>(it -> it.compareTo(otherLong) >= 0);
    }

    public static Condition<Long> isLt(Long otherLong) {
        return new Condition<>(it -> it.compareTo(otherLong) < 0);
    }

    public static Condition<Long> isLte(Long otherLong) {
        return new Condition<>(it -> it.compareTo(otherLong) <= 0);
    }
}
