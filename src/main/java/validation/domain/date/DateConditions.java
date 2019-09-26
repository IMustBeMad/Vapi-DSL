package validation.domain.date;

import validation.common.SingleCondition;

import java.time.LocalDate;

public class DateConditions {

    public static SingleCondition<LocalDate> isAfter(LocalDate otherDate) {
        return new SingleCondition<>(it -> it.isAfter(otherDate));
    }

    public static SingleCondition<LocalDate> isBefore(LocalDate otherDate) {
        return new SingleCondition<>(it -> it.isBefore(otherDate));
    }
}
