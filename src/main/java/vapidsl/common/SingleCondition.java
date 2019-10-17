package vapidsl.common;

import lombok.*;
import vapidsl.dict.FlowType;

import java.util.ArrayList;
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
    private List<ValidationError> onError = new ArrayList<>();

    public SingleCondition(Predicate<T> predicate) {
        this.predicate = predicate;
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
