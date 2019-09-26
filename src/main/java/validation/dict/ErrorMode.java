package validation.dict;

import validation.Validation;
import validation.domain.AbstractBaseValidation;

/**
 * The {@code ErrorMode} enum represents a mode
 * that should be used to report about performed validation.
 * <p>
 * Mode can dramatically change validation behavior,
 * so the attention should be paid to this.
 * <p>
 * If none {@code ErrorMode} is specified for parent
 * {@link Validation} than the {@code THROW} mode will be used.
 * <p>
 * For inner {@link Validation} created by {@link AbstractBaseValidation#deepInspecting}
 * default {@code ErrorMode} is set to {@code RETURN} and this cannot be changed.
 */
public enum ErrorMode {

    /**
     * The {@code ErrorMode} that is used to raise an exception on
     * validation errors.
     * <p>
     * This is the default mode for parent {@link Validation}.
     */
    THROW,

    /**
     * The {@code ErrorMode} that is used to return a list of validation errors.
     * <p>
     * This is the default mode for inner {@link Validation}.
     */
    RETURN
}
