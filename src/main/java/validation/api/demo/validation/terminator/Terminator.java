package validation.api.demo.validation.terminator;

import validation.api.demo.exception.SystemMessage;
import validation.api.demo.validation.common.ConditionCluster;
import validation.api.demo.validation.result.ValidationResult;

import java.util.List;
import java.util.Optional;

import static org.springframework.util.StringUtils.isEmpty;

public interface Terminator {

    <T> List<SystemMessage> failOnFirstError(ConditionCluster<T> conditionCluster, T obj);

    <T> List<SystemMessage> failOnLastError(ConditionCluster<T> conditionCluster, T obj);

    <T> List<SystemMessage> failOnNoErrors(ConditionCluster<T> conditionCluster, T obj);

    <T> List<SystemMessage> failOnNoneGroupMatch(List<ConditionCluster<T>> conditionClusters, T obj);

    <T> List<SystemMessage> failOnFirstGroupMatch(List<ConditionCluster<T>> conditionClusters, T obj);

    default SystemMessage getErrorReason(ValidationResult result, String onError) {
        if (!isEmpty(onError)) {
            return SystemMessage.withError("group", onError);
        }

        return Optional.ofNullable(result)
                       .map(ValidationResult::getReason)
                       .orElseGet(
                               () -> SystemMessage.withError("validation", "validation.error.no.error.code")
                       );
    }
}
