package validation.api.demo.validation.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import validation.api.demo.validation.common.*;
import validation.api.demo.validation.dict.*;
import validation.api.demo.validation.exception.SystemMessage;
import validation.api.demo.validation.terminator.impl.TerminatorFacade;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;


public abstract class BaseDataHolder<T> {

    protected T obj;
    protected ModeManager modeManager;

    private @Getter(AccessLevel.PACKAGE) ConditionCluster<T> currentCluster = new ConditionCluster<>();
    private @Getter(AccessLevel.PACKAGE) Condition<T> currentCondition;
    private @Getter(AccessLevel.PACKAGE) List<SystemMessage> errors;

    private List<ConditionCluster<T>> conditionClusters = new ArrayList<>(Collections.singletonList(this.currentCluster));

    protected List<SystemMessage> examine() {
        return examine(ErrorMode.THROW);
    }

    protected List<SystemMessage> examine(ErrorMode errorMode) {
        this.modeManager.setErrorMode(errorMode);

        return this.terminate();
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

        return new ValidationCondition<>(it -> innerValidation.examine(ErrorMode.RETURN).isEmpty(), innerValidation::getErrors);
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

    private List<SystemMessage> terminate() {
        List<SystemMessage> systemMessages = TerminatorFacade.INSTANCE.terminate(this.modeManager, this.conditionClusters, this.obj);
        this.errors = systemMessages;

        return systemMessages;
    }

    private SingleCondition<T> copyCondition(SingleCondition<T> condition) {
        SingleCondition<T> singleCondition = new SingleCondition<>();
        singleCondition.setPredicate(condition.getPredicate());

        return singleCondition;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    public class ModeManager {

        private MatchMode matchMode;
        private PurposeMode purposeMode;
        private ErrorMode errorMode;

        public ModeManager(MatchMode matchMode, PurposeMode purposeMode) {
            this.matchMode = matchMode;
            this.purposeMode = purposeMode;
        }
    }
}
