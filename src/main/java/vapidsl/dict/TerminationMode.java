package vapidsl.dict;

import vapidsl.common.ConditionCluster;
import vapidsl.domain.ConditionBinder;

/**
 * The {@code TerminationMode} enum represents a mode
 * that should be used while performing validation.
 * <p>
 * Depends on {@link PurposeMode} and {@link MatchMode} combination.
 */
public enum TerminationMode {

    /**
     * Represents fail fast algorithm.
     * With this mode an error will be return on first encounter.
     */
    FAIL_FAST,

    /**
     * Represents fail safe algorithm.
     * With this mode all errors will be gathered and returned for the {@link ConditionCluster}.
     */
    FAIL_SAFE,

    /**
     * Allows to fail the validation when none of the groups ({@link ConditionCluster}) have all the conditions matched.
     * First encountered errors/group errors will be gathered and returned.
     * <p>
     * {@link ConditionBinder#onError(String)}
     * {@link ConditionBinder#groupError(String)}
     */
    NONE_GROUP_MATCH,

    /**
     * Allows to fail the validation on first matched condition group ({@link ConditionCluster}).
     * All condition errors or a group error will be returned.
     * <p>
     * {@link ConditionBinder#onError(String)}
     * {@link ConditionBinder#groupError(String)}
     */
    LAZY_GROUP_MATCH
}
