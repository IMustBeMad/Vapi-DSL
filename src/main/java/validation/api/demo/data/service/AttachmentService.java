package validation.api.demo.data.service;

import org.springframework.stereotype.Service;
import validation.api.demo.data.common.Attachment;

import java.io.File;
import java.util.Collections;
import java.util.List;

@Service
public class AttachmentService {

    public List<Attachment> getClaimAttachments(Long id) {
        return Collections.emptyList();
    }

    public File getAttachmentFile(Attachment attachment) {
        return null;
    }
}
