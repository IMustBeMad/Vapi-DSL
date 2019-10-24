package vapidsl.terminator.impl;

import vapidsl.common.ConditionCluster;
import vapidsl.result.ValidationResult;
import vapidsl.terminator.Terminator;
import vapidsl.tester.impl.TesterFacade;

import java.util.ArrayList;
import java.util.List;

public enum TernaryTerminator implements Terminator {
    INSTANCE;

    @Override
    public <T> ValidationResult failFast(ConditionCluster<T> conditionCluster, T obj) {
        throw new UnsupportedOperationException();
    }

    @Override
    public <T> ValidationResult failSafe(ConditionCluster<T> conditionCluster, T obj) {
        throw new UnsupportedOperationException();
    }

    @Override
    public <T> ValidationResult failOnNoneGroupMatched(List<ConditionCluster<T>> conditionClusters, T obj) {
        TesterFacade tester = TesterFacade.INSTANCE;
        List<ValidationResult> validationResults = new ArrayList<>();

        for (ConditionCluster<T> conditionCluster : conditionClusters) {
            ValidationResult result = getFirstError(tester, conditionCluster, obj);

            if (result.isValid()) {
                return result;
            }

            validationResults.add(getResult(result, conditionCluster));
        }

        return getResult(validationResults);
    }

    @Override
    public <T> ValidationResult failOnFirstGroupMatched(List<ConditionCluster<T>> conditionClusters, T obj) {
        TesterFacade tester = TesterFacade.INSTANCE;

        for (ConditionCluster<T> conditionCluster : conditionClusters) {
            ValidationResult result = getFirstError(tester, conditionCluster, obj);

            if (result.isValid()) {
                return this.getResult(conditionCluster);
            }
        }

        return ValidationResult.ok();
    }

    private <T> ValidationResult getFirstError(TesterFacade tester, ConditionCluster<T> conditionCluster, T obj) {
        return conditionCluster.getConditions().stream()
                               .map(condition -> tester.test(condition, obj))
                               .filter(result -> !result.isValid())
                               .findFirst()
                               .orElse(ValidationResult.ok());
    }
}
