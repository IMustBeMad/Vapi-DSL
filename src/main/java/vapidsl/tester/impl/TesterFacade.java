package vapidsl.tester.impl;

import vapidsl.common.Condition;
import vapidsl.result.ValidationResult;

public enum TesterFacade {
    INSTANCE;

    public <T> ValidationResult test(Condition<T> condition, T obj) {
        return getValidationResult(condition, obj);
    }

    private <T> ValidationResult getValidationResult(Condition<T> condition, T obj) {
        boolean singleCondition = condition.getPredicates().size() == 1;

        return singleCondition ? SingleConditionTester.INSTANCE.test(condition, obj)
                               : LinkedConditionTester.INSTANCE.test(condition, obj);
    }
}
