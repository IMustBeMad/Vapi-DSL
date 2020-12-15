package vapidsl.termination.mode;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import vapidsl.Validation;
import vapidsl.common.ValidationError;
import vapidsl.dict.ErrorMode;
import vapidsl.exception.ValidationException;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@RunWith(JUnit4.class)
public class FailFastTerminationModeTest {

    @Test
    public void should_returnError_when_failOFirstError() {
        String testString = "test";
        String errorCode = "is.not.equal.to";

        List<ValidationError> messages = Validation.succeedIf(testString)
                                                   .isNotNull()
                                                   .isEqualTo("test42").onError(errorCode)
                                                   .matches("error")
                                                   .examine(ErrorMode.RETURN);

        assertThat(messages).extracting(ValidationError::getReasonCode)
                            .containsOnly(errorCode);
    }

    @Test
    public void should_throwError_when_failOnFirstError() {
        Long testLong = 42L;
        String errorCode = "no.equals";

        assertThatThrownBy(() -> Validation.succeedIf(testLong)
                                           .isGt(10L)
                                           .isLt(100L)
                                           .isEqualTo(43L).onError(errorCode)
                                           .examine())
                .isInstanceOf(ValidationException.class)
                .hasMessage(errorCode);
    }

    @Test
    public void should_returnGroupError_when_failOnFirstError() {
        LocalDate date = LocalDate.now();
        String groupError = "invalid.date";

        List<ValidationError> messages = Validation.succeedIf(date)
                                                   .isEqualTo(LocalDate.now().plusDays(1))
                                                   .isAfter(LocalDate.now().minusDays(1))
                                                   .groupError(groupError)
                                                   .examine(ErrorMode.RETURN);

        assertThat(messages).extracting(ValidationError::getReasonCode)
                            .containsOnly(groupError);
    }
}
