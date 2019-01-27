package validation.api.demo.common;

import lombok.*;

import java.util.function.Predicate;

@Getter
@Setter
@EqualsAndHashCode
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Condition<T> {

    private Predicate<T> predicate;
    private String onError;
}
