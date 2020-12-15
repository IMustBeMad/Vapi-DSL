package vapidsl.domain.date.localdate;

import vapidsl.common.SingleCondition;
import vapidsl.domain.ConditionBinder;
import vapidsl.domain.date.localdate.impl.LocalDateValidation;

import java.time.LocalDate;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

public abstract class LocalDateConditionBinder extends ConditionBinder<LocalDate, LocalDateValidation> {

    LocalDateConditionBinder(Class<?> selfType) {
        super(selfType);
    }

    public LocalDateValidation isAfter(LocalDate otherDate) {
        this.registrar.registerCondition(LocalDateConditions.isAfter(otherDate));

        return self;
    }

    public LocalDateValidation isBefore(LocalDate otherDate) {
        this.registrar.registerCondition(LocalDateConditions.isBefore(otherDate));

        return self;
    }

    public LocalDateValidation isAfterOrEqual(LocalDate otherDate) {
        this.registrar.registerCondition(LocalDateConditions.isAfterOrEqual(otherDate));

        return self;
    }

    public LocalDateValidation isBeforeOrEqual(LocalDate otherDate) {
        this.registrar.registerCondition(LocalDateConditions.isBeforeOrEqual(otherDate));

        return self;
    }

    @Override
    public LocalDateValidation isNull() {
        return super.isNull();
    }

    @Override
    public LocalDateValidation isNotNull() {
        return super.isNotNull();
    }

    @Override
    public LocalDateValidation isEqualTo(LocalDate otherDate) {
        return super.isEqualTo(otherDate);
    }

    @Override
    public LocalDateValidation isNotEqualTo(LocalDate otherDate) {
        return super.isNotEqualTo(otherDate);
    }

    @Override
    public LocalDateValidation withTerm(Predicate<LocalDate> predicate) {
        return super.withTerm(predicate);
    }

    @Override
    public LocalDateValidation withTerm(Supplier<Boolean> supplier) {
        return super.withTerm(supplier);
    }

    @Override
    public LocalDateValidation withTermDeeply(Function<LocalDate, ConditionBinder<LocalDate, LocalDateValidation>> validator) {
        return super.withTermDeeply(validator);
    }

    @Override
    @SafeVarargs
    public final LocalDateValidation satisfiesAny(SingleCondition<LocalDate>... conditions) {
        return super.satisfiesAny(conditions);
    }

    @Override
    @SafeVarargs
    public final LocalDateValidation satisfiesEvery(SingleCondition<LocalDate>... conditions) {
        return super.satisfiesEvery(conditions);
    }

    @Override
    public LocalDateValidation log(String msg, Object... values) {
        return super.log(msg, values);
    }

    @Override
    public <R> LocalDateValidation inspecting(Function<LocalDate, R> mapper, Predicate<R> predicate) {
        return super.inspecting(mapper, predicate);
    }

    @Override
    public <R> LocalDateValidation inspecting(Function<LocalDate, R> mapper, Supplier<SingleCondition<R>> condition) {
        return super.inspecting(mapper, condition);
    }

    @Override
    public <R, OTHER extends ConditionBinder<R, OTHER>> LocalDateValidation inspectingDeeply(Function<LocalDate, R> mapper, Function<R, ConditionBinder<R, OTHER>> validator) {
        return super.inspectingDeeply(mapper, validator);
    }
}
