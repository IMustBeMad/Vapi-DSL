package vapidsl.domain.map;

import vapidsl.common.SingleCondition;

import java.util.Map;

public class MapConditions {

    public static <K, V> SingleCondition<Map<K, V>> isEmpty() {
        return new SingleCondition<>(it -> it == null || it.isEmpty());
    }

    public static <K, V> SingleCondition<Map<K, V>> isNotEmpty() {
        return new SingleCondition<>(it -> it != null && !it.isEmpty());
    }
}
