package validation.api.demo.validation.domain;

import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import validation.api.demo.validation.common.LinkedCondition;
import validation.api.demo.validation.common.SingleCondition;
import validation.api.demo.validation.dict.Clause;
import validation.api.demo.validation.dict.ErrorMode;
import validation.api.demo.validation.dict.TerminationMode;
import validation.api.demo.validation.domain.object.ObjectConditions;

import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

@Slf4j
public abstract class AbstractBaseValidation<T> extends BaseDataHolder<T> {

    private static final Logger LOGGER = LogManager.getLogger(AbstractBaseValidation.class);

    protected AbstractBaseValidation<T> isNull() {
        registerCondition(ObjectConditions.isNull());

        return this;
    }

    protected AbstractBaseValidation<T> isNotNull() {
        registerCondition(ObjectConditions.isNotNull());

        return this;
    }

    protected AbstractBaseValidation<T> isEqualTo(T otherObj) {
        registerCondition(ObjectConditions.isEqualTo(otherObj));

        return this;
    }

    protected AbstractBaseValidation<T> isNotEqualTo(T otherObj) {
        registerCondition(ObjectConditions.isNotEqualTo(otherObj));

        return this;
    }

    protected AbstractBaseValidation<T> withTerm(Predicate<T> predicate) {
        memoize(new SingleCondition<>(predicate));

        return this;
    }

    protected AbstractBaseValidation<T> withTerm(Supplier<Boolean> supplier) {
        memoize(new SingleCondition<>(it -> supplier.get()));

        return this;
    }

    protected AbstractBaseValidation<T> withTerm(Function<T, AbstractBaseValidation<T>> validator) {
        return this.inspect(this.obj, validator);
    }

    protected AbstractBaseValidation<T> isAnyOf(SingleCondition<T>... conditions) {
        memoize(new LinkedCondition<>(List.of(conditions), Clause.OR));

        return this;
    }

    protected AbstractBaseValidation<T> isAllOf(SingleCondition<T>... conditions) {
        memoize(new LinkedCondition<>(List.of(conditions), Clause.AND));

        return this;
    }

    protected AbstractBaseValidation<T> or() {
        registerCluster();

        return this;
    }

    protected AbstractBaseValidation<T> log(String msg, Object... values) {
        LOGGER.debug(msg, values);

        return this;
    }

    protected <R> AbstractBaseValidation<T> inspecting(Function<T, R> mapper, Predicate<R> predicate) {
        memoize(toCondition(mapper.apply(this.obj), predicate));

        return this;
    }

    protected <R> AbstractBaseValidation<T> inspecting(Function<T, R> mapper, Supplier<SingleCondition<R>> condition) {
        memoize(new SingleCondition<>(it -> condition.get().getPredicate().test(mapper.apply((T) it))));

        return this;
    }

    protected <R> AbstractBaseValidation<T> deepInspecting(Function<T, R> mapper, Function<R, AbstractBaseValidation<R>> validator) {
        return this.inspect(mapper.apply(this.obj), validator);
    }

    protected AbstractBaseValidation<T> failOn(TerminationMode terminationMode) {
        registerModes(terminationMode, ErrorMode.THROW);

        return this;
    }

    protected AbstractBaseValidation<T> failOn(TerminationMode terminationMode, ErrorMode errorMode) {
        registerModes(terminationMode, errorMode);

        return this;
    }

    protected AbstractBaseValidation<T> onError(String error) {
        this.getCurrentCondition().setOnError(error);

        return this;
    }

    protected AbstractBaseValidation<T> onGroupError(String error) {
        this.getCurrentCluster().setOnError(error);

        return this;
    }

    private  <R> AbstractBaseValidation<T> inspect(R obj, Function<R, AbstractBaseValidation<R>> validator) {
        memoize(toCondition(obj, validator));

        return this;
    }
}
