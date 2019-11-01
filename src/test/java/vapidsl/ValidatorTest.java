package vapidsl;

import lombok.*;

public class ValidatorTest {

    protected static final String IS_NULL = "is.null";
    protected static final String IS_NOT_NULL = "is.not.null";
    protected static final String IS_NOT_EQUAL = "is.not.equal";
    protected static final String IS_EQUAL = "is.equal";
    protected static final String IS_EMPTY = "is.empty";
    protected static final String IS_NOT_EMPTY = "is.not.empty";
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
