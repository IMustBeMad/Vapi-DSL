package validation.api.demo.validation.domain.date;

import lombok.AllArgsConstructor;
import validation.api.demo.common.Condition;
import validation.api.demo.validation.domain.ValidationHolder;

import java.time.LocalDate;

@AllArgsConstructor
public abstract class AbstractDateValidation extends ValidationHolder<LocalDate> {

    public AbstractDateValidation isGt(LocalDate otherDate, String onError) {
        memoize(new Condition<>(it -> it.isAfter(otherDate), onError));

        return this;
    }
}
