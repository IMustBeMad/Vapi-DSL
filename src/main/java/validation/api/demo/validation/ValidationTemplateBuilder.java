package validation.api.demo.validation;

import validation.api.demo.validation.domain.object.impl.ObjectTemplateBuilder;

public class ValidationTemplateBuilder {

    public static ObjectTemplateBuilder forObject() {
        return new ObjectTemplateBuilder();
    }
}
