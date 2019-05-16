package validation.api.demo.validation.domain.date;

import validation.api.demo.validation.common.SingleCondition;
import validation.api.demo.validation.dict.ErrorMode;
import validation.api.demo.validation.dict.TerminationMode;
import validation.api.demo.validation.domain.AbstractBaseValidation;
import validation.api.demo.validation.domain.date.impl.DateValidation;

import java.time.LocalDate;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

public abstract class AbstractDateCondition extends AbstractBaseValidation<LocalDate> {

    public DateValidation isAfter(LocalDate otherDate) {
        registerCondition(DateConditions.isAfter(otherDate));

        return (DateValidation) this;
    }

    public DateValidation isBefore(LocalDate otherDate) {
        registerCondition(DateConditions.isBefore(otherDate));

        return (DateValidation) this;
    }

    @Override
    public DateValidation isNull() {
        return (DateValidation) super.isNull();
    }

    @Override
    public DateValidation isNotNull() {
        return (DateValidation) super.isNotNull();
    }

    @Override
    public AbstractDateCondition isEqualTo(LocalDate otherDate) {
        return (AbstractDateCondition) super.isEqualTo(otherDate);
    }

    @Override
    public DateValidation isNotEqualTo(LocalDate otherDate) {
        return (DateValidation) super.isNotEqualTo(otherDate);
    }

    @Override
    public DateValidation withTerm(Predicate<LocalDate> predicate) {
        return (DateValidation) super.withTerm(predicate);
    }

    @Override
    public DateValidation withTerm(Supplier<Boolean> supplier) {
        return (DateValidation) super.withTerm(supplier);
    }

    @Override
    public DateValidation isAnyOf(SingleCondition<LocalDate> condition1, SingleCondition<LocalDate> condition2) {
        return (DateValidation) super.isAnyOf(condition1, condition2);
    }

    @Override
    public DateValidation isAllOf(SingleCondition<LocalDate> condition1, SingleCondition<LocalDate> condition2) {
        return (DateValidation) super.isAllOf(condition1, condition2);
    }

    @Override
    public DateValidation log(String msg, Object... values) {
        return (DateValidation) super.log(msg, values);
    }

    @Override
    public <R> DateValidation inspecting(Function<LocalDate, R> mapper, Predicate<R> predicate) {
        return (DateValidation) super.inspecting(mapper, predicate);
    }

    @Override
    public <R> DateValidation inspecting(Function<LocalDate, R> mapper, Supplier<SingleCondition<R>> condition) {
        return (DateValidation) super.inspecting(mapper, condition);
    }

    @Override
    public <R> DateValidation deepInspecting(Function<LocalDate, R> mapper, Function<R, AbstractBaseValidation<R>> validator) {
        return (DateValidation) super.deepInspecting(mapper, validator);
    }

    @Override
    public DateValidation failOn(TerminationMode terminationMode) {
        return (DateValidation) super.failOn(terminationMode);
    }

    @Override
    public DateValidation failOn(TerminationMode terminationMode, ErrorMode errorMode) {
        return (DateValidation) super.failOn(terminationMode, errorMode);
    }
}
