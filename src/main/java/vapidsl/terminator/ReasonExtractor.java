package vapidsl.terminator;

import vapidsl.common.Condition;
import vapidsl.common.ConditionCluster;
import vapidsl.common.ValidationError;
import vapidsl.result.ValidationResult;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

public interface ReasonExtractor {

    default <T> ValidationResult getResult(ValidationResult result, ConditionCluster<T> conditionCluster) {
        return Optional.ofNullable(conditionCluster.getOnError())
                       .map(Collections::singletonList)
                       .map(ValidationResult::failed)
                       .orElse(result);
    }

    default <T> ValidationResult getResult(ConditionCluster<T> conditionCluster) {
        return Optional.ofNullable(conditionCluster.getOnError())
                       .map(Collections::singletonList)
                       .map(ValidationResult::failed)
                       .orElseGet(() -> ValidationResult.failed(getConditionErrors(conditionCluster)));
    }

    default ValidationResult getResult(List<ValidationResult> validationResults) {
        if (validationResults.stream().allMatch(ValidationResult::isValid)) {
            return ValidationResult.ok();
        }

        return ValidationResult.failed(validationResults.stream()
                                                        .filter(it -> !it.isValid())
                                                        .map(ValidationResult::getReason)
                                                        .flatMap(Collection::stream)
                                                        .collect(toList())
        );
    }

    private <T> List<ValidationError> getConditionErrors(ConditionCluster<T> conditionCluster) {
        return conditionCluster.getConditions().stream()
                               .map(Condition::getOnError)
                               .flatMap(Collection::stream)
                               .collect(toList());
    }
}
