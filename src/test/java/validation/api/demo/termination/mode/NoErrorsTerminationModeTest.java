package validation.api.demo.termination.mode;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import validation.api.demo.exception.SystemMessage;
import validation.api.demo.exception.ValidationException;
import validation.api.demo.validation.Validation;
import validation.api.demo.validation.dict.ErrorMode;
import validation.api.demo.validation.domain.string.impl.StringValidation;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static validation.api.demo.validation.dict.TerminationMode.NO_ERROR_ENCOUNTERED;

@RunWith(JUnit4.class)
public class NoErrorsTerminationModeTest {

    private static final String TEST = "test";
    private static final String ERROR = "incorrect.string";

    @Test
    public void should_returnError_when_noErrorsEncountered() {
        List<SystemMessage> messages = getStringValidation().failOn(NO_ERROR_ENCOUNTERED, ErrorMode.RETURN)
                                                            .examine();

        assertThat(messages).extracting(SystemMessage::getReasonCode)
                            .containsOnly(ERROR);
    }

    @Test
    public void should_throwError_when_noErrorsEncountered() {
        assertThatThrownBy(() -> getStringValidation().failOn(NO_ERROR_ENCOUNTERED)
                                                      .examine())
                .isInstanceOf(ValidationException.class)
                .hasMessage(ERROR);
    }

    @Test
    public void should_beNoErrors_when_errorEncountered() {
        Long id = 42L;

        Validation.verifyIf(id)
                  .isNotNull()
                  .isEqualTo(43L)
                  .onGroupError("equal.to.43")
                  .failOn(NO_ERROR_ENCOUNTERED)
                  .examine();
    }

    private StringValidation getStringValidation() {
        return Validation.verifyIf(TEST)
                         .isNotNull()
                         .matches("test")
                         .onGroupError(ERROR);
    }
}
