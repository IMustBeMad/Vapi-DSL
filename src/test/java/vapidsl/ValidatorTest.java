package vapidsl;

import lombok.*;

public class ValidatorTest {

    protected static final String IS_NULL = "is.null";
    protected static final String IS_NOT_NULL = "is.not.null";
    protected static final String IS_NOT_EQUAL = "is.not.equal";
    protected static final String IS_EQUAL = "is.equal";
    protected static final String IS_EMPTY = "is.empty";
    protected static final String IS_NOT_EMPTY = "is.not.empty";
    protected static final String IS_TRUE = "is.true";
    protected static final String IS_FALSE = "is.false";
    protected static final String IS_NOT_AFTER = "is.not.after";
    protected static final String IS_AFTER = "is.not.after";
    protected static final String IS_NOT_BEFORE = "is.not.before";
    protected static final String IS_BEFORE = "is.not.before";
    protected static final String IS_GREATER = "is.greater";
    protected static final String IS_NOT_GREATER = "is.not.greater";
    protected static final String IS_GREATER_OR_EQUAL = "is.greater.or.equal";
    protected static final String IS_NOT_GREATER_OR_EQUAL = "is.not.greater.or.equal";
    protected static final String IS_LESSER = "is.lesser";
    protected static final String IS_NOT_LESSER = "is.not.lesser";
    protected static final String IS_LESSER_OR_EQUAL = "is.lesser.or.equal";
    protected static final String IS_NOT_LESSER_OR_EQUAL = "is.not.lesser.or.equal";
    protected static final String NOT_CONTAINS = "not.contains";
    protected static final String CONTAINS = "contains";
    protected static final String NOT_SATISFIES = "not.satisfies";
    protected static final String INVALID_SIZE = "invalid.size";

    protected static final String INVALID_GROUP = "invalid.group";
    protected static final String PREFIX_INVALID_GROUP = "group:invalid.group";
    protected static final String DEFAULT_EXCEPTION = "validation:validation.error.no.error.code";

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @EqualsAndHashCode
    protected static class Obj {

        private String prop;
    }
}
