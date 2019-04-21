package validation.api.demo.validation.domain.object;

import lombok.extern.slf4j.Slf4j;
import validation.api.demo.validation.common.Condition;
import validation.api.demo.validation.common.LinkedCondition;
import validation.api.demo.validation.common.SingleCondition;
import validation.api.demo.validation.dict.Clause;
import validation.api.demo.validation.domain.ValidationHolder;

import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Slf4j
public abstract class AbstractObjectValidation<T> extends ValidationHolder<T> {

    public AbstractObjectValidation<T> isNull(String onError) {
        registerCondition(ObjectConditions.isNull(), onError);

        return this;
    }

    public AbstractObjectValidation<T> isNotNull(String onError) {
        preTest(ObjectConditions.isNotNull(), onError);

        return this;
    }

    public AbstractObjectValidation<T> isEqualTo(T otherObj, String onError) {
        registerCondition(ObjectConditions.isEqualTo(otherObj), onError);

        return this;
    }

    public AbstractObjectValidation<T> isNotEqualTo(T otherObj, String onError) {
        registerCondition(ObjectConditions.isNotEqualTo(otherObj), onError);

        return this;
    }

    public AbstractObjectValidation<T> withTerm(Predicate<T> predicate, String onError) {
        memoize(new SingleCondition<>(predicate, onError));

        return this;
    }

    public AbstractObjectValidation<T> isAnyOf(SingleCondition<T> condition1, SingleCondition<T> condition2, String onError) {
        memoize(new LinkedCondition<>(List.of(condition1, condition2), Clause.OR, onError));

        return this;
    }

    public AbstractObjectValidation<T> isAllOf(SingleCondition<T> condition1, SingleCondition<T> condition2, String onError) {
        memoize(new LinkedCondition<>(List.of(condition1, condition2), Clause.AND, onError));

        return this;
    }

    public AbstractObjectValidation<T> or() {
        registerCluster();

        return this;
    }

    public AbstractObjectValidation<T> log(String msg, Object... values) {
        log.debug(msg, values);

        return this;
    }

    public <R> AbstractObjectValidation<T> inspecting(Function<T, R> mapper, Predicate<R> predicate, String onError) {
        memoize(new SingleCondition<>(it -> predicate.test(mapper.apply((T) it)), onError));

        return this;
    }

    public <R> AbstractObjectValidation<T> inspecting(Function<T, R> mapper, Function<R, AbstractObjectValidation<R>> validator) {
        List<Condition<R>> fails = validator.apply(mapper.apply(this.obj))
                                            .innerExamine();

        memoize(new SingleCondition<>(it -> fails.isEmpty(), collectMessages(fails)));

        return this;
    }

    private <R> String collectMessages(List<Condition<R>> fails) {
        return fails.stream()
                    .map(Condition::getOnError)
                    .collect(Collectors.joining("\n"));
    }
}
