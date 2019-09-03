package validation.api.demo.data.feature;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import validation.api.demo.data.common.Attachment;
import validation.api.demo.validation.Validation;
import validation.api.demo.validation.dict.MatchMode;
import validation.api.demo.validation.domain.number.biginteger.LongConditions;
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

        Validation.succeedIf(attachments)
                  .log("should be no errors")
                  .ofSize(4).onError("incorrect.size")
                  .each(attachment -> Validation.failIf(attachment, MatchMode.EAGER)
                                                .inspecting(Attachment::getId, () -> LongConditions.isGt(0L)).onError("not.gt")
                                                .inspecting(Attachment::getOriginalName, () -> StringConditions.matches("test.*")).onError("not.matches")
                  )
                  .examine();
    }

    @Test
    public void should_beErrorOnSecondItem_when_validationHaveErrors() {
        List<Attachment> attachments = List.of(
                getAttachment(1L, "test"),
                getAttachment(-2L, "te23st"),
                getAttachment(3L, "test2"),
                getAttachment(4L, "test3")
        );

        Assertions.assertThatThrownBy(() -> Validation.succeedIf(attachments)
                                                      .log("should be error on second item")
                                                      .ofSize(4).onError("incorrect.size")
                                                      .each(attachment -> Validation.succeedIf(attachment, MatchMode.EAGER)
                                                                                    .inspecting(Attachment::getId, () -> LongConditions.isGt(0L)).onError("not.gt")
                                                                                    .inspecting(Attachment::getOriginalName, () -> StringConditions.matches("test.*")).onError("not.matches")
                                                      )
                                                      .examine())
                  .hasMessage("not.matches,\nnot.gt");
    }

    @Test
    public void should_beNoErrors_when_complexDeepInspect() {
        List<String> list = List.of("test1", "test2", "test3");

        Validation.succeedIf(list)
                  .ofSize(3)
                  .deepInspecting(
                          it -> it.get(0),
                          item -> Validation.failIf(item)
                                            .isNotNull()
                                            .isEqualTo("test1")
                                            .deepInspecting(
                                                    it -> it.substring(0, 1),
                                                    letter -> Validation.failIf(letter)
                                                                        .isEqualTo("d").onError("d.wrong.code")
                                                                        .matches("t")
                                            )
                  )
                  .examine();
    }

    private Attachment getAttachment(Long id, String name) {
        Attachment attachment = new Attachment();
        attachment.setId(id);
        attachment.setOriginalName(name);

        return attachment;
    }
}
