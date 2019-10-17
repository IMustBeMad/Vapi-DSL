package vapidsl.common;

import vapidsl.dict.FlowType;

import java.util.List;
import java.util.function.Predicate;

public interface Condition<T> {

    List<Predicate<T>> getPredicates();

    List<ValidationError> getOnError();

    void setOnError(List<ValidationError> errors);

    FlowType getFlowType();
}
