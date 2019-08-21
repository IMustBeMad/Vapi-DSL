package validation.api.demo.validation.domain.string.impl;

import validation.api.demo.validation.dict.*;
import validation.api.demo.validation.exception.SystemMessage;
import validation.api.demo.validation.domain.string.AbstractStringClause;

import java.util.List;

public class StringValidation extends AbstractStringClause {

    public StringValidation(String obj, MatchMode matchMode, PurposeMode purposeMode) {
        super();
        this.obj = obj;
        this.modeManager = new ModeManager(matchMode, purposeMode);
    }

    @Override
    public List<SystemMessage> examine() {
        return super.examine();
    }

    @Override
    public List<SystemMessage> examine(ErrorMode errorMode) {
        return super.examine(errorMode);
    }

    @Override
    public StringValidation onError(String error) {
        return ((StringValidation) super.onError(error));
    }

    @Override
    public StringValidation onGroupError(String error) {
        return (StringValidation) super.onGroupError(error);
    }
}
