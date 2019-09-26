package validation.domain;

import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import validation.common.LinkedCondition;
import validation.common.SingleCondition;
import validation.dict.Clause;
import validation.domain.object.ObjectConditions;
import validation.common.ConditionCluster;

import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

@Slf4j
public abstract class AbstractBaseValidation<T> extends BaseDataHolder<T> {

    private static final Logger LOGGER = LogManager.getLogger(AbstractBaseValidation.class);

    protected AbstractBaseValidation<T> isNull() {
        this.registerCondition(ObjectConditions.isNull());

        return this;
    }

    protected AbstractBaseValidation<T> isNotNull() {
        this.registerCondition(ObjectConditions.isNotNull());

        return this;
    }

    protected AbstractBaseValidation<T> isEqualTo(T otherObj) {
        this.registerCondition(ObjectConditions.isEqualTo(otherObj));

        return this;
    }

    protected AbstractBaseValidation<T> isNotEqualTo(T otherObj) {
        this.registerCondition(ObjectConditions.isNotEqualTo(otherObj));

        return this;
    }

    protected AbstractBaseValidation<T> withTerm(Predicate<T> predicate) {
        this.memoize(new SingleCondition<>(predicate));

        return this;
    }

    protected AbstractBaseValidation<T> withTerm(Supplier<Boolean> supplier) {
        this.memoize(new SingleCondition<>(it -> supplier.get()));

        return this;
    }

    protected AbstractBaseValidation<T> withTerm(Function<T, AbstractBaseValidation<T>> validator) {
        return this.inspect(this.obj, validator);
    }

    protected AbstractBaseValidation<T> isAnyOf(SingleCondition<T>... conditions) {
        this.memoize(new LinkedCondition<>(List.of(conditions), Clause.OR));

        return this;
    }

    protected AbstractBaseValidation<T> isAllOf(SingleCondition<T>... conditions) {
        this.memoize(new LinkedCondition<>(List.of(conditions), Clause.AND));

        return this;
    }

    protected AbstractBaseValidation<T> or() {
        this.registerCluster();

        return this;
    }

    protected AbstractBaseValidation<T> log(String msg, Object... values) {
        LOGGER.debug(msg, values);

        return this;
    }

    protected <R> AbstractBaseValidation<T> inspecting(Function<T, R> mapper, Predicate<R> predicate) {
        this.memoize(this.toCondition(mapper.apply(this.obj), predicate));

        return this;
    }

    protected <R> AbstractBaseValidation<T> inspecting(Function<T, R> mapper, Supplier<SingleCondition<R>> condition) {
        this.memoize(new SingleCondition<>(it -> condition.get().getPredicate().test(mapper.apply((T) it))));

        return this;
    }

    protected <R> AbstractBaseValidation<T> deepInspecting(Function<T, R> mapper, Function<R, AbstractBaseValidation<R>> validator) {
        return this.inspect(mapper.apply(this.obj), validator);
    }

    /**
     * Adds an {@code String} error to the current condition, so it will be used when condition is considered to be failed.
     *
     * @param error {@code String} message to use.
     * @return {@code this} validation object.
     */
    protected AbstractBaseValidation<T> onError(String error) {
        this.getCurrentCondition().setOnError(error);

        return this;
    }

    /**
     * Adds an {@code String} error to the current condition group {@link ConditionCluster},
     * so it will be used when condition group is considered to be failed.
     * <p>
     * Overrides {@link #onError(String)} value for the whole group.
     *
     * @param error {@code String} message to use.
     * @return {@code this} validation object.
     */
    protected AbstractBaseValidation<T> groupError(String error) {
        this.getCurrentCluster().setOnError(error);

        return this;
    }

    private <R> AbstractBaseValidation<T> inspect(R obj, Function<R, AbstractBaseValidation<R>> validator) {
        this.memoize(this.toCondition(obj, validator));

        return this;
    }
}
