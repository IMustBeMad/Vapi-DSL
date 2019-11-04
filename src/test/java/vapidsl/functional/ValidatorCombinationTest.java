package vapidsl.functional;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import vapidsl.Validation;
import vapidsl.ValidatorTest;

public class ValidatorCombinationTest extends ValidatorTest {

    @Test
    public void should_pass_when_succeedIfHasSuccessfulSucceedIf() {
        Validation.succeedIf("test")
                  .inspectingDeeply(
                          it -> it.substring(1),
                          it -> Validation.succeedIf(it)
                                          .isEqualTo("est")
                  )
                  .isEqualTo("test")
                  .examine();
    }

    @Test
    public void should_fail_when_succeedIfHasUnSuccessfulSucceedIf() {
        Assertions.assertThatThrownBy(() -> Validation.succeedIf("test")
                                                      .inspectingDeeply(
                                                              it -> it.substring(1),
                                                              it -> Validation.succeedIf(it)
                                                                              .isEqualTo("st")
                                                      )
                                                      .isEqualTo("test")
                                                      .examine())
                  .hasMessage(DEFAULT_EXCEPTION);
    }

    @Test
    public void should_fail_when_succeedIfHasSuccessfulFailIf() {
        Assertions.assertThatThrownBy(() -> Validation.succeedIf("test")
                                                      .inspectingDeeply(
                                                              it -> it.substring(1),
                                                              it -> Validation.failIf(it)
                                                                              .isEqualTo("est")
                                                      )
                                                      .isEqualTo("test")
                                                      .examine())
                  .hasMessage(DEFAULT_EXCEPTION);
    }

    @Test
    public void should_pass_when_succeedIfHasUnSuccessfulFailIf() {
        Validation.succeedIf("test")
                  .inspectingDeeply(
                          it -> it.substring(1),
                          it -> Validation.failIf(it)
                                          .isEqualTo("st")
                  )
                  .isEqualTo("test")
                  .examine();
    }

    @Test
    public void should_fail_when_failIfHasSuccessfulSucceedIf() {
        Assertions.assertThatThrownBy(() -> Validation.failIf("test")
                                                      .inspectingDeeply(
                                                              it -> it.substring(1),
                                                              it -> Validation.succeedIf(it)
                                                                              .isEqualTo("est")
                                                      )
                                                      .isEqualTo("test")
                                                      .examine())
                  .hasMessage(DEFAULT_EXCEPTION);
    }

    @Test
    public void should_pass_when_failIfHasUnSuccessfulSucceedIf() {
        Validation.failIf("test")
                  .inspectingDeeply(
                          it -> it.substring(1),
                          it -> Validation.succeedIf(it)
                                          .isEqualTo("st")
                  )
                  .isEqualTo("test")
                  .examine();
    }

    @Test
    public void should_pass_when_failIfHasSuccessfulFailIf() {
        Validation.failIf("test")
                  .inspectingDeeply(
                          it -> it.substring(1),
                          it -> Validation.failIf(it)
                                          .isEqualTo("est")
                  )
                  .isEqualTo("test")
                  .examine();
    }

    @Test
    public void should_fail_when_failIfHasUnSuccessfulFailIf() {
        Assertions.assertThatThrownBy(() -> Validation.failIf("test")
                                                      .inspectingDeeply(
                                                              it -> it.substring(1),
                                                              it -> Validation.failIf(it)
                                                                              .isEqualTo("st")
                                                      )
                                                      .isEqualTo("test")
                                                      .examine())
                  .hasMessage(DEFAULT_EXCEPTION);
    }
}
