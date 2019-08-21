package validation.api.demo.validation.tester.impl;

import validation.api.demo.validation.common.Condition;
import validation.api.demo.validation.result.ValidationResult;

public enum TesterFacade {
    INSTANCE;

    public <T> ValidationResult test(Condition<T> condition, T obj) {
        return getValidationResult(condition, obj, TestMode.STRAIGHT);
    }

    public <T> ValidationResult test(Condition<T> condition, T obj, TestMode testMode) {
        return getValidationResult(condition, obj, testMode);
    }

    private <T> ValidationResult getValidationResult(Condition<T> condition, T obj, TestMode testMode) {
        boolean singleCondition = condition.getPredicates().size() == 1;

        return singleCondition ? SingleConditionTester.INSTANCE.test(condition, obj, testMode)
                               : LinkedConditionTester.INSTANCE.test(condition, obj, testMode);
    }

    public enum TestMode {
        STRAIGHT, INVERTED
    }
}
