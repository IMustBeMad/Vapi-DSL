package validation.api.demo.data.common;

import lombok.Data;

import java.util.List;

@Data
public class ClientConfig {

    private List<Doc> validatedDocs;
    private String allowedExtensions;
    private Long sizeLimit;
    private String pdfaFormat;
}
