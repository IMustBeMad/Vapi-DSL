package validation.api.demo.validation.tester;

import validation.api.demo.validation.Validation;
import validation.api.demo.validation.common.Condition;
import validation.api.demo.validation.result.ValidationResult;
import validation.api.demo.validation.tester.impl.TesterFacade;

public interface Tester {

    <T> ValidationResult test(Condition<T> condition, T obj, TesterFacade.TestMode testMode);

    default ValidationResult getResult(boolean isValid, String onError, TesterFacade.TestMode testMode) {
        if (testMode == TesterFacade.TestMode.INVERTED) {
            return isValid ? Validation.failed(onError) : Validation.ok();
        }

        return isValid ? Validation.ok() : Validation.failed(onError);
    }
}
