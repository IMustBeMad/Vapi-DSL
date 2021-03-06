package vapidsl.tester.impl;

import vapidsl.common.Condition;
import vapidsl.result.ValidationResult;
import vapidsl.tester.Tester;

import java.util.List;
import java.util.function.Predicate;

public enum SingleConditionTester implements Tester {
    INSTANCE;

    @Override
    public <T> ValidationResult test(Condition<T> condition, T obj) {
        List<Predicate<T>> predicates = condition.getPredicates();
        Predicate<T> predicate = predicates.get(0);

        return getResult(predicate.test(obj), condition::getOnError);
    }
}
