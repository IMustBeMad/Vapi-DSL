package vapidsl.domain.map;

import lombok.experimental.UtilityClass;
import vapidsl.common.SingleCondition;

import java.util.Map;

@UtilityClass
public class MapConditions {

    public static <K, V> SingleCondition<Map<K, V>> isEmpty() {
        return new SingleCondition<>(it -> it == null || it.isEmpty());
    }

    public static <K, V> SingleCondition<Map<K, V>> isNotEmpty() {
        return new SingleCondition<>(it -> it != null && !it.isEmpty());
    }

    public static <V, K> SingleCondition<Map<K, V>> hasSize(int size) {
        return new SingleCondition<>(it -> it.size() == size);
    }

    public static <K, V> SingleCondition<Map<K, V>> containsKey(K keyValue) {
        return new SingleCondition<>(it -> it.containsKey(keyValue));
    }
}
