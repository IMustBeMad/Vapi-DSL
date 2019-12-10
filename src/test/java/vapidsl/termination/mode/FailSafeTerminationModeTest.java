package vapidsl.termination.mode;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import vapidsl.Validation;
import vapidsl.dict.ErrorMode;
import vapidsl.dict.MatchMode;
import vapidsl.domain.list.ListConditions;
import vapidsl.common.ValidationError;
import vapidsl.exception.ValidationException;
import vapidsl.domain.date.localdate.LocalDateConditions;

import java.time.LocalDate;
import java.util.List;

import static java.time.LocalDate.now;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static vapidsl.domain.object.ObjectConditions.isEqualTo;

@RunWith(JUnit4.class)
public class FailSafeTerminationModeTest {

    @Test
    public void should_returnError_when_lastErrorEncountered() {
        List<String> testList = List.of("test1", "test2", "test3");

        String sizeError = "incorrect.size";
        String allOfClauseError = "incorrect.all.of";

        List<ValidationError> messages = Validation.succeedIf(testList, MatchMode.EAGER)
                                                   .hasSize(4).onError(sizeError)
                                                   .satisfiesEvery(ListConditions.hasNoDuplicates(), ListConditions.isEmpty()).onError(allOfClauseError)
                                                   .inspecting(it -> it.get(0), it -> it.matches("test.*"))
                                                   .examine(ErrorMode.RETURN);

        assertThat(messages).extracting(ValidationError::getReasonCode)
                            .containsExactlyInAnyOrder(sizeError, allOfClauseError);
    }

    @Test
    public void should_throwGroupError_when_lastErrorEncountered() {
        LocalDate date = now();
        String errorEqual = "not.equal.to";
        String anyOfErrorClause = "incorrect.any.of";
        String groupError = "date.is.invalid";

        assertThatThrownBy(() -> Validation.succeedIf(date, MatchMode.EAGER)
                                           .isBefore(now().plusDays(10))
                                           .isEqualTo(now().minusDays(3)).onError(errorEqual)
                                           .satisfiesAny(LocalDateConditions.isAfter(now().plusDays(1)), isEqualTo(now())).onError(anyOfErrorClause)
                                           .groupError(groupError)
                                           .examine())
                .isInstanceOf(ValidationException.class)
                .hasMessage("group:" + groupError);
    }
}
