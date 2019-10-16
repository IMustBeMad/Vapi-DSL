package vapidsl.map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import vapidsl.Validation;
import vapidsl.ValidatorTest;

import java.util.Map;

@RunWith(Suite.class)
@Suite.SuiteClasses({MapTest.SucceedIfTest.class, MapTest.FailedIfTest.class})
public class MapTest extends ValidatorTest {

    public static class SucceedIfTest {

        @Test
        public void should_pass_when_allMapEntriesSuite() {
            Validation.succeedIf(Map.of(true, "test"))
                      .each(entry -> Validation.succeedIf(entry.getKey())
                                               .isTrue()
                                               .onError(entry.getValue())
                      ).examine();
        }
    }

    public static class FailedIfTest {

        @Test
        public void ignore() {

        }

    }
}
