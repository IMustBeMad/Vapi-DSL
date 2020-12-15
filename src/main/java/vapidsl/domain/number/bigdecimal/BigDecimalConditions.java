package vapidsl.domain.number.bigdecimal;

import lombok.experimental.UtilityClass;
import vapidsl.common.SingleCondition;

import java.math.BigDecimal;

@UtilityClass
public class BigDecimalConditions {

    public static SingleCondition<BigDecimal> isEqualTo(BigDecimal otherBigDecimal) {
        return new SingleCondition<>(it -> it.compareTo(otherBigDecimal) == 0);
    }

    public static SingleCondition<BigDecimal> isNotEqualTo(BigDecimal otherBigDecimal) {
        return new SingleCondition<>(it -> it.compareTo(otherBigDecimal) != 0);
    }

    public static SingleCondition<BigDecimal> isGt(BigDecimal otherBigDecimal) {
        return new SingleCondition<>(it -> it.compareTo(otherBigDecimal) > 0);
    }

    public static SingleCondition<BigDecimal> isGte(BigDecimal otherBigDecimal) {
        return new SingleCondition<>(it -> it.compareTo(otherBigDecimal) >= 0);
    }

    public static SingleCondition<BigDecimal> isLt(BigDecimal otherBigDecimal) {
        return new SingleCondition<>(it -> it.compareTo(otherBigDecimal) < 0);
    }

    public static SingleCondition<BigDecimal> isLte(BigDecimal otherBigDecimal) {
        return new SingleCondition<>(it -> it.compareTo(otherBigDecimal) <= 0);
    }
}
