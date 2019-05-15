package validation.api.demo.validation.common;

import lombok.*;
import validation.api.demo.validation.dict.Clause;
import validation.api.demo.validation.dict.FailureMode;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import static java.util.stream.Collectors.toList;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class LinkedCondition<T> implements Condition<T> {

    private List<SingleCondition<T>> conditions = new ArrayList<>();
    private FailureMode failureMode = FailureMode.COMMON;
    private Clause linkClause;
    private String onError;

    public LinkedCondition(List<SingleCondition<T>> conditions, Clause linkClause) {
        this.conditions = conditions;
        this.linkClause = linkClause;
    }

    public List<Predicate<T>> getPredicates() {
        return conditions.stream()
                         .map(SingleCondition::getPredicate)
                         .collect(toList());
    }
}
