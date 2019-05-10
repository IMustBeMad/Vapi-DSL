package validation.api.demo.data.validator.attachment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import validation.api.demo.data.common.*;
import validation.api.demo.data.service.AttachmentService;
import validation.api.demo.data.service.ClientService;
import validation.api.demo.data.service.PdfAValidator;
import validation.api.demo.data.service.UserService;
import validation.api.demo.exception.SystemMessage;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.Collections.emptyList;
import static org.springframework.util.CollectionUtils.isEmpty;

@Service
public class AttachmentValidator {

    private static final String PDF_SUFFIX = "TEST";
    @Autowired
    private AttachmentService attachmentService;

    @Autowired
    private UserService userService;

    @Autowired
    private PdfAValidator pdfAValidator;

    @Autowired
    private ClientService clientService;

    public List<SystemMessage> validate(Claim claim, List<Doc> docs) {
        List<Attachment> attachmentDtoList = attachmentService.getClaimAttachments(claim.getId());
        if (isEmpty(attachmentDtoList)) {
            return emptyList();
        }

        Long clientId = userService.getCurrentClientId();

        List<SystemMessage> validationErrors = new ArrayList<>();
        for (Attachment attachment : attachmentDtoList) {
            File file = attachmentService.getAttachmentFile(attachment);
            if (file.exists()) {
                validationErrors.addAll(verifyAttachmentOnSubmit(clientId, file, attachment, docs));
            }
        }

        return validationErrors;
    }

    public List<SystemMessage> verifyAttachmentOnSubmit(Long clientId, File file, Attachment attachment, List<Doc> claimDocs) {
        List<DocType> documentTypes = emptyIfNull(claimDocs).stream()
                                                            .map(Doc::getType)
                                                            .collect(Collectors.toList());

        return verifyFileByClient(file, attachment.getOriginalName(), documentTypes, clientId, true);
    }

    private List<Doc> emptyIfNull(List<Doc> claimDocs) {
        return claimDocs == null ? emptyList() : claimDocs;
    }

    public List<SystemMessage> verifyFileByClient(File file, String originalName, List<DocType> documentTypes, Long clientId, boolean formSubmit) {
        ClientConfig config = clientService.getClientConfig(clientId);
        boolean shouldValidate = config != null
                                         && !CollectionUtils.isEmpty(documentTypes)
                                         && !CollectionUtils.isEmpty(config.getValidatedDocs())
                                         && CollectionUtils.containsAny(config.getValidatedDocs(), documentTypes);

        if (shouldValidate) {
            ArrayList<SystemMessage> validationResult = new ArrayList<>();

            String allowedExtensions = config.getAllowedExtensions();
            if (!StringUtils.isEmpty(allowedExtensions)) {
                SystemMessage message = verifyExtensions(originalName, Arrays.asList(allowedExtensions.split(",")), formSubmit);
                addIfPresent(validationResult, message);
            }

            Long sizeLimit = config.getSizeLimit();
            if (sizeLimit != null) {
                SystemMessage message = verifySize(file, originalName, sizeLimit, formSubmit);
                addIfPresent(validationResult, message);
            }

            String pdfaFormat = config.getPdfaFormat();
            if (originalName.toLowerCase().endsWith(PDF_SUFFIX) && !StringUtils.isEmpty(pdfaFormat)) {
                SystemMessage message = pdfAValidator.validate(file, originalName, Arrays.asList(pdfaFormat.split(",")), formSubmit);
                addIfPresent(validationResult, message);
            }

            return validationResult;
        }

        return Collections.emptyList();
    }


    private SystemMessage verifyExtensions(String originalName, List<String> allowedExtensions, boolean formSubmit) {
        if (!isValidExtension(originalName, allowedExtensions)) {
            return new SystemMessage(
                    SystemMessage.SystemMessageType.ERROR,
                    formSubmit ? "error.file.type.not.allowed.submit" : "error.file.type.not.allowed",
                    originalName,
                    String.join(", ", allowedExtensions));
        }

        return null;
    }

    private boolean isValidExtension(String originalName, List<String> allowedExtensions) {
        return true;
    }

    private SystemMessage verifySize(File file, String originalName, Long sizeLimit, boolean formSubmit) {
        if (sizeLimit != null && fileIsValid(file)) {
            return new SystemMessage(
                    SystemMessage.SystemMessageType.ERROR,
                    formSubmit ? "error.file.size.exceeds.limit.submit" : "error.file.size.exceeds.limit",
                    originalName,
                    String.valueOf(sizeLimit));
        }

        return null;
    }

    private boolean fileIsValid(File file) {
        return true;
    }

    private void addIfPresent(List<SystemMessage> list, SystemMessage message) {
        if (message != null) {
            list.add(message);
        }
    }
}
