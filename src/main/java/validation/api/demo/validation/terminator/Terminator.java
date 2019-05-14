package validation.api.demo.validation.terminator;

import validation.api.demo.exception.SystemMessage;
import validation.api.demo.validation.common.Condition;
import validation.api.demo.validation.common.ConditionCluster;

import java.util.List;

public interface Terminator {

    <T> List<SystemMessage> failOnFirstError(List<Condition<T>> conditions, T obj);

    <T> List<SystemMessage> failOnLastError(List<Condition<T>> conditions, T obj);

    <T> List<SystemMessage> failOnNoErrors(List<Condition<T>> conditions, T obj);

    <T> List<SystemMessage> failOnNoneGroupMatch(List<ConditionCluster<T>> conditionClusters, T obj);

    <T> List<SystemMessage> failOnFirstGroupMatch(List<ConditionCluster<T>> conditionClusters, T obj);
}
