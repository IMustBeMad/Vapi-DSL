package validation.api.demo.data.validator.attachment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import validation.api.demo.data.common.*;
import validation.api.demo.data.service.AttachmentService;
import validation.api.demo.data.service.ClientService;
import validation.api.demo.data.service.UserService;
import validation.api.demo.validation.Validation;
import validation.api.demo.validation.domain.object.impl.ObjectValidation;

import java.io.File;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.Collections.emptyList;

public class AttachmentValidatorNew {

    @Autowired
    private AttachmentService attachmentService;

    @Autowired
    private UserService userService;

    @Autowired
    private ClientService clientService;

    public void validate(Claim claim, List<Doc> docs) {
        List<Attachment> attachments = attachmentService.getClaimAttachments(claim.getId());

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
                         .withTerm(() -> noConfig(docs), "error")
                         .or()
                         .withTerm(this::isValidFormat);
    }

    private ObjectValidation<Attachment> isValidFormat(Attachment attachment) {
        Long clientId = userService.getCurrentClientId();
        ClientConfig config = clientService.getClientConfig(clientId);

        return Validation.verifyIf(attachment)
                         .inspecting(Attachment::getOriginalName, name -> isValidExtension(name, config.getAllowedExtensions()), "error")
                         .inspecting(attachmentService::getAttachmentFile, file -> Validation.verifyIf(file)
                                                                                             .withTerm(() -> config.getSizeLimit() == null, "error")
                                                                                             .or()
                                                                                             .withTerm(this::isValidFile, "error")
                         );
    }

    private boolean noConfig(List<Doc> docs) {
        Long clientId = userService.getCurrentClientId();

        List<DocType> documentTypes = getDocTypes(docs);

        ClientConfig config = clientService.getClientConfig(clientId);
        return !shouldValidate(documentTypes, config);
    }

    private boolean shouldValidate(List<DocType> documentTypes, ClientConfig config) {
        return config != null
                       && !CollectionUtils.isEmpty(documentTypes)
                       && !CollectionUtils.isEmpty(config.getValidatedDocs())
                       && CollectionUtils.containsAny(config.getValidatedDocs(), documentTypes);
    }

    private List<DocType> getDocTypes(List<Doc> docs) {
        return emptyIfNull(docs).stream()
                                .map(Doc::getType)
                                .collect(Collectors.toList());
    }

    private File getAttachmentFile(Attachment attachment) {
        return attachmentService.getAttachmentFile(attachment);
    }

    private List<Attachment> getClaimAttachments(Long claimId) {
        return Collections.emptyList();
    }

    private Long getCurrentClientId() {
        return 42L;
    }

    private List<Doc> emptyIfNull(List<Doc> claimDocs) {
        return claimDocs == null ? emptyList() : claimDocs;
    }

    private boolean isValidExtension(String originalName, String allowedExtensions) {
        return true;
    }

    private boolean isValidFile(File file) {
        return file.getName().equals("TEST");
    }
}
