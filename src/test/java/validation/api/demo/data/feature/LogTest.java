package validation.api.demo.data.feature;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import validation.api.demo.validation.Validation;
import validation.api.demo.validation.dict.TerminationMode;

@RunWith(JUnit4.class)
public class LogTest {

    @Test
    public void should_writeLog_toConsole() {
        Validation.verifyIf("test")
                  .log("this is test string")
                  .isEqualTo("test")
                  .failOn(TerminationMode.FIRST_ERROR_ENCOUNTERED)
                  .examine();
    }
}
