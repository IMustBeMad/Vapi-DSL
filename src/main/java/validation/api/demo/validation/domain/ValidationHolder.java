package validation.api.demo.validation.domain;

import validation.api.demo.common.Condition;
import validation.api.demo.common.ConditionCluster;
import validation.api.demo.exception.ValidationException;
import validation.api.demo.validation.Validation;
import validation.api.demo.validation.result.ValidationResult;

import java.util.Arrays;
import java.util.List;


public abstract class ValidationHolder<T> {

    protected T obj;
    private ConditionCluster<T> currentCluster = new ConditionCluster<>();
    private List<ConditionCluster<T>> conditionClusters = Arrays.asList(this.currentCluster);

    public void failFast() {
        for (ConditionCluster<T> cluster : this.conditionClusters) {
            for (Condition<T> condition : cluster.getConditions()) {
                ValidationResult result = this.test(condition);

                if (!result.isValid()) {
                    throw new ValidationException(result.getReason());
                }
            }
        }
    }

    protected void memoize(Condition<T> condition) {
        this.currentCluster.add(condition);
    }

    private ValidationResult test(Condition<T> condition) {
        return condition.getPredicate().test(this.obj) ? Validation.ok() : Validation.failed(condition.getOnError());
    }
}
