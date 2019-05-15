package validation.api.demo.validation.domain.string.impl;

import validation.api.demo.exception.SystemMessage;
import validation.api.demo.validation.domain.string.AbstractStringClause;

import java.util.List;

public class StringValidation extends AbstractStringClause {

    public StringValidation(String value) {
        super();
        this.obj = value;
    }

    @Override
    public List<SystemMessage> examine() {
        return super.examine();
    }

    @Override
    public StringValidation onError(String error) {
        return ((StringValidation) super.onError(error));
    }
}
