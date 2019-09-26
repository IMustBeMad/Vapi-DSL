package validation.common;

import validation.dict.FlowType;

import java.util.List;
import java.util.function.Predicate;

public interface Condition<T> {

    List<Predicate<T>> getPredicates();

    String getOnError();

    void setOnError(String error);

    FlowType getFlowType();
}
