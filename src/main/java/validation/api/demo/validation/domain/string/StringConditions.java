package validation.api.demo.validation.domain.string;

import validation.api.demo.validation.common.SingleCondition;

public class StringConditions {

    public static SingleCondition<String> matches(String pattern) {
        return new SingleCondition<>(it -> it.matches(pattern));
    }

    public static SingleCondition<String> equals(String otherString) {
        return new SingleCondition<>(it -> it.equals(otherString));
    }

    public static SingleCondition<String> notEquals(String otherString) {
        return new SingleCondition<>(it -> !it.equals(otherString));
    }

    public static SingleCondition<String> isEmpty() {
        return new SingleCondition<>(String::isEmpty);
    }

    public static SingleCondition<String> isNotEmpty() {
        return new SingleCondition<>(it -> !it.isEmpty());
    }
}
