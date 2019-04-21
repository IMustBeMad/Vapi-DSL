package validation.api.demo.validation.domain.date;

import validation.api.demo.validation.common.SingleCondition;
import validation.api.demo.validation.domain.object.AbstractObjectValidation;

import java.time.LocalDate;
import java.util.function.Function;
import java.util.function.Predicate;

public abstract class AbstractDateValidation extends AbstractObjectValidation<LocalDate> {

    public AbstractDateValidation isAfter(LocalDate otherDate, String onError) {
        memoize(new SingleCondition<>(it -> it.isAfter(otherDate), onError));

        return this;
    }

    public AbstractDateValidation isBefore(LocalDate otherDate, String onError) {
        memoize(new SingleCondition<>(it -> it.isBefore(otherDate), onError));

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
    public AbstractDateValidation isAnyOf(SingleCondition<LocalDate> condition1, SingleCondition<LocalDate> condition2, String onError) {
        return ((AbstractDateValidation) super.isAnyOf(condition1, condition2, onError));
    }

    @Override
    public AbstractDateValidation isAllOf(SingleCondition<LocalDate> condition1, SingleCondition<LocalDate> condition2, String onError) {
        return ((AbstractDateValidation) super.isAllOf(condition1, condition2, onError));
    }

    @Override
    public AbstractDateValidation or() {
        return ((AbstractDateValidation) super.or());
    }

    @Override
    public AbstractDateValidation log(String msg, Object... values) {
        return (AbstractDateValidation) super.log(msg, values);
    }

    @Override
    public <R> AbstractDateValidation inspecting(Function<LocalDate, R> mapper, Predicate<R> predicate, String onError) {
        return (AbstractDateValidation) super.inspecting(mapper, predicate, onError);
    }

    @Override
    public <R> AbstractDateValidation inspecting(Function<LocalDate, R> mapper, Function<R, AbstractObjectValidation<R>> validator) {
        return (AbstractDateValidation) super.inspecting(mapper, validator);
    }
}
