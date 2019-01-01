package validation.api.demo.validation;

import lombok.*;
import validation.api.demo.SystemMessage;
import validation.api.demo.validation.result.ValidationResult;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public abstract class AbstractChainFunction<P> implements ChainFunction {

    protected @NonNull P param;

    protected PredicateCluster cluster = new PredicateCluster();
    private List<PredicateCluster> clusters = Arrays.asList(cluster);

    @Override
    public abstract AbstractChainFunction<P> and();

    @Override
    public abstract AbstractChainFunction<P> or();

    @Override
    public void failFast() {
        for (PredicateCluster predicateCluster : this.clusters) {
            SystemMessage error = predicateCluster.getFirstError();

            if (error != null) {
                throw new RuntimeException(error.getReasonCode());
            }
        }
    }

    @Override
    public void computeAndFail() {
        List<SystemMessage> messages = this.clusters.stream()
                                                    .map(PredicateCluster::computeErrors)
                                                    .flatMap(Collection::stream)
                                                    .collect(Collectors.toList());

        if (!messages.isEmpty()) {
            throw new RuntimeException("bulk message");
        }
    }

    @Getter
    @Setter
    class PredicateCluster {
        private List<Validation<P>> validations = new ArrayList<>();

        boolean push(Predicate<P> predicate, String errorMsg) {
            return this.validations.add(new Validation<>(predicate, errorMsg));
        }

        SystemMessage getFirstError() {
            for (Validation<P> validation : this.validations) {
                ValidationResult result = validation.test(param);

                if (!result.isValid()) {
                    return result.getReason();
                }
            }

            return null;
        }

        List<SystemMessage> computeErrors() {
            return this.validations.stream()
                                   .map(it -> it.test(param))
                                   .filter(it -> !it.isValid())
                                   .map(ValidationResult::getReason)
                                   .collect(Collectors.toList());
        }
    }
}
