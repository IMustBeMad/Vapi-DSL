package vapidsl.tester.impl;

import vapidsl.Validation;
import vapidsl.common.Condition;
import vapidsl.common.LinkedCondition;
import vapidsl.common.ValidationError;
import vapidsl.dict.Clause;
import vapidsl.result.ValidationResult;
import vapidsl.tester.Tester;

import java.util.List;
import java.util.function.Predicate;

public enum LinkedConditionTester implements Tester {
    INSTANCE;

    @Override
    public <T> ValidationResult test(Condition<T> condition, T obj) {
        LinkedCondition<T> linkedCondition = (LinkedCondition<T>) condition;

        List<Predicate<T>> predicates = linkedCondition.getPredicates();
        Clause linkClause = linkedCondition.getLinkClause();

        if (linkClause == Clause.AND) {
            return testAllMatch(condition, obj, linkedCondition);
        }

        return getResult(
                predicates.stream().anyMatch(it -> it.test(obj)),
                condition.getOnError()
        );
    }

    private <T> ValidationResult testAllMatch(Condition<T> condition, T obj, LinkedCondition<T> linkedCondition) {
        List<Condition<T>> conditions = linkedCondition.getConditions();
        for (Condition<T> partialCondition : conditions) {
            boolean isValid = partialCondition.getPredicates().stream().allMatch(it -> it.test(obj));

            if (!isValid) {
                List<ValidationError> onError = condition.getOnError();

                if (onError.isEmpty()) {
                    condition.setOnError(partialCondition.getOnError());
                }

                return Validation.failed(condition.getOnError());
            }
        }

        return Validation.ok();
    }
}
