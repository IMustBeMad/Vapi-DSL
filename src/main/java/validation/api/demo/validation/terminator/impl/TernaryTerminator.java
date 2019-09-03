package validation.api.demo.validation.terminator.impl;

import validation.api.demo.validation.common.Condition;
import validation.api.demo.validation.common.ConditionCluster;
import validation.api.demo.validation.exception.SystemMessage;
import validation.api.demo.validation.result.ValidationResult;
import validation.api.demo.validation.terminator.Terminator;
import validation.api.demo.validation.tester.impl.TesterFacade;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static org.apache.commons.lang3.StringUtils.isEmpty;

public enum TernaryTerminator implements Terminator {
    INSTANCE;

    @Override
    public <T> List<SystemMessage> unMatchLazily(ConditionCluster<T> conditionCluster, T obj) {
        throw new UnsupportedOperationException();
    }

    @Override
    public <T> List<SystemMessage> unMatchEagerly(ConditionCluster<T> conditionCluster, T obj) {
        throw new UnsupportedOperationException();
    }

    @Override
    public <T> List<SystemMessage> matchAllMatched(ConditionCluster<T> conditionCluster, T obj) {
        throw new UnsupportedOperationException();
    }

    @Override
    public <T> List<SystemMessage> matchNoneGroupMatched(List<ConditionCluster<T>> conditionClusters, T obj) {
        TesterFacade tester = TesterFacade.INSTANCE;
        List<SystemMessage> systemMessages = new ArrayList<>();

        for (ConditionCluster<T> conditionCluster : conditionClusters) {
            ValidationResult result = getFirstError(tester, conditionCluster, obj);

            if (result == null) {
                return Collections.emptyList();
            }
            systemMessages.addAll(getErrorReason(Collections.singletonList(result), conditionCluster.getOnError()));
        }

        return systemMessages;
    }

    @Override
    public <T> List<SystemMessage> matchByGroupLazily(List<ConditionCluster<T>> conditionClusters, T obj) {
        TesterFacade tester = TesterFacade.INSTANCE;

        for (ConditionCluster<T> conditionCluster : conditionClusters) {
            ValidationResult result = getFirstError(tester, conditionCluster, obj);

            if (result == null) {
                String onError = conditionCluster.getOnError();

                if (!isEmpty(onError)) {
                    return Collections.singletonList(SystemMessage.withError("group", onError));
                }

                return conditionCluster.getConditions().stream()
                                       .map(Condition::getOnError)
                                       .map(it -> SystemMessage.withError("", it))
                                       .collect(Collectors.toList());
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
