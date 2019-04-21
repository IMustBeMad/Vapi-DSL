package validation.api.demo.validation.common;

import lombok.*;

import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;

@Getter
@Setter
@EqualsAndHashCode
@ToString
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
public class SingleCondition<T> implements Condition<T> {

    private @NonNull Predicate<T> predicate;
    private String onError;

    @Override
    public List<Predicate<T>> getPredicates() {
        return Collections.singletonList(predicate);
    }
}
