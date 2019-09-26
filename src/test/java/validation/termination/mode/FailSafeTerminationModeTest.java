package validation.termination.mode;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import validation.Validation;
import validation.dict.ErrorMode;
import validation.dict.MatchMode;
import validation.domain.list.ListConditions;
import validation.exception.SystemMessage;
import validation.exception.ValidationException;
import validation.domain.date.DateConditions;

import java.time.LocalDate;
import java.util.List;

import static java.time.LocalDate.now;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static validation.domain.object.ObjectConditions.isEqualTo;

@RunWith(JUnit4.class)
public class FailSafeTerminationModeTest {

    @Test
    public void should_returnError_when_lastErrorEncountered() {
        List<String> testList = List.of("test1", "test2", "test3");

        String sizeError = "incorrect.size";
        String allOfClauseError = "incorrect.all.of";

        List<SystemMessage> messages = Validation.succeedIf(testList, MatchMode.EAGER)
                                                 .ofSize(4).onError(sizeError)
                                                 .isAllOf(ListConditions.hasNoDuplicates(), ListConditions.isEmpty()).onError(allOfClauseError)
                                                 .inspecting(it -> it.get(0), it -> it.matches("test.*"))
                                                 .examine(ErrorMode.RETURN);

        assertThat(messages).extracting(SystemMessage::getReasonCode)
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
                                           .isAnyOf(DateConditions.isAfter(now().plusDays(1)), isEqualTo(now())).onError(anyOfErrorClause)
                                           .groupError(groupError)
                                           .examine())
                .isInstanceOf(ValidationException.class)
                .hasMessage(groupError);
    }
}
