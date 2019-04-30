package validation.api.demo.data.common;

import lombok.*;

import java.util.List;

@Getter
@Setter
@EqualsAndHashCode
@ToString
@NoArgsConstructor
public class Claim {

    private Long id;
    private List<Attachment> attachments;
}
