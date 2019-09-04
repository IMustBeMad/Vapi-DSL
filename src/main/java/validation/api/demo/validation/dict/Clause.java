package validation.api.demo.validation.dict;

/**
 * {@code Clause} enum is used to link condition serial in {@link validation.api.demo.validation.common.LinkedCondition}
 * object.
 */
public enum Clause {

    /**
     * Represents logical "and"(&&) for conditions.
     */
    AND,

    /**
     * Represents logical "or"(||) for conditions.
     */
    OR
}
