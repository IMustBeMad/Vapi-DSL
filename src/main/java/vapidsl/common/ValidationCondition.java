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
    private Supplier<List<ValidationError>> validationSupplier;

    private List<ValidationError> onError = new ArrayList<>();
    private List<ValidationError> errorsByValidation = new ArrayList<>();

    private FlowType flowType = FlowType.COMMON;

    public ValidationCondition(Supplier<List<ValidationError>> validationSupplier) {
        this.validationSupplier = validationSupplier;
    }

    @Override
    public List<Predicate<T>> getPredicates() {
        initPredicate();

        return Collections.singletonList(this.predicate);
    }

    @Override
    public List<ValidationError> getOnError() {
        if (!this.onError.isEmpty()) {
            return this.onError;
        }

        return this.errorsByValidation;
    }

    private void initPredicate() {
        if (this.predicate == null) {
            this.errorsByValidation = validationSupplier.get();

            this.predicate = (it) -> this.errorsByValidation.isEmpty();
        }
    }
}
