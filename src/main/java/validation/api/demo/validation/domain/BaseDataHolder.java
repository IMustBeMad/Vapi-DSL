package validation.api.demo.validation.domain;

import validation.api.demo.exception.SystemMessage;
import validation.api.demo.exception.ValidationException;
import validation.api.demo.validation.common.Condition;
import validation.api.demo.validation.common.ConditionCluster;
import validation.api.demo.validation.common.SingleCondition;
import validation.api.demo.validation.dict.ErrorMode;
import validation.api.demo.validation.dict.TerminationMode;
import validation.api.demo.validation.result.ValidationResult;
import validation.api.demo.validation.terminator.impl.TerminatorFacade;
import validation.api.demo.validation.tester.impl.TesterFacade;

import java.util.Arrays;
import java.util.List;


public abstract class BaseDataHolder<T> {

    protected T obj;
    protected TerminationMode terminationMode;
    private List<SystemMessage> errors;

    private ConditionCluster<T> currentCluster = new ConditionCluster<>();
    private List<ConditionCluster<T>> conditionClusters = Arrays.asList(this.currentCluster);

    protected List<SystemMessage> failOn(TerminationMode terminationMode) {
        return this.failOn(terminationMode, ErrorMode.THROW);
    }

    protected List<SystemMessage> failOn(TerminationMode terminationMode, ErrorMode errorMode) {
        this.terminationMode = terminationMode;

        return this.terminate(terminationMode, errorMode);
    }

    List<SystemMessage> getError() {
        return this.errors;
    }

    List<SystemMessage> terminate(TerminationMode terminationMode, ErrorMode errorMode) {
        List<SystemMessage> systemMessages = TerminatorFacade.INSTANCE.terminate(terminationMode, errorMode, this.conditionClusters, this.obj);
        this.errors = systemMessages;

        return systemMessages;
    }

    void memoize(Condition<T> condition) {
        this.currentCluster.add(condition);
    }

    void registerCluster() {
        ConditionCluster<T> conditionCluster = new ConditionCluster<>();

        this.conditionClusters.add(conditionCluster);
        this.currentCluster = conditionCluster;
    }

    protected void registerCondition(SingleCondition<T> condition, String onError) {
        this.memoize(toSingleCondition(condition, onError));
    }

    void preTest(SingleCondition<T> condition, String onError) {
        ValidationResult result = this.test(toSingleCondition(condition, onError));

        if (!result.isValid()) {
            throw ValidationException.withError(result.getReason());
        }
    }

    private SingleCondition<T> toSingleCondition(SingleCondition<T> condition, String onError) {
        SingleCondition<T> singleCondition = new SingleCondition<>();
        singleCondition.setPredicate(condition.getPredicate());
        singleCondition.setOnError(onError);

        return singleCondition;
    }

    private ValidationResult test(Condition<T> condition) {
        return TesterFacade.INSTANCE.test(condition, this.obj);
    }
}
