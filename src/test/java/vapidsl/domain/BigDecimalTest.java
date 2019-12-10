package vapidsl.domain;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import vapidsl.Validation;
import vapidsl.ValidatorTest;
import vapidsl.common.ValidationError;
import vapidsl.dict.ErrorMode;
import vapidsl.domain.number.bigdecimal.BigDecimalConditions;
import vapidsl.domain.object.ObjectConditions;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.function.Function;

@RunWith(Suite.class)
@Suite.SuiteClasses({BigDecimalTest.SucceedIfTest.class, BigDecimalTest.FailedIfTest.class})
public class BigDecimalTest extends ValidatorTest {

    public static class SucceedIfTest {

        @Test
        public void should_pass_when_isGt() {
            Validation.succeedIf(BigDecimal.TEN)
                      .isGt(BigDecimal.ONE)
                      .examine();
        }

        @Test
        public void should_fail_when_isNotGt() {
            Assertions.assertThatThrownBy(() -> Validation.succeedIf(BigDecimal.ONE)
                                                          .isGt(BigDecimal.TEN).onError(IS_NOT_GREATER)
                                                          .examine())
                      .hasMessage(IS_NOT_GREATER);
        }

        @Test
        public void should_pass_when_isGte() {
            Validation.succeedIf(BigDecimal.TEN)
                      .isGte(BigDecimal.TEN)
                      .examine();
        }

        @Test
        public void should_fail_when_isNotGte() {
            Assertions.assertThatThrownBy(() -> Validation.succeedIf(BigDecimal.ONE)
                                                          .isGte(BigDecimal.TEN).onError(IS_NOT_GREATER_OR_EQUAL)
                                                          .examine())
                      .hasMessage(IS_NOT_GREATER_OR_EQUAL);
        }

        @Test
        public void should_pass_when_isLt() {
            Validation.succeedIf(BigDecimal.ONE)
                      .isLt(BigDecimal.TEN)
                      .examine();
        }

        @Test
        public void should_fail_when_isNotLt() {
            Assertions.assertThatThrownBy(() -> Validation.succeedIf(BigDecimal.TEN)
                                                          .isLt(BigDecimal.TEN).onError(IS_NOT_LESSER)
                                                          .examine())
                      .hasMessage(IS_NOT_LESSER);
        }

        @Test
        public void should_pass_when_isLte() {
            Validation.succeedIf(BigDecimal.ONE)
                      .isLte(BigDecimal.ONE)
                      .examine();
        }

        @Test
        public void should_fail_when_isNotLte() {
            Assertions.assertThatThrownBy(() -> Validation.succeedIf(BigDecimal.TEN)
                                                          .isLte(BigDecimal.ONE).onError(IS_NOT_LESSER_OR_EQUAL)
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
            Assertions.assertThatThrownBy(() -> Validation.succeedIf(BigDecimal.ONE)
                                                          .isNull().onError(IS_NOT_NULL)
                                                          .examine())
                      .hasMessage(IS_NOT_NULL);
        }

        @Test
        public void should_pass_when_isNotNull() {
            Validation.succeedIf(BigDecimal.ONE)
                      .isNotNull()
                      .examine();
        }

        @Test
        public void should_fail_when_isNull() {
            BigDecimal number = null;

            Assertions.assertThatThrownBy(() -> Validation.succeedIf(number)
                                                          .isNotNull().onError(IS_NULL)
                                                          .examine())
                      .hasMessage(IS_NULL);
        }

        @Test
        public void should_pass_when_isEqualTo() {
            BigDecimal thisNumber = BigDecimal.ONE;
            BigDecimal thatNumber = BigDecimal.ONE;

            Validation.succeedIf(thisNumber)
                      .isEqualTo(thatNumber)
                      .examine();
        }

        @Test
        public void should_fail_when_isNotEqualTo() {
            BigDecimal thisNumber = BigDecimal.ONE;
            BigDecimal thatNumber = BigDecimal.TEN;

            Assertions.assertThatThrownBy(() -> Validation.succeedIf(thisNumber)
                                                          .isEqualTo(thatNumber).onError(IS_NOT_EQUAL)
                                                          .examine())
                      .hasMessage(IS_NOT_EQUAL);
        }

        @Test
        public void should_pass_when_isNotEqualTo() {
            BigDecimal thisNumber = BigDecimal.ONE;
            BigDecimal thatNumber = BigDecimal.TEN;

            Validation.succeedIf(thisNumber)
                      .isNotEqualTo(thatNumber)
                      .examine();
        }

        @Test
        public void should_fail_when_isEqualTo() {
            BigDecimal thisNumber = BigDecimal.ONE;
            BigDecimal thatNumber = BigDecimal.ONE;

            Assertions.assertThatThrownBy(() -> Validation.succeedIf(thisNumber)
                                                          .isNotEqualTo(thatNumber).onError(IS_EQUAL)
                                                          .examine())
                      .hasMessage(IS_EQUAL);
        }

        @Test
        public void should_fail_withOnlySpecifiedError_when_multipleGroupFail() {
            Assertions.assertThat(Validation.succeedIf(BigDecimal.ONE)
                                            .isGt(BigDecimal.TEN)
                                            .or()
                                            .isNull()
                                            .or()
                                            .isLt(BigDecimal.ONE)
                                            .groupError("test")
                                            .examine(ErrorMode.RETURN))
                      .extracting(ValidationError::getReasonCode)
                      .containsExactly("test");
        }

        @Test
        public void should_pass_when_termPredicatePasses() {
            Validation.succeedIf(BigDecimal.TEN)
                      .withTerm(it -> it.equals(BigDecimal.TEN))
                      .examine();
        }

        @Test
        public void should_fail_when_termPredicateNotPasses() {
            Assertions.assertThatThrownBy(() -> Validation.succeedIf(BigDecimal.TEN)
                                                          .withTerm(it -> it.equals(BigDecimal.ONE)).onError(IS_NOT_EQUAL)
                                                          .examine())
                      .hasMessage(IS_NOT_EQUAL);
        }

        @Test
        public void should_pass_when_termSupplierPasses() {
            Validation.succeedIf(BigDecimal.ONE)
                      .withTerm(() -> 1 == 1)
                      .examine();
        }

        @Test
        public void should_fail_when_termSupplierNotPasses() {
            Assertions.assertThatThrownBy(() -> Validation.succeedIf(BigDecimal.ONE)
                                                          .withTerm(() -> 1 == 2).onError(IS_NOT_EQUAL)
                                                          .examine())
                      .hasMessage(IS_NOT_EQUAL);
        }

        @Test
        public void should_pass_when_satisfiesAny() {
            Validation.succeedIf(BigDecimal.ONE)
                      .satisfiesAny(BigDecimalConditions.isEqualTo(BigDecimal.ONE), BigDecimalConditions.isGt(BigDecimal.TEN))
                      .examine();
        }

        @Test
        public void should_fail_when_notSatisfiesAny() {
            Assertions.assertThatThrownBy(() -> Validation.succeedIf(BigDecimal.TEN)
                                                          .satisfiesAny(
                                                                  BigDecimalConditions.isEqualTo(BigDecimal.ONE),
                                                                  BigDecimalConditions.isGt(BigDecimal.TEN)
                                                          )
                                                          .onError(NOT_SATISFIES)
                                                          .examine())
                      .hasMessage(NOT_SATISFIES);
        }

        @Test
        public void should_pass_when_satisfiesEvery() {
            Validation.succeedIf(BigDecimal.ONE)
                      .satisfiesEvery(
                              BigDecimalConditions.isLt(BigDecimal.TEN),
                              BigDecimalConditions.isGte(BigDecimal.ONE)
                      )
                      .examine();
        }

        @Test
        public void should_fail_when_notSatisfiesEvery() {
            Assertions.assertThatThrownBy(() -> Validation.succeedIf(BigDecimal.TEN)
                                                          .satisfiesEvery(
                                                                  BigDecimalConditions.isLt(BigDecimal.TEN),
                                                                  BigDecimalConditions.isGte(BigDecimal.ONE)
                                                          ).onError(NOT_SATISFIES)
                                                          .examine())
                      .hasMessage(NOT_SATISFIES);
        }

        @Test
        public void should_log_msg() {
            Validation.succeedIf(BigDecimal.TEN)
                      .log("checking if equals")
                      .isEqualTo(BigDecimal.TEN)
                      .examine();
        }

        @Test
        public void should_pass_when_inspectingPredicatePasses() {
            Validation.succeedIf(BigDecimal.ONE)
                      .inspecting(it -> BigDecimal.TEN, it -> it.equals(BigDecimal.TEN))
                      .examine();
        }

        @Test
        public void should_fail_when_inspectingPredicateNotPasses() {
            Assertions.assertThatThrownBy(() -> Validation.succeedIf(BigDecimal.TEN)
                                                          .inspecting(Function.identity(), it -> it.equals(BigDecimal.ONE)).onError(INVALID_SIZE)
                                                          .examine())
                      .hasMessage(INVALID_SIZE);
        }

        @Test
        public void should_pass_when_inspectingSupplierPasses() {
            Validation.succeedIf(BigDecimal.TEN)
                      .inspecting(it -> BigDecimal.ONE, () -> BigDecimalConditions.isEqualTo(BigDecimal.ONE))
                      .examine();
        }

        @Test
        public void should_fail_when_inspectingSupplierNotPasses() {
            Assertions.assertThatThrownBy(() -> Validation.succeedIf(BigDecimal.ONE)
                                                          .inspecting(Function.identity(), () -> BigDecimalConditions.isEqualTo(BigDecimal.TEN)).onError(IS_NOT_EQUAL)
                                                          .examine())
                      .hasMessage(IS_NOT_EQUAL);
        }

        @Test
        public void should_pass_when_inspectingWithPredicatePasses() {
            Validation.succeedIf(BigDecimal.TEN)
                      .inspecting(Function.identity(), it -> it.equals(BigDecimal.TEN))
                      .examine();
        }

        @Test
        public void should_fail_when_inspectingWithPredicateNotPasses() {
            Assertions.assertThatThrownBy(() -> Validation.succeedIf(BigDecimal.ONE)
                                                          .inspecting(
                                                                  Function.identity(),
                                                                  it -> it.equals(BigDecimal.TEN)
                                                          ).onError(IS_NOT_EQUAL)
                                                          .examine())
                      .hasMessage(IS_NOT_EQUAL);
        }

        @Test
        public void should_pass_when_inspectingWithSupplierPasses() {
            Validation.succeedIf(BigDecimal.ONE)
                      .inspecting(Function.identity(), ObjectConditions::isNotNull)
                      .examine();
        }

        @Test
        public void should_fail_when_inspectingWithSupplierNotPasses() {
            Assertions.assertThatThrownBy(() -> Validation.succeedIf(BigDecimal.ZERO)
                                                          .inspecting(
                                                                  Function.identity(),
                                                                  ObjectConditions::isNull
                                                          ).onError(IS_NOT_NULL)
                                                          .examine())
                      .hasMessage(IS_NOT_NULL);
        }

        @Test
        public void should_pass_when_firstGroupMatches() {
            BigDecimal number = null;

            Validation.succeedIf(number)
                      .isNull().onError(IS_NOT_NULL)
                      .or()
                      .isEqualTo(BigDecimal.TEN).onError(IS_NOT_EQUAL)
                      .examine();
        }

        @Test
        public void should_pass_when_secondGroupMatches() {
            Validation.succeedIf(BigDecimal.ONE)
                      .isNull().onError(IS_NOT_NULL)
                      .or()
                      .isNotNull().onError(IS_NULL)
                      .examine();
        }

        @Test
        public void should_fail_when_groupsNotMatch() {
            Assertions.assertThatThrownBy(() -> Validation.succeedIf(BigDecimal.ONE)
                                                          .isNull().onError(IS_NOT_NULL)
                                                          .or()
                                                          .isEqualTo(BigDecimal.TEN).onError(IS_NOT_EQUAL)
                                                          .examine())
                      .hasMessage(IS_NOT_NULL + "\n" + IS_NOT_EQUAL);
        }

        @Test
        public void should_pass_when_inspectingDeeplyPasses() {
            Validation.succeedIf(BigDecimal.TEN)
                      .inspectingDeeply(Function.identity(), it -> Validation.succeedIf(it).isEqualTo(BigDecimal.TEN))
                      .examine();
        }

        @Test
        public void should_fail_when_inspectingDeeplyNotPasses() {
            Assertions.assertThatThrownBy(() -> Validation.succeedIf(BigDecimal.TEN)
                                                          .inspectingDeeply(
                                                                  it -> BigDecimal.ONE,
                                                                  it -> Validation.succeedIf(it).isEqualTo(BigDecimal.TEN).onError(IS_NOT_EQUAL))
                                                          .examine())
                      .hasMessage(IS_NOT_EQUAL);
        }
    }

    public static class FailedIfTest {

        @Test
        public void should_fail_when_allConditionsMatch() {
            Assertions.assertThat(Validation.failIf(BigDecimal.TEN)
                                            .isNotNull()
                                            .isEqualTo(BigDecimal.TEN)
                                            .groupError("is invalid number")
                                            .examine(ErrorMode.RETURN))
                      .extracting(ValidationError::getReasonCode)
                      .containsExactly("is invalid number");
        }

        @Test
        public void should_fail_when_isNull() {
            BigDecimal number = null;

            Assertions.assertThatThrownBy(() -> Validation.failIf(number)
                                                          .isNull()
                                                          .examine())
                      .hasMessage(DEFAULT_EXCEPTION);
        }

        @Test
        public void should_pass_when_isNotNull() {
            Validation.failIf(BigDecimal.TEN)
                      .isNull().onError(IS_NOT_NULL)
                      .examine();
        }

        @Test
        public void should_fail_when_isNotNull() {
            Assertions.assertThatThrownBy(() -> Validation.failIf(BigDecimal.ONE)
                                                          .isNotNull()
                                                          .examine())
                      .hasMessage(DEFAULT_EXCEPTION);
        }

        @Test
        public void should_pass_when_isNull() {
            BigDecimal number = null;

            Validation.failIf(number)
                      .isNotNull().onError(IS_NULL)
                      .examine();
        }

        @Test
        public void should_fail_when_isEqualTo() {
            BigDecimal thisNumber = BigDecimal.ONE;
            BigDecimal otherNumber = BigDecimal.ONE;

            Assertions.assertThatThrownBy(() -> Validation.failIf(thisNumber)
                                                          .isEqualTo(otherNumber)
                                                          .examine())
                      .hasMessage(DEFAULT_EXCEPTION);
        }

        @Test
        public void should_pass_when_isNotEqualTo() {
            BigDecimal thisNumber = BigDecimal.ONE;
            BigDecimal otherNumber = BigDecimal.TEN;

            Validation.failIf(thisNumber)
                      .isEqualTo(otherNumber).onError(IS_NOT_EQUAL)
                      .examine();
        }

        @Test
        public void should_fail_when_isNotEqualTo() {
            BigDecimal thisNumber = BigDecimal.ONE;
            BigDecimal otherNumber = BigDecimal.TEN;

            Assertions.assertThatThrownBy(() -> Validation.failIf(thisNumber)
                                                          .isNotEqualTo(otherNumber)
                                                          .examine())
                      .hasMessage(DEFAULT_EXCEPTION);
        }

        @Test
        public void should_pass_when_isEqualTo() {
            BigDecimal thisNumber = BigDecimal.ONE;
            BigDecimal otherNumber = BigDecimal.ONE;

            Validation.failIf(thisNumber)
                      .isNotEqualTo(otherNumber).onError(IS_EQUAL)
                      .examine();
        }

        @Test
        public void should_fail_when_termPredicatePasses() {
            Assertions.assertThatThrownBy(() -> Validation.failIf(BigDecimal.TEN)
                                                          .withTerm(it -> it.equals(BigDecimal.TEN))
                                                          .examine())
                      .hasMessage(DEFAULT_EXCEPTION);
        }

        @Test
        public void should_pass_when_termPredicateNotPasses() {
            Validation.failIf(BigDecimal.TEN)
                      .withTerm(it -> it.equals(BigDecimal.ONE)).onError(IS_EQUAL)
                      .examine();
        }

        @Test
        public void should_fail_when_termSupplierPasses() {
            Assertions.assertThatThrownBy(() -> Validation.failIf(BigDecimal.TEN)
                                                          .withTerm(() -> 1 == 1)
                                                          .examine())
                      .hasMessage(DEFAULT_EXCEPTION);
        }

        @Test
        public void should_pass_when_termSupplierNotPasses() {
            Validation.failIf(BigDecimal.TEN)
                      .withTerm(() -> 1 == 2).onError(IS_NOT_EQUAL)
                      .examine();
        }

        @Test
        public void should_fail_when_satisfiesAny() {
            Assertions.assertThatThrownBy(() -> Validation.failIf(BigDecimal.TEN)
                                                          .satisfiesAny(ObjectConditions.isNull(), ObjectConditions.isNotNull())
                                                          .examine())
                      .hasMessage(DEFAULT_EXCEPTION);
        }

        @Test
        public void should_pass_when_notSatisfiesAny() {
            Validation.failIf(BigDecimal.TEN)
                      .satisfiesAny(ObjectConditions.isNull(), BigDecimalConditions.isEqualTo(BigDecimal.ONE)).onError(NOT_SATISFIES)
                      .examine();
        }

        @Test
        public void should_fail_when_satisfiesEvery() {
            Assertions.assertThatThrownBy(() -> Validation.failIf(BigDecimal.TEN)
                                                          .satisfiesEvery(ObjectConditions.isNotNull(), BigDecimalConditions.isEqualTo(BigDecimal.TEN))
                                                          .examine())
                      .hasMessage(DEFAULT_EXCEPTION);
        }

        @Test
        public void should_pass_when_notSatisfiesEvery() {
            Validation.failIf(BigDecimal.TEN)
                      .satisfiesEvery(
                              ObjectConditions.isNull(),
                              BigDecimalConditions.isLte(BigDecimal.ONE)
                      ).onError(NOT_SATISFIES)
                      .examine();
        }

        @Test
        public void should_fail_when_inspectingPredicatePasses() {
            Assertions.assertThatThrownBy(() -> Validation.failIf(BigDecimal.TEN)
                                                          .inspecting(Function.identity(), it -> it.equals(BigDecimal.TEN))
                                                          .examine())
                      .hasMessage(DEFAULT_EXCEPTION);
        }

        @Test
        public void should_pass_when_inspectingPredicateNotPasses() {
            Validation.failIf(BigDecimal.TEN)
                      .inspecting(it -> BigDecimal.ONE, Objects::isNull)
                      .examine();
        }

        @Test
        public void should_fail_when_inspectingSupplierPasses() {
            Assertions.assertThatThrownBy(() -> Validation.failIf(BigDecimal.TEN)
                                                          .inspecting(Function.identity(), ObjectConditions::isNotNull)
                                                          .examine())
                      .hasMessage(DEFAULT_EXCEPTION);
        }

        @Test
        public void should_pass_when_inspectingSupplierNotPasses() {
            Validation.failIf(BigDecimal.TEN)
                      .inspecting(Function.identity(), ObjectConditions::isNull)
                      .examine();
        }

        @Test
        public void should_fail_when_inspectingWithPredicatePasses() {
            Assertions.assertThatThrownBy(() -> Validation.failIf(BigDecimal.TEN)
                                                          .inspecting(Function.identity(), it -> it.equals(BigDecimal.TEN))
                                                          .examine())
                      .hasMessage(DEFAULT_EXCEPTION);
        }

        @Test
        public void should_pass_when_inspectingWithPredicateNotPasses() {
            Validation.failIf(BigDecimal.TEN)
                      .inspecting(
                              Function.identity(),
                              it -> it.equals(BigDecimal.ONE)
                      ).onError(IS_EQUAL)
                      .examine();
        }

        @Test
        public void should_fail_when_inspectingWithSupplierPasses() {
            Assertions.assertThatThrownBy(() -> Validation.failIf(BigDecimal.TEN)
                                                          .inspecting(Function.identity(), ObjectConditions::isNotNull)
                                                          .examine())
                      .hasMessage(DEFAULT_EXCEPTION);
        }

        @Test
        public void should_pass_when_inspectingWithSupplierNotPasses() {
            Validation.failIf(BigDecimal.TEN)
                      .inspecting(
                              Function.identity(),
                              ObjectConditions::isNull
                      ).onError(IS_NOT_AFTER)
                      .examine();
        }

        @Test
        public void should_fail_when_firstGroupMatches() {
            BigDecimal number = null;

            Assertions.assertThatThrownBy(() -> Validation.failIf(number)
                                                          .isNull().onError(IS_NULL)
                                                          .or()
                                                          .isEqualTo(BigDecimal.TEN).onError(IS_EQUAL)
                                                          .examine())
                      .hasMessage(IS_NULL);
        }

        @Test
        public void should_fail_when_secondGroupMatches() {
            Assertions.assertThatThrownBy(() -> Validation.failIf(BigDecimal.TEN)
                                                          .isNull().onError(IS_NULL)
                                                          .or()
                                                          .isNotNull().onError(IS_NOT_NULL)
                                                          .examine())
                      .hasMessage(IS_NOT_NULL);
        }

        @Test
        public void should_pass_when_groupsNotMatch() {
            Validation.failIf(BigDecimal.TEN)
                      .isNull().onError(IS_NULL)
                      .or()
                      .isEqualTo(BigDecimal.ONE).onError(IS_EQUAL)
                      .examine();
        }

        @Test
        public void should_fail_when_inspectingDeeplyPasses() {
            Assertions.assertThatThrownBy(() -> Validation.failIf(BigDecimal.TEN)
                                                          .inspectingDeeply(Function.identity(), it -> Validation.succeedIf(it).isEqualTo(BigDecimal.TEN))
                                                          .examine())
                      .hasMessage(DEFAULT_EXCEPTION);
        }

        @Test
        public void should_pass_when_inspectingDeeplyNotPasses() {
            Validation.failIf(BigDecimal.TEN)
                      .inspectingDeeply(
                              it -> BigDecimal.ONE,
                              it -> Validation.succeedIf(it).isEqualTo(BigDecimal.TEN).onError(IS_NOT_EQUAL))
                      .examine();
        }
    }
}
