package validation.api.demo.validation.terminator;

import validation.api.demo.validation.common.ConditionCluster;
import validation.api.demo.validation.common.ValidationError;

import java.util.List;

public interface Terminator extends ReasonExtractor {

    <T> List<ValidationError> failFast(ConditionCluster<T> conditionCluster, T obj);

    <T> List<ValidationError> failSafe(ConditionCluster<T> conditionCluster, T obj);

    <T> List<ValidationError> failOnNoneGroupMatched(List<ConditionCluster<T>> conditionClusters, T obj);

    <T> List<ValidationError> failOnFirstGroupMatched(List<ConditionCluster<T>> conditionClusters, T obj);
}
