package validation.api.demo.validation.common;

import lombok.*;
import validation.api.demo.exception.SystemMessage;
import validation.api.demo.validation.dict.FailureMode;

import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import static org.springframework.util.StringUtils.isEmpty;

@Getter
@Setter
@EqualsAndHashCode
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ValidationCondition<T> implements Condition<T> {

    private Predicate<T> predicate;
    private FailureMode failureMode = FailureMode.COMMON;
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
