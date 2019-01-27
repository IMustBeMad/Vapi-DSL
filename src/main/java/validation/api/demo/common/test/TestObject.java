package validation.api.demo.common.test;

import lombok.*;

import java.util.List;

@Getter
@Setter
@EqualsAndHashCode
@ToString
@NoArgsConstructor
public class TestObject {

    private Long id;
    private String name;
    private List<String> linkedNames;
    private InnerTestObject innerTestObject;
    private List<InnerTestObject> innerTestObjects;
}
