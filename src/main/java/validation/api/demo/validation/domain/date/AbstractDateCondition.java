package validation.api.demo.validation.domain.date;

import validation.api.demo.validation.common.SingleCondition;
import validation.api.demo.validation.dict.TerminationMode;
import validation.api.demo.validation.domain.AbstractBaseValidation;
import validation.api.demo.validation.domain.date.impl.DateValidation;

import java.time.LocalDate;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

public abstract class AbstractDateCondition extends AbstractBaseValidation<LocalDate> {

    public DateValidation isAfter(LocalDate otherDate, String onError) {
        registerCondition(DateConditions.isAfter(otherDate), onError);

        return (DateValidation) this;
    }

    public DateValidation isBefore(LocalDate otherDate, String onError) {
        registerCondition(DateConditions.isBefore(otherDate), onError);

        return (DateValidation) this;
    }

    @Override
    public DateValidation isNull(String onError) {
        return (DateValidation) super.isNull(onError);
    }

    @Override
    public DateValidation isNotNull(String onError) {
        return (DateValidation) super.isNotNull(onError);
    }

    @Override
    public AbstractDateCondition isEqualTo(LocalDate otherDate, String onError) {
        return (AbstractDateCondition) super.isEqualTo(otherDate, onError);
    }

    @Override
    public DateValidation isNotEqualTo(LocalDate otherDate, String onError) {
        return (DateValidation) super.isNotEqualTo(otherDate, onError);
    }

    @Override
    public DateValidation withTerm(Predicate<LocalDate> predicate, String onError) {
        return (DateValidation) super.withTerm(predicate, onError);
    }

    @Override
    public DateValidation withTerm(Supplier<Boolean> supplier, String onError) {
        return (DateValidation) super.withTerm(supplier, onError);
    }

    @Override
    public DateValidation isAnyOf(SingleCondition<LocalDate> condition1, SingleCondition<LocalDate> condition2, String onError) {
        return (DateValidation) super.isAnyOf(condition1, condition2, onError);
    }

    @Override
    public DateValidation isAllOf(SingleCondition<LocalDate> condition1, SingleCondition<LocalDate> condition2, String onError) {
        return (DateValidation) super.isAllOf(condition1, condition2, onError);
    }

    @Override
    public DateValidation log(String msg, Object... values) {
        return (DateValidation) super.log(msg, values);
    }

    @Override
    public <R> DateValidation inspecting(Function<LocalDate, R> mapper, Predicate<R> predicate, String onError) {
        return (DateValidation) super.inspecting(mapper, predicate, onError);
    }

    @Override
    public <R> DateValidation inspecting(Function<LocalDate, R> mapper, TerminationMode terminationMode, Function<R, AbstractBaseValidation<R>> validator) {
        return (DateValidation) super.inspecting(mapper, terminationMode, validator);
    }

    @Override
    public <R> DateValidation inspecting(Function<LocalDate, R> mapper, Supplier<SingleCondition<R>> condition) {
        return (DateValidation) super.inspecting(mapper, condition);
    }
}
