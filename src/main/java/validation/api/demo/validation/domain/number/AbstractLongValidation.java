package validation.api.demo.validation.domain.number;

import lombok.AllArgsConstructor;
import validation.api.demo.common.Condition;
import validation.api.demo.validation.domain.ValidationHolder;

@AllArgsConstructor
public abstract class AbstractLongValidation extends ValidationHolder<Long> {

    public AbstractLongValidation isGt(Long otherLong, String onError) {
        memoize(new Condition<>(it -> it.compareTo(otherLong) > 0, onError));

        return this;
    }
}
