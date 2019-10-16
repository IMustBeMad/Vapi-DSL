package vapidsl.common;

import lombok.*;
import vapidsl.dict.Clause;
import vapidsl.dict.FlowType;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;

import static java.util.stream.Collectors.toList;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class LinkedCondition<T> implements Condition<T> {

    private List<Condition<T>> conditions = new ArrayList<>();
    private FlowType flowType = FlowType.COMMON;
    private Clause linkClause;
    private String onError;

    public LinkedCondition(List<Condition<T>> conditions, Clause linkClause) {
        this.conditions = conditions;
        this.linkClause = linkClause;
    }

    @Override
    public List<Predicate<T>> getPredicates() {
        return conditions.stream()
                         .map(Condition::getPredicates)
                         .flatMap(Collection::stream)
                         .collect(toList());
    }

    @Override
    public String getOnError() {
        if (onError != null) {
            return onError;
        }

        return conditions.stream()
                         .map(Condition::getOnError)
                         .filter(Objects::nonNull)
                         .findFirst()
                         .orElse(null);
    }
}
