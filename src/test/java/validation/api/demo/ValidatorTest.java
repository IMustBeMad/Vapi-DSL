package validation.api.demo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import validation.api.demo.data.common.InnerTestObject;
import validation.api.demo.data.common.TestObject;
import validation.api.demo.validation.Validation;
import validation.api.demo.validation.dict.ErrorMode;
import validation.api.demo.validation.dict.TerminationMode;

import java.time.LocalDate;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ValidatorTest {

    private static final String ERROR_IS_NULL = "is.null";
    private static final String ERROR_NOT_EQUALS = "not.equals";
    private static final String ERROR_NOT_MATCHED = "not.matched";
    private static final String ERROR_NOT_GT = "not.gt";
    private static final String ERROR_NOT_IN_REPO = "not.in.repo";
    private static final String ERROR_NOT_CONTAINS = "not.contains";
    private static final String ERROR_HAS_DUPLICATES = "has.duplicates";
    private static final String ERROR_WRONG_SIZE = "wrong.size";

    @Test
    public void test() {
        Long val = null;

        Validation.verifyIf(val)
                  .isNotNull("is.null")
                  .isEqualTo(42L, "not.equal")
                  .failOn(TerminationMode.FIRST_ERROR_ENCOUNTERED);
    }

    @Test
    public void testString() {
        String issueNumber = "IR20180203123";

        Validation.verifyIf(issueNumber)
                  .isNotNull(ERROR_IS_NULL)
                  .isEqualTo("test", ERROR_NOT_EQUALS)
                  .matches("IR.*", ERROR_NOT_MATCHED)
                  .failOn(TerminationMode.LAST_ERROR_ENCOUNTERED, ErrorMode.RETURN);
    }

    @Test
    public void testDate() {
        LocalDate now = LocalDate.now();

        Validation.verifyIf(now)
                  .isEqualTo(now.plusDays(1), ERROR_NOT_EQUALS)
                  .isAfter(now.minusDays(1), ERROR_NOT_GT)
                  .failOn(TerminationMode.FIRST_ERROR_ENCOUNTERED);
    }

    @Test
    public void testLong() {
        Validation.verifyIf(42L)
                  .isNull(ERROR_IS_NULL)
                  .isEqualTo(43L, ERROR_NOT_EQUALS)
                  .isGt(45L, ERROR_NOT_GT)
                  .failOn(TerminationMode.LAST_ERROR_ENCOUNTERED, ErrorMode.RETURN);
    }

    @Test
    public void testList() {
        List<String> names = List.of("John", "Rick", "Mathew", "Max", "Jamie", "John");

        Validation.verifyIf(names)
                  .isNotNull(ERROR_IS_NULL)
                  .contains("Rick", ERROR_NOT_CONTAINS)
                  .hasNoDuplicates(ERROR_HAS_DUPLICATES)
                  .ofSize(10, ERROR_WRONG_SIZE)
                  .inspecting(
                          list -> list.get(0),
                          TerminationMode.LAST_ERROR_ENCOUNTERED,
                          name -> Validation.verifyIf(name).isEqualTo("test", ERROR_NOT_EQUALS))
                  .failOn(TerminationMode.FIRST_ERROR_ENCOUNTERED);
    }

    @Test
    public void testObject() {
        TestObject testObj = getTestObj();

        Validation.verifyIf(testObj)
                  .isNotNull(ERROR_IS_NULL)
                  .isEqualTo(testObj, ERROR_NOT_EQUALS)
                  .withTerm(this::isSavedInRepository, ERROR_NOT_IN_REPO)
                  .inspecting(TestObject::getName, name -> name.matches("Fancy.*"), ERROR_NOT_MATCHED)
                  .inspecting(
                          TestObject::getId,
                          TerminationMode.LAST_ERROR_ENCOUNTERED,
                          id -> Validation.verifyIf(id)
                                          .isNull(ERROR_IS_NULL)
                                          .isGt(41L, ERROR_NOT_GT)
                  )
                  .failOn(TerminationMode.FIRST_ERROR_ENCOUNTERED);
    }

    @Test
    public void testArray() {
        String[] strings = {"test", "test2"};

        Validation.verifyIf(strings)
                  .contains("test", ERROR_NOT_CONTAINS)
                  .ofSize(2, ERROR_WRONG_SIZE)
                  .inspecting(array -> array[0], el -> el.equals("test"), ERROR_NOT_EQUALS)
                  .failOn(TerminationMode.FIRST_ERROR_ENCOUNTERED);
    }

    /*repository check for instance*/
    private boolean isSavedInRepository(TestObject testObject) {
        return true;
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
