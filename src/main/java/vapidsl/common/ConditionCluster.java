package vapidsl.common;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class ConditionCluster<T> {

    private List<Condition<T>> conditions = new ArrayList<>();
    private String onError;

    public void add(Condition<T> condition) {
        this.conditions.add(condition);
    }
}
