package validation.api.demo.validation.dict;

import validation.api.demo.validation.Validation;

/**
 * Represents a purpose of validation.
 *
 * For {@link Validation#succeedIf(Object)} {@link PurposeMode} will be set to {@code SUCCESS}.
 * For {@link Validation#failIf(Object)} (Object)} {@link PurposeMode} will be set to {@code FAIL}.
 */
public enum PurposeMode {

    /**
     * Mode that used for {@link Validation#succeedIf(Object)} validations.
     */
    SUCCESS,

    /**
     * Mode that used for {@link Validation#failIf(Object)} validations.
     */
    FAIL
}
