package validation.api.demo.validation.common;

import validation.api.demo.validation.dict.FailureMode;

import java.util.List;
import java.util.function.Predicate;

public interface Condition<T> {

    List<Predicate<T>> getPredicates();

    String getOnError();

    void setOnError(String error);

    FailureMode getFailureMode();
}
