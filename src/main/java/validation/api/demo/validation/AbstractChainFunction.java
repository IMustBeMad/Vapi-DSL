package validation.api.demo.validation;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import validation.api.demo.SystemMessage;
import validation.api.demo.validation.result.ValidationResult;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public abstract class AbstractChainFunction<P> implements ChainFunction {

    protected @NonNull P param;

    protected PredicateCluster cluster = new PredicateCluster();
    protected List<PredicateCluster> clusters = new ArrayList<>(List.of(this.cluster));

    @Override
    public abstract AbstractChainFunction<P> and();

    @Override
    public abstract AbstractChainFunction<P> or();

    @Override
    public void validate() {
        List<SystemMessage> messages = new ArrayList<>();

        for (PredicateCluster predicateCluster : this.clusters) {
            List<SystemMessage> errors = predicateCluster.computeErrors();

            if (errors.isEmpty()) {
                return;
            }

            messages.addAll(errors);
        }

        throwOnErrors(messages);
    }

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
    public void computeFails() {
        List<SystemMessage> messages = this.clusters.stream()
                                                    .map(PredicateCluster::computeErrors)
                                                    .flatMap(Collection::stream)
                                                    .collect(Collectors.toList());

        throwOnErrors(messages);
    }

    private void throwOnErrors(List<SystemMessage> messages) {
        if (!messages.isEmpty()) {
            throw new RuntimeException(messages.stream().map(SystemMessage::getReasonCode).collect(Collectors.joining("\n")));
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
