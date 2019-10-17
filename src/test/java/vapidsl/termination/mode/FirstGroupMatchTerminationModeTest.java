package vapidsl.termination.mode;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import vapidsl.Validation;
import vapidsl.dict.ErrorMode;
import vapidsl.domain.number.biginteger.impl.LongValidation;
import vapidsl.common.ValidationError;

import java.util.List;

@RunWith(JUnit4.class)
public class FirstGroupMatchTerminationModeTest {

    @Test
    public void should_returnError_whenAnyGroupIsMatched() {
        String errorMsg = "invalid.number";

        List<ValidationError> messages = getErroneousValidation(errorMsg).examine(ErrorMode.RETURN);

        Assertions.assertThat(messages).hasSize(1)
                  .extracting(ValidationError::getReasonCode)
                  .containsExactly(errorMsg);
    }

    @Test
    public void should_throwError_whenAnyGroupIsMatched() {
        String errorMsg = "invalid.number";

        Assertions.assertThatThrownBy(() -> getErroneousValidation(errorMsg).examine())
                  .hasMessage("group:" + errorMsg);
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
