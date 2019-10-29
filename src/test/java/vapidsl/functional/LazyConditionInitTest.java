package vapidsl.functional;

import lombok.*;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import vapidsl.Validation;
import vapidsl.domain.object.ObjectConditions;
import vapidsl.domain.string.StringConditions;

import java.util.List;
import java.util.Map;

public class LazyConditionInitTest {

    private static final String IS_NULL = "is.null";
    private static final String IS_NOT_NULL = "is.not.null";
    private static final String IS_NOT_EQUAL = "is.not.equal";
    private static final String IS_EMPTY = "is.empty";

    @Test
    public void should_failWith_isNullError_when_objIsNullAndPredicateInspecting() {
        Obj obj = null;

        Assertions.assertThatThrownBy(() -> Validation.succeedIf(obj)
                                                      .isNotNull().onError(IS_NULL)
                                                      .inspecting(Obj::getProp, it -> it.equals("test")).onError(IS_NOT_EQUAL)
                                                      .examine())
                  .extracting(Throwable::getMessage)
                  .isEqualTo(IS_NULL);
    }

    @Test
    public void should_pass_when_when_objIsNotNullAndPredicateInspecting() {
        Obj obj = new Obj("test");

        Validation.succeedIf(obj)
                  .isNotNull().onError(IS_NULL)
                  .inspecting(Obj::getProp, it -> it.equals("test")).onError(IS_NOT_EQUAL)
                  .examine();
    }

    @Test
    public void should_failWith_isNullError_when_objIsNullAndSimpleConditionInspecting() {
        Obj obj = null;

        Assertions.assertThatThrownBy(() -> Validation.succeedIf(obj)
                                                      .isNotNull().onError(IS_NULL)
                                                      .inspecting(Obj::getProp, StringConditions::isNotEmpty).onError(IS_EMPTY)
                                                      .examine())
                  .extracting(Throwable::getMessage)
                  .isEqualTo(IS_NULL);
    }

    @Test
    public void should_pass_when_objIsNotNullAndSimpleConditionInspecting() {
        Obj obj = new Obj("test");

        Validation.succeedIf(obj)
                  .isNotNull().onError(IS_NULL)
                  .inspecting(Obj::getProp, StringConditions::isNotEmpty).onError(IS_EMPTY)
                  .examine();
    }

    @Test
    public void should_failWith_isNullError_when_objIsNullAndDeepInspecting() {
        Obj obj = null;

        Assertions.assertThatThrownBy(() -> Validation.succeedIf(obj)
                                                      .isNotNull().onError(IS_NULL)
                                                      .deepInspecting(Obj::getProp, it -> Validation.succeedIf(it)
                                                                                                    .isEqualTo("test")
                                                                                                    .onError(IS_NOT_EQUAL))
                                                      .examine())
                  .extracting(Throwable::getMessage)
                  .isEqualTo(IS_NULL);
    }

    @Test
    public void should_pass_when_objIsNotNullAndDeepInspecting() {
        Obj obj = new Obj("test");

        Validation.succeedIf(obj)
                  .isNotNull().onError(IS_NULL)
                  .deepInspecting(Obj::getProp, it -> Validation.succeedIf(it)
                                                                .isEqualTo("test")
                                                                .onError(IS_NOT_EQUAL))
                  .examine();
    }

    @Test
    public void should_failWith_isNullError_when_objListIsNullAndHasEverySimpleConditionAsReference() {
        List<Obj> list = null;

        Assertions.assertThatThrownBy(() -> Validation.succeedIf(list)
                                                      .isNotNull().onError(IS_NULL)
                                                      .every(ObjectConditions::isNull).onError(IS_NOT_NULL)
                                                      .examine())
                  .extracting(Throwable::getMessage)
                  .isEqualTo(IS_NULL);
    }

    @Test
    public void should_pass_when_objListIsNotNullAndHasEverySimpleConditionAsReference() {
        List<Obj> list = List.of(new Obj(), new Obj());

        Validation.succeedIf(list)
                  .isNotNull().onError(IS_NULL)
                  .every(ObjectConditions::isNotNull).onError(IS_NULL)
                  .examine();
    }

    @Test
    public void should_failWith_isNullError_when_objListIsNullAndHasEverySimpleCondition() {
        List<Obj> list = null;

        Assertions.assertThatThrownBy(() -> Validation.succeedIf(list)
                                                      .isNotNull().onError(IS_NULL)
                                                      .every(() -> ObjectConditions.isEqualTo(new Obj())).onError(IS_NOT_EQUAL)
                                                      .examine())
                  .extracting(Throwable::getMessage)
                  .isEqualTo(IS_NULL);
    }

    @Test
    public void should_pass_when_objListIsNotNullAndHasEverySimpleCondition() {
        List<Obj> list = List.of(new Obj(), new Obj());

        Validation.succeedIf(list)
                  .isNotNull().onError(IS_NULL)
                  .every(() -> ObjectConditions.isEqualTo(new Obj())).onError(IS_NOT_EQUAL)
                  .examine();
    }

    @Test
    public void should_failWith_isNullError_when_objListIsNullAndHasEveryValidationCondition() {
        List<Obj> list = null;

        Assertions.assertThatThrownBy(() -> Validation.succeedIf(list)
                                                      .isNotNull().onError(IS_NULL)
                                                      .every(obj -> Validation.succeedIf(obj)
                                                                              .inspecting(Obj::getProp, StringConditions::isNotEmpty).onError(IS_EMPTY)
                                                      )
                                                      .examine())
                  .extracting(Throwable::getMessage)
                  .isEqualTo(IS_NULL);
    }

    @Test
    public void should_pass_objListIsNotNullAndHasEveryValidationCondition() {
        List<Obj> list = List.of(new Obj("test"), new Obj("test1"));

        Validation.succeedIf(list)
                  .isNotNull().onError(IS_NULL)
                  .every(obj -> Validation.succeedIf(obj)
                                          .inspecting(Obj::getProp, StringConditions::isNotEmpty).onError(IS_EMPTY)
                  )
                  .examine();
    }

    @Test
    public void should_failWith_isNullError_when_objArrayIsNullAndHasEverySimpleConditionAsReference() {
        Obj[] array = null;

        Assertions.assertThatThrownBy(() -> Validation.succeedIf(array)
                                                      .isNotNull().onError(IS_NULL)
                                                      .every(ObjectConditions::isNull).onError(IS_NOT_NULL)
                                                      .examine())
                  .extracting(Throwable::getMessage)
                  .isEqualTo(IS_NULL);
    }

    @Test
    public void should_pass_when_objArrayIsNotNullAndHasEverySimpleConditionAsReference() {
        Obj[] array = new Obj[]{new Obj(), new Obj()};

        Validation.succeedIf(array)
                  .isNotNull().onError(IS_NULL)
                  .every(ObjectConditions::isNotNull).onError(IS_NOT_NULL)
                  .examine();
    }

    @Test
    public void should_failWith_isNullError_when_objArrayIsNullAndHasEverySimpleCondition() {
        Obj[] array = null;

        Assertions.assertThatThrownBy(() -> Validation.succeedIf(array)
                                                      .isNotNull().onError(IS_NULL)
                                                      .every(() -> ObjectConditions.isEqualTo(new Obj())).onError(IS_NOT_EQUAL)
                                                      .examine())
                  .extracting(Throwable::getMessage)
                  .isEqualTo(IS_NULL);
    }

    @Test
    public void should_pass_when_objArrayIsNotNullAndHasEverySimpleCondition() {
        Obj[] array = new Obj[]{new Obj(), new Obj()};

        Validation.succeedIf(array)
                  .isNotNull().onError(IS_NULL)
                  .every(() -> ObjectConditions.isEqualTo(new Obj())).onError(IS_NOT_EQUAL)
                  .examine();
    }

    @Test
    public void should_failWith_isNullError_when_objArrayIsNullAndHasEveryValidationCondition() {
        Obj[] array = null;

        Assertions.assertThatThrownBy(() -> Validation.succeedIf(array)
                                                      .isNotNull().onError(IS_NULL)
                                                      .every(obj -> Validation.succeedIf(obj)
                                                                              .inspecting(Obj::getProp, StringConditions::isNotEmpty).onError(IS_EMPTY)
                                                      )
                                                      .examine())
                  .extracting(Throwable::getMessage)
                  .isEqualTo(IS_NULL);
    }

    @Test
    public void should_pass_when_objArrayIsNotNullAndHasEveryValidationCondition() {
        Obj[] array = new Obj[]{new Obj("test"), new Obj("test1")};

        Validation.succeedIf(array)
                  .isNotNull().onError(IS_NULL)
                  .every(obj -> Validation.succeedIf(obj)
                                          .inspecting(Obj::getProp, StringConditions::isNotEmpty).onError(IS_EMPTY)
                  )
                  .examine();
    }

    @Test
    public void should_failWith_isNullError_when_objMapIsNullAndHasEveryValidationCondition() {
        Map<String, Obj> map = null;

        Assertions.assertThatThrownBy(() -> Validation.succeedIf(map)
                                                      .isNotNull().onError(IS_NULL)
                                                      .diveEvery((key, obj) -> Validation.succeedIf(obj)
                                                                                         .inspecting(Obj::getProp, StringConditions::isNotEmpty).onError(IS_EMPTY)
                                                      )
                                                      .examine())
                  .extracting(Throwable::getMessage)
                  .isEqualTo(IS_NULL);
    }

    @Test
    public void should_pass_when_objMapIsNotNullAndHasEveryValidationCondition() {
        Map<String, Obj> map = Map.of("test", new Obj("test"));

        Validation.succeedIf(map)
                  .isNotNull().onError(IS_NULL)
                  .diveEvery((key, obj) -> Validation.succeedIf(obj)
                                                     .inspecting(Obj::getProp, StringConditions::isNotEmpty).onError(IS_EMPTY)
                  )
                  .examine();
    }

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @EqualsAndHashCode
    private static class Obj {

        private String prop;
    }
}
