package vapidsl.domain.array;

import vapidsl.common.SingleCondition;

import java.util.Arrays;

public class ArrayConditions {

    public static <T> SingleCondition<T[]> contains(T element) {
        return new SingleCondition<>(array -> Arrays.asList(array).contains(element));
    }

    public static <T> SingleCondition<T[]> hasSize(int size) {
        return new SingleCondition<>(array -> array.length == size);
    }

    public static <T> SingleCondition<T[]> isEmpty() {
        return new SingleCondition<>(array -> array.length == 0);
    }

    public static <T> SingleCondition<T[]> isNotEmpty() {
        return new SingleCondition<>(array -> array.length > 0);
    }
}
