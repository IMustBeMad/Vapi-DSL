package validation.api.demo.exception;

import lombok.*;

@Getter
@Setter
@EqualsAndHashCode
@ToString
@NoArgsConstructor
@AllArgsConstructor(staticName = "withError")
public class SystemMessage {

    private String field;
    private String reasonCode;

    public SystemMessage(String type, String code, String originalName, String join) {

    }

    public class SystemMessageType {
        public static final String ERROR = "ERROR";
    }
}
