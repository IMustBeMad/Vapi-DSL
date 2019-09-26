package validation.api.demo.data;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import validation.api.demo.validation.Validation;
import validation.api.demo.validation.domain.number.biginteger.LongConditions;
import validation.api.demo.validation.common.ValidationError;

import java.util.List;

@RunWith(JUnit4.class)
public class ValidatorListTest {

    @Test
    public void should_beNoErrors_when_eachElementIsCorrect() {
        List<Long> longs = List.of(1L, 2L, 3L, 42L);

        List<ValidationError> messages = Validation.succeedIf(longs)
                                                   .ofSize(4).onError("incorrect.size")
                                                   .isAllOf()
                                                   .inspecting(it -> it.get(3), it -> it == 42L).onError("not.equal")
                                                   .each(LongConditions.isGt(0L)).onError("not.greater")
                                                   .examine();

        Assertions.assertThat(messages).isEmpty();
    }

    @Test
    public void should_raiseAnError_when_eachElementIsNotCorrect() {
        List<Long> longs = List.of(1L, 2L, 3L, 42L);

        Assertions.assertThatThrownBy(() -> Validation.succeedIf(longs)
                                                      .ofSize(4).onError("incorrect.size")
                                                      .inspecting(it -> it.get(3), it -> 42L == it).onError("not.equal")
                                                      .each(LongConditions.isGt(42L)).onError("not.greater")
                                                      .examine())
                  .hasMessage("not.greater");
    }
}
