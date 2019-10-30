package vapidsl.domain;

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

    private static final String NOT_EVERY_MATCH = "every.not.match";

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

        @Test
        public void should_pass_when_everyElementMatches() {
            Map<String, String> map = Map.of(
                    "test1", "test1",
                    "test2", "test2"
            );

            Validation.succeedIf(map)
                      .every((key, value) -> value.matches("test.*"))
                      .examine();
        }

        @Test
        public void should_pass_when_everyDeeplyElementMatches() {
            Map<String, String> map = Map.of(
                    "test1", "test1",
                    "test2", "test2"
            );

            Validation.succeedIf(map)
                      .everyDeeply((key, value) -> Validation.succeedIf(value)
                                                             .startsWith("t")
                                                             .matches("test.*"))
                      .examine();
        }

        @Test
        public void should_fail_when_everyElementNotMatches() {
            Map<String, String> map = Map.of(
                    "test1", "test1",
                    "test2", "invalid"
            );

            Assertions.assertThatThrownBy(() -> Validation.succeedIf(map)
                                                          .every((key, value) -> value.matches("test.*")).onError(NOT_EVERY_MATCH)
                                                          .examine())
                      .hasMessage(NOT_EVERY_MATCH);
        }

        @Test
        public void should_fail_when_everyDeeplyElementNotMatches() {
            Map<String, String> map = Map.of(
                    "test1", "test1",
                    "test2", "invalid"
            );

            Assertions.assertThatThrownBy(() -> Validation.succeedIf(map)
                                                          .everyDeeply((key, value) -> Validation.succeedIf(value)
                                                                                                 .startsWith("t")
                                                                                                 .matches("test.*"))
                                                          .onError(NOT_EVERY_MATCH)
                                                          .examine())
                      .hasMessage(NOT_EVERY_MATCH);
        }

        @Test
        public void should_fail_withAllFieldsAndCodes() {
            Validation.succeedIf(Map.of("t", "test"), MatchMode.EAGER)
                      .everyDeeply((key, value) -> Validation.succeedIf(key, MatchMode.EAGER)
                                                             .isEqualTo("s").onError("key", "not equal")
                                                             .matches("//d").onError("key", "not matches")
                      )
                      .inspecting(it -> it.get("t"), StringConditions::isBlank).onError("key", "not blank")
                      .examine(ErrorMode.RETURN);
        }
    }

    public static class FailedIfTest {

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
