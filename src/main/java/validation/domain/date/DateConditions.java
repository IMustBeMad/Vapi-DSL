package validation.domain.date;

import validation.common.SingleCondition;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class DateConditions {

    public static SingleCondition<LocalDate> isAfter(LocalDate otherDate) {
        return new SingleCondition<>(it -> it.isAfter(otherDate));
    }

    public static SingleCondition<LocalDate> isBefore(LocalDate otherDate) {
        return new SingleCondition<>(it -> it.isBefore(otherDate));
    }

    public static SingleCondition<LocalDate> isAfterOrEqual(LocalDate otherDate) {
        return new SingleCondition<>(it -> it.isAfter(otherDate) || it.isEqual(otherDate));
    }

    public static SingleCondition<LocalDate> isBeforeOrEqual(LocalDate otherDate) {
        return new SingleCondition<>(it -> it.isBefore(otherDate) || it.isEqual(otherDate));
    }

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
