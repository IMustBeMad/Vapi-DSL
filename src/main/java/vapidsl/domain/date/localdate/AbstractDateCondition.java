package vapidsl.domain.date.localdate;

import vapidsl.common.SingleCondition;
import vapidsl.domain.AbstractBaseValidation;
import vapidsl.domain.date.localdate.impl.DateValidation;

import java.time.LocalDate;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

public abstract class AbstractDateCondition extends AbstractBaseValidation<LocalDate, DateValidation> {

    AbstractDateCondition(Class<?> selfType) {
        super(selfType);
    }

    public DateValidation isAfter(LocalDate otherDate) {
        this.registerCondition(DateConditions.isAfter(otherDate));

        return self;
    }

    public DateValidation isBefore(LocalDate otherDate) {
        this.registerCondition(DateConditions.isBefore(otherDate));

        return self;
    }

    public DateValidation isAfterOrEqual(LocalDate otherDate) {
        this.registerCondition(DateConditions.isAfterOrEqual(otherDate));

        return self;
    }

    public DateValidation isBeforeOrEqual(LocalDate otherDate) {
        this.registerCondition(DateConditions.isBeforeOrEqual(otherDate));

        return self;
    }

    @Override
    public DateValidation isNull() {
        return super.isNull();
    }

    @Override
    public DateValidation isNotNull() {
        return super.isNotNull();
    }

    @Override
    public DateValidation isEqualTo(LocalDate otherDate) {
        return super.isEqualTo(otherDate);
    }

    @Override
    public DateValidation isNotEqualTo(LocalDate otherDate) {
        return super.isNotEqualTo(otherDate);
    }

    @Override
    public DateValidation withTerm(Predicate<LocalDate> predicate) {
        return super.withTerm(predicate);
    }

    @Override
    public DateValidation withTerm(Supplier<Boolean> supplier) {
        return super.withTerm(supplier);
    }

    @Override
    public DateValidation withTermDeeply(Function<LocalDate, AbstractBaseValidation<LocalDate, DateValidation>> validator) {
        return super.withTermDeeply(validator);
    }

    @Override
    @SafeVarargs
    public final DateValidation satisfiesAny(SingleCondition<LocalDate>... conditions) {
        return super.satisfiesAny(conditions);
    }

    @Override
    @SafeVarargs
    public final DateValidation satisfiesEvery(SingleCondition<LocalDate>... conditions) {
        return super.satisfiesEvery(conditions);
    }

    @Override
    public DateValidation log(String msg, Object... values) {
        return super.log(msg, values);
    }

    @Override
    public <R> DateValidation inspecting(Function<LocalDate, R> mapper, Predicate<R> predicate) {
        return super.inspecting(mapper, predicate);
    }

    @Override
    public <R> DateValidation inspecting(Function<LocalDate, R> mapper, Supplier<SingleCondition<R>> condition) {
        return super.inspecting(mapper, condition);
    }

    @Override
    public <R, OTHER extends AbstractBaseValidation<R, OTHER>> DateValidation inspectingDeeply(Function<LocalDate, R> mapper, Function<R, AbstractBaseValidation<R, OTHER>> validator) {
        return super.inspectingDeeply(mapper, validator);
    }
}
