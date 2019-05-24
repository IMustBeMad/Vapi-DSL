package validation.api.demo.data.feature;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import validation.api.demo.data.common.Attachment;
import validation.api.demo.validation.Validation;
import validation.api.demo.validation.dict.TerminationMode;
import validation.api.demo.validation.domain.number.LongConditions;
import validation.api.demo.validation.domain.string.StringConditions;

import java.util.List;

@RunWith(JUnit4.class)
public class DeepInspectingTest {

    @Test
    public void should_beNoErrors_when_validationsHaveNoErrors() {
        List<Attachment> attachments = List.of(
                getAttachment(1L, "test"),
                getAttachment(2L, "test1"),
                getAttachment(3L, "test2"),
                getAttachment(4L, "test3")
        );

        Validation.verifyIf(attachments)
                  .log("should be no errors")
                  .ofSize(4).onError("incorrect.size")
                  .each(attachment -> Validation.verifyIf(attachment)
                                                .inspecting(Attachment::getId, () -> LongConditions.isGt(0L)).onError("not.gt")
                                                .inspecting(Attachment::getOriginalName, () -> StringConditions.matches("test.*")).onError("not.matches")
                                                .failOn(TerminationMode.LAST_ERROR_ENCOUNTERED)
                  )
                  .failOn(TerminationMode.FIRST_ERROR_ENCOUNTERED)
                  .examine();
    }

    @Test
    public void should_beErrorOnSecondItem_when_validationHaveErrors() {
        List<Attachment> attachments = List.of(
                getAttachment(1L, "test"),
                getAttachment(-2L, "error"),
                getAttachment(3L, "test2"),
                getAttachment(4L, "test3")
        );

        Assertions.assertThatThrownBy(() -> Validation.verifyIf(attachments)
                                                      .log("should be error on second item")
                                                      .ofSize(4).onError("incorrect.size")
                                                      .each(attachment -> Validation.verifyIf(attachment)
                                                                                    .inspecting(Attachment::getId, () -> LongConditions.isGt(0L)).onError("not.gt")
                                                                                    .inspecting(Attachment::getOriginalName, () -> StringConditions.matches("test.*")).onError("not.matches")
                                                                                    .failOn(TerminationMode.LAST_ERROR_ENCOUNTERED)
                                                      )
                                                      .failOn(TerminationMode.FIRST_ERROR_ENCOUNTERED)
                                                      .examine())
                  .hasMessage("not.matches,\nnot.gt");
    }

    @Test
    public void should_beNoErrors_when_complexDeepInspect() {
        List<String> list = List.of("test1", "test2", "test3");

        Validation.verifyIf(list)
                  .ofSize(3)
                  .deepInspecting(
                          it -> it.get(0),
                          item -> Validation.verifyIf(item)
                                            .isNotNull()
                                            .isEqualTo("test1")
                                            .deepInspecting(
                                                    it -> it.substring(0, 1),
                                                    letter -> Validation.verifyIf(letter)
                                                                        .isEqualTo("d").onError("d.wrong.code")
                                                                        .matches("t")
                                                                        .failOn(TerminationMode.FIRST_ERROR_ENCOUNTERED)
                                            ).failOn(TerminationMode.LAST_ERROR_ENCOUNTERED)
                  )
                  .failOn(TerminationMode.NO_ERROR_ENCOUNTERED)
                  .examine();
    }

    private Attachment getAttachment(Long id, String name) {
        Attachment attachment = new Attachment();
        attachment.setId(id);
        attachment.setOriginalName(name);

        return attachment;
    }
}
