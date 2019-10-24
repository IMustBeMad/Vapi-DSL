package vapidsl.terminator;

import vapidsl.common.ConditionCluster;
import vapidsl.result.ValidationResult;

import java.util.List;

public interface Terminator extends ReasonExtractor {

    <T> ValidationResult failFast(ConditionCluster<T> conditionCluster, T obj);

    <T> ValidationResult failSafe(ConditionCluster<T> conditionCluster, T obj);

    <T> ValidationResult failOnNoneGroupMatched(List<ConditionCluster<T>> conditionClusters, T obj);

    <T> ValidationResult failOnFirstGroupMatched(List<ConditionCluster<T>> conditionClusters, T obj);
}
