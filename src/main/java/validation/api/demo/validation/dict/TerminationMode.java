package validation.api.demo.validation.dict;

import validation.api.demo.validation.Validation;
import validation.api.demo.validation.common.ConditionCluster;

/**
 * The {@code TerminationMode} enum represents a mode
 * that should be used while performing validation.
 * Mode can dramatically change validation behavior,
 * so the attention should be paid to this.
 * <p>
 * If none {@code TerminationMode} is specified for
 * a {@link Validation} than the {@code FIRST_ERROR_ENCOUNTERED}
 * will be set as default if only one {@link ConditionCluster}
 * is present, otherwise {@code NONE_GROUP_MATCH} will be used.
 */
public enum TerminationMode {

    /**
     * The {@code TerminationMode} that represents fail fast algorithm.
     * With this mode an error will be raised on first encounter.
     * Used as default if only one {@link ConditionCluster} is present.
     * <p>
     * Usable when validation has only one {@link ConditionCluster}.
     */
    FIRST_ERROR_ENCOUNTERED,

    /**
     * The {@code TerminationMode} that represents fail safe algorithm.
     * With this mode an error will be raised after all the errors are gathered.
     * <p>
     * Usable when validation has only one {@link ConditionCluster}.
     */
    LAST_ERROR_ENCOUNTERED,

    /**
     * The {@code TerminationMode} that allows to fail the validation
     * when all the conditions are suitable.
     * With this mode an error will be raised on first encounter of suitable condition.
     * <p>
     * Usable when validation has only one {@link ConditionCluster}.
     */
    NO_ERROR_ENCOUNTERED,

    /**
     * The {@code TerminationMode} that allows to fail the validation
     * when none of the {@link ConditionCluster} have all the conditions matched.
     * With this mode an error will be raised if none {@link ConditionCluster} is matched.
     * <p>
     * If {@link ConditionCluster} is only one, this mode will behave as {@code FIRST_ERROR_ENCOUNTERED} mode.
     * <p>
     * Used as default if more than one {@link ConditionCluster} is present.
     */
    NONE_GROUP_MATCH,

    /**
     * The {@code TerminationMode} that allows to fail on first matched {@link ConditionCluster}.
     * With this mode an error will be raised on first encounter of matched {@link ConditionCluster}.
     * <p>
     * If {@link ConditionCluster} is only one, this mode will behave as {@code NO_ERROR_ENCOUNTERED} mode.
     */
    FIRST_GROUP_MATCH
}
