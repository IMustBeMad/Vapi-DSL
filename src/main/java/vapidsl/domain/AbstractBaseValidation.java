package vapidsl.domain;

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
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

public abstract class AbstractBaseValidation<T, SELF extends AbstractBaseValidation<T, SELF>> extends BaseDataHolder<T, SELF> {

    private static final Logger LOGGER = LogManager.getLogger(AbstractBaseValidation.class);

    protected AbstractBaseValidation(Class<?> selfType) {
        super(selfType);
    }

    protected SELF isNull() {
        this.registerCondition(ObjectConditions.isNull());

        return self;
    }

    protected SELF isNotNull() {
        this.registerCondition(ObjectConditions.isNotNull());

        return self;
    }

    protected SELF isEqualTo(T otherObj) {
        this.registerCondition(ObjectConditions.isEqualTo(otherObj));

        return self;
    }

    protected SELF isNotEqualTo(T otherObj) {
        this.registerCondition(ObjectConditions.isNotEqualTo(otherObj));

        return self;
    }

    protected SELF withTerm(Predicate<T> predicate) {
        this.memoize(new SingleCondition<>(predicate));

        return self;
    }

    protected SELF withTerm(Supplier<Boolean> supplier) {
        this.memoize(new SingleCondition<>(it -> supplier.get()));

        return self;
    }

    protected SELF withTerm(Function<T, AbstractBaseValidation<T, SELF>> validator) {
        this.memoize(this.toCondition(() -> validator.apply(this.obj)));

        return self;
    }

    protected SELF satisfiesAny(SingleCondition<T>... conditions) {
        this.memoize(new LinkedCondition<>(() -> List.of(conditions), Clause.OR));

        return self;
    }

    protected SELF satisfiesAll(SingleCondition<T>... conditions) {
        this.memoize(new LinkedCondition<>(() -> List.of(conditions), Clause.AND));

        return self;
    }

    protected AbstractBaseValidation<T, SELF> or() {
        this.registerCluster();

        return self;
    }

    protected SELF log(String msg, Object... values) {
        LOGGER.debug(msg, values);

        return self;
    }

    protected <R> SELF inspecting(Function<T, R> mapper, Predicate<R> predicate) {
        this.memoize(new SingleCondition<>(it -> predicate.test(mapper.apply((T) it))));

        return self;
    }

    protected <R> SELF inspecting(Function<T, R> mapper, Supplier<SingleCondition<R>> condition) {
        this.memoize(new SingleCondition<>(it -> condition.get().getPredicate().test(mapper.apply((T) it))));

        return self;
    }

    protected <R, OTHER extends AbstractBaseValidation<R, OTHER>> SELF deepInspecting(Function<T, R> mapper, Function<R, AbstractBaseValidation<R, OTHER>> validator) {
        this.memoize(this.toCondition(() -> validator.apply(mapper.apply(this.obj))));

        return self;
    }

    /**
     * Adds an {@code String} error to the current condition, so it will be used when condition is considered to be failed.
     *
     * @param error {@code String} message to use.
     * @return {@code this} validation object.
     */
    protected SELF onError(String error) {
        this.getCurrentCondition().setOnError(Collections.singletonList(ValidationError.withCode(error)));

        return self;
    }

    /**
     * @see #onError(String) ()
     */
    protected SELF onError(String field, String error) {
        this.getCurrentCondition().setOnError(Collections.singletonList(ValidationError.of(field, error)));

        return self;
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
    protected SELF groupError(String error) {
        this.getCurrentCluster().setOnError(ValidationError.of("group", error));

        return self;
    }
}
