package vapidsl.domain;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import vapidsl.Validation;
import vapidsl.ValidatorTest;

@RunWith(Suite.class)
@Suite.SuiteClasses({BoolTest.SucceedIfTest.class, BoolTest.FailedIfTest.class})
public class BoolTest extends ValidatorTest {

    public static class SucceedIfTest {

        @Test
        public void should_pass_when_isTrue() {
            Validation.succeedIf(true)
                      .isTrue()
                      .examine();
        }

        @Test
        public void should_fail_when_isFalse() {
            Assertions.assertThatThrownBy(() -> Validation.succeedIf(false)
                                                          .isTrue().onError(IS_FALSE)
                                                          .examine())
                      .hasMessage(IS_FALSE);
        }

        @Test
        public void should_pass_when_isFalse() {
            Validation.succeedIf(false)
                      .isFalse()
                      .examine();
        }

        @Test
        public void should_fail_when_isTrue() {
            Assertions.assertThatThrownBy(() -> Validation.succeedIf(true)
                                                          .isFalse().onError(IS_TRUE)
                                                          .examine())
                      .hasMessage(IS_TRUE);
        }

        @Test
        public void should_pass_when_isNull() {
            Boolean bool = null;

            Validation.succeedIf(bool)
                      .isNull()
                      .examine();
        }

        @Test
        public void should_fail_when_isNotNull() {
            Boolean bool = true;

            Assertions.assertThatThrownBy(() -> Validation.succeedIf(bool)
                                                          .isNull().onError(IS_NOT_NULL)
                                                          .examine())
                      .hasMessage(IS_NOT_NULL);
        }

        @Test
        public void should_pass_when_isNotNull() {
            Boolean bool = true;

            Validation.succeedIf(bool)
                      .isNotNull()
                      .examine();
        }

        @Test
        public void should_fail_when_isNull() {
            Boolean bool = null;

            Assertions.assertThatThrownBy(() -> Validation.succeedIf(bool)
                                                          .isNotNull().onError(IS_NULL)
                                                          .examine())
                      .hasMessage(IS_NULL);
        }

        @Test
        public void should_pass_when_anyGroupIsMatched() {
            Validation.succeedIf(true)
                      .isNull()
                      .or()
                      .isNotNull()
                      .isTrue()
                      .examine();
        }

        @Test
        public void should_fail_when_noneGroupIsMatched() {
            Assertions.assertThatThrownBy(() -> Validation.succeedIf(true)
                                                          .isNull()
                                                          .or()
                                                          .isNotNull()
                                                          .isFalse()
                                                          .groupError(INVALID_GROUP)
                                                          .examine())
                      .hasMessage(PREFIX_INVALID_GROUP);
        }
    }


    public static class FailedIfTest {

        @Test
        public void should_fail_when_isTrue() {
            Assertions.assertThatThrownBy(() -> Validation.failIf(true)
                                                          .isTrue().onError(IS_TRUE)
                                                          .examine())
                      .hasMessage(IS_TRUE);
        }

        @Test
        public void should_pass_when_isFalse() {
            Validation.failIf(false)
                      .isTrue().onError(IS_FALSE)
                      .examine();
        }

        @Test
        public void should_fail_when_isFalse() {
            Assertions.assertThatThrownBy(() -> Validation.failIf(false)
                                                          .isFalse().onError(IS_FALSE)
                                                          .examine())
                      .hasMessage(IS_FALSE);
        }

        @Test
        public void should_pass_when_isTrue() {
            Validation.failIf(true)
                      .isFalse().onError(IS_TRUE)
                      .examine();
        }

        @Test
        public void should_fail_when_isNull() {
            Boolean bool = null;

            Assertions.assertThatThrownBy(() -> Validation.failIf(bool)
                                                          .isNull().onError(IS_NULL)
                                                          .examine())
                      .hasMessage(IS_NULL);
        }

        @Test
        public void should_pass_when_isNotNull() {
            Boolean bool = true;

            Validation.failIf(bool)
                      .isNull().onError(IS_NOT_NULL)
                      .examine();
        }

        @Test
        public void should_fail_when_isNotNull() {
            Boolean bool = true;

            Assertions.assertThatThrownBy(() -> Validation.failIf(bool)
                                                          .isNotNull().onError(IS_NOT_NULL)
                                                          .examine())
                      .hasMessage(IS_NOT_NULL);
        }

        @Test
        public void should_pass_when_isNull() {
            Boolean bool = null;

            Validation.failIf(bool)
                      .isNotNull().onError(IS_NULL)
                      .examine();
        }
    }
}
