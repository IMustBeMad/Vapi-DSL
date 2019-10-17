package vapidsl.map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import vapidsl.Validation;
import vapidsl.ValidatorTest;
import vapidsl.dict.ErrorMode;
import vapidsl.dict.MatchMode;
import vapidsl.domain.string.StringConditions;

import java.util.Map;

@RunWith(Suite.class)
@Suite.SuiteClasses({MapTest.SucceedIfTest.class, MapTest.FailedIfTest.class})
public class MapTest extends ValidatorTest {

    public static class SucceedIfTest {

        @Test
        public void ignore() {

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
    }
}
