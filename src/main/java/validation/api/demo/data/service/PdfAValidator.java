package validation.api.demo.data.service;

import org.springframework.stereotype.Service;
import validation.api.demo.data.exception.SystemMessage;

import java.io.File;
import java.util.List;

@Service
public class PdfAValidator {

    public SystemMessage validate(File file, String originalName, List<String> asList, boolean formSubmit) {
        return null;
    }
}
