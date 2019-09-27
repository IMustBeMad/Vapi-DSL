package vapidsl.common;

import lombok.*;
import vapidsl.dict.FlowType;

import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import static org.apache.commons.lang3.StringUtils.isEmpty;

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
    private String onError;

    public ValidationCondition(Predicate<T> predicate, Supplier<List<ValidationError>> supplier) {
        this.predicate = predicate;
        this.supplier = supplier;
    }

    @Override
    public List<Predicate<T>> getPredicates() {
        return Collections.singletonList(this.predicate);
    }

    @Override
    public String getOnError() {
        if (!isEmpty(this.onError)) {
            return this.onError;
        }

        return String.join(",\n", getErrorCodes());
    }

    private List<String> getErrorCodes() {
        return this.supplier.get().stream()
                            .map(ValidationError::getReasonCode)
                            .collect(Collectors.toList());
    }
}
