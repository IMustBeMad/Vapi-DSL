package validation.api.demo.validation.terminator;

import validation.api.demo.exception.SystemMessage;
import validation.api.demo.validation.common.Condition;
import validation.api.demo.validation.common.ConditionCluster;

import java.util.List;

public interface Terminator {

    <T> void failFast(List<Condition<T>> conditions, T obj);

    <T> void failSafe(List<Condition<T>> conditions, T obj);

    <T> void failIfNoneGroupMatch(List<ConditionCluster<T>> conditionClusters, T obj);

    <T> List<SystemMessage> examine(List<Condition<T>> conditions, T obj);
}
