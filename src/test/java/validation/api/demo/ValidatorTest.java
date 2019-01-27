package validation.api.demo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import validation.api.demo.common.test.InnerTestObject;
import validation.api.demo.common.test.TestObject;
import validation.api.demo.validation.Validation;

import java.time.LocalDate;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ValidatorTest {

    @Test
    public void test() {
        String issueNumber = "IR20180203123";

        Validation.verifyIf(42L)
                  .isNull("test")
                  .isEqualTo(43L, "")
                  .isGt(45L, "")
                  .perform();

        Validation.verifyIf(issueNumber)
                  .isEqualTo("test", "lala")
                  .matches("IR\\.*", "does not match")
                  .perform();
    }

    @Test
    public void testValidation() {
        TestObject testObj = getTestObj();

        Validation.verifyIf(testObj)
                  .isNotNull("Object is null")
                  .isEqualTo(testObj, "Does not equal")
//                  .inspecting(TestObject::getName, it -> matches("Fancy\\.*"))

//                  .inspecting(TestObject::getId, id -> Validation.assertThat(id).isNumber().isGt(43L))
                  .failFast();
    }

    private TestObject getTestObj() {
        TestObject testObject = new TestObject();
        testObject.setId(42L);
        testObject.setName("Fancy (bad) name");
        testObject.setLinkedNames(List.of("super", "duper", "names", "to", "concatenated"));
        testObject.setInnerTestObject(InnerTestObject.of(43L, "Inner object name", LocalDate.now()));
        testObject.setInnerTestObjects(
                List.of(
                        InnerTestObject.of(44L, "List object", LocalDate.now().minusDays(1)),
                        new InnerTestObject()
                )
        );

        return testObject;
    }
}
