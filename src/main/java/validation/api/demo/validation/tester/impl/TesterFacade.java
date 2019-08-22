package validation.api.demo.validation.tester.impl;

import validation.api.demo.validation.common.Condition;
import validation.api.demo.validation.result.ValidationResult;
import validation.api.demo.validation.tester.Tester;

public enum TesterFacade {
    INSTANCE;

    public <T> ValidationResult test(Condition<T> condition, T obj) {
        return getValidationResult(condition, obj, Tester.TestMode.STRAIGHT);
    }

    public <T> ValidationResult test(Condition<T> condition, T obj, Tester.TestMode testMode) {
        return getValidationResult(condition, obj, testMode);
    }

    private <T> ValidationResult getValidationResult(Condition<T> condition, T obj, Tester.TestMode testMode) {
        boolean singleCondition = condition.getPredicates().size() == 1;

        return singleCondition ? SingleConditionTester.INSTANCE.test(condition, obj, testMode)
                               : LinkedConditionTester.INSTANCE.test(condition, obj, testMode);
    }
}
