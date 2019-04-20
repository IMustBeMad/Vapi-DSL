package validation.api.demo.validation.common;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class SubConditionCluster<T> {

    private List<Condition<T>> conditions = new ArrayList<>();
    private String onError;
}
