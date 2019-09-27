package vapidsl.dict;

import vapidsl.common.LinkedCondition;

/**
 * {@code Clause} enum is used to link condition serial in {@link LinkedCondition}
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
