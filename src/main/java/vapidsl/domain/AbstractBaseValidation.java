package vapidsl.domain;

import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import vapidsl.common.ConditionCluster;
import vapidsl.common.LinkedCondition;
import vapidsl.common.SingleCondition;
import vapidsl.common.ValidationError;
import vapidsl.dict.Clause;
import vapidsl.domain.object.ObjectConditions;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
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

    protected AbstractBaseValidation<T> satisfiesAny(SingleCondition<T>... conditions) {
        this.memoize(new LinkedCondition<>(List.of(conditions), Clause.OR));

        return this;
    }

    protected AbstractBaseValidation<T> satisfiesAll(SingleCondition<T>... conditions) {
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
        R mapped = Optional.ofNullable(this.obj)
                           .map(mapper)
                           .orElse(null);

        this.memoize(this.toCondition(mapped, predicate));

        return this;
    }

    protected <R> AbstractBaseValidation<T> inspecting(Function<T, R> mapper, Supplier<SingleCondition<R>> condition) {
        this.memoize(new SingleCondition<>(it -> condition.get().getPredicate().test(mapper.apply((T) it))));

        return this;
    }

    protected <R> AbstractBaseValidation<T> deepInspecting(Function<T, R> mapper, Function<R, AbstractBaseValidation<R>> validator) {
        R mapped = Optional.ofNullable(this.obj)
                           .map(mapper)
                           .orElse(null);

        return this.inspect(mapped, validator);
    }

    /**
     * Adds an {@code String} error to the current condition, so it will be used when condition is considered to be failed.
     *
     * @param error {@code String} message to use.
     * @return {@code this} validation object.
     */
    protected AbstractBaseValidation<T> onError(String error) {
        this.getCurrentCondition().setOnError(Collections.singletonList(ValidationError.withCode(error)));

        return this;
    }

    /**
     * @see #onError(String) ()
     */
    protected AbstractBaseValidation<T> onError(String field, String error) {
        this.getCurrentCondition().setOnError(Collections.singletonList(ValidationError.of(field, error)));

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
        this.getCurrentCluster().setOnError(ValidationError.of("group", error));

        return this;
    }

    private <R> AbstractBaseValidation<T> inspect(R obj, Function<R, AbstractBaseValidation<R>> validator) {
        this.memoize(this.toCondition(obj, validator));

        return this;
    }
}
