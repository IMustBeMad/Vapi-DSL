package vapidsl;

import lombok.*;

public class ValidatorTest {

    protected static final String IS_NULL = "is.null";
    protected static final String IS_NOT_NULL = "is.not.null";
    protected static final String IS_NOT_EQUAL = "is.not.equal";
    protected static final String IS_EMPTY = "is.empty";
    protected static final String IS_NOT_EMPTY = "is.not.empty";

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @EqualsAndHashCode
    protected static class Obj {

        private String prop;
    }
}
