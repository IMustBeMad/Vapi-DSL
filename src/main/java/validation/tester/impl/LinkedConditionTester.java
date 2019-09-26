package validation.tester.impl;

import validation.Validation;
import validation.common.Condition;
import validation.common.LinkedCondition;
import validation.dict.Clause;
import validation.result.ValidationResult;
import validation.tester.Tester;

import java.util.List;
import java.util.function.Predicate;

import static org.apache.commons.lang3.StringUtils.isEmpty;

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
                String onError = condition.getOnError();

                if (isEmpty(onError)) {
                    condition.setOnError(partialCondition.getOnError());
                }

                return Validation.failed(condition.getOnError());
            }
        }

        return Validation.ok();
    }
}
