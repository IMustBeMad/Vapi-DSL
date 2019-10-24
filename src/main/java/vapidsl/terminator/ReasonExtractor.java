package vapidsl.terminator;

import vapidsl.common.Condition;
import vapidsl.common.ConditionCluster;
import vapidsl.common.ValidationError;
import vapidsl.result.ValidationResult;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

import static java.util.stream.Collectors.toList;

public interface ReasonExtractor {

    default <T> List<ValidationError> getErrorReason(ValidationResult result, ConditionCluster<T> conditionCluster) {
        ValidationError groupError = conditionCluster.getOnError();
        if (groupError != null) {
            return Collections.singletonList(groupError);
        }

        List<ValidationError> messages = result.getReason();

        return result.getReason().isEmpty() ? Collections.singletonList(ValidationError.of("validation", "validation.error.no.error.code"))
                                            : messages;
    }

    default <T> List<ValidationError> getErrorReason(ConditionCluster<T> conditionCluster) {
        ValidationError groupError = conditionCluster.getOnError();
        if (groupError != null) {
            return Collections.singletonList(groupError);
        }

        List<ValidationError> conditionErrors = getConditionErrors(conditionCluster);
        if (conditionErrors.isEmpty()) {
            return Collections.singletonList(ValidationError.of("validation", "validation.error.no.error.code"));
        }

        return conditionErrors;
    }

    private <T> List<ValidationError> getConditionErrors(ConditionCluster<T> conditionCluster) {
        return conditionCluster.getConditions().stream()
                               .map(Condition::getOnError)
                               .flatMap(Collection::stream)
                               .collect(toList());
    }
}
