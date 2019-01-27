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
}
