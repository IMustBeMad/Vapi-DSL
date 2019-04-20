package validation.api.demo.validation.domain.object;

import lombok.extern.slf4j.Slf4j;
import validation.api.demo.common.Condition;
import validation.api.demo.validation.domain.ValidationHolder;

import java.util.List;
import java.util.Objects;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Slf4j
public abstract class AbstractObjectValidation<T> extends ValidationHolder<T> {

    public AbstractObjectValidation<T> isNull(String onError) {
        memoize(new Condition<>(Objects::isNull, onError));

        return this;
    }

    public AbstractObjectValidation<T> isNotNull(String onError) {
        memoize(new Condition<>(Objects::nonNull, onError));

        return this;
    }

    public AbstractObjectValidation<T> isEqualTo(T otherObj, String onError) {
        memoize(new Condition<>(it -> it.equals(otherObj), onError));

        return this;
    }

    public AbstractObjectValidation<T> isNotEqualTo(T otherObj, String onError) {
        memoize(new Condition<>(it -> !it.equals(otherObj), onError));

        return this;
    }

    public AbstractObjectValidation<T> withTerm(Predicate<T> predicate, String onError) {
        memoize(new Condition<>(predicate, onError));

        return this;
    }

    public AbstractObjectValidation<T> log(String msg, Object... values) {
        log.debug(msg, values);

        return this;
    }

    public <R> AbstractObjectValidation<T> inspecting(Function<T, R> mapper, Predicate<R> predicate, String onError) {
        memoize(new Condition<>(it -> predicate.test(mapper.apply((T) it)), onError));

        return this;
    }

    public <R> AbstractObjectValidation<T> inspecting(Function<T, R> mapper, Function<R, AbstractObjectValidation<R>> validator) {
        List<Condition<R>> fails = validator.apply(mapper.apply(this.obj))
                                            .innerExamine();

        memoize(new Condition<>(it -> fails.isEmpty(), collectMessages(fails)));

        return this;
    }

    private <R> String collectMessages(List<Condition<R>> fails) {
        return fails.stream()
                    .map(Condition::getOnError)
                    .collect(Collectors.joining("\n"));
    }
}
