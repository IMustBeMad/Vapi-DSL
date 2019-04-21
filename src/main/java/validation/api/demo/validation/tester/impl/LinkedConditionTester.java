package validation.api.demo.validation.tester.impl;

import validation.api.demo.validation.common.Condition;
import validation.api.demo.validation.common.LinkedCondition;
import validation.api.demo.validation.dict.Clause;
import validation.api.demo.validation.result.ValidationResult;
import validation.api.demo.validation.tester.Tester;

import java.util.List;
import java.util.function.Predicate;

public enum LinkedConditionTester implements Tester {
    INSTANCE;

    @Override
    public <T> ValidationResult test(Condition<T> condition, T obj) {
        List<Predicate<T>> predicates = condition.getPredicates();
        Clause linkClause = ((LinkedCondition) condition).getLinkClause();

        boolean isValid = linkClause == Clause.AND ? predicates.stream().allMatch(it -> it.test(obj))
                                                   : predicates.stream().anyMatch(it -> it.test(obj));

        return getResult(isValid, condition);
    }
}
