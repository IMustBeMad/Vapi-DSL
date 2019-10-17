package vapidsl.common;

import lombok.*;
import vapidsl.dict.FlowType;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;
import java.util.function.Supplier;

@Getter
@Setter
@EqualsAndHashCode
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ValidationCondition<T> implements Condition<T> {

    private Predicate<T> predicate;
    private FlowType flowType = FlowType.COMMON;
    private Supplier<List<ValidationError>> supplier;
    private List<ValidationError> onError = new ArrayList<>();

    public ValidationCondition(Predicate<T> predicate, Supplier<List<ValidationError>> supplier) {
        this.predicate = predicate;
        this.supplier = supplier;
    }

    @Override
    public List<Predicate<T>> getPredicates() {
        return Collections.singletonList(this.predicate);
    }

    @Override
    public List<ValidationError> getOnError() {
        if (!this.onError.isEmpty()) {
            return this.onError;
        }

        return this.supplier.get();
    }
}
