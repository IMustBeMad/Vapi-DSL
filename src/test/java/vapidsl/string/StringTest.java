package vapidsl.string;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import vapidsl.Validation;
import vapidsl.dict.MatchMode;
import vapidsl.ValidatorTest;

@RunWith(Suite.class)
@Suite.SuiteClasses({StringTest.SucceedIfTest.class, StringTest.FailedIfTest.class})
public class StringTest extends ValidatorTest {

    private static final String STRING = "test";
    private static final String PREFIX = "tes";

    private static final String INVALID_STRING = "not test";

    public static class SucceedIfTest {

        private static void lazySimpleTestStub(String isEqualTo, String startsWith, int ofLength) {
            simpleTest(MatchMode.LAZY, isEqualTo, startsWith, ofLength);
        }

        private static void eagerSimpleTestStub(String isEqualTo, String startsWith, int ofLength) {
            simpleTest(MatchMode.EAGER, isEqualTo, startsWith, ofLength);
        }

        private static void simpleTest(MatchMode matchMode, String isEqualTo, String startsWith, int ofLength) {
            Validation.succeedIf(STRING, matchMode)
                      .isEqualTo(isEqualTo).onError("is.not.eq.test")
                      .startsWith(startsWith).onError("does.not.start.with")
                      .ofLength(ofLength).onError("length.is.invalid")
                      .examine();
        }

        private static void groupTest(String isEqualTo, String startsWith, int ofLength) {
            Validation.succeedIf(STRING)
                      .isEqualTo(isEqualTo).onError("is.not.eq.test")
                      .or()
                      .startsWith(startsWith).onError("does.not.start.with")
                      .ofLength(ofLength).onError("length.is.invalid")
                      .examine();
        }

        @Test
        public void should_pass_when_allConditionsAreMatched_withAnyMode() {
            lazySimpleTestStub(STRING, PREFIX, 4);
            eagerSimpleTestStub(STRING, PREFIX, 4);
        }

        @Test
        public void should_pass_when_allConditionsOfAnyGroupAreMatched() {
            groupTest(STRING, PREFIX, 4);
        }

        @Test
        public void should_fail_when_noneGroupConditionsAreMatched() {
            Assertions.assertThatThrownBy(() -> groupTest(INVALID_STRING, INVALID_STRING, 3))
                      .hasMessage("is.not.eq.test\ndoes.not.start.with");
        }

        @Test
        public void should_fail_when_firstErrorMet_withLazyMode() {
            Assertions.assertThatThrownBy(() -> lazySimpleTestStub(INVALID_STRING, PREFIX, 4))
                      .hasMessage("is.not.eq.test");
        }

        @Test
        public void should_failWithFirstError_when_firstErrorMetDespiteSeveralErrors_withLazyMode() {
            Assertions.assertThatThrownBy(() -> lazySimpleTestStub(INVALID_STRING, INVALID_STRING, 4))
                      .hasMessage("is.not.eq.test");
        }

        @Test
        public void should_failWithAllErrors_withEagerMode() {
            Assertions.assertThatThrownBy(() -> eagerSimpleTestStub(INVALID_STRING, INVALID_STRING, 4))
                      .hasMessage("is.not.eq.test\ndoes.not.start.with");
        }
    }

    public static class FailedIfTest {

        private static void simpleTest(String isEqualTo, String startsWith, int ofLength) {
            Validation.failIf(STRING)
                      .isEqualTo(isEqualTo).onError("is.not.eq.test")
                      .startsWith(startsWith).onError("does.not.start.with")
                      .ofLength(ofLength).onError("length.is.invalid")
                      .examine();
        }

        private static void groupTest(String isEqualTo, String startsWith, int ofLength) {
            Validation.failIf(STRING)
                      .isEqualTo(isEqualTo).onError("is.not.eq.test")
                      .or()
                      .startsWith(startsWith).onError("does.not.start.with")
                      .or()
                      .ofLength(ofLength).onError("length.is.invalid")
                      .groupError("group.error")
                      .examine();
        }

        @Test
        public void should_failWithAllErrors_when_allConditionsAreMatched() {
            Assertions.assertThatThrownBy(() -> simpleTest(STRING, PREFIX, 4))
                      .hasMessage("is.not.eq.test\ndoes.not.start.with\nlength.is.invalid");
        }

        @Test
        public void should_pass_when_anyConditionIsNotMet() {
            simpleTest(INVALID_STRING, PREFIX, 4);
        }

        @Test
        public void should_failWithGroupError_when_groupConditionsMatchedAndGroupHasError() {
            Assertions.assertThatThrownBy(() -> groupTest(INVALID_STRING, INVALID_STRING, 4))
                      .hasMessage("group:group.error");
        }

        @Test
        public void should_failWithError_when_groupConditionsMatchedAndNoGroupErrorPresent() {
            Assertions.assertThatThrownBy(() -> groupTest(STRING, INVALID_STRING, 3))
                      .hasMessage("is.not.eq.test");
        }

        @Test
        public void should_pass_when_noneGroupConditionsMatched() {
            groupTest(INVALID_STRING, INVALID_STRING, 3);
        }
    }
}