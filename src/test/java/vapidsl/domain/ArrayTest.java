package vapidsl.domain;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import vapidsl.Validation;
import vapidsl.ValidatorTest;
import vapidsl.domain.array.ArrayConditions;
import vapidsl.domain.list.ListConditions;
import vapidsl.domain.object.ObjectConditions;
import vapidsl.domain.string.StringConditions;
import vapidsl.exception.ValidationException;

import java.util.Arrays;

@RunWith(Suite.class)
@Suite.SuiteClasses({ArrayTest.SucceedIfTest.class, ArrayTest.FailedIfTest.class})
public class ArrayTest extends ValidatorTest {

    private static final String[] STRING_ARRAY = new String[]{"test", "test1", "test2"};

    public static class SucceedIfTest {

        @Test
        public void should_pass_when_everyElementSupplierMatch() {
            Validation.succeedIf(STRING_ARRAY)
                      .isNotEmpty()
                      .every(() -> StringConditions.matches("test.*"))
                      .examine();
        }

        @Test
        public void should_pass_when_everyElementPredicateMatch() {
            Validation.succeedIf(STRING_ARRAY)
                      .isNotEmpty()
                      .every(it -> it.matches("test.*"))
                      .examine();
        }

        @Test
        public void should_fail_when_notEveryElementMatch() {
            Assertions.assertThatThrownBy(() -> Validation.succeedIf(new Object[]{new Obj(), new Obj("test2")})
                                                          .every(() -> ObjectConditions.isEqualTo(new Obj())).onError(IS_NOT_EQUAL)
                                                          .examine())
                      .hasMessage(IS_NOT_EQUAL);
        }

        @Test
        public void should_pass_when_everyDeeplyElementMatch() {
            Validation.succeedIf(new Obj[]{new Obj("test"), new Obj("test2")})
                      .everyDeeply(obj -> Validation.succeedIf(obj)
                                                    .inspecting(Obj::getProp, StringConditions::isNotEmpty))
                      .examine();
        }

        @Test
        public void should_fail_when_notEveryDeeplyElementMatch() {
            Assertions.assertThatThrownBy(() -> Validation.succeedIf(new Obj[]{new Obj(""), new Obj("test2")})
                                                          .everyDeeply(it -> Validation.succeedIf(it)
                                                                                       .inspecting(Obj::getProp, StringConditions::isEmpty)).onError(IS_NOT_EMPTY)
                                                          .examine())
                      .isInstanceOf(ValidationException.class)
                      .hasMessage(IS_NOT_EMPTY);
        }

        @Test
        public void should_pass_when_contains() {
            Validation.succeedIf(STRING_ARRAY)
                      .contains("test1")
                      .examine();
        }

        @Test
        public void should_fail_when_doesNotContain() {
            Assertions.assertThatThrownBy(() -> Validation.succeedIf(STRING_ARRAY)
                                                          .contains("test3").onError(NOT_CONTAINS)
                                                          .examine())
                      .isInstanceOf(ValidationException.class)
                      .hasMessage(NOT_CONTAINS);
        }

        @Test
        public void should_pass_when_hasCorrectSize() {
            Validation.succeedIf(STRING_ARRAY)
                      .hasSize(3)
                      .examine();
        }

        @Test
        public void should_fail_when_hasIncorrectCorrectSize() {
            Assertions.assertThatThrownBy(() -> Validation.succeedIf(STRING_ARRAY)
                                                          .hasSize(2).onError(INVALID_SIZE)
                                                          .examine())
                      .hasMessage(INVALID_SIZE);
        }

        @Test
        public void should_pass_when_isEmpty() {
            Validation.succeedIf(new String[]{})
                      .isEmpty()
                      .examine();
        }

        @Test
        public void should_fail_when_isNotEmpty() {
            Assertions.assertThatThrownBy(() -> Validation.succeedIf(STRING_ARRAY)
                                                          .isEmpty().onError(IS_NOT_EMPTY)
                                                          .examine())
                      .hasMessage(IS_NOT_EMPTY);
        }

        @Test
        public void should_pass_when_isNotEmpty() {
            Validation.succeedIf(STRING_ARRAY)
                      .isNotEmpty()
                      .examine();
        }

        @Test
        public void should_fail_when_isEmpty() {
            Assertions.assertThatThrownBy(() -> Validation.succeedIf(new String[]{})
                                                          .isNotEmpty().onError(IS_EMPTY)
                                                          .examine())
                      .hasMessage(IS_EMPTY);
        }

        @Test
        public void should_pass_when_isNull() {
            String[] array = null;

            Validation.succeedIf(array)
                      .isNull()
                      .examine();
        }

        @Test
        public void should_fail_when_isNotNull() {
            Assertions.assertThatThrownBy(() -> Validation.succeedIf(STRING_ARRAY)
                                                          .isNull().onError(IS_NOT_NULL)
                                                          .examine())
                      .hasMessage(IS_NOT_NULL);
        }

        @Test
        public void should_pass_when_isNotNull() {
            Validation.succeedIf(STRING_ARRAY)
                      .isNotNull()
                      .examine();
        }

        @Test
        public void should_fail_when_isNull() {
            String[] array = null;

            Assertions.assertThatThrownBy(() -> Validation.succeedIf(array)
                                                          .isNotNull().onError(IS_NULL)
                                                          .examine())
                      .hasMessage(IS_NULL);
        }

        @Test
        public void should_pass_when_isEqualTo() {
            String[] array1 = new String[]{"test"};
            String[] array2 = new String[]{"test"};

            Validation.succeedIf(array1)
                      .isEqualTo(array2)
                      .examine();
        }

        @Test
        public void should_fail_when_isNotEqualTo() {
            String[] array1 = new String[]{"test"};
            String[] array2 = new String[]{"test", "test2"};

            Assertions.assertThatThrownBy(() -> Validation.succeedIf(array1)
                                                          .isEqualTo(array2).onError(IS_NOT_EQUAL)
                                                          .examine())
                      .hasMessage(IS_NOT_EQUAL);
        }

        @Test
        public void should_pass_when_isNotEqualTo() {
            String[] array1 = new String[]{"test"};
            String[] array2 = new String[]{"test", "test2"};

            Validation.succeedIf(array1)
                      .isNotEqualTo(array2)
                      .examine();
        }

        @Test
        public void should_fail_when_isEqualTo() {
            String[] array1 = new String[]{"test"};
            String[] array2 = new String[]{"test"};

            Assertions.assertThatThrownBy(() -> Validation.succeedIf(array1)
                                                          .isNotEqualTo(array2).onError(IS_EQUAL)
                                                          .examine())
                      .hasMessage(IS_EQUAL);
        }

        @Test
        public void should_pass_when_termPredicatePasses() {
            Validation.succeedIf(STRING_ARRAY)
                      .withTerm(it -> it.length == 3)
                      .examine();
        }

        @Test
        public void should_fail_when_termPredicateNotPasses() {
            Assertions.assertThatThrownBy(() -> Validation.succeedIf(STRING_ARRAY)
                                                          .withTerm(it -> it.length == 4).onError(INVALID_SIZE)
                                                          .examine())
                      .hasMessage(INVALID_SIZE);
        }

        @Test
        public void should_pass_when_termSupplierPasses() {
            Validation.succeedIf(STRING_ARRAY)
                      .withTerm(() -> 1 == 1)
                      .examine();
        }

        @Test
        public void should_fail_when_termSupplierNotPasses() {
            Assertions.assertThatThrownBy(() -> Validation.succeedIf(STRING_ARRAY)
                                                          .withTerm(() -> 1 == 2).onError(IS_NOT_EQUAL)
                                                          .examine())
                      .hasMessage(IS_NOT_EQUAL);
        }

        @Test
        public void should_pass_when_satisfiesAny() {
            Validation.succeedIf(STRING_ARRAY)
                      .satisfiesAny(ArrayConditions.isEmpty(), ArrayConditions.hasSize(3))
                      .examine();
        }

        @Test
        public void should_fail_when_notSatisfiesAny() {
            Assertions.assertThatThrownBy(() -> Validation.succeedIf(STRING_ARRAY)
                                                          .satisfiesAny(ArrayConditions.isEmpty(), ArrayConditions.hasSize(1)).onError(NOT_SATISFIES)
                                                          .examine())
                      .hasMessage(NOT_SATISFIES);
        }

        @Test
        public void should_pass_when_satisfiesEvery() {
            Validation.succeedIf(STRING_ARRAY)
                      .satisfiesEvery(ArrayConditions.hasSize(3), ArrayConditions.isNotEmpty())
                      .examine();
        }

        @Test
        public void should_fail_when_notSatisfiesEvery() {
            Assertions.assertThatThrownBy(() -> Validation.succeedIf(STRING_ARRAY)
                                                          .satisfiesEvery(ArrayConditions.hasSize(2), ArrayConditions.isNotEmpty()).onError(NOT_SATISFIES)
                                                          .examine())
                      .hasMessage(NOT_SATISFIES);
        }

        @Test
        public void should_log_msg() {
            Validation.succeedIf(STRING_ARRAY)
                      .log("checking if empty")
                      .isNotEmpty()
                      .examine();
        }

        @Test
        public void should_pass_when_inspectingPredicatePasses() {
            Validation.succeedIf(STRING_ARRAY)
                      .inspecting(Arrays::asList, it -> it.size() == 3)
                      .examine();
        }

        @Test
        public void should_fail_when_inspectingPredicateNotPasses() {
            Assertions.assertThatThrownBy(() -> Validation.succeedIf(STRING_ARRAY)
                                                          .inspecting(Arrays::asList, it -> it.size() == 2).onError(INVALID_SIZE)
                                                          .examine())
                      .hasMessage(INVALID_SIZE);
        }

        @Test
        public void should_pass_when_inspectingSupplierPasses() {
            Validation.succeedIf(STRING_ARRAY)
                      .inspecting(Arrays::asList, ListConditions::isNotEmpty)
                      .examine();
        }

        @Test
        public void should_fail_when_inspectingSupplierNotPasses() {
            Assertions.assertThatThrownBy(() -> Validation.succeedIf(STRING_ARRAY)
                                                          .inspecting(Arrays::asList, ListConditions::isEmpty).onError(IS_NOT_EMPTY)
                                                          .examine())
                      .hasMessage(IS_NOT_EMPTY);
        }

        @Test
        public void should_pass_when_withTermDeeplyPasses() {
            Validation.succeedIf(STRING_ARRAY)
                      .withTermDeeply(it -> Validation.succeedIf(it).isNotEmpty())
                      .examine();
        }

        @Test
        public void should_fail_when_withTermDeeplyNotPasses() {
            Assertions.assertThatThrownBy(() -> Validation.succeedIf(STRING_ARRAY)
                                                          .withTermDeeply(it -> Validation.succeedIf(it).isEmpty().onError(IS_NOT_EMPTY))
                                                          .examine())
                      .hasMessage(IS_NOT_EMPTY);
        }

        @Test
        public void should_pass_when_inspectingDeeplyPasses() {
            Validation.succeedIf(STRING_ARRAY)
                      .inspectingDeeply(Arrays::asList, it -> Validation.succeedIf(it).isNotEmpty())
                      .examine();
        }

        @Test
        public void should_fail_when_inspectingDeeplyNotPasses() {
            Assertions.assertThatThrownBy(() -> Validation.succeedIf(STRING_ARRAY)
                                                          .inspectingDeeply(Arrays::asList, it -> Validation.succeedIf(it).isEmpty().onError(IS_NOT_EMPTY))
                                                          .examine())
                      .hasMessage(IS_NOT_EMPTY);
        }

        @Test
        public void should_pass_when_firstGroupMatches() {
            String[] array = null;

            Validation.succeedIf(array)
                      .isNull().onError(IS_NOT_NULL)
                      .or()
                      .contains("test").onError(NOT_CONTAINS)
                      .examine();
        }

        @Test
        public void should_pass_when_secondGroupMatches() {
            Validation.succeedIf(STRING_ARRAY)
                      .isEmpty().onError(IS_NOT_EMPTY)
                      .or()
                      .isNotEmpty().onError(IS_EMPTY)
                      .examine();
        }

        @Test
        public void should_fail_when_groupsNotMatch() {
            Assertions.assertThatThrownBy(() -> Validation.succeedIf(STRING_ARRAY)
                                                          .isEmpty().onError(IS_NOT_EMPTY)
                                                          .or()
                                                          .isNull().onError(IS_NOT_NULL)
                                                          .examine())
                      .hasMessage(IS_NOT_EMPTY + "\n" + IS_NOT_NULL);
        }
    }

    public static class FailedIfTest {

        @Test
        public void should_fail_when_everyElementSupplierMatch() {
            Assertions.assertThatThrownBy(() -> Validation.failIf(STRING_ARRAY)
                                                          .isNotEmpty()
                                                          .every(() -> StringConditions.matches("test.*"))
                                                          .examine())
                      .hasMessage(DEFAULT_EXCEPTION);
        }

        @Test
        public void should_fail_when_everyElementPredicateMatch() {
            Assertions.assertThatThrownBy(() -> Validation.failIf(STRING_ARRAY)
                                                          .isNotEmpty()
                                                          .every(it -> it.matches("test.*"))
                                                          .groupError(INVALID_GROUP)
                                                          .examine())
                      .hasMessage(PREFIX_INVALID_GROUP);
        }

        @Test
        public void should_pass_when_notEveryElementMatch() {
            Validation.failIf(new Object[]{new Obj(), new Obj("test2")})
                      .every(() -> ObjectConditions.isEqualTo(new Obj())).onError(IS_NOT_EQUAL)
                      .examine();
        }

        @Test
        public void should_fail_when_everyDeeplyElementMatch() {
            Assertions.assertThatThrownBy(() -> Validation.failIf(new Obj[]{new Obj("test"), new Obj("test2")})
                                                          .everyDeeply(obj -> Validation.succeedIf(obj)
                                                                                        .inspecting(Obj::getProp, StringConditions::isNotEmpty))
                                                          .examine())
                      .hasMessage(DEFAULT_EXCEPTION);
        }

        @Test
        public void should_pass_when_notEveryDeeplyElementMatch() {
            Validation.failIf(new Obj[]{new Obj(""), new Obj("test2")})
                      .everyDeeply(it -> Validation.failIf(it)
                                                   .inspecting(Obj::getProp, StringConditions::isEmpty)).onError(IS_NOT_EMPTY)
                      .examine();
        }

        @Test
        public void should_pass_when_contains() {
            Assertions.assertThatThrownBy(() -> Validation.failIf(STRING_ARRAY)
                                                          .contains("test1")
                                                          .examine())
                      .hasMessage(DEFAULT_EXCEPTION);
        }

        @Test
        public void should_pass_when_doesNotContain() {
            Validation.failIf(STRING_ARRAY)
                      .contains("test3").onError(NOT_CONTAINS)
                      .examine();
        }

        @Test
        public void should_fail_when_hasCorrectSize() {
            Assertions.assertThatThrownBy(() -> Validation.failIf(STRING_ARRAY)
                                                          .hasSize(3)
                                                          .examine())
                      .hasMessage(DEFAULT_EXCEPTION);
        }

        @Test
        public void should_pass_when_hasIncorrectCorrectSize() {
            Validation.failIf(STRING_ARRAY)
                      .hasSize(2).onError(INVALID_SIZE)
                      .examine();
        }

        @Test
        public void should_fail_when_isEmpty() {
            Assertions.assertThatThrownBy(() -> Validation.failIf(new String[]{})
                                                          .isEmpty()
                                                          .examine())
                      .hasMessage(DEFAULT_EXCEPTION);
        }

        @Test
        public void should_pass_when_isNotEmpty() {
            Validation.failIf(STRING_ARRAY)
                      .isEmpty().onError(IS_NOT_EMPTY)
                      .examine();
        }

        @Test
        public void should_fail_when_isNotEmpty() {
            Assertions.assertThatThrownBy(() -> Validation.failIf(STRING_ARRAY)
                                                          .isNotEmpty()
                                                          .examine())
                      .hasMessage(DEFAULT_EXCEPTION);
        }

        @Test
        public void should_pass_when_isEmpty() {
            Validation.failIf(new String[]{})
                      .isNotEmpty().onError(IS_EMPTY)
                      .examine();
        }

        @Test
        public void should_fail_when_isNull() {
            String[] array = null;

            Assertions.assertThatThrownBy(() -> Validation.failIf(array)
                                                          .isNull()
                                                          .examine())
                      .hasMessage(DEFAULT_EXCEPTION);
        }

        @Test
        public void should_pass_when_isNotNull() {
            Validation.failIf(STRING_ARRAY)
                      .isNull().onError(IS_NOT_NULL)
                      .examine();
        }

        @Test
        public void should_fail_when_isNotNull() {
            Assertions.assertThatThrownBy(() -> Validation.failIf(STRING_ARRAY)
                                                          .isNotNull()
                                                          .examine())
                      .hasMessage(DEFAULT_EXCEPTION);
        }

        @Test
        public void should_pass_when_isNull() {
            String[] array = null;

            Validation.failIf(array)
                      .isNotNull().onError(IS_NULL)
                      .examine();
        }

        @Test
        public void should_fail_when_isEqualTo() {
            String[] array1 = new String[]{"test"};
            String[] array2 = new String[]{"test"};

            Assertions.assertThatThrownBy(() -> Validation.failIf(array1)
                                                          .isEqualTo(array2)
                                                          .examine())
                      .hasMessage(DEFAULT_EXCEPTION);
        }

        @Test
        public void should_pass_when_isNotEqualTo() {
            String[] array1 = new String[]{"test"};
            String[] array2 = new String[]{"test", "test2"};

            Validation.failIf(array1)
                      .isEqualTo(array2).onError(IS_NOT_EQUAL)
                      .examine();
        }

        @Test
        public void should_fail_when_isNotEqualTo() {
            String[] array1 = new String[]{"test"};
            String[] array2 = new String[]{"test", "test2"};

            Assertions.assertThatThrownBy(() -> Validation.failIf(array1)
                                                          .isNotEqualTo(array2)
                                                          .examine())
                      .hasMessage(DEFAULT_EXCEPTION);
        }

        @Test
        public void should_pass_when_isEqualTo() {
            String[] array1 = new String[]{"test"};
            String[] array2 = new String[]{"test"};

            Validation.failIf(array1)
                      .isNotEqualTo(array2).onError(IS_EQUAL)
                      .examine();
        }

        @Test
        public void should_fail_when_termPredicatePasses() {
            Assertions.assertThatThrownBy(() -> Validation.failIf(STRING_ARRAY)
                                                          .withTerm(it -> it.length == 3)
                                                          .examine())
                      .hasMessage(DEFAULT_EXCEPTION);
        }

        @Test
        public void should_pass_when_termPredicateNotPasses() {
            Validation.failIf(STRING_ARRAY)
                      .withTerm(it -> it.length == 4).onError(INVALID_SIZE)
                      .examine();
        }

        @Test
        public void should_fail_when_termSupplierPasses() {
            Assertions.assertThatThrownBy(() -> Validation.failIf(STRING_ARRAY)
                                                          .withTerm(() -> 1 == 1)
                                                          .examine())
                      .hasMessage(DEFAULT_EXCEPTION);
        }

        @Test
        public void should_pass_when_termSupplierNotPasses() {
            Validation.failIf(STRING_ARRAY)
                      .withTerm(() -> 1 == 2).onError(IS_NOT_EQUAL)
                      .examine();
        }

        @Test
        public void should_fail_when_satisfiesAny() {
            Assertions.assertThatThrownBy(() -> Validation.failIf(STRING_ARRAY)
                                                          .satisfiesAny(ArrayConditions.isEmpty(), ArrayConditions.hasSize(3))
                                                          .examine())
                      .hasMessage(DEFAULT_EXCEPTION);
        }

        @Test
        public void should_pass_when_notSatisfiesAny() {
            Validation.failIf(STRING_ARRAY)
                      .satisfiesAny(ArrayConditions.isEmpty(), ArrayConditions.hasSize(1)).onError(NOT_SATISFIES)
                      .examine();
        }

        @Test
        public void should_fail_when_satisfiesEvery() {
            Assertions.assertThatThrownBy(() -> Validation.failIf(STRING_ARRAY)
                                                          .satisfiesEvery(ArrayConditions.hasSize(3), ArrayConditions.isNotEmpty())
                                                          .examine())
                      .hasMessage(DEFAULT_EXCEPTION);
        }

        @Test
        public void should_pass_when_notSatisfiesEvery() {
            Validation.failIf(STRING_ARRAY)
                      .satisfiesEvery(ArrayConditions.hasSize(2), ArrayConditions.isNotEmpty()).onError(NOT_SATISFIES)
                      .examine();
        }

        @Test
        public void should_fail_when_inspectingPredicatePasses() {
            Assertions.assertThatThrownBy(() -> Validation.failIf(STRING_ARRAY)
                                                          .inspecting(Arrays::asList, it -> it.size() == 3)
                                                          .examine())
                      .hasMessage(DEFAULT_EXCEPTION);
        }

        @Test
        public void should_pass_when_inspectingPredicateNotPasses() {
            Validation.failIf(STRING_ARRAY)
                      .inspecting(Arrays::asList, it -> it.size() == 2).onError(INVALID_SIZE)
                      .examine();
        }

        @Test
        public void should_fail_when_inspectingSupplierPasses() {
            Assertions.assertThatThrownBy(() -> Validation.failIf(STRING_ARRAY)
                                                          .inspecting(Arrays::asList, ListConditions::isNotEmpty)
                                                          .examine())
                      .hasMessage(DEFAULT_EXCEPTION);
        }

        @Test
        public void should_pass_when_inspectingSupplierNotPasses() {
            Validation.failIf(STRING_ARRAY)
                      .inspecting(Arrays::asList, ListConditions::isEmpty).onError(IS_NOT_EMPTY)
                      .examine();
        }

        @Test
        public void should_fail_when_withTermDeeplyPasses() {
            Assertions.assertThatThrownBy(() -> Validation.failIf(STRING_ARRAY)
                                                          .withTermDeeply(it -> Validation.succeedIf(it).isNotEmpty())
                                                          .examine())
                      .hasMessage(DEFAULT_EXCEPTION);
        }

        @Test
        public void should_pass_when_withTermDeeplyNotPasses() {
            Validation.failIf(STRING_ARRAY)
                      .withTermDeeply(it -> Validation.succeedIf(it).isEmpty().onError(IS_NOT_EMPTY))
                      .examine();
        }

        @Test
        public void should_fail_when_inspectingDeeplyPasses() {
            Assertions.assertThatThrownBy(() -> Validation.failIf(STRING_ARRAY)
                                                          .inspectingDeeply(Arrays::asList, it -> Validation.succeedIf(it).isNotEmpty())
                                                          .examine())
                      .hasMessage(DEFAULT_EXCEPTION);
        }

        @Test
        public void should_pass_when_inspectingDeeplyNotPasses() {
            Validation.failIf(STRING_ARRAY)
                      .inspectingDeeply(Arrays::asList, it -> Validation.succeedIf(it).isEmpty().onError(IS_NOT_EMPTY))
                      .examine();
        }

        @Test
        public void should_fail_when_firstGroupMatches() {
            String[] array = null;

            Assertions.assertThatThrownBy(() -> Validation.failIf(array)
                                                          .isNull().onError(IS_NULL)
                                                          .or()
                                                          .contains("test").onError(CONTAINS)
                                                          .examine())
                      .hasMessage(IS_NULL);
        }

        @Test
        public void should_fail_when_secondGroupMatches() {
            Assertions.assertThatThrownBy(() -> Validation.failIf(STRING_ARRAY)
                                                          .isEmpty().onError(IS_EMPTY)
                                                          .or()
                                                          .isNotEmpty().onError(IS_NOT_EMPTY)
                                                          .examine())
                      .hasMessage(IS_NOT_EMPTY);
        }

        @Test
        public void should_pass_when_groupsNotMatch() {
            Validation.failIf(STRING_ARRAY)
                      .isEmpty().onError(IS_NOT_EMPTY)
                      .or()
                      .isNull().onError(IS_NOT_NULL)
                      .examine();
        }
    }
}
