package validation.api.demo.validation.common;

import lombok.*;
import validation.api.demo.validation.dict.FailureMode;

import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;

@Getter
@Setter
@EqualsAndHashCode
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class SingleCondition<T> implements Condition<T> {

    private Predicate<T> predicate;
    private FailureMode failureMode = FailureMode.COMMON;
    private String onError;

    public SingleCondition(Predicate<T> predicate) {
        this.predicate = predicate;
    }

    public SingleCondition(Predicate<T> predicate, String onError) {
        this.predicate = predicate;
        this.onError = onError;
    }

    public SingleCondition(Predicate<T> predicate, FailureMode failureMode) {
        this.predicate = predicate;
        this.failureMode = failureMode;
    }

    @Override
    public List<Predicate<T>> getPredicates() {
        return Collections.singletonList(predicate);
    }
}
