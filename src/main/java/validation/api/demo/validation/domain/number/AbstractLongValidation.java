package validation.api.demo.validation.domain.number;

import lombok.AllArgsConstructor;
import validation.api.demo.common.Condition;
import validation.api.demo.validation.domain.object.AbstractObjectValidation;

@AllArgsConstructor
public abstract class AbstractLongValidation extends AbstractObjectValidation<Long> {

    public AbstractLongValidation isGt(Long otherLong, String onError) {
        memoize(new Condition<>(it -> it.compareTo(otherLong) > 0, onError));

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
    public AbstractLongValidation isEqualTo(Long otherObj, String onError) {
        return (AbstractLongValidation) super.isEqualTo(otherObj, onError);
    }

    @Override
    public AbstractLongValidation isNotEqualTo(Long otherObj, String onError) {
        return (AbstractLongValidation) super.isNotEqualTo(otherObj, onError);
    }
}
