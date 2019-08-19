package validation.api.demo.validation.domain.object.impl;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import validation.api.demo.validation.common.Condition;
import validation.api.demo.validation.common.ConditionCluster;

import java.util.List;
import java.util.function.Function;

@Getter
@Setter
@AllArgsConstructor
public class ObjectTemplate<T> implements Function<T, ObjectValidation<T>> {

    private List<ConditionCluster<T>> conditionClusters;
    private ConditionCluster<T> currentCluster ;
    private Condition<T> currentCondition;

    @Override
    public ObjectValidation<T> apply(T obj) {
        return new ObjectValidation<>(obj, this);
    }
}
