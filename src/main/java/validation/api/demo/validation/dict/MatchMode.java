package validation.api.demo.validation.dict;

/**
 * Represents the way of error gathering: lazily with {@code LAZY} and eagerly with {@code EAGER}.
 * Is used in combination with {@link PurposeMode}.
 * <p>
 * {@link TerminationMode} depends on it.
 */
public enum MatchMode {

    /**
     * Used to return first error match or not match for specific validation.
     */
    LAZY,

    /**
     * Used to gather and return all errors for specific validation.
     */
    EAGER
}
