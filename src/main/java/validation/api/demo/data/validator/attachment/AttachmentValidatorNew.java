package validation.api.demo.data.validator.attachment;

import validation.api.demo.data.common.Attachment;
import validation.api.demo.data.common.Claim;
import validation.api.demo.data.common.Doc;
import validation.api.demo.validation.Validation;
import validation.api.demo.validation.domain.object.impl.ObjectValidation;

import java.io.File;
import java.util.Collections;
import java.util.List;

public class AttachmentValidatorNew {

    public void validate(Claim claim, List<Doc> docs) {
        List<Attachment> attachments = claim.getAttachments();

        Validation.verifyIf(attachments)
                  .isEmpty("test")
                  .or()
                  .each(it -> this.isValid(it, docs))
                  .failIfNoneGroupMatch();
    }

    private ObjectValidation<Attachment> isValid(Attachment attachment, List<Doc> docs) {
        return Validation.verifyIf(attachment)
                         .inspecting(this::getAttachmentFile, File::exists, "")
                         .or()
                         .withTerm(this::noConfig, "error")
                         .or()
                         .withTerm(this::isValidFormat, "test");
    }

    private boolean isValidFormat(Attachment it) {
        return false;
    }

    private boolean noConfig(Attachment attachment) {
//        1) getDocumentTypes
//        2) check if config is present

        return true;
    }

    private File getAttachmentFile(Attachment attachment) {
        return null;
    }

    private List<Attachment> getClaimAttachments(Long claimId) {
        return Collections.emptyList();
    }

    private Long getCurrentClientId() {
        return 42L;
    }
}
