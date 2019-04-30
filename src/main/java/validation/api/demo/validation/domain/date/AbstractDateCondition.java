package validation.api.demo.validation.domain.date;

import validation.api.demo.validation.common.SingleCondition;
import validation.api.demo.validation.domain.AbstractBaseValidation;

import java.time.LocalDate;
import java.util.function.Function;
import java.util.function.Predicate;

public abstract class AbstractDateCondition extends AbstractBaseValidation<LocalDate> {

    public AbstractDateCondition isAfter(LocalDate otherDate, String onError) {
        registerCondition(DateConditions.isAfter(otherDate), onError);

        return this;
    }

    public AbstractDateCondition isBefore(LocalDate otherDate, String onError) {
        registerCondition(DateConditions.isBefore(otherDate), onError);

        return this;
    }

    @Override
    public AbstractDateCondition isNull(String onError) {
        return (AbstractDateCondition) super.isNull(onError);
    }

    @Override
    public AbstractDateCondition isNotNull(String onError) {
        return (AbstractDateCondition) super.isNotNull(onError);
    }

    @Override
    public AbstractDateCondition isEqualTo(LocalDate otherDate, String onError) {
        return (AbstractDateCondition) super.isEqualTo(otherDate, onError);
    }

    @Override
    public AbstractDateCondition isNotEqualTo(LocalDate otherDate, String onError) {
        return (AbstractDateCondition) super.isNotEqualTo(otherDate, onError);
    }

    @Override
    public AbstractDateCondition withTerm(Predicate<LocalDate> predicate, String onError) {
        return (AbstractDateCondition) super.withTerm(predicate, onError);
    }

    @Override
    public AbstractDateCondition isAnyOf(SingleCondition<LocalDate> condition1, SingleCondition<LocalDate> condition2, String onError) {
        return (AbstractDateCondition) super.isAnyOf(condition1, condition2, onError);
    }

    @Override
    public AbstractDateCondition isAllOf(SingleCondition<LocalDate> condition1, SingleCondition<LocalDate> condition2, String onError) {
        return (AbstractDateCondition) super.isAllOf(condition1, condition2, onError);
    }

    @Override
    public AbstractDateCondition log(String msg, Object... values) {
        return (AbstractDateCondition) super.log(msg, values);
    }

    @Override
    public <R> AbstractDateCondition inspecting(Function<LocalDate, R> mapper, Predicate<R> predicate, String onError) {
        return (AbstractDateCondition) super.inspecting(mapper, predicate, onError);
    }

    @Override
    public <R> AbstractDateCondition inspecting(Function<LocalDate, R> mapper, Function<R, AbstractBaseValidation<R>> validator) {
        return (AbstractDateCondition) super.inspecting(mapper, validator);
    }
}
