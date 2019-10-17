package vapidsl.map;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import vapidsl.Validation;
import vapidsl.ValidatorTest;
import vapidsl.dict.ErrorMode;
import vapidsl.dict.MatchMode;
import vapidsl.domain.string.StringConditions;
import vapidsl.exception.ValidationException;

import java.util.Map;

@RunWith(Suite.class)
@Suite.SuiteClasses({MapTest.SucceedIfTest.class, MapTest.FailedIfTest.class})
public class MapTest extends ValidatorTest {

    public static class SucceedIfTest {

        @Test
        public void should_pass_when_mapIsEmpty() {
            Validation.succeedIf(Map.of())
                      .isEmpty()
                      .examine();
        }


        @Test
        public void should_pass_when_mapIsNotEmpty() {
            Validation.succeedIf(Map.of("test", "test"))
                      .isNotEmpty()
                      .examine();
        }
    }

    public static class FailedIfTest {

        @Test
        public void should_fail_withAllFieldsAndCodes() {
            Validation.succeedIf(Map.of("t", "test"), MatchMode.EAGER)
                      .every((key, value) -> Validation.succeedIf(key, MatchMode.EAGER)
                                                       .isEqualTo("s").onError("key", "not equal")
                                                       .matches("//d").onError("key", "not matches")
                      )
                      .inspecting(it -> it.get("t"), StringConditions::isBlank).onError("key", "not blank")
                      .examine(ErrorMode.RETURN);
        }

        @Test
        public void should_fail_when_mapIsEmpty() {
            Assertions.assertThatThrownBy(() -> Validation.failIf(Map.of())
                                                          .isEmpty()
                                                          .examine())
                      .isInstanceOf(ValidationException.class);
        }

        @Test
        public void should_fail_when_mapIsNotEmpty() {
            Assertions.assertThatThrownBy(() -> Validation.failIf(Map.of("test", "test"))
                                                          .isNotEmpty()
                                                          .examine())
                      .isInstanceOf(ValidationException.class);
        }
    }
}
