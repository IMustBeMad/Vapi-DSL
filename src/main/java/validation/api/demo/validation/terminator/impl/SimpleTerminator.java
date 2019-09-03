package validation.api.demo.validation.terminator.impl;

import validation.api.demo.validation.Validation;
import validation.api.demo.validation.common.Condition;
import validation.api.demo.validation.common.ConditionCluster;
import validation.api.demo.validation.dict.FlowType;
import validation.api.demo.validation.exception.SystemMessage;
import validation.api.demo.validation.result.ValidationResult;
import validation.api.demo.validation.terminator.Terminator;
import validation.api.demo.validation.tester.impl.TesterFacade;

import java.util.*;
import java.util.stream.Collectors;

import static org.apache.commons.lang3.StringUtils.isEmpty;

public enum SimpleTerminator implements Terminator {
    INSTANCE;

    @Override
    public <T> List<SystemMessage> unMatchLazily(ConditionCluster<T> conditionCluster, T obj) {
        TesterFacade tester = TesterFacade.INSTANCE;
        List<Condition<T>> conditions = conditionCluster.getConditions();

        for (Condition<T> condition : conditions) {
            ValidationResult result = tester.test(condition, obj);

            if (!result.isValid()) {
                return getErrorReason(Collections.singletonList(result), conditionCluster.getOnError());
            }
        }

        return Collections.emptyList();
    }

    @Override
    public <T> List<SystemMessage> unMatchEagerly(ConditionCluster<T> conditionCluster, T obj) {
        TesterFacade tester = TesterFacade.INSTANCE;
        List<Condition<T>> conditions = conditionCluster.getConditions();
        Set<SystemMessage> errors = new HashSet<>();

        for (Condition<T> condition : conditions) {
            ValidationResult result = tester.test(condition, obj);

            if (!result.isValid()) {
                SystemMessage reason = result.getReason();

                if (condition.getFlowType() == FlowType.EARLY_EXIT) {
                    return Collections.singletonList(reason);
                }

                errors.addAll(getErrorReason(Collections.singletonList(result), conditionCluster.getOnError()));
            }
        }

        return new ArrayList<>(errors);
    }

    @Override
    public <T> List<SystemMessage> matchAllMatched(ConditionCluster<T> conditionCluster, T obj) {
        List<SystemMessage> systemMessages = this.unMatchEagerly(conditionCluster, obj);

        if (systemMessages.isEmpty()) {
            if (conditionCluster.getConditions().size() == 1) {
                Condition<T> condition = conditionCluster.getConditions().get(0);
                ValidationResult result = Validation.failed(condition.getOnError());

                return getErrorReason(Collections.singletonList(result), conditionCluster.getOnError());
            }

            return getErrorReason(Collections.emptyList(), conditionCluster.getOnError());
        }

        return Collections.emptyList();
    }

    @Override
    public <T> List<SystemMessage> matchNoneGroupMatched(List<ConditionCluster<T>> conditionClusters, T obj) {
        throw new UnsupportedOperationException();
    }

    @Override
    public <T> List<SystemMessage> matchByGroupLazily(List<ConditionCluster<T>> conditionClusters, T obj) {
        ConditionCluster<T> conditionCluster = conditionClusters.get(0);

        List<SystemMessage> systemMessages = this.unMatchLazily(conditionCluster, obj);

        if (systemMessages.isEmpty()) {
            String onError = conditionCluster.getOnError();

            if (!isEmpty(onError)) {
                return Collections.singletonList(SystemMessage.withError("group", onError));
            }

            return conditionCluster.getConditions().stream()
                                   .map(Condition::getOnError)
                                   .map(it -> SystemMessage.withError("", it))
                                   .collect(Collectors.toList());
        }

        return Collections.emptyList();
    }
}
