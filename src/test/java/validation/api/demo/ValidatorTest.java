package validation.api.demo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import validation.api.demo.validation.BaseChainFunction;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ValidatorTest {

    @Test
    public void test() {
        String issueNumber = "IR20180203123";

        BaseChainFunction.assertThat(issueNumber)
                         .matches("^IRM\\d{11,}")
                         .or()
                         .isDate(nmbr -> nmbr.substring(2, 11), "yyyMMdd")
                         .computeFails();
    }
}
