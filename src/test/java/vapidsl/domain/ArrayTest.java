package vapidsl.domain;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import vapidsl.Validation;
import vapidsl.ValidatorTest;
import vapidsl.domain.object.ObjectConditions;
import vapidsl.domain.string.StringConditions;
import vapidsl.exception.ValidationException;

@RunWith(Suite.class)
@Suite.SuiteClasses({ArrayTest.SucceedIfTest.class, ArrayTest.FailedIfTest.class})
public class ArrayTest extends ValidatorTest {

    public static class SucceedIfTest {

        @Test
        public void should_pass_when_everyElementSupplierMatch() {
            Validation.succeedIf(new String[]{"test", "test1"})
                      .isNotEmpty()
                      .every(() -> StringConditions.matches("test.*"))
                      .examine();
        }

        @Test
        public void should_pass_when_everyElementPredicateMatch() {
            Validation.succeedIf(new String[]{"test", "test1"})
                      .isNotEmpty()
                      .every(it -> it.matches("test.*"))
                      .examine();
        }

        @Test
        public void should_fail_when_notEveryElementMatch() {
            Assertions.assertThatThrownBy(() -> Validation.succeedIf(new Object[]{new Obj(), new Obj("test2")})
                                                          .every(() -> ObjectConditions.isEqualTo(new Obj())).onError(IS_NOT_EQUAL)
                                                          .examine())
                      .hasMessage(IS_NOT_EQUAL);
        }

        @Test
        public void should_pass_when_everyDeeplyElementMatch() {
            Validation.succeedIf(new Obj[]{new Obj("test"), new Obj("test2")})
                      .everyDeeply(obj -> Validation.succeedIf(obj)
                                                    .inspecting(Obj::getProp, StringConditions::isNotEmpty))
                      .examine();
        }

        @Test
        public void should_fail_when_notEveryDeeplyElementMatch() {
            Assertions.assertThatThrownBy(() -> Validation.succeedIf(new Obj[]{new Obj(""), new Obj("test2")})
                                                          .everyDeeply(it -> Validation.succeedIf(it)
                                                                                       .inspecting(Obj::getProp, StringConditions::isEmpty)).onError(IS_NOT_EMPTY)
                                                          .examine())
                      .isInstanceOf(ValidationException.class)
                      .hasMessage(IS_NOT_EMPTY);
        }
    }

    public static class FailedIfTest {

        @Test
        public void ignore() {

        }
    }
}
