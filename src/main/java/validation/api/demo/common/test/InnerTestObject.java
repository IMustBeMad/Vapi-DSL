package validation.api.demo.common.test;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@EqualsAndHashCode
@ToString
@NoArgsConstructor
@AllArgsConstructor(staticName = "of")
public class InnerTestObject {

    private Long id;
    private String name;
    private LocalDate date;
}
