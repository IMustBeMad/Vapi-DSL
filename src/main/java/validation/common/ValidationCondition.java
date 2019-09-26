package validation.common;

import lombok.*;
import validation.exception.SystemMessage;
import validation.dict.FlowType;

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
    private Supplier<List<SystemMessage>> supplier;
    private String onError;

    public ValidationCondition(Predicate<T> predicate, Supplier<List<SystemMessage>> supplier) {
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
                            .map(SystemMessage::getReasonCode)
                            .collect(Collectors.toList());
    }
}
