package validation.api.demo.validation.terminator;

import validation.api.demo.exception.SystemMessage;
import validation.api.demo.validation.common.Condition;
import validation.api.demo.validation.common.ConditionCluster;

import java.util.List;

public interface Terminator {

    <T> List<SystemMessage> failFast(List<Condition<T>> conditions, T obj);

    <T> List<SystemMessage> failSafe(List<Condition<T>> conditions, T obj);

    <T> List<SystemMessage> failIfNoneGroupMatch(List<ConditionCluster<T>> conditionClusters, T obj);
}
