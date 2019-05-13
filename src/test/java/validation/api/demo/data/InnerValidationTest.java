package validation.api.demo.data;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import validation.api.demo.data.common.Attachment;
import validation.api.demo.validation.Validation;
import validation.api.demo.validation.dict.TerminationMode;
import validation.api.demo.validation.domain.number.LongConditions;
import validation.api.demo.validation.domain.string.StringConditions;

import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
public class InnerValidationTest {

    @Test
    public void should_beNoErrors_when_validationsHaveNoErrors() {
        List<Attachment> attachments = List.of(
                getAttachment(1L, "test"),
                getAttachment(2L, "test1"),
                getAttachment(3L, "test2"),
                getAttachment(4L, "test3")
        );

        Validation.verifyIf(attachments)
                  .ofSize(4, "incorrect.size")
                  .each(TerminationMode.ERRORS_COMPUTED,
                        attachment -> Validation.verifyIf(attachment)
                                                .inspecting(Attachment::getId, () -> LongConditions.isGt(0L))
                                                .inspecting(Attachment::getOriginalName, () -> StringConditions.matches("test.*"))
                  )
                  .failOn(TerminationMode.FIRST_ERROR);
    }

    @Test
    public void should_beErrorOnSecondItem_when_validationHaveErrors() {
        List<Attachment> attachments = List.of(
                getAttachment(1L, "test"),
                getAttachment(-2L, "error"),
                getAttachment(3L, "test2"),
                getAttachment(4L, "test3")
        );

        Validation.verifyIf(attachments)
                  .ofSize(4, "incorrect.size")
                  .each(TerminationMode.ERRORS_COMPUTED,
                        attachment -> Validation.verifyIf(attachment)
                                                .inspecting(Attachment::getId, () -> LongConditions.isGt(0L))
                                                .inspecting(Attachment::getOriginalName, () -> StringConditions.matches("test.*"))
                  )
                  .failOn(TerminationMode.FIRST_ERROR);
    }

    private Attachment getAttachment(Long id, String name) {
        Attachment attachment = new Attachment();
        attachment.setId(id);
        attachment.setOriginalName(name);

        return attachment;
    }
}
