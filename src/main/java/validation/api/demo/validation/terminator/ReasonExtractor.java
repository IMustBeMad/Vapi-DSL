package validation.api.demo.validation.terminator;

import validation.api.demo.validation.common.Condition;
import validation.api.demo.validation.common.ConditionCluster;
import validation.api.demo.validation.common.ValidationError;
import validation.api.demo.validation.result.ValidationResult;

import java.util.Collections;
import java.util.List;

import static java.util.stream.Collectors.toList;
import static org.apache.commons.lang3.StringUtils.isEmpty;

public interface ReasonExtractor {

    default List<ValidationError> getErrorReason(List<ValidationResult> results, String groupError) {
        if (!isEmpty(groupError)) {
            return getErrors("group", groupError);
        }

        List<ValidationError> messages = getReasons(results);

        return messages.isEmpty() ? getErrors("validation", "validation.error.no.error.code")
                                  : messages;
    }

    default <T> List<ValidationError> getErrorReason(ConditionCluster<T> conditionCluster) {
        String groupError = conditionCluster.getOnError();
        if (!isEmpty(groupError)) {
            return getErrors("group", groupError);
        }

        List<String> conditionErrors = getConditionErrors(conditionCluster);
        if (conditionErrors.isEmpty()) {
            return getErrors("validation", "validation.error.no.error.code");
        }

        return conditionErrors.stream()
                              .map(ValidationError::withCode)
                              .collect(toList());
    }

    private <T> List<String> getConditionErrors(ConditionCluster<T> conditionCluster) {
        return conditionCluster.getConditions().stream()
                               .map(Condition::getOnError)
                               .collect(toList());
    }

    private List<ValidationError> getReasons(List<ValidationResult> results) {
        return results.stream()
                      .map(ValidationResult::getReason)
                      .collect(toList());
    }

    private List<ValidationError> getErrors(String field, String onError) {
        return Collections.singletonList(ValidationError.of(field, onError));
    }
}
