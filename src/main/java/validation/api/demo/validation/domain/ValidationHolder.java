package validation.api.demo.validation.domain;

import validation.api.demo.validation.common.Condition;
import validation.api.demo.validation.common.ConditionCluster;
import validation.api.demo.exception.SystemMessage;
import validation.api.demo.exception.ValidationException;
import validation.api.demo.validation.Validation;
import validation.api.demo.validation.common.SubConditionCluster;
import validation.api.demo.validation.result.ValidationResult;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;


public abstract class ValidationHolder<T> {

    protected T obj;
    private ConditionCluster<T> currentCluster = new ConditionCluster<>();
    private List<ConditionCluster<T>> conditionClusters = Arrays.asList(this.currentCluster);

    public void failFast() {
        for (ConditionCluster<T> cluster : this.conditionClusters) {
            for (Condition<T> condition : cluster.getConditions()) {
                ValidationResult result = this.test(condition);

                if (!result.isValid()) {
                    throw ValidationException.withError(result.getReason());
                }
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
        return this.conditionClusters.stream()
                                     .map(ConditionCluster::getConditions)
                                     .flatMap(Collection::stream)
                                     .map(this::test)
                                     .filter(it -> !it.isValid())
                                     .map(ValidationResult::getReason)
                                     .collect(Collectors.toList());
    }

    protected void memoize(Condition<T> condition) {
        this.currentCluster.add(condition);
    }

    protected void memoize(SubConditionCluster<T> subConditions) {
        this.currentCluster.add(subConditions);
    }

    protected List<Condition<T>> innerExamine() {
        return this.conditionClusters.stream()
                                     .map(ConditionCluster::getConditions)
                                     .flatMap(Collection::stream)
                                     .filter(it -> !this.test(it).isValid())
                                     .collect(Collectors.toList());
    }

    private ValidationResult test(Condition<T> condition) {
        return condition.getPredicate().test(this.obj) ? Validation.ok() : Validation.failed(condition.getOnError());
    }
}
