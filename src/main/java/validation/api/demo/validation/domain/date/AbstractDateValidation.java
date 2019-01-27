package validation.api.demo.validation.domain.date;

import lombok.AllArgsConstructor;
import validation.api.demo.common.Condition;
import validation.api.demo.validation.domain.object.AbstractObjectValidation;

import java.time.LocalDate;

@AllArgsConstructor
public abstract class AbstractDateValidation extends AbstractObjectValidation<LocalDate> {

    public AbstractDateValidation isGt(LocalDate otherDate, String onError) {
        memoize(new Condition<>(it -> it.isAfter(otherDate), onError));

        return this;
    }

    @Override
    public AbstractDateValidation isNull(String onError) {
        return (AbstractDateValidation) super.isNull(onError);
    }

    @Override
    public AbstractDateValidation isNotNull(String onError) {
        return (AbstractDateValidation) super.isNotNull(onError);
    }

    @Override
    public AbstractDateValidation isEqualTo(LocalDate otherObj, String onError) {
        return (AbstractDateValidation) super.isEqualTo(otherObj, onError);
    }

    @Override
    public AbstractDateValidation isNotEqualTo(LocalDate otherObj, String onError) {
        return (AbstractDateValidation) super.isNotEqualTo(otherObj, onError);
    }
}
