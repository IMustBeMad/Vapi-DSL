package vapidsl.domain;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import vapidsl.Validation;
import vapidsl.ValidatorTest;
import vapidsl.domain.list.ListConditions;
import vapidsl.domain.object.ObjectConditions;
import vapidsl.domain.string.StringConditions;
import vapidsl.exception.ValidationException;

import java.util.Collections;
import java.util.List;
import java.util.function.Function;

@RunWith(Suite.class)
@Suite.SuiteClasses({ListTest.SucceedIfTest.class, ListTest.FailedIfTest.class})
public class ListTest extends ValidatorTest {

    public static class SucceedIfTest {

        @Test
        public void should_pass_when_everyElementSupplierMatch() {
            Validation.succeedIf(List.of("test", "test1"))
                      .isNotEmpty()
                      .hasNoDuplicates()
                      .every(() -> StringConditions.matches("test.*"))
                      .examine();
        }

        @Test
        public void should_pass_when_everyElementPredicateMatch() {
            Validation.succeedIf(List.of("test", "test1"))
                      .isNotEmpty()
                      .hasNoDuplicates()
                      .every(it -> it.matches("test.*"))
                      .examine();
        }

        @Test
        public void should_fail_when_notEveryElementMatch() {
            Assertions.assertThatThrownBy(() -> Validation.succeedIf(List.of(new Obj(), new Obj("test2")))
                                                          .every(() -> ObjectConditions.isEqualTo(new Obj())).onError(IS_NOT_EQUAL)
                                                          .examine())
                      .hasMessage(IS_NOT_EQUAL);
        }

        @Test
        public void should_pass_when_everyDeeplyElementMatch() {
            Validation.succeedIf(List.of(new Obj("test"), new Obj("test2")))
                      .everyDeeply(obj -> Validation.succeedIf(obj)
                                                    .inspecting(Obj::getProp, StringConditions::isNotEmpty))
                      .examine();
        }

        @Test
        public void should_fail_when_notEveryDeeplyElementMatch() {
            Assertions.assertThatThrownBy(() -> Validation.succeedIf(List.of(new Obj(""), new Obj("test2")))
                                                          .everyDeeply(it -> Validation.succeedIf(it)
                                                                                       .inspecting(Obj::getProp, StringConditions::isEmpty)).onError(IS_NOT_EMPTY)
                                                          .examine())
                      .isInstanceOf(ValidationException.class)
                      .hasMessage(IS_NOT_EMPTY);
        }

        @Test
        public void should_pass_when_containsElement() {
            Validation.succeedIf(List.of("test", "test1"))
                      .contains("test")
                      .examine();
        }

        @Test
        public void should_pass_when_doesNotContainElement() {
            Assertions.assertThatThrownBy(() -> Validation.succeedIf(List.of("test", "test1"))
                                                          .contains("test3")
                                                          .examine())
                      .hasMessage(DEFAULT_EXCEPTION);
        }

        @Test
        public void should_pass_when_isNull() {
            List<Object> lists = null;

            Validation.succeedIf(lists)
                      .isNull()
                      .examine();
        }

        @Test
        public void should_fail_when_isNotNull() {
            Assertions.assertThatThrownBy(() -> Validation.succeedIf(List.of("test"))
                                                          .isNull().onError(IS_NOT_NULL)
                                                          .examine())
                      .hasMessage(IS_NOT_NULL);
        }

        @Test
        public void should_pass_when_isNotNull() {
            Validation.succeedIf(List.of("test"))
                      .isNotNull()
                      .examine();
        }

        @Test
        public void should_fail_when_isNull() {
            List<String> list = null;

            Assertions.assertThatThrownBy(() -> Validation.succeedIf(list)
                                                          .isNotNull().onError(IS_NULL)
                                                          .examine())
                      .hasMessage(IS_NULL);
        }

        @Test
        public void should_pass_when_isEqualTo() {
            List<String> list = List.of("test");
            List<String> otherList = List.of("test");

            Validation.succeedIf(list)
                      .isEqualTo(otherList)
                      .examine();
        }

        @Test
        public void should_fail_when_isNotEqualTo() {
            List<String> list = List.of("test");
            List<String> otherList = List.of("test", "test2");

            Assertions.assertThatThrownBy(() -> Validation.succeedIf(list)
                                                          .isEqualTo(otherList).onError(IS_NOT_EQUAL)
                                                          .examine())
                      .hasMessage(IS_NOT_EQUAL);
        }

        @Test
        public void should_pass_when_isNotEqualTo() {
            List<String> list = List.of("test");
            List<String> otherList = List.of("test", "test2");

            Validation.succeedIf(list)
                      .isNotEqualTo(otherList)
                      .examine();
        }

        @Test
        public void should_fail_when_isEqualTo() {
            List<String> list = List.of("test");
            List<String> otherList = List.of("test");

            Assertions.assertThatThrownBy(() -> Validation.succeedIf(list)
                                                          .isNotEqualTo(otherList).onError(IS_EQUAL)
                                                          .examine())
                      .hasMessage(IS_EQUAL);
        }

        @Test
        public void should_pass_when_termPredicatePasses() {
            Validation.succeedIf(List.of("test", "test2", "test3"))
                      .withTerm(it -> it.size() == 3)
                      .examine();
        }

        @Test
        public void should_fail_when_termPredicateNotPasses() {
            Assertions.assertThatThrownBy(() -> Validation.succeedIf(List.of("test", "test2", "test3"))
                                                          .withTerm(it -> it.size() == 4).onError(INVALID_SIZE)
                                                          .examine())
                      .hasMessage(INVALID_SIZE);
        }

        @Test
        public void should_pass_when_termSupplierPasses() {
            Validation.succeedIf(List.of("test"))
                      .withTerm(() -> 1 == 1)
                      .examine();
        }

        @Test
        public void should_fail_when_termSupplierNotPasses() {
            Assertions.assertThatThrownBy(() -> Validation.succeedIf(List.of("test"))
                                                          .withTerm(() -> 1 == 2).onError(IS_NOT_EQUAL)
                                                          .examine())
                      .hasMessage(IS_NOT_EQUAL);
        }

        @Test
        public void should_pass_when_satisfiesAny() {
            Validation.succeedIf(List.of("test", "test2", "test3"))
                      .satisfiesAny(ListConditions.isEmpty(), ListConditions.hasSize(3))
                      .examine();
        }

        @Test
        public void should_fail_when_notSatisfiesAny() {
            Assertions.assertThatThrownBy(() -> Validation.succeedIf(List.of("test", "test2", "test3"))
                                                          .satisfiesAny(ListConditions.isEmpty(), ListConditions.hasSize(1)).onError(NOT_SATISFIES)
                                                          .examine())
                      .hasMessage(NOT_SATISFIES);
        }

        @Test
        public void should_pass_when_satisfiesEvery() {
            Validation.succeedIf(List.of("test", "test2", "test3"))
                      .satisfiesEvery(ListConditions.hasSize(3), ListConditions.isNotEmpty())
                      .examine();
        }

        @Test
        public void should_fail_when_notSatisfiesEvery() {
            Assertions.assertThatThrownBy(() -> Validation.succeedIf(List.of("test", "test2", "test3"))
                                                          .satisfiesEvery(ListConditions.hasSize(2), ListConditions.isNotEmpty()).onError(NOT_SATISFIES)
                                                          .examine())
                      .hasMessage(NOT_SATISFIES);
        }

        @Test
        public void should_log_msg() {
            Validation.succeedIf(List.of("test"))
                      .log("checking if empty")
                      .isNotEmpty()
                      .examine();
        }

        @Test
        public void should_pass_when_inspectingPredicatePasses() {
            Validation.succeedIf(List.of("test"))
                      .inspecting(it -> it.get(0), it -> it.length() == 4)
                      .examine();
        }

        @Test
        public void should_fail_when_inspectingPredicateNotPasses() {
            Assertions.assertThatThrownBy(() -> Validation.succeedIf(List.of("test"))
                                                          .inspecting(it -> it.get(0), it -> it.length() == 2).onError(INVALID_SIZE)
                                                          .examine())
                      .hasMessage(INVALID_SIZE);
        }

        @Test
        public void should_pass_when_inspectingSupplierPasses() {
            Validation.succeedIf(List.of("test"))
                      .inspecting(Function.identity(), ListConditions::isNotEmpty)
                      .examine();
        }

        @Test
        public void should_fail_when_inspectingSupplierNotPasses() {
            Assertions.assertThatThrownBy(() -> Validation.succeedIf(List.of("test"))
                                                          .inspecting(Function.identity(), ListConditions::isEmpty).onError(IS_NOT_EMPTY)
                                                          .examine())
                      .hasMessage(IS_NOT_EMPTY);
        }

        @Test
        public void should_pass_when_withTermDeeplyPasses() {
            Validation.succeedIf(List.of("test"))
                      .withTermDeeply(it -> Validation.succeedIf(it).isNotEmpty())
                      .examine();
        }

        @Test
        public void should_fail_when_withTermDeeplyNotPasses() {
            Assertions.assertThatThrownBy(() -> Validation.succeedIf(List.of("test"))
                                                          .withTermDeeply(it -> Validation.succeedIf(it).isEmpty().onError(IS_NOT_EMPTY))
                                                          .examine())
                      .hasMessage(IS_NOT_EMPTY);
        }

        @Test
        public void should_pass_when_inspectingDeeplyPasses() {
            Validation.succeedIf(List.of("test"))
                      .inspectingDeeply(Function.identity(), it -> Validation.succeedIf(it).isNotEmpty())
                      .examine();
        }

        @Test
        public void should_fail_when_inspectingDeeplyNotPasses() {
            Assertions.assertThatThrownBy(() -> Validation.succeedIf(List.of("test"))
                                                          .inspectingDeeply(Function.identity(), it -> Validation.succeedIf(it).isEmpty().onError(IS_NOT_EMPTY))
                                                          .examine())
                      .hasMessage(IS_NOT_EMPTY);
        }

        @Test
        public void should_pass_when_firstGroupMatches() {
            List<String> list = null;

            Validation.succeedIf(list)
                      .isNull().onError(IS_NOT_NULL)
                      .or()
                      .contains("test").onError(NOT_CONTAINS)
                      .examine();
        }

        @Test
        public void should_pass_when_secondGroupMatches() {
            Validation.succeedIf(List.of("test"))
                      .isEmpty().onError(IS_NOT_EMPTY)
                      .or()
                      .isNotEmpty().onError(IS_EMPTY)
                      .examine();
        }

        @Test
        public void should_fail_when_groupsNotMatch() {
            Assertions.assertThatThrownBy(() -> Validation.succeedIf(List.of("test"))
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
            Assertions.assertThatThrownBy(() -> Validation.failIf(List.of("test"))
                                                          .isNotEmpty()
                                                          .every(() -> StringConditions.matches("test.*"))
                                                          .examine())
                      .hasMessage(DEFAULT_EXCEPTION);
        }

        @Test
        public void should_fail_when_everyElementPredicateMatch() {
            Assertions.assertThatThrownBy(() -> Validation.failIf(List.of("test"))
                                                          .isNotEmpty()
                                                          .every(it -> it.matches("test.*"))
                                                          .groupError(INVALID_GROUP)
                                                          .examine())
                      .hasMessage(PREFIX_INVALID_GROUP);
        }

        @Test
        public void should_pass_when_notEveryElementMatch() {
            Validation.failIf(List.of(new Obj(), new Obj("test2")))
                      .every(() -> ObjectConditions.isEqualTo(new Obj())).onError(IS_NOT_EQUAL)
                      .examine();
        }

        @Test
        public void should_fail_when_everyDeeplyElementMatch() {
            Assertions.assertThatThrownBy(() -> Validation.failIf(List.of(new Obj("test"), new Obj("test2")))
                                                          .everyDeeply(obj -> Validation.succeedIf(obj)
                                                                                        .inspecting(Obj::getProp, StringConditions::isNotEmpty))
                                                          .examine())
                      .hasMessage(DEFAULT_EXCEPTION);
        }

        @Test
        public void should_pass_when_notEveryDeeplyElementMatch() {
            Validation.failIf(List.of(new Obj(""), new Obj("test2")))
                      .everyDeeply(it -> Validation.failIf(it)
                                                   .inspecting(Obj::getProp, StringConditions::isEmpty)).onError(IS_NOT_EMPTY)
                      .examine();
        }

        @Test
        public void should_pass_when_contains() {
            Assertions.assertThatThrownBy(() -> Validation.failIf(List.of("test1", "test2"))
                                                          .contains("test1")
                                                          .examine())
                      .hasMessage(DEFAULT_EXCEPTION);
        }

        @Test
        public void should_pass_when_doesNotContain() {
            Validation.failIf(List.of("test1", "test2"))
                      .contains("test3").onError(NOT_CONTAINS)
                      .examine();
        }

        @Test
        public void should_fail_when_hasCorrectSize() {
            Assertions.assertThatThrownBy(() -> Validation.failIf(List.of("test1", "test2", "test3"))
                                                          .hasSize(3)
                                                          .examine())
                      .hasMessage(DEFAULT_EXCEPTION);
        }

        @Test
        public void should_pass_when_hasIncorrectCorrectSize() {
            Validation.failIf(List.of("test1", "test2", "test3"))
                      .hasSize(2).onError(INVALID_SIZE)
                      .examine();
        }

        @Test
        public void should_fail_when_isEmpty() {
            Assertions.assertThatThrownBy(() -> Validation.failIf(Collections.emptyList())
                                                          .isEmpty()
                                                          .examine())
                      .hasMessage(DEFAULT_EXCEPTION);
        }

        @Test
        public void should_pass_when_isNotEmpty() {
            Validation.failIf(List.of("test"))
                      .isEmpty().onError(IS_NOT_EMPTY)
                      .examine();
        }

        @Test
        public void should_fail_when_isNotEmpty() {
            Assertions.assertThatThrownBy(() -> Validation.failIf(List.of("test"))
                                                          .isNotEmpty()
                                                          .examine())
                      .hasMessage(DEFAULT_EXCEPTION);
        }

        @Test
        public void should_pass_when_isEmpty() {
            Validation.failIf(Collections.emptyList())
                      .isNotEmpty().onError(IS_EMPTY)
                      .examine();
        }

        @Test
        public void should_fail_when_isNull() {
            List<String> list = null;

            Assertions.assertThatThrownBy(() -> Validation.failIf(list)
                                                          .isNull()
                                                          .examine())
                      .hasMessage(DEFAULT_EXCEPTION);
        }

        @Test
        public void should_pass_when_isNotNull() {
            Validation.failIf(List.of("test"))
                      .isNull().onError(IS_NOT_NULL)
                      .examine();
        }

        @Test
        public void should_fail_when_isNotNull() {
            Assertions.assertThatThrownBy(() -> Validation.failIf(List.of("test"))
                                                          .isNotNull()
                                                          .examine())
                      .hasMessage(DEFAULT_EXCEPTION);
        }

        @Test
        public void should_pass_when_isNull() {
            List<String> list = null;

            Validation.failIf(list)
                      .isNotNull().onError(IS_NULL)
                      .examine();
        }

        @Test
        public void should_fail_when_isEqualTo() {
            List<String> list = List.of("test");
            List<String> otherList = List.of("test");

            Assertions.assertThatThrownBy(() -> Validation.failIf(list)
                                                          .isEqualTo(otherList)
                                                          .examine())
                      .hasMessage(DEFAULT_EXCEPTION);
        }

        @Test
        public void should_pass_when_isNotEqualTo() {
            List<String> list = List.of("test");
            List<String> otherList = List.of("test", "test2");

            Validation.failIf(list)
                      .isEqualTo(otherList).onError(IS_NOT_EQUAL)
                      .examine();
        }

        @Test
        public void should_fail_when_isNotEqualTo() {
            List<String> list = List.of("test");
            List<String> otherList = List.of("test", "test2");

            Assertions.assertThatThrownBy(() -> Validation.failIf(list)
                                                          .isNotEqualTo(otherList)
                                                          .examine())
                      .hasMessage(DEFAULT_EXCEPTION);
        }

        @Test
        public void should_pass_when_isEqualTo() {
            List<String> list = List.of("test");
            List<String> otherList = List.of("test");

            Validation.failIf(list)
                      .isNotEqualTo(otherList).onError(IS_EQUAL)
                      .examine();
        }

        @Test
        public void should_fail_when_termPredicatePasses() {
            Assertions.assertThatThrownBy(() -> Validation.failIf(List.of("test", "test2", "test3"))
                                                          .withTerm(it -> it.size() == 3)
                                                          .examine())
                      .hasMessage(DEFAULT_EXCEPTION);
        }

        @Test
        public void should_pass_when_termPredicateNotPasses() {
            Validation.failIf(List.of("test", "test2", "test3"))
                      .withTerm(it -> it.size() == 4).onError(INVALID_SIZE)
                      .examine();
        }

        @Test
        public void should_fail_when_termSupplierPasses() {
            Assertions.assertThatThrownBy(() -> Validation.failIf(List.of("test", "test2", "test3"))
                                                          .withTerm(() -> 1 == 1)
                                                          .examine())
                      .hasMessage(DEFAULT_EXCEPTION);
        }

        @Test
        public void should_pass_when_termSupplierNotPasses() {
            Validation.failIf(List.of("test", "test2", "test3"))
                      .withTerm(() -> 1 == 2).onError(IS_NOT_EQUAL)
                      .examine();
        }

        @Test
        public void should_fail_when_satisfiesAny() {
            Assertions.assertThatThrownBy(() -> Validation.failIf(List.of("test", "test2", "test3"))
                                                          .satisfiesAny(ListConditions.isEmpty(), ListConditions.hasSize(3))
                                                          .examine())
                      .hasMessage(DEFAULT_EXCEPTION);
        }

        @Test
        public void should_pass_when_notSatisfiesAny() {
            Validation.failIf(List.of("test", "test2", "test3"))
                      .satisfiesAny(ListConditions.isEmpty(), ListConditions.hasSize(1)).onError(NOT_SATISFIES)
                      .examine();
        }

        @Test
        public void should_fail_when_satisfiesEvery() {
            Assertions.assertThatThrownBy(() -> Validation.failIf(List.of("test", "test2", "test3"))
                                                          .satisfiesEvery(ListConditions.hasSize(3), ListConditions.isNotEmpty())
                                                          .examine())
                      .hasMessage(DEFAULT_EXCEPTION);
        }

        @Test
        public void should_pass_when_notSatisfiesEvery() {
            Validation.failIf(List.of("test", "test2", "test3"))
                      .satisfiesEvery(ListConditions.hasSize(2), ListConditions.isNotEmpty()).onError(NOT_SATISFIES)
                      .examine();
        }

        @Test
        public void should_fail_when_inspectingPredicatePasses() {
            Assertions.assertThatThrownBy(() -> Validation.failIf(List.of("test", "test2", "test3"))
                                                          .inspecting(Function.identity(), it -> it.size() == 3)
                                                          .examine())
                      .hasMessage(DEFAULT_EXCEPTION);
        }

        @Test
        public void should_pass_when_inspectingPredicateNotPasses() {
            Validation.failIf(List.of("test", "test2", "test3"))
                      .inspecting(Function.identity(), it -> it.size() == 2).onError(INVALID_SIZE)
                      .examine();
        }

        @Test
        public void should_fail_when_inspectingSupplierPasses() {
            Assertions.assertThatThrownBy(() -> Validation.failIf(List.of("test", "test2", "test3"))
                                                          .inspecting(Function.identity(), ListConditions::isNotEmpty)
                                                          .examine())
                      .hasMessage(DEFAULT_EXCEPTION);
        }

        @Test
        public void should_pass_when_inspectingSupplierNotPasses() {
            Validation.failIf(List.of("test", "test2", "test3"))
                      .inspecting(Function.identity(), ListConditions::isEmpty).onError(IS_NOT_EMPTY)
                      .examine();
        }

        @Test
        public void should_fail_when_withTermDeeplyPasses() {
            Assertions.assertThatThrownBy(() -> Validation.failIf(List.of("test", "test2", "test3"))
                                                          .withTermDeeply(it -> Validation.succeedIf(it).isNotEmpty())
                                                          .examine())
                      .hasMessage(DEFAULT_EXCEPTION);
        }

        @Test
        public void should_pass_when_withTermDeeplyNotPasses() {
            Validation.failIf(List.of("test", "test2", "test3"))
                      .withTermDeeply(it -> Validation.succeedIf(it).isEmpty().onError(IS_NOT_EMPTY))
                      .examine();
        }

        @Test
        public void should_fail_when_inspectingDeeplyPasses() {
            Assertions.assertThatThrownBy(() -> Validation.failIf(List.of("test", "test2", "test3"))
                                                          .inspectingDeeply(Function.identity(), it -> Validation.succeedIf(it).isNotEmpty())
                                                          .examine())
                      .hasMessage(DEFAULT_EXCEPTION);
        }

        @Test
        public void should_pass_when_inspectingDeeplyNotPasses() {
            Validation.failIf(List.of("test", "test2", "test3"))
                      .inspectingDeeply(Function.identity(), it -> Validation.succeedIf(it).isEmpty().onError(IS_NOT_EMPTY))
                      .examine();
        }

        @Test
        public void should_fail_when_firstGroupMatches() {
            List<String> list = null;

            Assertions.assertThatThrownBy(() -> Validation.failIf(list)
                                                          .isNull().onError(IS_NULL)
                                                          .or()
                                                          .contains("test").onError(CONTAINS)
                                                          .examine())
                      .hasMessage(IS_NULL);
        }

        @Test
        public void should_fail_when_secondGroupMatches() {
            Assertions.assertThatThrownBy(() -> Validation.failIf(List.of("test", "test2", "test3"))
                                                          .isEmpty().onError(IS_EMPTY)
                                                          .or()
                                                          .isNotEmpty().onError(IS_NOT_EMPTY)
                                                          .examine())
                      .hasMessage(IS_NOT_EMPTY);
        }

        @Test
        public void should_pass_when_groupsNotMatch() {
            Validation.failIf(List.of("test", "test2", "test3"))
                      .isEmpty().onError(IS_NOT_EMPTY)
                      .or()
                      .isNull().onError(IS_NOT_NULL)
                      .examine();
        }
    }
}
