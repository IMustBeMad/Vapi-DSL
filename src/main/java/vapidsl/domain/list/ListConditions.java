package vapidsl.domain.list;

import vapidsl.common.SingleCondition;

import java.util.HashSet;
import java.util.List;

public class ListConditions {

    public static <T> SingleCondition<List<T>> contains(T element) {
        return new SingleCondition<>(it -> it.contains(element));
    }

    public static <T> SingleCondition<List<T>> hasSize(int size) {
        return new SingleCondition<>(it -> it.size() == size);
    }

    public static <T> SingleCondition<List<T>> hasNoDuplicates() {
        return new SingleCondition<>(it -> new HashSet<>(it).size() == it.size());
    }

    public static <T> SingleCondition<List<T>> isEmpty() {
        return new SingleCondition<>(List::isEmpty);
    }

    public static <T> SingleCondition<List<T>> isNotEmpty() {
        return new SingleCondition<>(it -> !it.isEmpty());
    }
}
