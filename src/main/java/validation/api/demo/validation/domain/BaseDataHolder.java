package validation.api.demo.validation.domain;

import validation.api.demo.exception.SystemMessage;
import validation.api.demo.validation.common.Condition;
import validation.api.demo.validation.common.ConditionCluster;
import validation.api.demo.validation.common.SingleCondition;
import validation.api.demo.validation.dict.ErrorMode;
import validation.api.demo.validation.dict.TerminationMode;
import validation.api.demo.validation.terminator.impl.TerminatorFacade;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public abstract class BaseDataHolder<T> {

    protected T obj;
    private TerminationMode terminationMode;
    private ErrorMode errorMode;
    private List<SystemMessage> errors;

    private ConditionCluster<T> currentCluster = new ConditionCluster<>();
    private List<ConditionCluster<T>> conditionClusters = new ArrayList<>(Collections.singletonList(this.currentCluster));
    private Condition<T> currentCondition;

    protected List<SystemMessage> examine() {
        if (this.terminationMode == null || this.errorMode == null) {
            this.terminationMode = getDefaultTerminationMode();
            this.errorMode = getDefaultErrorMode();
        }

        return this.terminate(this.terminationMode, this.errorMode);
    }

    protected void registerCondition(SingleCondition<T> condition) {
        SingleCondition<T> conditionToRegister = toSingleCondition(condition);
        this.currentCondition = conditionToRegister;

        this.memoize(conditionToRegister);
    }

    List<SystemMessage> getError() {
        return this.errors;
    }

    Condition<T> getCurrentCondition() {
        return this.currentCondition;
    }

    void memoize(Condition<T> condition) {
        this.currentCluster.add(condition);
    }

    void registerCluster() {
        ConditionCluster<T> conditionCluster = new ConditionCluster<>();

        this.conditionClusters.add(conditionCluster);
        this.currentCluster = conditionCluster;
    }

    void registerModes(TerminationMode terminationMode, ErrorMode errorMode) {
        this.terminationMode = terminationMode;
        this.errorMode = errorMode;
    }

    private List<SystemMessage> terminate(TerminationMode terminationMode, ErrorMode errorMode) {
        List<SystemMessage> systemMessages = TerminatorFacade.INSTANCE.terminate(terminationMode, errorMode, this.conditionClusters, this.obj);
        this.errors = systemMessages;

        return systemMessages;
    }

    private SingleCondition<T> toSingleCondition(SingleCondition<T> condition) {
        SingleCondition<T> singleCondition = new SingleCondition<>();
        singleCondition.setPredicate(condition.getPredicate());

        return singleCondition;
    }

    private ErrorMode getDefaultErrorMode() {
        return ErrorMode.THROW;
    }

    private TerminationMode getDefaultTerminationMode() {
        return conditionClusters.size() == 1 ? TerminationMode.FIRST_ERROR_ENCOUNTERED
                                             : TerminationMode.NONE_GROUP_MATCH;
    }
}
