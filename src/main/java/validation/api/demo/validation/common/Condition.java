package validation.api.demo.validation.common;

import lombok.*;

import java.util.function.Predicate;

@Getter
@Setter
@EqualsAndHashCode
@ToString
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
public class Condition<T> {

    private @NonNull Predicate<T> predicate;
    private String onError;
}
