package validation.api.demo.validation.domain;

import validation.api.demo.exception.SystemMessage;
import validation.api.demo.exception.ValidationException;
import validation.api.demo.validation.common.Condition;
import validation.api.demo.validation.common.ConditionCluster;
import validation.api.demo.validation.common.SingleCondition;
import validation.api.demo.validation.result.ValidationResult;
import validation.api.demo.validation.tester.impl.LinkedConditionTester;
import validation.api.demo.validation.tester.impl.SingleConditionTester;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;


public abstract class ValidationHolder<T> {

    protected T obj;
    private ConditionCluster<T> currentCluster = new ConditionCluster<>();
    private List<ConditionCluster<T>> conditionClusters = Arrays.asList(this.currentCluster);

    public void failFast() {
        List<Condition<T>> conditions = this.currentCluster.getConditions();

        for (Condition<T> condition : conditions) {
            ValidationResult result = this.test(condition);

            if (!result.isValid()) {
                throw ValidationException.withError(result.getReason());
            }
        }
    }

    public void failSafe() {
        List<SystemMessage> systemMessages = this.examine();

        if (!systemMessages.isEmpty()) {
            throw ValidationException.withError(systemMessages);
        }
    }

    public List<SystemMessage> examine() {
        List<Condition<T>> conditions = this.currentCluster.getConditions();

        return conditions.stream()
                         .map(this::test)
                         .filter(it -> !it.isValid())
                         .map(ValidationResult::getReason)
                         .collect(Collectors.toList());
    }

    protected void preTest(SingleCondition<T> condition, String onError) {
        ValidationResult result = this.test(toSingleCondition(condition, onError));

        if (!result.isValid()) {
            throw ValidationException.withError(result.getReason());
        }
    }

    protected void memoize(Condition<T> condition) {
        this.currentCluster.add(condition);
    }

    protected List<Condition<T>> innerExamine() {
        return this.conditionClusters.stream()
                                     .map(ConditionCluster::getConditions)
                                     .flatMap(Collection::stream)
                                     .filter(it -> !this.test(it).isValid())
                                     .collect(Collectors.toList());
    }

    protected void registerCluster() {
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
        boolean singleCondition = condition.getPredicates().size() == 1;

        return singleCondition ? SingleConditionTester.INSTANCE.test(condition, this.obj)
                               : LinkedConditionTester.INSTANCE.test(condition, this.obj);
    }
}
