package validation.api.demo.termination.mode;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import validation.api.demo.validation.Validation;
import validation.api.demo.validation.dict.ErrorMode;
import validation.api.demo.validation.domain.string.impl.StringValidation;
import validation.api.demo.validation.common.ValidationError;
import validation.api.demo.validation.exception.ValidationException;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@RunWith(JUnit4.class)
public class NoErrorsTerminationModeTest {

    private static final String TEST = "test";
    private static final String ERROR = "incorrect.string";

    @Test
    public void should_returnError_when_noErrorsEncountered() {
        List<ValidationError> messages = getStringValidation().examine(ErrorMode.RETURN);

        assertThat(messages).extracting(ValidationError::getReasonCode)
                            .containsOnly(ERROR);
    }

    @Test
    public void should_throwError_when_noErrorsEncountered() {
        assertThatThrownBy(() -> getStringValidation().examine())
                .isInstanceOf(ValidationException.class)
                .hasMessage(ERROR);
    }

    @Test
    public void should_beNoErrors_when_errorEncountered() {
        Long id = 42L;

        Validation.failIf(id)
                  .isNotNull()
                  .isEqualTo(43L)
                  .groupError("equal.to.43")
                  .examine();
    }

    private StringValidation getStringValidation() {
        return Validation.failIf(TEST)
                         .isNotNull()
                         .matches("test")
                         .groupError(ERROR);
    }
}
