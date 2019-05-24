package validation.api.demo.termination.mode;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import validation.api.demo.validation.exception.SystemMessage;
import validation.api.demo.validation.Validation;
import validation.api.demo.validation.dict.ErrorMode;
import validation.api.demo.validation.dict.TerminationMode;
import validation.api.demo.validation.domain.number.impl.LongValidation;

import java.util.List;

@RunWith(JUnit4.class)
public class FirstGroupMatchTerminationModeTest {

    @Test
    public void should_returnError_whenAnyGroupIsMatched() {
        String errorMsg = "invalid.number";

        List<SystemMessage> messages = getErroneousValidation(errorMsg)
                                               .failOn(TerminationMode.FIRST_GROUP_MATCH, ErrorMode.RETURN)
                                               .examine();

        Assertions.assertThat(messages).hasSize(1)
                  .extracting(SystemMessage::getReasonCode)
                  .containsExactly(errorMsg);
    }

    @Test
    public void should_throwError_whenAnyGroupIsMatched() {
        String errorMsg = "invalid.number";

        Assertions.assertThatThrownBy(() -> getErroneousValidation(errorMsg).failOn(TerminationMode.FIRST_GROUP_MATCH)
                                                                            .examine())
                  .hasMessage(errorMsg);
    }

    @Test
    public void should_beNoErrors_whenNoGroupIsMatched() {
        Validation.verifyIf(42L)
                  .isNull()
                  .onGroupError("is.null")
                  .or()
                  .isEqualTo(43L)
                  .onGroupError("is.not.equal")
                  .failOn(TerminationMode.FIRST_GROUP_MATCH)
                  .examine();
    }

    private LongValidation getErroneousValidation(String errorMsg) {
        return Validation.verifyIf(42L)
                         .isNull()
                         .or()
                         .isEqualTo(42L)
                         .onGroupError(errorMsg);
    }
}
