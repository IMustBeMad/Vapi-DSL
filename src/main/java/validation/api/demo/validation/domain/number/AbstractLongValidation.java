package validation.api.demo.validation.domain.number;

import validation.api.demo.common.Condition;
import validation.api.demo.validation.domain.object.AbstractObjectValidation;

import java.util.function.Function;
import java.util.function.Predicate;

public abstract class AbstractLongValidation extends AbstractObjectValidation<Long> {

    public AbstractLongValidation isGt(Long otherLong, String onError) {
        memoize(new Condition<>(it -> it.compareTo(otherLong) > 0, onError));

        return this;
    }

    public AbstractLongValidation isLt(Long otherLong, String onError) {
        memoize(new Condition<>(it -> it.compareTo(otherLong) < 0, onError));

        return this;
    }

    @Override
    public AbstractLongValidation isNull(String onError) {
        return (AbstractLongValidation) super.isNull(onError);
    }

    @Override
    public AbstractLongValidation isNotNull(String onError) {
        return (AbstractLongValidation) super.isNotNull(onError);
    }

    @Override
    public AbstractLongValidation isEqualTo(Long otherLong, String onError) {
        return (AbstractLongValidation) super.isEqualTo(otherLong, onError);
    }

    @Override
    public AbstractLongValidation isNotEqualTo(Long otherLong, String onError) {
        return (AbstractLongValidation) super.isNotEqualTo(otherLong, onError);
    }

    @Override
    public AbstractLongValidation withTerm(Predicate<Long> predicate, String onError) {
        return (AbstractLongValidation) super.withTerm(predicate, onError);
    }

    @Override
    public <R> AbstractLongValidation inspecting(Function<Long, R> mapper, Predicate<R> predicate, String onError) {
        return (AbstractLongValidation) super.inspecting(mapper, predicate, onError);
    }
}
