package validation.api.demo.termination.mode;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import validation.api.demo.validation.Validation;
import validation.api.demo.validation.dict.TerminationMode;
import validation.api.demo.validation.domain.object.ObjectConditions;

import java.util.List;

@RunWith(JUnit4.class)
public class NoneGroupMatchTerminationModeTest {

    @Test
    public void should_throwError_when_noneGroupIsMatched() {
        String isNotEmptyError = "is.not.empty";
        String isNotNullError = "is.not.null";

        Assertions.assertThatThrownBy(() -> Validation.verifyIf("test")
                                                      .isEmpty().onError(isNotEmptyError)
                                                      .or()
                                                      .isNull().onError(isNotNullError)
                                                      .failOn(TerminationMode.NONE_GROUP_MATCH)
                                                      .examine())
                  .hasMessage(isNotEmptyError + "\n" + isNotNullError);
    }

    @Test
    public void should_beNoErrors_when_anyGroupIsMatched() {
        Validation.verifyIf(List.of(42L, 42L))
                  .isEmpty().onError("not.empty")
                  .or()
                  .inspecting(it -> it.get(0), ObjectConditions::isNull).onError("is.not.null")
                  .or()
                  .ofSize(2).onError("incorrect.size")
                  .failOn(TerminationMode.NONE_GROUP_MATCH)
                  .examine();
    }
}
