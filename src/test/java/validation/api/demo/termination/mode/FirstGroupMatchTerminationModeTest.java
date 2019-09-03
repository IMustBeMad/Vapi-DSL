package validation.api.demo.termination.mode;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import validation.api.demo.validation.Validation;
import validation.api.demo.validation.dict.ErrorMode;
import validation.api.demo.validation.domain.number.biginteger.impl.LongValidation;
import validation.api.demo.validation.exception.SystemMessage;

import java.util.List;

@RunWith(JUnit4.class)
public class FirstGroupMatchTerminationModeTest {

    @Test
    public void should_returnError_whenAnyGroupIsMatched() {
        String errorMsg = "invalid.number";

        List<SystemMessage> messages = getErroneousValidation(errorMsg).examine(ErrorMode.RETURN);

        Assertions.assertThat(messages).hasSize(1)
                  .extracting(SystemMessage::getReasonCode)
                  .containsExactly(errorMsg);
    }

    @Test
    public void should_throwError_whenAnyGroupIsMatched() {
        String errorMsg = "invalid.number";

        Assertions.assertThatThrownBy(() -> getErroneousValidation(errorMsg).examine())
                  .hasMessage(errorMsg);
    }

    @Test
    public void should_beNoErrors_whenNoGroupIsMatched() {
        Validation.failIf(42L)
                  .isNull()
                  .groupError("is.null")
                  .or()
                  .isEqualTo(43L)
                  .groupError("is.not.equal")
                  .examine();
    }

    private LongValidation getErroneousValidation(String errorMsg) {
        return Validation.failIf(42L)
                         .isNull()
                         .or()
                         .isEqualTo(42L)
                         .groupError(errorMsg);
    }
}
