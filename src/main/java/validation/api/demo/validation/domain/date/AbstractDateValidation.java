package validation.api.demo.validation.domain.date;

import validation.api.demo.common.Condition;
import validation.api.demo.validation.domain.object.AbstractObjectValidation;

import java.time.LocalDate;
import java.util.function.Function;
import java.util.function.Predicate;

public abstract class AbstractDateValidation extends AbstractObjectValidation<LocalDate> {

    public AbstractDateValidation isAfter(LocalDate otherDate, String onError) {
        memoize(new Condition<>(it -> it.isAfter(otherDate), onError));

        return this;
    }

    public AbstractDateValidation isBefore(LocalDate otherDate, String onError) {
        memoize(new Condition<>(it -> it.isBefore(otherDate), onError));

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
    public AbstractDateValidation isEqualTo(LocalDate otherDate, String onError) {
        return (AbstractDateValidation) super.isEqualTo(otherDate, onError);
    }

    @Override
    public AbstractDateValidation isNotEqualTo(LocalDate otherDate, String onError) {
        return (AbstractDateValidation) super.isNotEqualTo(otherDate, onError);
    }

    @Override
    public AbstractDateValidation withTerm(Predicate<LocalDate> predicate, String onError) {
        return (AbstractDateValidation) super.withTerm(predicate, onError);
    }

    @Override
    public <R> AbstractDateValidation inspecting(Function<LocalDate, R> mapper, Predicate<R> predicate, String onError) {
        return (AbstractDateValidation) super.inspecting(mapper, predicate, onError);
    }
}
