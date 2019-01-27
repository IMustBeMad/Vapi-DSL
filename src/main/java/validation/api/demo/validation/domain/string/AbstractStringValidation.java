package validation.api.demo.validation.domain.string;

import lombok.AllArgsConstructor;
import validation.api.demo.common.Condition;
import validation.api.demo.validation.domain.object.AbstractObjectValidation;

@AllArgsConstructor
public abstract class AbstractStringValidation extends AbstractObjectValidation<String> {

    public AbstractStringValidation matches(String pattern, String onError) {
        memoize(new Condition<>(it -> it.matches(pattern), onError));

        return this;
    }

    @Override
    public AbstractStringValidation isNull(String onError) {
        return (AbstractStringValidation) super.isNull(onError);
    }

    @Override
    public AbstractStringValidation isNotNull(String onError) {
        return (AbstractStringValidation) super.isNotNull(onError);
    }

    @Override
    public AbstractStringValidation isEqualTo(String otherObj, String onError) {
        return (AbstractStringValidation) super.isEqualTo(otherObj, onError);
    }

    @Override
    public AbstractStringValidation isNotEqualTo(String otherObj, String onError) {
        return (AbstractStringValidation) super.isNotEqualTo(otherObj, onError);
    }
}
