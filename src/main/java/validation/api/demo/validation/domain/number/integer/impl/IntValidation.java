package validation.api.demo.validation.domain.number.integer.impl;

import validation.api.demo.validation.domain.number.integer.AbstractIntClause;
import validation.api.demo.validation.exception.SystemMessage;

import java.util.List;

public class IntValidation extends AbstractIntClause {

    public IntValidation(Integer integer) {
        super();
        this.obj = integer;
    }

    @Override
    public List<SystemMessage> examine() {
        return super.examine();
    }

    @Override
    public IntValidation onError(String error) {
        return (IntValidation) super.onError(error);
    }

    @Override
    public IntValidation onGroupError(String error) {
        return (IntValidation) super.onGroupError(error);
    }
}
