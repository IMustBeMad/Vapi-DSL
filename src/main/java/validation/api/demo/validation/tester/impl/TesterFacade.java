package validation.api.demo.validation.tester.impl;

import validation.api.demo.validation.common.Condition;
import validation.api.demo.validation.result.ValidationResult;

public enum TesterFacade {
    INSTANCE;

    public <T> ValidationResult test(Condition<T> condition, T obj) {
        boolean singleCondition = condition.getPredicates().size() == 1;

        return singleCondition ? SingleConditionTester.INSTANCE.test(condition, obj)
                               : LinkedConditionTester.INSTANCE.test(condition, obj);
    }
}
