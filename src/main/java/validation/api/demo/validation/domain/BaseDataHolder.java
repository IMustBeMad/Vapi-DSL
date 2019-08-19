package validation.api.demo.validation.domain;

import validation.api.demo.validation.exception.SystemMessage;
import validation.api.demo.validation.common.*;
import validation.api.demo.validation.dict.ErrorMode;
import validation.api.demo.validation.dict.TerminationMode;
import validation.api.demo.validation.terminator.impl.TerminatorFacade;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;


public abstract class BaseDataHolder<T> {

    protected T obj;
    private TerminationMode terminationMode;
    private ErrorMode errorMode;
    private List<SystemMessage> errors;

    private ConditionCluster<T> currentCluster = new ConditionCluster<>();
    private Condition<T> currentCondition;
    private List<ConditionCluster<T>> conditionClusters = new ArrayList<>(Collections.singletonList(this.currentCluster));

    List<SystemMessage> getError() {
        return this.errors;
    }

    Condition<T> getCurrentCondition() {
        return this.currentCondition;
    }

    ConditionCluster<T> getCurrentCluster() {
        return this.currentCluster;
    }

    protected List<SystemMessage> examine() {
        if (this.terminationMode == null) {
            this.terminationMode = getDefaultTerminationMode();
        }
        if (this.errorMode == null) {
            this.errorMode = getDefaultErrorMode();
        }

        return this.terminate(this.terminationMode, this.errorMode);
    }

    protected void registerCondition(SingleCondition<T> condition) {
        this.memoize(copyCondition(condition));
    }

    protected void registerCondition(LinkedCondition<T> condition) {
        this.memoize(condition);
    }

    protected <R> SingleCondition<T> toCondition(R obj, Predicate<R> predicate) {
        return new SingleCondition<>(it -> predicate.test(obj));
    }

    protected <R> ValidationCondition<T> toCondition(R obj, Function<R, AbstractBaseValidation<R>> validator) {
        AbstractBaseValidation<R> innerValidation = validator.apply(obj);
        innerValidation.setDeepInspectingDefaultErrorMore();

        return new ValidationCondition<>(it -> innerValidation.examine().isEmpty(), innerValidation::getError);
    }

    void memoize(Condition<T> condition) {
        this.currentCondition = condition;
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

    void setDeepInspectingDefaultErrorMore() {
        this.errorMode = ErrorMode.RETURN;
    }

    private List<SystemMessage> terminate(TerminationMode terminationMode, ErrorMode errorMode) {
        List<SystemMessage> systemMessages = TerminatorFacade.INSTANCE.terminate(terminationMode, errorMode, this.conditionClusters, this.obj);
        this.errors = systemMessages;

        return systemMessages;
    }

    private SingleCondition<T> copyCondition(SingleCondition<T> condition) {
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
