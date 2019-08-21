package validation.api.demo.termination.mode;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import validation.api.demo.validation.Validation;
import validation.api.demo.validation.dict.ErrorMode;
import validation.api.demo.validation.dict.MatchMode;
import validation.api.demo.validation.domain.list.ListConditions;
import validation.api.demo.validation.exception.SystemMessage;
import validation.api.demo.validation.exception.ValidationException;

import java.time.LocalDate;
import java.util.List;

import static java.time.LocalDate.now;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static validation.api.demo.validation.domain.date.DateConditions.isAfter;
import static validation.api.demo.validation.domain.object.ObjectConditions.isEqualTo;

@RunWith(JUnit4.class)
public class LastErrorTerminationModeTest {

    @Test
    public void should_returnError_when_lastErrorEncountered() {
        List<String> testList = List.of("test1", "test2", "test3");

        String sizeError = "incorrect.size";
        String allOfClauseError = "incorrect.all.of";

        List<SystemMessage> messages = Validation.failIf(testList, MatchMode.EAGER)
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

        assertThatThrownBy(() -> Validation.failIf(date, MatchMode.EAGER)
                                           .isBefore(now().plusDays(10))
                                           .isEqualTo(now().minusDays(3)).onError(errorEqual)
                                           .isAnyOf(isAfter(now().plusDays(1)), isEqualTo(now())).onError(anyOfErrorClause)
                                           .onGroupError(groupError)
                                           .examine())
                .isInstanceOf(ValidationException.class)
                .hasMessage(groupError);
    }
}
