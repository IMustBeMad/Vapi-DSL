package validation.api.demo.validation.terminator.impl;

import validation.api.demo.validation.common.ConditionCluster;
import validation.api.demo.validation.common.ValidationError;
import validation.api.demo.validation.result.ValidationResult;
import validation.api.demo.validation.terminator.Terminator;
import validation.api.demo.validation.tester.impl.TesterFacade;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public enum TernaryTerminator implements Terminator {
    INSTANCE;

    @Override
    public <T> List<ValidationError> failFast(ConditionCluster<T> conditionCluster, T obj) {
        throw new UnsupportedOperationException();
    }

    @Override
    public <T> List<ValidationError> failSafe(ConditionCluster<T> conditionCluster, T obj) {
        throw new UnsupportedOperationException();
    }

    @Override
    public <T> List<ValidationError> failOnNoneGroupMatched(List<ConditionCluster<T>> conditionClusters, T obj) {
        TesterFacade tester = TesterFacade.INSTANCE;
        List<ValidationError> validationErrors = new ArrayList<>();

        for (ConditionCluster<T> conditionCluster : conditionClusters) {
            ValidationResult result = getFirstError(tester, conditionCluster, obj);

            if (result == null) {
                return Collections.emptyList();
            }
            validationErrors.addAll(getErrorReason(Collections.singletonList(result), conditionCluster.getOnError()));
        }

        return validationErrors;
    }

    @Override
    public <T> List<ValidationError> failOnFirstGroupMatched(List<ConditionCluster<T>> conditionClusters, T obj) {
        TesterFacade tester = TesterFacade.INSTANCE;

        for (ConditionCluster<T> conditionCluster : conditionClusters) {
            ValidationResult result = getFirstError(tester, conditionCluster, obj);

            if (result == null) {
                return getErrorReason(conditionCluster);
            }
        }

        return Collections.emptyList();
    }

    private <T> ValidationResult getFirstError(TesterFacade tester, ConditionCluster<T> conditionCluster, T obj) {
        return conditionCluster.getConditions().stream()
                               .map(condition -> tester.test(condition, obj))
                               .filter(result -> !result.isValid())
                               .findFirst()
                               .orElse(null);
    }
}
