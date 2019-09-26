package validation.api.demo.validation.common;

import lombok.*;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ValidationError {

    private String field;
    private String reasonCode;

    public static ValidationError of(String field, String reasonCode) {
        return new ValidationError(field, reasonCode);
    }

    public static ValidationError withCode(String reasonCode) {
        return new ValidationError("", reasonCode);
    }
}
