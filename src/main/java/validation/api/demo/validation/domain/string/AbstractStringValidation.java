package validation.api.demo.validation.domain.string;

import lombok.AllArgsConstructor;
import validation.api.demo.common.Condition;
import validation.api.demo.validation.domain.ValidationHolder;

@AllArgsConstructor
public abstract class AbstractStringValidation extends ValidationHolder<String> {

    public AbstractStringValidation matches(String pattern, String onError) {
        memoize(new Condition<>(it -> it.matches(pattern), onError));

        return this;
    }
}
