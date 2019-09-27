package vapidsl.domain.date;

import vapidsl.common.SingleCondition;
import vapidsl.domain.AbstractBaseValidation;
import vapidsl.domain.date.impl.DateValidation;

import java.time.LocalDate;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

public abstract class AbstractDateCondition extends AbstractBaseValidation<LocalDate> {

    public DateValidation isAfter(LocalDate otherDate) {
        this.registerCondition(DateConditions.isAfter(otherDate));

        return self();
    }

    public DateValidation isBefore(LocalDate otherDate) {
        this.registerCondition(DateConditions.isBefore(otherDate));

        return self();
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
    public DateValidation isEqualTo(LocalDate otherDate) {
        return (DateValidation) super.isEqualTo(otherDate);
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
    @SafeVarargs
    public final DateValidation isAnyOf(SingleCondition<LocalDate>... conditions) {
        return (DateValidation) super.isAnyOf(conditions);
    }

    @Override
    @SafeVarargs
    public final DateValidation isAllOf(SingleCondition<LocalDate>... conditions) {
        return (DateValidation) super.isAllOf(conditions);
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

    private DateValidation self() {
        return (DateValidation) this;
    }
}
