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
        Validation.verifyIf("test")
                  .matches("test").onError("error")
                  .isNotEmpty()
                  .failOn(TerminationMode.LAST_ERROR_ENCOUNTERED)
                  .examine();
    }

    @Test
    public void testString() {
        String issueNumber = "IR20180203123";

        Validation.verifyIf(issueNumber)
                  .isNotNull()
                  .isEqualTo("test").onError("not.equal.test")
                  .matches("IR.*")
                  .failOn(TerminationMode.LAST_ERROR_ENCOUNTERED, ErrorMode.THROW)
                  .examine();
    }

    @Test
    public void testDate() {
        LocalDate now = LocalDate.now();

        Validation.verifyIf(now)
                  .isEqualTo(now.plusDays(1))
                  .isAfter(now.minusDays(1))
                  .failOn(TerminationMode.FIRST_ERROR_ENCOUNTERED);
    }

    @Test
    public void testLong() {
        Validation.verifyIf(42L)
                  .isNull()
                  .isEqualTo(43L)
                  .isGt(45L)
                  .failOn(TerminationMode.LAST_ERROR_ENCOUNTERED, ErrorMode.RETURN);
    }

    @Test
    public void testList() {
        List<String> names = List.of("John", "Rick", "Mathew", "Max", "Jamie", "John");

        Validation.verifyIf(names)
                  .isNotNull()
                  .contains("Rick")
                  .hasNoDuplicates()
                  .ofSize(10)
                  .innerValidation(
                          list -> list.get(0),
                          name -> Validation.verifyIf(name)
                  )
                  .failOn(TerminationMode.FIRST_ERROR_ENCOUNTERED);
    }
//
//    @Test
//    public void testObject() {
//        TestObject testObj = getTestObj();
//
//        Validation.verifyIf(testObj)
//                  .isNotNull()
//                  .isEqualTo(testObj)
//                  .withTerm(this::isSavedInRepository)
//                  .inspecting(TestObject::getName, name -> name.matches("Fancy.*"))
//                  .inspecting(
//                          TestObject::getId,
//                          id -> Validation.verifyIf(id)
//                                          .isNull(ERROR_IS_NULL)
//                                          .isGt(41L, ERROR_NOT_GT)
//                  )
//                  .failOn(TerminationMode.FIRST_ERROR_ENCOUNTERED);
//    }
//
//    @Test
//    public void testArray() {
//        String[] strings = {"test", "test2"};
//
//        Validation.verifyIf(strings)
//                  .contains("test")
//                  .ofSize(2)
//                  .inspecting(array -> array[0], el -> el.equals("test"))
//                  .failOn(TerminationMode.FIRST_ERROR_ENCOUNTERED);
//    }

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
