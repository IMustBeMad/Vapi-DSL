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
import vapidsl.domain.date.localdate.DateConditions;
import vapidsl.domain.object.ObjectConditions;

import java.time.LocalDate;
import java.util.Objects;
import java.util.function.Function;

@RunWith(Suite.class)
@Suite.SuiteClasses({DateTest.SucceedIfTest.class, DateTest.FailedIfTest.class})
public class DateTest extends ValidatorTest {

    public static class SucceedIfTest {

        @Test
        public void should_pass_when_isAfter() {
            Validation.succeedIf(LocalDate.now())
                      .isAfter(LocalDate.now().minusDays(1))
                      .examine();
        }

        @Test
        public void should_fail_when_isNotAfter() {
            Assertions.assertThatThrownBy(() -> Validation.succeedIf(LocalDate.now())
                                                          .isAfter(LocalDate.now()).onError(IS_NOT_AFTER)
                                                          .examine())
                      .hasMessage(IS_NOT_AFTER);
        }

        @Test
        public void should_pass_when_isBefore() {
            Validation.succeedIf(LocalDate.now())
                      .isBefore(LocalDate.now().plusDays(1))
                      .examine();
        }

        @Test
        public void should_fail_when_isNotBefore() {
            Assertions.assertThatThrownBy(() -> Validation.succeedIf(LocalDate.now())
                                                          .isBefore(LocalDate.now()).onError(IS_NOT_BEFORE)
                                                          .examine())
                      .hasMessage(IS_NOT_BEFORE);
        }

        @Test
        public void should_pass_when_isAfterOrEqual() {
            Validation.succeedIf(LocalDate.now())
                      .isAfterOrEqual(LocalDate.now())
                      .examine();
        }

        @Test
        public void should_fail_when_isNotAfterOrEqual() {
            Assertions.assertThatThrownBy(() -> Validation.succeedIf(LocalDate.now())
                                                          .isAfterOrEqual(LocalDate.now().plusDays(1)).onError(IS_BEFORE)
                                                          .examine())
                      .hasMessage(IS_BEFORE);
        }

        @Test
        public void should_pass_when_isBeforeOrEqual() {
            Validation.succeedIf(LocalDate.now())
                      .isBeforeOrEqual(LocalDate.now().plusDays(1))
                      .examine();
        }

        @Test
        public void should_fail_when_isNotBeforeOrEqual() {
            Assertions.assertThatThrownBy(() -> Validation.succeedIf(LocalDate.now())
                                                          .isBeforeOrEqual(LocalDate.now().minusDays(1)).onError(IS_AFTER)
                                                          .examine())
                      .hasMessage(IS_AFTER);
        }

        @Test
        public void should_fail_when_otherArgumentIsNull() {
            LocalDate firstDate = LocalDate.now();
            LocalDate secondDate = null;

            Assertions.assertThat(Validation.succeedIf(firstDate)
                                            .inspectingDeeply(
                                                    Function.identity(),
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
                      .inspectingDeeply(
                              DateHolder::getStartDate,
                              it -> Validation.succeedIf(it)
                                              .isNotNull().onError("start date is null")
                                              .isAfter(LocalDate.now()).onError("start date is invalid")
                      )
                      .or()
                      .inspectingDeeply(
                              DateHolder::getEndDate,
                              it -> Validation.succeedIf(it)
                                              .isNotNull()
                                              .isEqualTo(LocalDate.now())
                      )
                      .examine();
        }

        @Test
        public void should_pass_when_isNull() {
            LocalDate date = null;

            Validation.succeedIf(date)
                      .isNull()
                      .examine();
        }

        @Test
        public void should_fail_when_isNotNull() {
            Assertions.assertThatThrownBy(() -> Validation.succeedIf(LocalDate.now())
                                                          .isNull().onError(IS_NOT_NULL)
                                                          .examine())
                      .hasMessage(IS_NOT_NULL);
        }

        @Test
        public void should_pass_when_isNotNull() {
            Validation.succeedIf(LocalDate.now())
                      .isNotNull()
                      .examine();
        }

        @Test
        public void should_fail_when_isNull() {
            LocalDate date = null;

            Assertions.assertThatThrownBy(() -> Validation.succeedIf(date)
                                                          .isNotNull().onError(IS_NULL)
                                                          .examine())
                      .hasMessage(IS_NULL);
        }

        @Test
        public void should_pass_when_isEqualTo() {
            LocalDate date1 = LocalDate.now();
            LocalDate date2 = LocalDate.now();

            Validation.succeedIf(date1)
                      .isEqualTo(date2)
                      .examine();
        }

        @Test
        public void should_fail_when_isNotEqualTo() {
            LocalDate date1 = LocalDate.now();
            LocalDate date2 = LocalDate.now().plusDays(1);

            Assertions.assertThatThrownBy(() -> Validation.succeedIf(date1)
                                                          .isEqualTo(date2).onError(IS_NOT_EQUAL)
                                                          .examine())
                      .hasMessage(IS_NOT_EQUAL);
        }

        @Test
        public void should_pass_when_isNotEqualTo() {
            LocalDate date1 = LocalDate.now();
            LocalDate date2 = LocalDate.now().plusDays(1);

            Validation.succeedIf(date1)
                      .isNotEqualTo(date2)
                      .examine();
        }

        @Test
        public void should_fail_when_isEqualTo() {
            LocalDate date1 = LocalDate.now();
            LocalDate date2 = LocalDate.now();

            Assertions.assertThatThrownBy(() -> Validation.succeedIf(date1)
                                                          .isNotEqualTo(date2).onError(IS_EQUAL)
                                                          .examine())
                      .hasMessage(IS_EQUAL);
        }

        @Test
        public void should_pass_when_termPredicatePasses() {
            Validation.succeedIf(LocalDate.now())
                      .withTerm(it -> it.isEqual(LocalDate.now()))
                      .examine();
        }

        @Test
        public void should_fail_when_termPredicateNotPasses() {
            Assertions.assertThatThrownBy(() -> Validation.succeedIf(LocalDate.now())
                                                          .withTerm(it -> it.equals(LocalDate.now().plusDays(1))).onError(INVALID_SIZE)
                                                          .examine())
                      .hasMessage(INVALID_SIZE);
        }

        @Test
        public void should_pass_when_termSupplierPasses() {
            Validation.succeedIf(LocalDate.now())
                      .withTerm(() -> 1 == 1)
                      .examine();
        }

        @Test
        public void should_fail_when_termSupplierNotPasses() {
            Assertions.assertThatThrownBy(() -> Validation.succeedIf(LocalDate.now())
                                                          .withTerm(() -> 1 == 2).onError(IS_NOT_EQUAL)
                                                          .examine())
                      .hasMessage(IS_NOT_EQUAL);
        }

        @Test
        public void should_pass_when_satisfiesAny() {
            Validation.succeedIf(LocalDate.now())
                      .satisfiesAny(DateConditions.isBefore(LocalDate.now()), DateConditions.isEqualTo(LocalDate.now()))
                      .examine();
        }

        @Test
        public void should_fail_when_notSatisfiesAny() {
            Assertions.assertThatThrownBy(() -> Validation.succeedIf(LocalDate.now())
                                                          .satisfiesAny(DateConditions.isBefore(LocalDate.now()), DateConditions.isNoEqualTo(LocalDate.now())).onError(NOT_SATISFIES)
                                                          .examine())
                      .hasMessage(NOT_SATISFIES);
        }

        @Test
        public void should_pass_when_satisfiesEvery() {
            Validation.succeedIf(LocalDate.now())
                      .satisfiesEvery(
                              DateConditions.isEqualTo(LocalDate.now()),
                              DateConditions.isAfter(LocalDate.now().minusDays(1))
                      )
                      .examine();
        }

        @Test
        public void should_fail_when_notSatisfiesEvery() {
            Assertions.assertThatThrownBy(() -> Validation.succeedIf(LocalDate.now())
                                                          .satisfiesEvery(
                                                                  DateConditions.isEqualTo(LocalDate.now()),
                                                                  DateConditions.isBefore(LocalDate.now().minusDays(1))
                                                          ).onError(NOT_SATISFIES)
                                                          .examine())
                      .hasMessage(NOT_SATISFIES);
        }

        @Test
        public void should_log_msg() {
            Validation.succeedIf(LocalDate.now())
                      .log("checking if equals")
                      .isEqualTo(LocalDate.now())
                      .examine();
        }

        @Test
        public void should_pass_when_inspectingPredicatePasses() {
            DateHolder dateHolder = new DateHolder(LocalDate.now(), LocalDate.now());

            Validation.succeedIf(dateHolder)
                      .inspecting(DateHolder::getStartDate, it -> it.isEqual(LocalDate.now()))
                      .examine();
        }

        @Test
        public void should_fail_when_inspectingPredicateNotPasses() {
            DateHolder dateHolder = new DateHolder(LocalDate.now(), LocalDate.now());

            Assertions.assertThatThrownBy(() -> Validation.succeedIf(dateHolder)
                                                          .inspecting(DateHolder::getStartDate, it -> it.isEqual(LocalDate.now().plusDays(1))).onError(INVALID_SIZE)
                                                          .examine())
                      .hasMessage(INVALID_SIZE);
        }

        @Test
        public void should_pass_when_inspectingSupplierPasses() {
            DateHolder dateHolder = new DateHolder(LocalDate.now(), LocalDate.now());

            Validation.succeedIf(dateHolder)
                      .inspecting(DateHolder::getStartDate, () -> DateConditions.isEqualTo(LocalDate.now()))
                      .examine();
        }

        @Test
        public void should_fail_when_inspectingSupplierNotPasses() {
            DateHolder dateHolder = new DateHolder(LocalDate.now(), LocalDate.now());

            Assertions.assertThatThrownBy(() -> Validation.succeedIf(dateHolder)
                                                          .inspecting(DateHolder::getStartDate, () -> DateConditions.isEqualTo(LocalDate.now().plusDays(1))).onError(IS_NOT_EMPTY)
                                                          .examine())
                      .hasMessage(IS_NOT_EMPTY);
        }

        @Test
        public void should_pass_when_withTermDeeplyPasses() {
            Validation.succeedIf(LocalDate.now())
                      .withTermDeeply(it -> Validation.succeedIf(it).isAfterOrEqual(LocalDate.now()))
                      .examine();
        }

        @Test
        public void should_fail_when_withTermDeeplyNotPasses() {
            Assertions.assertThatThrownBy(() -> Validation.succeedIf(LocalDate.now())
                                                          .withTermDeeply(it -> Validation.succeedIf(it).isBefore(LocalDate.now()).onError(IS_NOT_BEFORE))
                                                          .examine())
                      .hasMessage(IS_NOT_BEFORE);
        }

        @Test
        public void should_pass_when_inspectingDeeplyPasses() {
            DateHolder dateHolder = new DateHolder(LocalDate.now(), LocalDate.now());

            Validation.succeedIf(dateHolder)
                      .inspectingDeeply(DateHolder::getStartDate, it -> Validation.succeedIf(it).isBeforeOrEqual(LocalDate.now()))
                      .examine();
        }

        @Test
        public void should_fail_when_inspectingDeeplyNotPasses() {
            DateHolder dateHolder = new DateHolder(LocalDate.now(), LocalDate.now());

            Assertions.assertThatThrownBy(() -> Validation.succeedIf(dateHolder)
                                                          .inspectingDeeply(
                                                                  DateHolder::getStartDate,
                                                                  it -> Validation.succeedIf(it).isAfter(LocalDate.now()).onError(IS_NOT_AFTER))
                                                          .examine())
                      .hasMessage(IS_NOT_AFTER);
        }

        @Test
        public void should_pass_when_inspectingWithPredicatePasses() {
            Validation.succeedIf(LocalDate.now())
                      .inspecting(Function.identity(), it -> it.isEqual(LocalDate.now()))
                      .examine();
        }

        @Test
        public void should_fail_when_inspectingWithPredicateNotPasses() {
            Assertions.assertThatThrownBy(() -> Validation.succeedIf(LocalDate.now())
                                                          .inspecting(
                                                                  Function.identity(),
                                                                  it -> it.isAfter(LocalDate.now())
                                                          ).onError(IS_NOT_AFTER)
                                                          .examine())
                      .hasMessage(IS_NOT_AFTER);
        }

        @Test
        public void should_pass_when_inspectingWithSupplierPasses() {
            Validation.succeedIf(LocalDate.now())
                      .inspecting(Function.identity(), ObjectConditions::isNotNull)
                      .examine();
        }

        @Test
        public void should_fail_when_inspectingWithSupplierNotPasses() {
            Assertions.assertThatThrownBy(() -> Validation.succeedIf(LocalDate.now())
                                                          .inspecting(
                                                                  Function.identity(),
                                                                  ObjectConditions::isNull
                                                          ).onError(IS_NOT_AFTER)
                                                          .examine())
                      .hasMessage(IS_NOT_AFTER);
        }

        @Test
        public void should_pass_when_firstGroupMatches() {
            LocalDate date = null;

            Validation.succeedIf(date)
                      .isNull().onError(IS_NOT_NULL)
                      .or()
                      .isEqualTo(LocalDate.now()).onError(NOT_CONTAINS)
                      .examine();
        }

        @Test
        public void should_pass_when_secondGroupMatches() {
            Validation.succeedIf(LocalDate.now())
                      .isNull().onError(IS_NOT_NULL)
                      .or()
                      .isNotNull().onError(IS_NULL)
                      .examine();
        }

        @Test
        public void should_fail_when_groupsNotMatch() {
            Assertions.assertThatThrownBy(() -> Validation.succeedIf(LocalDate.now())
                                                          .isNull().onError(IS_NOT_NULL)
                                                          .or()
                                                          .isEqualTo(LocalDate.now().plusDays(1)).onError(IS_NOT_EQUAL)
                                                          .examine())
                      .hasMessage(IS_NOT_NULL + "\n" + IS_NOT_EQUAL);
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

        @Test
        public void should_fail_when_isNull() {
            LocalDate date = null;

            Assertions.assertThatThrownBy(() -> Validation.failIf(date)
                                                          .isNull()
                                                          .examine())
                      .hasMessage(DEFAULT_EXCEPTION);
        }

        @Test
        public void should_pass_when_isNotNull() {
            Validation.failIf(LocalDate.now())
                      .isNull().onError(IS_NOT_NULL)
                      .examine();
        }

        @Test
        public void should_fail_when_isNotNull() {
            Assertions.assertThatThrownBy(() -> Validation.failIf(LocalDate.now())
                                                          .isNotNull()
                                                          .examine())
                      .hasMessage(DEFAULT_EXCEPTION);
        }

        @Test
        public void should_pass_when_isNull() {
            LocalDate date = null;

            Validation.failIf(date)
                      .isNotNull().onError(IS_NULL)
                      .examine();
        }

        @Test
        public void should_fail_when_isEqualTo() {
            LocalDate date = LocalDate.now();
            LocalDate otherDate = LocalDate.now();

            Assertions.assertThatThrownBy(() -> Validation.failIf(date)
                                                          .isEqualTo(otherDate)
                                                          .examine())
                      .hasMessage(DEFAULT_EXCEPTION);
        }

        @Test
        public void should_pass_when_isNotEqualTo() {
            LocalDate date = LocalDate.now();
            LocalDate otherDate = LocalDate.now().plusDays(1);

            Validation.failIf(date)
                      .isEqualTo(otherDate).onError(IS_NOT_EQUAL)
                      .examine();
        }

        @Test
        public void should_fail_when_isNotEqualTo() {
            LocalDate date = LocalDate.now();
            LocalDate otherDate = LocalDate.now().plusDays(1);

            Assertions.assertThatThrownBy(() -> Validation.failIf(date)
                                                          .isNotEqualTo(otherDate)
                                                          .examine())
                      .hasMessage(DEFAULT_EXCEPTION);
        }

        @Test
        public void should_pass_when_isEqualTo() {
            LocalDate date = LocalDate.now();
            LocalDate otherDate = LocalDate.now();

            Validation.failIf(date)
                      .isNotEqualTo(otherDate).onError(IS_EQUAL)
                      .examine();
        }

        @Test
        public void should_fail_when_termPredicatePasses() {
            Assertions.assertThatThrownBy(() -> Validation.failIf(LocalDate.now())
                                                          .withTerm(it -> it.isEqual(LocalDate.now()))
                                                          .examine())
                      .hasMessage(DEFAULT_EXCEPTION);
        }

        @Test
        public void should_pass_when_termPredicateNotPasses() {
            Validation.failIf(LocalDate.now())
                      .withTerm(it -> it.isEqual(LocalDate.now().plusDays(1))).onError(INVALID_SIZE)
                      .examine();
        }

        @Test
        public void should_fail_when_termSupplierPasses() {
            Assertions.assertThatThrownBy(() -> Validation.failIf(LocalDate.now())
                                                          .withTerm(() -> 1 == 1)
                                                          .examine())
                      .hasMessage(DEFAULT_EXCEPTION);
        }

        @Test
        public void should_pass_when_termSupplierNotPasses() {
            Validation.failIf(LocalDate.now())
                      .withTerm(() -> 1 == 2).onError(IS_NOT_EQUAL)
                      .examine();
        }

        @Test
        public void should_fail_when_satisfiesAny() {
            Assertions.assertThatThrownBy(() -> Validation.failIf(LocalDate.now())
                                                          .satisfiesAny(ObjectConditions.isNull(), ObjectConditions.isNotNull())
                                                          .examine())
                      .hasMessage(DEFAULT_EXCEPTION);
        }

        @Test
        public void should_pass_when_notSatisfiesAny() {
            Validation.failIf(LocalDate.now())
                      .satisfiesAny(ObjectConditions.isNull(), DateConditions.isEqualTo(LocalDate.now().plusDays(1))).onError(NOT_SATISFIES)
                      .examine();
        }

        @Test
        public void should_fail_when_satisfiesEvery() {
            Assertions.assertThatThrownBy(() -> Validation.failIf(LocalDate.now())
                                                          .satisfiesEvery(ObjectConditions.isNotNull(), DateConditions.isEqualTo(LocalDate.now()))
                                                          .examine())
                      .hasMessage(DEFAULT_EXCEPTION);
        }

        @Test
        public void should_pass_when_notSatisfiesEvery() {
            Validation.failIf(LocalDate.now())
                      .satisfiesEvery(
                              ObjectConditions.isNull(),
                              DateConditions.isAfterOrEqual(LocalDate.now().plusDays(1))
                      ).onError(NOT_SATISFIES)
                      .examine();
        }

        @Test
        public void should_fail_when_inspectingPredicatePasses() {
            DateHolder dateHolder = new DateHolder(LocalDate.now(), LocalDate.now());

            Assertions.assertThatThrownBy(() -> Validation.failIf(dateHolder)
                                                          .inspecting(DateHolder::getEndDate, it -> it.isEqual(LocalDate.now()))
                                                          .examine())
                      .hasMessage(DEFAULT_EXCEPTION);
        }

        @Test
        public void should_pass_when_inspectingPredicateNotPasses() {
            DateHolder dateHolder = new DateHolder(LocalDate.now(), LocalDate.now());

            Validation.failIf(dateHolder)
                      .inspecting(DateHolder::getEndDate, Objects::isNull)
                      .examine();
        }

        @Test
        public void should_fail_when_inspectingSupplierPasses() {
            DateHolder dateHolder = new DateHolder(LocalDate.now(), LocalDate.now());

            Assertions.assertThatThrownBy(() -> Validation.failIf(dateHolder)
                                                          .inspecting(DateHolder::getEndDate, ObjectConditions::isNotNull)
                                                          .examine())
                      .hasMessage(DEFAULT_EXCEPTION);
        }

        @Test
        public void should_pass_when_inspectingSupplierNotPasses() {
            DateHolder dateHolder = new DateHolder(LocalDate.now(), LocalDate.now());

            Validation.failIf(dateHolder)
                      .inspecting(DateHolder::getEndDate, ObjectConditions::isNull)
                      .examine();
        }

        @Test
        public void should_fail_when_withTermDeeplyPasses() {
            Assertions.assertThatThrownBy(() -> Validation.failIf(LocalDate.now())
                                                          .withTermDeeply(it -> Validation.succeedIf(it).isNotNull())
                                                          .examine())
                      .hasMessage(DEFAULT_EXCEPTION);
        }

        @Test
        public void should_pass_when_withTermDeeplyNotPasses() {
            Validation.failIf(LocalDate.now())
                      .withTermDeeply(it -> Validation.succeedIf(it).isNull())
                      .examine();
        }

        @Test
        public void should_fail_when_inspectingDeeplyPasses() {
            DateHolder dateHolder = new DateHolder(LocalDate.now(), LocalDate.now());

            Assertions.assertThatThrownBy(() -> Validation.failIf(dateHolder)
                                                          .inspectingDeeply(DateHolder::getEndDate, it -> Validation.succeedIf(it).isNotNull())
                                                          .examine())
                      .hasMessage(DEFAULT_EXCEPTION);
        }

        @Test
        public void should_pass_when_inspectingDeeplyNotPasses() {
            DateHolder dateHolder = new DateHolder(LocalDate.now(), LocalDate.now());

            Validation.failIf(dateHolder)
                      .inspectingDeeply(DateHolder::getEndDate, it -> Validation.succeedIf(it).isNull())
                      .examine();
        }


        @Test
        public void should_fail_when_inspectingWithPredicatePasses() {
            Assertions.assertThatThrownBy(() -> Validation.failIf(LocalDate.now())
                                                          .inspecting(Function.identity(), it -> it.isEqual(LocalDate.now()))
                                                          .examine())
                      .hasMessage(DEFAULT_EXCEPTION);
        }

        @Test
        public void should_pass_when_inspectingWithPredicateNotPasses() {
            Validation.failIf(LocalDate.now())
                      .inspecting(
                              Function.identity(),
                              it -> it.isAfter(LocalDate.now())
                      ).onError(IS_NOT_AFTER)
                      .examine();
        }

        @Test
        public void should_fail_when_inspectingWithSupplierPasses() {
            Assertions.assertThatThrownBy(() -> Validation.failIf(LocalDate.now())
                                                          .inspecting(Function.identity(), ObjectConditions::isNotNull)
                                                          .examine())
                      .hasMessage(DEFAULT_EXCEPTION);
        }

        @Test
        public void should_pass_when_inspectingWithSupplierNotPasses() {
            Validation.failIf(LocalDate.now())
                      .inspecting(
                              Function.identity(),
                              ObjectConditions::isNull
                      ).onError(IS_NOT_AFTER)
                      .examine();
        }

        @Test
        public void should_fail_when_firstGroupMatches() {
            LocalDate date = null;

            Assertions.assertThatThrownBy(() -> Validation.failIf(date)
                                                          .isNull().onError(IS_NULL)
                                                          .or()
                                                          .isEqualTo(LocalDate.now()).onError(IS_NOT_EQUAL)
                                                          .examine())
                      .hasMessage(IS_NULL);
        }

        @Test
        public void should_fail_when_secondGroupMatches() {
            Assertions.assertThatThrownBy(() -> Validation.failIf(LocalDate.now())
                                                          .isNull().onError(IS_NULL)
                                                          .or()
                                                          .isNotNull().onError(IS_NOT_NULL)
                                                          .examine())
                      .hasMessage(IS_NOT_NULL);
        }

        @Test
        public void should_pass_when_groupsNotMatch() {
            Validation.failIf(LocalDate.now())
                      .isNull().onError(IS_NULL)
                      .or()
                      .isEqualTo(LocalDate.now().plusDays(1)).onError(IS_EQUAL)
                      .examine();
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
