package vapidsl.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import vapidsl.common.*;
import vapidsl.dict.ErrorMode;
import vapidsl.dict.MatchMode;
import vapidsl.dict.PurposeMode;
import vapidsl.exception.ValidationException;
import vapidsl.result.ValidationResult;
import vapidsl.terminator.impl.TerminatorFacade;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.function.BiPredicate;
import java.util.function.Predicate;
import java.util.function.Supplier;


public abstract class Binder<T, SELF extends Binder<T, SELF>> {

    protected SELF self;

    protected T obj;
    protected ModeManager modeManager;

    protected final ConditionMapper<T> mapper = new ConditionMapper<>();
    protected final Registrar registrar = new Registrar();

    private @Getter(AccessLevel.PACKAGE) ConditionCluster<T> currentCluster = new ConditionCluster<>();
    private @Getter(AccessLevel.PACKAGE) Condition<T> currentCondition;
    private @Getter(AccessLevel.PACKAGE) ValidationResult result;

    private List<ConditionCluster<T>> conditionClusters = new ArrayList<>(Collections.singletonList(this.currentCluster));

    @SuppressWarnings("unchecked")
    Binder(Class<?> selfType) {
        this.self = (SELF) selfType.cast(this);
    }

    protected List<ValidationError> examine() {
        return examine(ErrorMode.THROW);
    }

    protected List<ValidationError> examine(ErrorMode errorMode) {
        this.modeManager.setErrorMode(errorMode);
        this.terminate();

        return getValidationResult(self.getResult());
    }

    private List<ValidationError> getValidationResult(ValidationResult validationResult) {
        List<ValidationError> reason = validationResult.getReason();

        if (validationResult.isValid()) {
            return reason;
        }

        return reason.isEmpty() ? Collections.singletonList(ValidationError.of("validation", "validation.error.no.error.code"))
                                : reason;
    }

    private void terminate() {
        this.result = TerminatorFacade.INSTANCE.terminate(this.modeManager, this.conditionClusters, this.obj);

        if (this.modeManager.getErrorMode() == ErrorMode.THROW && !this.result.isValid()) {
            throw ValidationException.withError(getValidationResult(this.result));
        }
    }

    @Getter
    @Setter
    @NoArgsConstructor
    public static class ModeManager {

        private MatchMode matchMode;
        private PurposeMode purposeMode;
        private ErrorMode errorMode;

        public ModeManager(MatchMode matchMode, PurposeMode purposeMode) {
            this.matchMode = matchMode;
            this.purposeMode = purposeMode;
        }
    }

    protected class Registrar {

        public void registerCondition(Condition<T> condition) {
            currentCondition = condition;
            currentCluster.add(condition);
        }

        void registerCluster() {
            ConditionCluster<T> conditionCluster = new ConditionCluster<>();

            conditionClusters.add(conditionCluster);
            currentCluster = conditionCluster;
        }
    }

    protected static class ConditionMapper<T> {

        public <R> SingleCondition<T> toCondition(R obj, Predicate<R> predicate) {
            return new SingleCondition<>(it -> predicate.test(obj));
        }

        public <K, V> SingleCondition<T> toCondition(Map.Entry<K, V> obj, BiPredicate<? super K, ? super V> biPredicate) {
            return new SingleCondition<>(it -> biPredicate.test(obj.getKey(), obj.getValue()));
        }

        public <R, OTHER extends ConditionBinder<R, OTHER>> ValidationCondition<T> toCondition(Supplier<ConditionBinder<R, OTHER>> validationSupplier) {
            return new ValidationCondition<>(() -> validationSupplier.get().examine(ErrorMode.RETURN));
        }
    }
}
