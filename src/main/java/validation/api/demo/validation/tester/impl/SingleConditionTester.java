package validation.api.demo.validation.tester.impl;

import validation.api.demo.validation.common.Condition;
import validation.api.demo.validation.result.ValidationResult;
import validation.api.demo.validation.tester.Tester;

import java.util.List;
import java.util.function.Predicate;

public enum SingleConditionTester implements Tester {
    INSTANCE;

    @Override
    public <T> ValidationResult test(Condition<T> condition, T obj, TesterFacade.TestMode testMode) {
        List<Predicate<T>> predicates = condition.getPredicates();
        Predicate<T> predicate = predicates.get(0);

        return getResult(predicate.test(obj), condition.getOnError(), testMode);
    }
}
