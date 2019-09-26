package validation.common;

import lombok.*;
import validation.dict.FlowType;

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
    private FlowType flowType = FlowType.COMMON;
    private String onError;

    public SingleCondition(Predicate<T> predicate) {
        this.predicate = predicate;
    }

    public SingleCondition(Predicate<T> predicate, String onError) {
        this.predicate = predicate;
        this.onError = onError;
    }

    public SingleCondition(Predicate<T> predicate, FlowType flowType) {
        this.predicate = predicate;
        this.flowType = flowType;
    }

    @Override
    public List<Predicate<T>> getPredicates() {
        return Collections.singletonList(predicate);
    }
}
