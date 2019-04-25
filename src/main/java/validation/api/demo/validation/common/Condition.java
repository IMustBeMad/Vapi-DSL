package validation.api.demo.validation.common;

import java.util.List;
import java.util.function.Predicate;

public interface Condition<T> {

    List<Predicate<T>> getPredicates();

    String getOnError();
}
