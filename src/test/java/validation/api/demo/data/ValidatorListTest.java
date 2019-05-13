package validation.api.demo.data;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import validation.api.demo.validation.Validation;
import validation.api.demo.validation.dict.TerminationMode;
import validation.api.demo.validation.domain.number.LongConditions;

import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
public class ValidatorListTest {

    @Test
    public void should_beNoErrors_when_eachElementIsCorrect() {
        List<Long> longs = List.of(1L, 2L, 3L, 42L);

        Validation.verifyIf(longs)
                  .ofSize(4, "incorrect.size")
                  .inspecting(it -> it.get(3), it -> 42L == it, "not.equal")
                  .each(LongConditions.isGt(0L), "not.greater")
                  .failOn(TerminationMode.FIRST_ERROR);
    }

    @Test
    public void should_raiseAnError_when_eachElementIsNotCorrect() {
        List<Long> longs = List.of(1L, 2L, 3L, 42L);

        Validation.verifyIf(longs)
                  .ofSize(4, "incorrect.size")
                  .inspecting(it -> it.get(3), it -> 42L == it, "not.equal")
                  .each(LongConditions.isGt(42L), "not.greater")
                  .failOn(TerminationMode.FIRST_ERROR);
    }
}
