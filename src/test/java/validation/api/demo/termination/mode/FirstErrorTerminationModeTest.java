package validation.api.demo.termination.mode;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import validation.api.demo.validation.exception.SystemMessage;
import validation.api.demo.validation.exception.ValidationException;
import validation.api.demo.validation.Validation;
import validation.api.demo.validation.dict.ErrorMode;
import validation.api.demo.validation.dict.TerminationMode;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@RunWith(JUnit4.class)
public class FirstErrorTerminationModeTest {

    @Test
    public void should_returnError_when_failOFirstError() {
        String testString = "test";
        String errorCode = "is.not.equal.to";

        List<SystemMessage> messages = Validation.verifyIf(testString)
                                                 .isNotNull()
                                                 .isEqualTo("test42").onError(errorCode)
                                                 .matches("error")
                                                 .failOn(TerminationMode.FIRST_ERROR_ENCOUNTERED, ErrorMode.RETURN)
                                                 .examine();

        assertThat(messages).extracting(SystemMessage::getReasonCode)
                            .containsOnly(errorCode);
    }

    @Test
    public void should_throwError_when_failOnFirstError() {
        Long testLong = 42L;
        String errorCode = "no.equals";

        assertThatThrownBy(() -> Validation.verifyIf(testLong)
                                           .isGt(10L)
                                           .isLt(100L)
                                           .isEqualTo(43L).onError(errorCode)
                                           .failOn(TerminationMode.FIRST_ERROR_ENCOUNTERED)
                                           .examine())
                .isInstanceOf(ValidationException.class)
                .hasMessage(errorCode);
    }

    @Test
    public void should_returnGroupError_when_failOnFirstError() {
        LocalDate date = LocalDate.now();
        String groupError = "invalid.date";

        List<SystemMessage> messages = Validation.verifyIf(date)
                                                 .isEqualTo(LocalDate.now().plusDays(1))
                                                 .isAfter(LocalDate.now().minusDays(1))
                                                 .onGroupError(groupError)
                                                 .failOn(TerminationMode.FIRST_ERROR_ENCOUNTERED, ErrorMode.RETURN)
                                                 .examine();

        assertThat(messages).extracting(SystemMessage::getReasonCode)
                            .containsOnly(groupError);
    }
}
