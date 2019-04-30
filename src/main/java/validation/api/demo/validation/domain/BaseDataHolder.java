package validation.api.demo.validation.domain;

import validation.api.demo.exception.SystemMessage;
import validation.api.demo.exception.ValidationException;
import validation.api.demo.validation.common.Condition;
import validation.api.demo.validation.common.ConditionCluster;
import validation.api.demo.validation.common.SingleCondition;
import validation.api.demo.validation.result.ValidationResult;
import validation.api.demo.validation.terminator.impl.TerminatorFacade;
import validation.api.demo.validation.tester.impl.TesterFacade;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;


public abstract class BaseDataHolder<T> {

    protected T obj;
    private ConditionCluster<T> currentCluster = new ConditionCluster<>();
    private List<ConditionCluster<T>> conditionClusters = Arrays.asList(this.currentCluster);

    protected void failFast() {
        TerminatorFacade.INSTANCE.failFast(this.conditionClusters, this.obj);
    }

    protected void failSafe() {
        TerminatorFacade.INSTANCE.failSafe(this.conditionClusters, this.obj);
    }

    protected List<SystemMessage> examine() {
        return TerminatorFacade.INSTANCE.examine(this.conditionClusters, this.obj);
    }

    protected void failIfNoneGroupMatch() {
        TerminatorFacade.INSTANCE.failIfNoneGroupMatch(this.conditionClusters, this.obj);
    }

    void preTest(SingleCondition<T> condition, String onError) {
        ValidationResult result = this.test(toSingleCondition(condition, onError));

        if (!result.isValid()) {
            throw ValidationException.withError(result.getReason());
        }
    }

    void memoize(Condition<T> condition) {
        this.currentCluster.add(condition);
    }

    List<Condition<T>> innerExamine() {
        return this.conditionClusters.stream()
                                     .map(ConditionCluster::getConditions)
                                     .flatMap(Collection::stream)
                                     .filter(it -> !this.test(it).isValid())
                                     .collect(Collectors.toList());
    }

    void registerCluster() {
        ConditionCluster<T> conditionCluster = new ConditionCluster<>();

        this.conditionClusters.add(conditionCluster);
        this.currentCluster = conditionCluster;
    }

    protected void registerCondition(SingleCondition<T> condition, String onError) {
        this.memoize(toSingleCondition(condition, onError));
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
