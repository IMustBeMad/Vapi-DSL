package vapidsl.domain;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import vapidsl.Validation;
import vapidsl.ValidatorTest;
import vapidsl.dict.ErrorMode;
import vapidsl.dict.MatchMode;
import vapidsl.domain.map.MapConditions;
import vapidsl.domain.string.StringConditions;
import vapidsl.exception.ValidationException;

import java.util.Collections;
import java.util.Map;
import java.util.function.Function;

@RunWith(Suite.class)
@Suite.SuiteClasses({MapTest.SucceedIfTest.class, MapTest.FailedIfTest.class})
public class MapTest extends ValidatorTest {

    private static final String NOT_EVERY_MATCH = "every.not.match";

    public static class SucceedIfTest {

        @Test
        public void should_pass_when_mapIsEmpty() {
            Validation.succeedIf(Map.of())
                      .isEmpty()
                      .examine();
        }

        @Test
        public void should_pass_when_mapIsNotEmpty() {
            Validation.succeedIf(Map.of("test", "test"))
                      .isNotEmpty()
                      .examine();
        }

        @Test
        public void should_pass_when_everyElementMatches() {
            Map<String, String> map = Map.of(
                    "test1", "test1",
                    "test2", "test2"
            );

            Validation.succeedIf(map)
                      .every((key, value) -> value.matches("test.*"))
                      .examine();
        }

        @Test
        public void should_pass_when_everyDeeplyElementMatches() {
            Map<String, String> map = Map.of(
                    "test1", "test1",
                    "test2", "test2"
            );

            Validation.succeedIf(map)
                      .everyDeeply((key, value) -> Validation.succeedIf(value)
                                                             .startsWith("t")
                                                             .matches("test.*"))
                      .examine();
        }

        @Test
        public void should_fail_when_everyElementNotMatches() {
            Map<String, String> map = Map.of(
                    "test1", "test1",
                    "test2", "invalid"
            );

            Assertions.assertThatThrownBy(() -> Validation.succeedIf(map)
                                                          .every((key, value) -> value.matches("test.*")).onError(NOT_EVERY_MATCH)
                                                          .examine())
                      .hasMessage(NOT_EVERY_MATCH);
        }

        @Test
        public void should_fail_when_everyDeeplyElementNotMatches() {
            Map<String, String> map = Map.of(
                    "test1", "test1",
                    "test2", "invalid"
            );

            Assertions.assertThatThrownBy(() -> Validation.succeedIf(map)
                                                          .everyDeeply((key, value) -> Validation.succeedIf(value)
                                                                                                 .startsWith("t")
                                                                                                 .matches("test.*"))
                                                          .onError(NOT_EVERY_MATCH)
                                                          .examine())
                      .hasMessage(NOT_EVERY_MATCH);
        }

        @Test
        public void should_fail_withAllFieldsAndCodes() {
            Validation.succeedIf(Map.of("t", "test"), MatchMode.EAGER)
                      .everyDeeply((key, value) -> Validation.succeedIf(key, MatchMode.EAGER)
                                                             .isEqualTo("s").onError("key", "not equal")
                                                             .matches("//d").onError("key", "not matches")
                      )
                      .inspecting(it -> it.get("t"), StringConditions::isBlank).onError("key", "not blank")
                      .examine(ErrorMode.RETURN);
        }

        @Test
        public void should_pass_when_isNull() {
            Map<String, String> map = null;

            Validation.succeedIf(map)
                      .isNull()
                      .examine();
        }

        @Test
        public void should_fail_when_isNotNull() {
            Assertions.assertThatThrownBy(() -> Validation.succeedIf(Map.of("test", "test"))
                                                          .isNull().onError(IS_NOT_NULL)
                                                          .examine())
                      .hasMessage(IS_NOT_NULL);
        }

        @Test
        public void should_pass_when_isNotNull() {
            Validation.succeedIf(Map.of("test", "test"))
                      .isNotNull()
                      .examine();
        }

        @Test
        public void should_fail_when_isNull() {
            Map<String, String> map = null;

            Assertions.assertThatThrownBy(() -> Validation.succeedIf(map)
                                                          .isNotNull().onError(IS_NULL)
                                                          .examine())
                      .hasMessage(IS_NULL);
        }

        @Test
        public void should_pass_when_isEqualTo() {
            Map<String, String> map = Map.of("test", "test");
            Map<String, String> otherMap = Map.of("test", "test");

            Validation.succeedIf(map)
                      .isEqualTo(otherMap)
                      .examine();
        }

        @Test
        public void should_fail_when_isNotEqualTo() {
            Map<String, String> map = Map.of("test", "test");
            Map<String, String> otherMap = Map.of("test", "test", "test2", "test2");

            Assertions.assertThatThrownBy(() -> Validation.succeedIf(map)
                                                          .isEqualTo(otherMap).onError(IS_NOT_EQUAL)
                                                          .examine())
                      .hasMessage(IS_NOT_EQUAL);
        }

        @Test
        public void should_pass_when_isNotEqualTo() {
            Map<String, String> map = Map.of("test", "test");
            Map<String, String> otherMap = Map.of("test", "test", "test2", "test2");

            Validation.succeedIf(map)
                      .isNotEqualTo(otherMap)
                      .examine();
        }

        @Test
        public void should_fail_when_isEqualTo() {
            Map<String, String> map = Map.of("test", "test");
            Map<String, String> otherMap = Map.of("test", "test");

            Assertions.assertThatThrownBy(() -> Validation.succeedIf(map)
                                                          .isNotEqualTo(otherMap).onError(IS_EQUAL)
                                                          .examine())
                      .hasMessage(IS_EQUAL);
        }

        @Test
        public void should_pass_when_termPredicatePasses() {
            Validation.succeedIf(Map.of("test", "test"))
                      .withTerm(it -> it.size() == 1)
                      .examine();
        }

        @Test
        public void should_fail_when_termPredicateNotPasses() {
            Assertions.assertThatThrownBy(() -> Validation.succeedIf(Map.of("test", "test"))
                                                          .withTerm(it -> it.size() == 2).onError(INVALID_SIZE)
                                                          .examine())
                      .hasMessage(INVALID_SIZE);
        }

        @Test
        public void should_pass_when_termSupplierPasses() {
            Validation.succeedIf(Map.of("test", "test"))
                      .withTerm(() -> 1 == 1)
                      .examine();
        }

        @Test
        public void should_fail_when_termSupplierNotPasses() {
            Assertions.assertThatThrownBy(() -> Validation.succeedIf(Map.of("test", "test"))
                                                          .withTerm(() -> 1 == 2).onError(IS_NOT_EQUAL)
                                                          .examine())
                      .hasMessage(IS_NOT_EQUAL);
        }

        @Test
        public void should_pass_when_satisfiesAny() {
            Validation.succeedIf(Map.of("test", "test"))
                      .satisfiesAny(MapConditions.isEmpty(), MapConditions.hasSize(1))
                      .examine();
        }

        @Test
        public void should_fail_when_notSatisfiesAny() {
            Assertions.assertThatThrownBy(() -> Validation.succeedIf(Map.of("test", "test"))
                                                          .satisfiesAny(MapConditions.isEmpty(), MapConditions.hasSize(2)).onError(NOT_SATISFIES)
                                                          .examine())
                      .hasMessage(NOT_SATISFIES);
        }

        @Test
        public void should_pass_when_satisfiesEvery() {
            Validation.succeedIf(Map.of("test", "test"))
                      .satisfiesEvery(MapConditions.hasSize(1), MapConditions.isNotEmpty())
                      .examine();
        }

        @Test
        public void should_fail_when_notSatisfiesEvery() {
            Assertions.assertThatThrownBy(() -> Validation.succeedIf(Map.of("test", "test"))
                                                          .satisfiesEvery(MapConditions.hasSize(2), MapConditions.isNotEmpty()).onError(NOT_SATISFIES)
                                                          .examine())
                      .hasMessage(NOT_SATISFIES);
        }

        @Test
        public void should_log_msg() {
            Validation.succeedIf(Map.of("test", "test"))
                      .log("checking if empty")
                      .isNotEmpty()
                      .examine();
        }

        @Test
        public void should_pass_when_inspectingPredicatePasses() {
            Validation.succeedIf(Map.of("test", "test"))
                      .inspecting(Function.identity(), it -> it.size() == 1)
                      .examine();
        }

        @Test
        public void should_fail_when_inspectingPredicateNotPasses() {
            Assertions.assertThatThrownBy(() -> Validation.succeedIf(Map.of("test", "test"))
                                                          .inspecting(Function.identity(), it -> it.size() == 2).onError(INVALID_SIZE)
                                                          .examine())
                      .hasMessage(INVALID_SIZE);
        }

        @Test
        public void should_pass_when_inspectingSupplierPasses() {
            Validation.succeedIf(Map.of("test", "test"))
                      .inspecting(Function.identity(), MapConditions::isNotEmpty)
                      .examine();
        }

        @Test
        public void should_fail_when_inspectingSupplierNotPasses() {
            Assertions.assertThatThrownBy(() -> Validation.succeedIf(Map.of("test", "test"))
                                                          .inspecting(Function.identity(), MapConditions::isEmpty).onError(IS_NOT_EMPTY)
                                                          .examine())
                      .hasMessage(IS_NOT_EMPTY);
        }

        @Test
        public void should_pass_when_withTermDeeplyPasses() {
            Validation.succeedIf(Map.of("test", "test"))
                      .withTermDeeply(it -> Validation.succeedIf(it).isNotEmpty())
                      .examine();
        }

        @Test
        public void should_fail_when_withTermDeeplyNotPasses() {
            Assertions.assertThatThrownBy(() -> Validation.succeedIf(Map.of("test", "test"))
                                                          .withTermDeeply(it -> Validation.succeedIf(it).isEmpty().onError(IS_NOT_EMPTY))
                                                          .examine())
                      .hasMessage(IS_NOT_EMPTY);
        }

        @Test
        public void should_pass_when_inspectingDeeplyPasses() {
            Validation.succeedIf(Map.of("test", "test"))
                      .inspectingDeeply(Function.identity(), it -> Validation.succeedIf(it).isNotEmpty())
                      .examine();
        }

        @Test
        public void should_fail_when_inspectingDeeplyNotPasses() {
            Assertions.assertThatThrownBy(() -> Validation.succeedIf(Map.of("test", "test"))
                                                          .inspectingDeeply(Function.identity(), it -> Validation.succeedIf(it).isEmpty().onError(IS_NOT_EMPTY))
                                                          .examine())
                      .hasMessage(IS_NOT_EMPTY);
        }

        @Test
        public void should_pass_when_firstGroupMatches() {
            Map<String, String> list = null;

            Validation.succeedIf(list)
                      .isNull().onError(IS_NOT_NULL)
                      .or()
                      .containsKey("test").onError(NOT_CONTAINS)
                      .examine();
        }

        @Test
        public void should_pass_when_secondGroupMatches() {
            Validation.succeedIf(Map.of("test", "test"))
                      .isEmpty().onError(IS_NOT_EMPTY)
                      .or()
                      .isNotEmpty().onError(IS_EMPTY)
                      .examine();
        }

        @Test
        public void should_fail_when_groupsNotMatch() {
            Assertions.assertThatThrownBy(() -> Validation.succeedIf(Map.of("test", "test"))
                                                          .isEmpty().onError(IS_NOT_EMPTY)
                                                          .or()
                                                          .isNull().onError(IS_NOT_NULL)
                                                          .examine())
                      .hasMessage(IS_NOT_EMPTY + "\n" + IS_NOT_NULL);
        }

        @Test
        public void should_pass_when_hasCorrectSize() {
            Validation.succeedIf(Map.of("test", "test"))
                      .hasSize(1)
                      .examine();
        }

        @Test
        public void should_fail_when_hasIncorrectSize() {
            Assertions.assertThatThrownBy(() -> Validation.succeedIf(Map.of("test", "test"))
                                                          .hasSize(2)
                                                          .examine())
                      .hasMessage(DEFAULT_EXCEPTION);
        }
    }

    public static class FailedIfTest {

        @Test
        public void should_fail_when_mapIsEmpty() {
            Assertions.assertThatThrownBy(() -> Validation.failIf(Map.of())
                                                          .isEmpty()
                                                          .examine())
                      .isInstanceOf(ValidationException.class);
        }

        @Test
        public void should_fail_when_mapIsNotEmpty() {
            Assertions.assertThatThrownBy(() -> Validation.failIf(Map.of("test", "test"))
                                                          .isNotEmpty()
                                                          .examine())
                      .isInstanceOf(ValidationException.class);
        }

        @Test
        public void should_fail_when_everyElementPredicateMatch() {
            Assertions.assertThatThrownBy(() -> Validation.failIf(Map.of("test", "test"))
                                                          .isNotEmpty()
                                                          .every((key, value) -> key.matches("test.*"))
                                                          .groupError(INVALID_GROUP)
                                                          .examine())
                      .hasMessage(PREFIX_INVALID_GROUP);
        }

        @Test
        public void should_pass_when_notEveryElementMatch() {
            Validation.failIf(Map.of("test", new Obj(), "test2", new Obj("test2")))
                      .every((key, value) -> value.equals(new Obj())).onError(IS_NOT_EQUAL)
                      .examine();
        }

        @Test
        public void should_fail_when_everyDeeplyElementMatch() {
            Assertions.assertThatThrownBy(() -> Validation.failIf(Map.of("test", new Obj("test"), "test2", new Obj("test2")))
                                                          .everyDeeply((key, value) -> Validation.succeedIf(value)
                                                                                        .inspecting(Obj::getProp, StringConditions::isNotEmpty))
                                                          .examine())
                      .hasMessage(DEFAULT_EXCEPTION);
        }

        @Test
        public void should_pass_when_notEveryDeeplyElementMatch() {
            Validation.failIf(Map.of("test", new Obj(""), "test2", new Obj("test2")))
                      .everyDeeply((key, value) -> Validation.failIf(value)
                                                   .inspecting(Obj::getProp, StringConditions::isEmpty)).onError(IS_NOT_EMPTY)
                      .examine();
        }

        @Test
        public void should_pass_when_contains() {
            Assertions.assertThatThrownBy(() -> Validation.failIf(Map.of("test1", "test"))
                                                          .containsKey("test1")
                                                          .examine())
                      .hasMessage(DEFAULT_EXCEPTION);
        }

        @Test
        public void should_pass_when_doesNotContain() {
            Validation.failIf(Map.of("test1", "test"))
                      .containsKey("test3").onError(NOT_CONTAINS)
                      .examine();
        }

        @Test
        public void should_fail_when_hasCorrectSize() {
            Assertions.assertThatThrownBy(() -> Validation.failIf(Map.of("test1", "test"))
                                                          .hasSize(1)
                                                          .examine())
                      .hasMessage(DEFAULT_EXCEPTION);
        }

        @Test
        public void should_pass_when_hasIncorrectCorrectSize() {
            Validation.failIf(Map.of("test1", "test"))
                      .hasSize(2).onError(INVALID_SIZE)
                      .examine();
        }

        @Test
        public void should_fail_when_isEmpty() {
            Assertions.assertThatThrownBy(() -> Validation.failIf(Collections.emptyMap())
                                                          .isEmpty()
                                                          .examine())
                      .hasMessage(DEFAULT_EXCEPTION);
        }

        @Test
        public void should_pass_when_isNotEmpty() {
            Validation.failIf(Map.of("test1", "test"))
                      .isEmpty().onError(IS_NOT_EMPTY)
                      .examine();
        }

        @Test
        public void should_fail_when_isNotEmpty() {
            Assertions.assertThatThrownBy(() -> Validation.failIf(Map.of("test1", "test"))
                                                          .isNotEmpty()
                                                          .examine())
                      .hasMessage(DEFAULT_EXCEPTION);
        }

        @Test
        public void should_pass_when_isEmpty() {
            Validation.failIf(Collections.emptyMap())
                      .isNotEmpty().onError(IS_EMPTY)
                      .examine();
        }

        @Test
        public void should_fail_when_isNull() {
            Map<String, String> map = null;

            Assertions.assertThatThrownBy(() -> Validation.failIf(map)
                                                          .isNull()
                                                          .examine())
                      .hasMessage(DEFAULT_EXCEPTION);
        }

        @Test
        public void should_pass_when_isNotNull() {
            Validation.failIf(Map.of("test1", "test"))
                      .isNull().onError(IS_NOT_NULL)
                      .examine();
        }

        @Test
        public void should_fail_when_isNotNull() {
            Assertions.assertThatThrownBy(() -> Validation.failIf(Map.of("test1", "test"))
                                                          .isNotNull()
                                                          .examine())
                      .hasMessage(DEFAULT_EXCEPTION);
        }

        @Test
        public void should_pass_when_isNull() {
            Map<String, String> map = null;

            Validation.failIf(map)
                      .isNotNull().onError(IS_NULL)
                      .examine();
        }

        @Test
        public void should_fail_when_isEqualTo() {
            Map<String, String> map = Map.of("test1", "test");
            Map<String, String> otherMap = Map.of("test1", "test");

            Assertions.assertThatThrownBy(() -> Validation.failIf(map)
                                                          .isEqualTo(otherMap)
                                                          .examine())
                      .hasMessage(DEFAULT_EXCEPTION);
        }

        @Test
        public void should_pass_when_isNotEqualTo() {
            Map<String, String> map = Map.of("test1", "test");
            Map<String, String> otherMap = Map.of("test1", "test", "test2", "test");

            Validation.failIf(map)
                      .isEqualTo(otherMap).onError(IS_NOT_EQUAL)
                      .examine();
        }

        @Test
        public void should_fail_when_isNotEqualTo() {
            Map<String, String> map = Map.of("test1", "test");
            Map<String, String> otherMap = Map.of("test1", "test", "test2", "test");

            Assertions.assertThatThrownBy(() -> Validation.failIf(map)
                                                          .isNotEqualTo(otherMap)
                                                          .examine())
                      .hasMessage(DEFAULT_EXCEPTION);
        }

        @Test
        public void should_pass_when_isEqualTo() {
            Map<String, String> map = Map.of("test1", "test");
            Map<String, String> otherMap = Map.of("test1", "test");

            Validation.failIf(map)
                      .isNotEqualTo(otherMap).onError(IS_EQUAL)
                      .examine();
        }

        @Test
        public void should_fail_when_termPredicatePasses() {
            Assertions.assertThatThrownBy(() -> Validation.failIf(Map.of("test", "test1"))
                                                          .withTerm(it -> it.size() == 1)
                                                          .examine())
                      .hasMessage(DEFAULT_EXCEPTION);
        }

        @Test
        public void should_pass_when_termPredicateNotPasses() {
            Validation.failIf(Map.of("test", "test1"))
                      .withTerm(it -> it.size() == 4).onError(INVALID_SIZE)
                      .examine();
        }

        @Test
        public void should_fail_when_termSupplierPasses() {
            Assertions.assertThatThrownBy(() -> Validation.failIf(Map.of("test", "test1"))
                                                          .withTerm(() -> 1 == 1)
                                                          .examine())
                      .hasMessage(DEFAULT_EXCEPTION);
        }

        @Test
        public void should_pass_when_termSupplierNotPasses() {
            Validation.failIf(Map.of("test", "test1"))
                      .withTerm(() -> 1 == 2).onError(IS_NOT_EQUAL)
                      .examine();
        }

        @Test
        public void should_fail_when_satisfiesAny() {
            Assertions.assertThatThrownBy(() -> Validation.failIf(Map.of("test", "test1"))
                                                          .satisfiesAny(MapConditions.isEmpty(), MapConditions.hasSize(1))
                                                          .examine())
                      .hasMessage(DEFAULT_EXCEPTION);
        }

        @Test
        public void should_pass_when_notSatisfiesAny() {
            Validation.failIf(Map.of("test", "test1"))
                      .satisfiesAny(MapConditions.isEmpty(), MapConditions.hasSize(2)).onError(NOT_SATISFIES)
                      .examine();
        }

        @Test
        public void should_fail_when_satisfiesEvery() {
            Assertions.assertThatThrownBy(() -> Validation.failIf(Map.of("test", "test1"))
                                                          .satisfiesEvery(MapConditions.hasSize(1), MapConditions.isNotEmpty())
                                                          .examine())
                      .hasMessage(DEFAULT_EXCEPTION);
        }

        @Test
        public void should_pass_when_notSatisfiesEvery() {
            Validation.failIf(Map.of("test", "test1"))
                      .satisfiesEvery(MapConditions.hasSize(2), MapConditions.isNotEmpty()).onError(NOT_SATISFIES)
                      .examine();
        }

        @Test
        public void should_fail_when_inspectingPredicatePasses() {
            Assertions.assertThatThrownBy(() -> Validation.failIf(Map.of("test", "test1"))
                                                          .inspecting(Function.identity(), it -> it.size() == 1)
                                                          .examine())
                      .hasMessage(DEFAULT_EXCEPTION);
        }

        @Test
        public void should_pass_when_inspectingPredicateNotPasses() {
            Validation.failIf(Map.of("test", "test1"))
                      .inspecting(Function.identity(), it -> it.size() == 2).onError(INVALID_SIZE)
                      .examine();
        }

        @Test
        public void should_fail_when_inspectingSupplierPasses() {
            Assertions.assertThatThrownBy(() -> Validation.failIf(Map.of("test", "test1"))
                                                          .inspecting(Function.identity(), MapConditions::isNotEmpty)
                                                          .examine())
                      .hasMessage(DEFAULT_EXCEPTION);
        }

        @Test
        public void should_pass_when_inspectingSupplierNotPasses() {
            Validation.failIf(Map.of("test", "test1"))
                      .inspecting(Function.identity(), MapConditions::isEmpty).onError(IS_NOT_EMPTY)
                      .examine();
        }

        @Test
        public void should_fail_when_withTermDeeplyPasses() {
            Assertions.assertThatThrownBy(() -> Validation.failIf(Map.of("test", "test1"))
                                                          .withTermDeeply(it -> Validation.succeedIf(it).isNotEmpty())
                                                          .examine())
                      .hasMessage(DEFAULT_EXCEPTION);
        }

        @Test
        public void should_pass_when_withTermDeeplyNotPasses() {
            Validation.failIf(Map.of("test", "test1"))
                      .withTermDeeply(it -> Validation.succeedIf(it).isEmpty().onError(IS_NOT_EMPTY))
                      .examine();
        }

        @Test
        public void should_fail_when_inspectingDeeplyPasses() {
            Assertions.assertThatThrownBy(() -> Validation.failIf(Map.of("test", "test1"))
                                                          .inspectingDeeply(Function.identity(), it -> Validation.succeedIf(it).isNotEmpty())
                                                          .examine())
                      .hasMessage(DEFAULT_EXCEPTION);
        }

        @Test
        public void should_pass_when_inspectingDeeplyNotPasses() {
            Validation.failIf(Map.of("test", "test1"))
                      .inspectingDeeply(Function.identity(), it -> Validation.succeedIf(it).isEmpty().onError(IS_NOT_EMPTY))
                      .examine();
        }

        @Test
        public void should_fail_when_firstGroupMatches() {
            Map<String, String> map = null;

            Assertions.assertThatThrownBy(() -> Validation.failIf(map)
                                                          .isNull().onError(IS_NULL)
                                                          .or()
                                                          .containsKey("test").onError(CONTAINS)
                                                          .examine())
                      .hasMessage(IS_NULL);
        }

        @Test
        public void should_fail_when_secondGroupMatches() {
            Assertions.assertThatThrownBy(() -> Validation.failIf(Map.of("test", "test1"))
                                                          .isEmpty().onError(IS_EMPTY)
                                                          .or()
                                                          .isNotEmpty().onError(IS_NOT_EMPTY)
                                                          .examine())
                      .hasMessage(IS_NOT_EMPTY);
        }

        @Test
        public void should_pass_when_groupsNotMatch() {
            Validation.failIf(Map.of("test", "test1"))
                      .isEmpty().onError(IS_NOT_EMPTY)
                      .or()
                      .isNull().onError(IS_NOT_NULL)
                      .examine();
        }
    }
}
