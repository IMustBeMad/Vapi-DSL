package validation.api.demo.validation.domain;

import lombok.extern.slf4j.Slf4j;
import validation.api.demo.validation.common.LinkedCondition;
import validation.api.demo.validation.common.SingleCondition;
import validation.api.demo.validation.common.ValidationCondition;
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

    protected AbstractBaseValidation<T> isNull(String onError) {
        registerCondition(ObjectConditions.isNull(), onError);

        return this;
    }

    protected AbstractBaseValidation<T> isNotNull(String onError) {
        preTest(ObjectConditions.isNotNull(), onError);

        return this;
    }

    protected AbstractBaseValidation<T> isEqualTo(T otherObj, String onError) {
        registerCondition(ObjectConditions.isEqualTo(otherObj), onError);

        return this;
    }

    protected AbstractBaseValidation<T> isNotEqualTo(T otherObj, String onError) {
        registerCondition(ObjectConditions.isNotEqualTo(otherObj), onError);

        return this;
    }

    protected AbstractBaseValidation<T> withTerm(Predicate<T> predicate, String onError) {
        memoize(new SingleCondition<>(predicate, onError));

        return this;
    }

    protected AbstractBaseValidation<T> withTerm(Supplier<Boolean> supplier, String onError) {
        memoize(new SingleCondition<>(it -> supplier.get(), onError));

        return this;
    }

    protected AbstractBaseValidation<T> withTerm(TerminationMode terminationMode, Function<T, AbstractBaseValidation<T>> validator) {
        return this.inspect(this.obj, terminationMode, validator);
    }

    protected AbstractBaseValidation<T> isAnyOf(SingleCondition<T> condition1, SingleCondition<T> condition2, String onError) {
        memoize(new LinkedCondition<>(List.of(condition1, condition2), Clause.OR, onError));

        return this;
    }

    protected AbstractBaseValidation<T> isAllOf(SingleCondition<T> condition1, SingleCondition<T> condition2, String onError) {
        memoize(new LinkedCondition<>(List.of(condition1, condition2), Clause.AND, onError));

        return this;
    }

    public AbstractBaseValidation<T> or() {
        registerCluster();

        return this;
    }

    protected AbstractBaseValidation<T> log(String msg, Object... values) {
        log.debug(msg, values);

        return this;
    }

    protected <R> AbstractBaseValidation<T> inspecting(Function<T, R> mapper, Predicate<R> predicate, String onError) {
        return this.inspect(mapper.apply(this.obj), predicate, onError);
    }

    protected <R> AbstractBaseValidation<T> inspecting(Function<T, R> mapper, Supplier<SingleCondition<R>> condition, String onError) {
        memoize(new SingleCondition<>(it -> condition.get().getPredicate().test(mapper.apply((T) it)), onError));

        return this;
    }

    protected <R> AbstractBaseValidation<T> inspecting(Function<T, R> mapper, TerminationMode terminationMode, Function<R, AbstractBaseValidation<R>> validator) {
        return this.inspect(mapper.apply(this.obj), terminationMode, validator);
    }

    protected <R> AbstractBaseValidation<T> inspect(R obj, TerminationMode terminationMode, Function<R, AbstractBaseValidation<R>> validator) {
        AbstractBaseValidation<R> innerValidation = validator.apply(obj);
        memoize(new ValidationCondition<>(it -> innerValidation.terminate(terminationMode, ErrorMode.RETURN).isEmpty(), innerValidation::getError));

        return this;
    }

    protected <R> AbstractBaseValidation<T> inspect(R obj, Predicate<R> predicate, String onError) {
        memoize(new SingleCondition<>(it -> predicate.test(obj), onError));

        return this;
    }
}
