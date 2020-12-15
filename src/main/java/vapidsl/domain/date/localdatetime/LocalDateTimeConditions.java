package vapidsl.domain.date.localdatetime;

import lombok.experimental.UtilityClass;
import vapidsl.common.SingleCondition;

import java.time.LocalDateTime;

@UtilityClass
public class LocalDateTimeConditions {

    public static SingleCondition<LocalDateTime> isAfter(LocalDateTime otherDate) {
        return new SingleCondition<>(it -> it.isAfter(otherDate));
    }

    public static SingleCondition<LocalDateTime> isBefore(LocalDateTime otherDate) {
        return new SingleCondition<>(it -> it.isBefore(otherDate));
    }

    public static SingleCondition<LocalDateTime> isAfterOrEqual(LocalDateTime otherDate) {
        return new SingleCondition<>(it -> it.isAfter(otherDate) || it.isEqual(otherDate));
    }

    public static SingleCondition<LocalDateTime> isBeforeOrEqual(LocalDateTime otherDate) {
        return new SingleCondition<>(it -> it.isBefore(otherDate) || it.isEqual(otherDate));
    }
}
