package vapidsl.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import vapidsl.Validation;
import vapidsl.ValidatorTest;
import vapidsl.common.ValidationError;
import vapidsl.dict.ErrorMode;
import vapidsl.domain.date.DateConditions;
import vapidsl.domain.object.ObjectConditions;

import java.time.LocalDate;
import java.util.function.Function;

@RunWith(Suite.class)
@Suite.SuiteClasses({DateTest.SucceedIfTest.class, DateTest.FailedIfTest.class})
public class DateTest extends ValidatorTest {


    public static class SucceedIfTest {

        @Test
        public void should_fail_when_otherArgumentIsNull() {
            LocalDate firstDate = LocalDate.now();
            LocalDate secondDate = null;

            Assertions.assertThat(Validation.succeedIf(firstDate)
                                            .deepInspecting(Function.identity(),
                                                            it -> Validation.succeedIf(it)
                                                                            .isNull()
                                                                            .or()
                                                                            .withTerm(() -> secondDate != null)
                                                                            .isAfter(secondDate)
                                            )
                                            .groupError("test")
                                            .examine(ErrorMode.RETURN))
                      .extracting(ValidationError::getReasonCode)
                      .containsExactly("test");
        }

        @Test
        public void should_fail_withOnlySpecifiedError_when_multipleGroupFail() {
            LocalDate date = LocalDate.now();

            Assertions.assertThat(Validation.succeedIf(date)
                                            .isBefore(LocalDate.now().minusDays(1))
                                            .or()
                                            .isNull()
                                            .or()
                                            .isAfter(LocalDate.now().plusDays(1))
                                            .isBefore(LocalDate.now().minusDays(1))
                                            .groupError("test")
                                            .examine(ErrorMode.RETURN))
                      .extracting(ValidationError::getReasonCode)
                      .containsExactly("test");
        }

        @Test
        public void should_pass_when_validatableIsNull() {
            DateHolder dateHolder = DateHolder.of(null, LocalDate.now());

            Validation.succeedIf(dateHolder)
                      .isNotNull().onError("holder is null")
                      .deepInspecting(DateHolder::getStartDate,
                                      it -> Validation.succeedIf(it)
                                                      .isNotNull().onError("start date is null")
                                                      .isAfter(LocalDate.now()).onError("start date is invalid")
                      )
                      .or()
                      .deepInspecting(DateHolder::getEndDate,
                                      it -> Validation.succeedIf(it)
                                                      .isNotNull()
                                                      .isEqualTo(LocalDate.now())
                      )
                      .examine();
        }
    }


    public static class FailedIfTest {

        @Test
        public void should_fail_when_allConditionsMatch() {
            DateHolder holder = DateHolder.of(LocalDate.now(), LocalDate.now().plusDays(5));

            Assertions.assertThat(Validation.failIf(holder)
                                            .inspecting(DateHolder::getStartDate, ObjectConditions::isNotNull)
                                            .inspecting(DateHolder::getEndDate, ObjectConditions::isNotNull)
                                            .inspecting(DateHolder::getStartDate, () -> DateConditions.isBefore(holder.getEndDate()))
                                            .groupError("start date is before end date")
                                            .examine(ErrorMode.RETURN))
                      .extracting(ValidationError::getReasonCode)
                      .containsExactly("start date is before end date");
        }
    }

    @Getter
    @Setter
    @AllArgsConstructor(staticName = "of")
    private static class DateHolder {

        private LocalDate startDate;
        private LocalDate endDate;
    }
}
