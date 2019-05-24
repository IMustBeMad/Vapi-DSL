package validation.api.demo.data.exception;

import lombok.*;

@Getter
@Setter
@EqualsAndHashCode
@ToString
@NoArgsConstructor
public class SystemMessage {

    public SystemMessage(String type, String code, String originalName, String join) {

    }

    public class SystemMessageType {
        public static final String ERROR = "ERROR";
    }
}
