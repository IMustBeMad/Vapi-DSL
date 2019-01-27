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
}
