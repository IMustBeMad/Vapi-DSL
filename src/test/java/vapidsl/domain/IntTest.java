package vapidsl.domain;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import vapidsl.Validation;
import vapidsl.ValidatorTest;
import vapidsl.common.ValidationError;
import vapidsl.dict.ErrorMode;
import vapidsl.domain.number.integer.IntConditions;
import vapidsl.domain.object.ObjectConditions;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.function.Function;

@RunWith(Suite.class)
@Suite.SuiteClasses({IntTest.SucceedIfTest.class, IntTest.FailedIfTest.class})
public class IntTest extends ValidatorTest {

    private static final Integer TEN = 10;
    private static final Integer ONE = 1;
    private static final Integer ZERO = 0;

    public static class SucceedIfTest {

        @Test
        public void should_pass_when_isGt() {
            Validation.succeedIf(TEN)
                      .isGt(ONE)
                      .examine();
        }

        @Test
        public void should_fail_when_isNotGt() {
            Assertions.assertThatThrownBy(() -> Validation.succeedIf(ONE)
                                                          .isGt(TEN).onError(IS_NOT_GREATER)
                                                          .examine())
                      .hasMessage(IS_NOT_GREATER);
        }

        @Test
        public void should_pass_when_isGte() {
            Validation.succeedIf(TEN)
                      .isGte(TEN)
                      .examine();
        }

        @Test
        public void should_fail_when_isNotGte() {
            Assertions.assertThatThrownBy(() -> Validation.succeedIf(ONE)
                                                          .isGte(TEN).onError(IS_NOT_GREATER_OR_EQUAL)
                                                          .examine())
                      .hasMessage(IS_NOT_GREATER_OR_EQUAL);
        }

        @Test
        public void should_pass_when_isLt() {
            Validation.succeedIf(ONE)
                      .isLt(TEN)
                      .examine();
        }

        @Test
        public void should_fail_when_isNotLt() {
            Assertions.assertThatThrownBy(() -> Validation.succeedIf(TEN)
                                                          .isLt(TEN).onError(IS_NOT_LESSER)
                                                          .examine())
                      .hasMessage(IS_NOT_LESSER);
        }

        @Test
        public void should_pass_when_isLte() {
            Validation.succeedIf(ONE)
                      .isLte(ONE)
                      .examine();
        }

        @Test
        public void should_fail_when_isNotLte() {
            Assertions.assertThatThrownBy(() -> Validation.succeedIf(TEN)
                                                          .isLte(ONE).onError(IS_NOT_LESSER_OR_EQUAL)
                                                          .examine())
                      .hasMessage(IS_NOT_LESSER_OR_EQUAL);
        }

        @Test
        public void should_pass_when_isNull() {
            BigDecimal number = null;

            Validation.succeedIf(number)
                      .isNull()
                      .examine();
        }

        @Test
        public void should_fail_when_isNotNull() {
            Assertions.assertThatThrownBy(() -> Validation.succeedIf(ONE)
                                                          .isNull().onError(IS_NOT_NULL)
                                                          .examine())
                      .hasMessage(IS_NOT_NULL);
        }

        @Test
        public void should_pass_when_isNotNull() {
            Validation.succeedIf(ONE)
                      .isNotNull()
                      .examine();
        }

        @Test
        public void should_fail_when_isNull() {
            Integer number = null;

            Assertions.assertThatThrownBy(() -> Validation.succeedIf(number)
                                                          .isNotNull().onError(IS_NULL)
                                                          .examine())
                      .hasMessage(IS_NULL);
        }

        @Test
        public void should_pass_when_isEqualTo() {
            Validation.succeedIf(ONE)
                      .isEqualTo(ONE)
                      .examine();
        }

        @Test
        public void should_fail_when_isNotEqualTo() {
            Assertions.assertThatThrownBy(() -> Validation.succeedIf(ONE)
                                                          .isEqualTo(TEN).onError(IS_NOT_EQUAL)
                                                          .examine())
                      .hasMessage(IS_NOT_EQUAL);
        }

        @Test
        public void should_pass_when_isNotEqualTo() {
            Validation.succeedIf(ONE)
                      .isNotEqualTo(TEN)
                      .examine();
        }

        @Test
        public void should_fail_when_isEqualTo() {
            Assertions.assertThatThrownBy(() -> Validation.succeedIf(ONE)
                                                          .isNotEqualTo(ONE).onError(IS_EQUAL)
                                                          .examine())
                      .hasMessage(IS_EQUAL);
        }

        @Test
        public void should_fail_withOnlySpecifiedError_when_multipleGroupFail() {
            Assertions.assertThat(Validation.succeedIf(ONE)
                                            .isGt(TEN)
                                            .or()
                                            .isNull()
                                            .or()
                                            .isLt(ONE)
                                            .groupError("test")
                                            .examine(ErrorMode.RETURN))
                      .extracting(ValidationError::getReasonCode)
                      .containsExactly("test");
        }

        @Test
        public void should_pass_when_termPredicatePasses() {
            Validation.succeedIf(TEN)
                      .withTerm(it -> it.equals(TEN))
                      .examine();
        }

        @Test
        public void should_fail_when_termPredicateNotPasses() {
            Assertions.assertThatThrownBy(() -> Validation.succeedIf(TEN)
                                                          .withTerm(it -> it.equals(ONE)).onError(IS_NOT_EQUAL)
                                                          .examine())
                      .hasMessage(IS_NOT_EQUAL);
        }

        @Test
        public void should_pass_when_termSupplierPasses() {
            Validation.succeedIf(ONE)
                      .withTerm(() -> 1 == 1)
                      .examine();
        }

        @Test
        public void should_fail_when_termSupplierNotPasses() {
            Assertions.assertThatThrownBy(() -> Validation.succeedIf(ONE)
                                                          .withTerm(() -> 1 == 2).onError(IS_NOT_EQUAL)
                                                          .examine())
                      .hasMessage(IS_NOT_EQUAL);
        }

        @Test
        public void should_pass_when_satisfiesAny() {
            Validation.succeedIf(ONE)
                      .satisfiesAny(IntConditions.isEqualTo(ONE), IntConditions.isGt(TEN))
                      .examine();
        }

        @Test
        public void should_fail_when_notSatisfiesAny() {
            Assertions.assertThatThrownBy(() -> Validation.succeedIf(TEN)
                                                          .satisfiesAny(
                                                                  IntConditions.isEqualTo(ONE),
                                                                  IntConditions.isGt(TEN)
                                                          )
                                                          .onError(NOT_SATISFIES)
                                                          .examine())
                      .hasMessage(NOT_SATISFIES);
        }

        @Test
        public void should_pass_when_satisfiesEvery() {
            Validation.succeedIf(ONE)
                      .satisfiesEvery(
                              IntConditions.isLt(TEN),
                              IntConditions.isGte(ONE)
                      )
                      .examine();
        }

        @Test
        public void should_fail_when_notSatisfiesEvery() {
            Assertions.assertThatThrownBy(() -> Validation.succeedIf(TEN)
                                                          .satisfiesEvery(
                                                                  IntConditions.isLt(TEN),
                                                                  IntConditions.isGte(ONE)
                                                          ).onError(NOT_SATISFIES)
                                                          .examine())
                      .hasMessage(NOT_SATISFIES);
        }

        @Test
        public void should_log_msg() {
            Validation.succeedIf(TEN)
                      .log("checking if equals")
                      .isEqualTo(TEN)
                      .examine();
        }

        @Test
        public void should_pass_when_inspectingPredicatePasses() {
            Validation.succeedIf(ONE)
                      .inspecting(it -> TEN, it -> it.equals(TEN))
                      .examine();
        }

        @Test
        public void should_fail_when_inspectingPredicateNotPasses() {
            Assertions.assertThatThrownBy(() -> Validation.succeedIf(TEN)
                                                          .inspecting(Function.identity(), it -> it.equals(ONE)).onError(INVALID_SIZE)
                                                          .examine())
                      .hasMessage(INVALID_SIZE);
        }

        @Test
        public void should_pass_when_inspectingSupplierPasses() {
            Validation.succeedIf(TEN)
                      .inspecting(it -> ONE, () -> IntConditions.isEqualTo(ONE))
                      .examine();
        }

        @Test
        public void should_fail_when_inspectingSupplierNotPasses() {
            Assertions.assertThatThrownBy(() -> Validation.succeedIf(ONE)
                                                          .inspecting(Function.identity(), () -> IntConditions.isEqualTo(TEN)).onError(IS_NOT_EQUAL)
                                                          .examine())
                      .hasMessage(IS_NOT_EQUAL);
        }

        @Test
        public void should_pass_when_inspectingWithPredicatePasses() {
            Validation.succeedIf(TEN)
                      .inspecting(Function.identity(), it -> it.equals(TEN))
                      .examine();
        }

        @Test
        public void should_fail_when_inspectingWithPredicateNotPasses() {
            Assertions.assertThatThrownBy(() -> Validation.succeedIf(ONE)
                                                          .inspecting(
                                                                  Function.identity(),
                                                                  it -> it.equals(TEN)
                                                          ).onError(IS_NOT_EQUAL)
                                                          .examine())
                      .hasMessage(IS_NOT_EQUAL);
        }

        @Test
        public void should_pass_when_inspectingWithSupplierPasses() {
            Validation.succeedIf(ONE)
                      .inspecting(Function.identity(), ObjectConditions::isNotNull)
                      .examine();
        }

        @Test
        public void should_fail_when_inspectingWithSupplierNotPasses() {
            Assertions.assertThatThrownBy(() -> Validation.succeedIf(ZERO)
                                                          .inspecting(
                                                                  Function.identity(),
                                                                  ObjectConditions::isNull
                                                          ).onError(IS_NOT_NULL)
                                                          .examine())
                      .hasMessage(IS_NOT_NULL);
        }

        @Test
        public void should_pass_when_firstGroupMatches() {
            Integer number = null;

            Validation.succeedIf(number)
                      .isNull().onError(IS_NOT_NULL)
                      .or()
                      .isEqualTo(TEN).onError(IS_NOT_EQUAL)
                      .examine();
        }

        @Test
        public void should_pass_when_secondGroupMatches() {
            Validation.succeedIf(ONE)
                      .isNull().onError(IS_NOT_NULL)
                      .or()
                      .isNotNull().onError(IS_NULL)
                      .examine();
        }

        @Test
        public void should_fail_when_groupsNotMatch() {
            Assertions.assertThatThrownBy(() -> Validation.succeedIf(ONE)
                                                          .isNull().onError(IS_NOT_NULL)
                                                          .or()
                                                          .isEqualTo(TEN).onError(IS_NOT_EQUAL)
                                                          .examine())
                      .hasMessage(IS_NOT_NULL + "\n" + IS_NOT_EQUAL);
        }

        @Test
        public void should_pass_when_inspectingDeeplyPasses() {
            Validation.succeedIf(TEN)
                      .inspectingDeeply(Function.identity(), it -> Validation.succeedIf(it).isEqualTo(TEN))
                      .examine();
        }

        @Test
        public void should_fail_when_inspectingDeeplyNotPasses() {
            Assertions.assertThatThrownBy(() -> Validation.succeedIf(TEN)
                                                          .inspectingDeeply(
                                                                  it -> ONE,
                                                                  it -> Validation.succeedIf(it).isEqualTo(TEN).onError(IS_NOT_EQUAL))
                                                          .examine())
                      .hasMessage(IS_NOT_EQUAL);
        }
    }

    public static class FailedIfTest {

        @Test
        public void should_fail_when_allConditionsMatch() {
            Assertions.assertThat(Validation.failIf(TEN)
                                            .isNotNull()
                                            .isEqualTo(TEN)
                                            .groupError("is invalid number")
                                            .examine(ErrorMode.RETURN))
                      .extracting(ValidationError::getReasonCode)
                      .containsExactly("is invalid number");
        }

        @Test
        public void should_fail_when_isNull() {
            Integer number = null;

            Assertions.assertThatThrownBy(() -> Validation.failIf(number)
                                                          .isNull()
                                                          .examine())
                      .hasMessage(DEFAULT_EXCEPTION);
        }

        @Test
        public void should_pass_when_isNotNull() {
            Validation.failIf(TEN)
                      .isNull().onError(IS_NOT_NULL)
                      .examine();
        }

        @Test
        public void should_fail_when_isNotNull() {
            Assertions.assertThatThrownBy(() -> Validation.failIf(ONE)
                                                          .isNotNull()
                                                          .examine())
                      .hasMessage(DEFAULT_EXCEPTION);
        }

        @Test
        public void should_pass_when_isNull() {
            Integer number = null;

            Validation.failIf(number)
                      .isNotNull().onError(IS_NULL)
                      .examine();
        }

        @Test
        public void should_fail_when_isEqualTo() {
            Assertions.assertThatThrownBy(() -> Validation.failIf(ONE)
                                                          .isEqualTo(ONE)
                                                          .examine())
                      .hasMessage(DEFAULT_EXCEPTION);
        }

        @Test
        public void should_pass_when_isNotEqualTo() {
            Validation.failIf(ONE)
                      .isEqualTo(TEN).onError(IS_NOT_EQUAL)
                      .examine();
        }

        @Test
        public void should_fail_when_isNotEqualTo() {
            Assertions.assertThatThrownBy(() -> Validation.failIf(ONE)
                                                          .isNotEqualTo(TEN)
                                                          .examine())
                      .hasMessage(DEFAULT_EXCEPTION);
        }

        @Test
        public void should_pass_when_isEqualTo() {
            Validation.failIf(ONE)
                      .isNotEqualTo(ONE).onError(IS_EQUAL)
                      .examine();
        }

        @Test
        public void should_fail_when_termPredicatePasses() {
            Assertions.assertThatThrownBy(() -> Validation.failIf(TEN)
                                                          .withTerm(it -> it.equals(TEN))
                                                          .examine())
                      .hasMessage(DEFAULT_EXCEPTION);
        }

        @Test
        public void should_pass_when_termPredicateNotPasses() {
            Validation.failIf(TEN)
                      .withTerm(it -> it.equals(ONE)).onError(IS_EQUAL)
                      .examine();
        }

        @Test
        public void should_fail_when_termSupplierPasses() {
            Assertions.assertThatThrownBy(() -> Validation.failIf(TEN)
                                                          .withTerm(() -> 1 == 1)
                                                          .examine())
                      .hasMessage(DEFAULT_EXCEPTION);
        }

        @Test
        public void should_pass_when_termSupplierNotPasses() {
            Validation.failIf(TEN)
                      .withTerm(() -> 1 == 2).onError(IS_NOT_EQUAL)
                      .examine();
        }

        @Test
        public void should_fail_when_satisfiesAny() {
            Assertions.assertThatThrownBy(() -> Validation.failIf(TEN)
                                                          .satisfiesAny(ObjectConditions.isNull(), ObjectConditions.isNotNull())
                                                          .examine())
                      .hasMessage(DEFAULT_EXCEPTION);
        }

        @Test
        public void should_pass_when_notSatisfiesAny() {
            Validation.failIf(TEN)
                      .satisfiesAny(ObjectConditions.isNull(), IntConditions.isEqualTo(ONE)).onError(NOT_SATISFIES)
                      .examine();
        }

        @Test
        public void should_fail_when_satisfiesEvery() {
            Assertions.assertThatThrownBy(() -> Validation.failIf(TEN)
                                                          .satisfiesEvery(ObjectConditions.isNotNull(), IntConditions.isEqualTo(TEN))
                                                          .examine())
                      .hasMessage(DEFAULT_EXCEPTION);
        }

        @Test
        public void should_pass_when_notSatisfiesEvery() {
            Validation.failIf(TEN)
                      .satisfiesEvery(
                              ObjectConditions.isNull(),
                              IntConditions.isLte(ONE)
                      ).onError(NOT_SATISFIES)
                      .examine();
        }

        @Test
        public void should_fail_when_inspectingPredicatePasses() {
            Assertions.assertThatThrownBy(() -> Validation.failIf(TEN)
                                                          .inspecting(Function.identity(), it -> it.equals(TEN))
                                                          .examine())
                      .hasMessage(DEFAULT_EXCEPTION);
        }

        @Test
        public void should_pass_when_inspectingPredicateNotPasses() {
            Validation.failIf(TEN)
                      .inspecting(it -> ONE, Objects::isNull)
                      .examine();
        }

        @Test
        public void should_fail_when_inspectingSupplierPasses() {
            Assertions.assertThatThrownBy(() -> Validation.failIf(TEN)
                                                          .inspecting(Function.identity(), ObjectConditions::isNotNull)
                                                          .examine())
                      .hasMessage(DEFAULT_EXCEPTION);
        }

        @Test
        public void should_pass_when_inspectingSupplierNotPasses() {
            Validation.failIf(TEN)
                      .inspecting(Function.identity(), ObjectConditions::isNull)
                      .examine();
        }

        @Test
        public void should_fail_when_inspectingWithPredicatePasses() {
            Assertions.assertThatThrownBy(() -> Validation.failIf(TEN)
                                                          .inspecting(Function.identity(), it -> it.equals(TEN))
                                                          .examine())
                      .hasMessage(DEFAULT_EXCEPTION);
        }

        @Test
        public void should_pass_when_inspectingWithPredicateNotPasses() {
            Validation.failIf(TEN)
                      .inspecting(
                              Function.identity(),
                              it -> it.equals(ONE)
                      ).onError(IS_EQUAL)
                      .examine();
        }

        @Test
        public void should_fail_when_inspectingWithSupplierPasses() {
            Assertions.assertThatThrownBy(() -> Validation.failIf(TEN)
                                                          .inspecting(Function.identity(), ObjectConditions::isNotNull)
                                                          .examine())
                      .hasMessage(DEFAULT_EXCEPTION);
        }

        @Test
        public void should_pass_when_inspectingWithSupplierNotPasses() {
            Validation.failIf(TEN)
                      .inspecting(
                              Function.identity(),
                              ObjectConditions::isNull
                      ).onError(IS_NOT_AFTER)
                      .examine();
        }

        @Test
        public void should_fail_when_firstGroupMatches() {
            Integer number = null;

            Assertions.assertThatThrownBy(() -> Validation.failIf(number)
                                                          .isNull().onError(IS_NULL)
                                                          .or()
                                                          .isEqualTo(TEN).onError(IS_EQUAL)
                                                          .examine())
                      .hasMessage(IS_NULL);
        }

        @Test
        public void should_fail_when_secondGroupMatches() {
            Assertions.assertThatThrownBy(() -> Validation.failIf(TEN)
                                                          .isNull().onError(IS_NULL)
                                                          .or()
                                                          .isNotNull().onError(IS_NOT_NULL)
                                                          .examine())
                      .hasMessage(IS_NOT_NULL);
        }

        @Test
        public void should_pass_when_groupsNotMatch() {
            Validation.failIf(TEN)
                      .isNull().onError(IS_NULL)
                      .or()
                      .isEqualTo(ONE).onError(IS_EQUAL)
                      .examine();
        }

        @Test
        public void should_fail_when_inspectingDeeplyPasses() {
            Assertions.assertThatThrownBy(() -> Validation.failIf(TEN)
                                                          .inspectingDeeply(Function.identity(), it -> Validation.succeedIf(it).isEqualTo(TEN))
                                                          .examine())
                      .hasMessage(DEFAULT_EXCEPTION);
        }

        @Test
        public void should_pass_when_inspectingDeeplyNotPasses() {
            Validation.failIf(TEN)
                      .inspectingDeeply(
                              it -> ONE,
                              it -> Validation.succeedIf(it).isEqualTo(TEN).onError(IS_NOT_EQUAL))
                      .examine();
        }
    }
}
