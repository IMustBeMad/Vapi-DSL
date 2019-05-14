package validation.api.demo.validation.common;

import lombok.*;
import validation.api.demo.exception.SystemMessage;

import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;

@Getter
@Setter
@EqualsAndHashCode
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ValidationCondition<T> implements Condition<T> {

    private Predicate<T> predicate;
    private Supplier<List<SystemMessage>> supplier;

    @Override
    public List<Predicate<T>> getPredicates() {
        return Collections.singletonList(this.predicate);
    }

    @Override
    public String getOnError() {
        return String.join(",\n", getErrorCodes());
    }

    private List<String> getErrorCodes() {
        return this.supplier.get().stream()
                            .map(SystemMessage::getReasonCode)
                            .collect(Collectors.toList());
    }
}
