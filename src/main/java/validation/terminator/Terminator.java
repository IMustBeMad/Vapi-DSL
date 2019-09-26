package validation.terminator;

import validation.common.ConditionCluster;
import validation.exception.SystemMessage;

import java.util.List;

public interface Terminator extends ReasonExtractor {

    <T> List<SystemMessage> failFast(ConditionCluster<T> conditionCluster, T obj);

    <T> List<SystemMessage> failSafe(ConditionCluster<T> conditionCluster, T obj);

    <T> List<SystemMessage> failOnNoneGroupMatched(List<ConditionCluster<T>> conditionClusters, T obj);

    <T> List<SystemMessage> failOnFirstGroupMatched(List<ConditionCluster<T>> conditionClusters, T obj);
}
