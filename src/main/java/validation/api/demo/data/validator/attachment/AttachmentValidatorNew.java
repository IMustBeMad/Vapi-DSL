package validation.api.demo.data.validator.attachment;

import validation.api.demo.data.common.Attachment;
import validation.api.demo.data.common.Claim;
import validation.api.demo.data.common.Doc;
import validation.api.demo.validation.Validation;
import validation.api.demo.validation.domain.list.ListConditions;

import java.util.Collections;
import java.util.List;

public class AttachmentValidatorNew {

    public void validate(Claim claim, List<Doc> docs) {
        List<Attachment> attachments = claim.getAttachments();

        Validation.verifyIf(attachments)
                  .isEmpty("test")
                  .or()
                  .withTerm(verifyAttachmentOnSubmit(clientId, file, attachment, docs))
                  .failIfNoneGroupMatch();
    }

    private List<Attachment> getClaimAttachments(Long claimId) {
        return Collections.emptyList();
    }

    private Long getCurrentClientId() {
        return 42L;
    }
}
