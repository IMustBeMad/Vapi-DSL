package vapidsl.common;

import lombok.*;
import vapidsl.dict.Clause;
import vapidsl.dict.FlowType;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;
import java.util.function.Supplier;

import static java.util.stream.Collectors.toList;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class LinkedCondition<T> implements Condition<T> {

    private List<Condition<T>> conditions;
    private Supplier<List<Condition<T>>> conditionSupplier;

    private FlowType flowType = FlowType.COMMON;
    private Clause linkClause;
    private List<ValidationError> onError = new ArrayList<>();

    public LinkedCondition(Supplier<List<Condition<T>>> conditionSupplier, Clause linkClause) {
        this.conditionSupplier = conditionSupplier;
        this.linkClause = linkClause;
    }

    @Override
    public List<Predicate<T>> getPredicates() {
        return this.getConditions()
                   .stream()
                   .map(Condition::getPredicates)
                   .flatMap(Collection::stream)
                   .collect(toList());
    }

    @Override
    public List<ValidationError> getOnError() {
        if (!this.onError.isEmpty()) {
            return this.onError;
        }

        return this.conditions.stream()
                              .map(Condition::getOnError)
                              .filter(it -> !it.isEmpty())
                              .findFirst()
                              .orElseGet(Collections::emptyList);
    }

    public List<Condition<T>> getConditions() {
        initConditions();

        return this.conditions;
    }

    private void initConditions() {
        if (this.conditions == null) {
            this.conditions = conditionSupplier.get();
        }
    }
}
