package validation.api.demo.validation.terminator;

import validation.api.demo.validation.common.ConditionCluster;
import validation.api.demo.validation.exception.SystemMessage;

import java.util.List;

public interface Terminator extends ReasonExtractor {

    <T> List<SystemMessage> matchLazily(ConditionCluster<T> conditionCluster, T obj);

    <T> List<SystemMessage> matchEagerly(ConditionCluster<T> conditionCluster, T obj);

    <T> List<SystemMessage> matchAllMatched(ConditionCluster<T> conditionCluster, T obj);

    <T> List<SystemMessage> matchNoneGroupMatched(List<ConditionCluster<T>> conditionClusters, T obj);

    <T> List<SystemMessage> matchGroupLazily(List<ConditionCluster<T>> conditionClusters, T obj);
}
