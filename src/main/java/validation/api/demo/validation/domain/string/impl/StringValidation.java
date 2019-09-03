package validation.api.demo.validation.domain.string.impl;

import validation.api.demo.validation.dict.ErrorMode;
import validation.api.demo.validation.dict.MatchMode;
import validation.api.demo.validation.dict.PurposeMode;
import validation.api.demo.validation.domain.string.AbstractStringClause;
import validation.api.demo.validation.exception.SystemMessage;

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

    /**
     * {@inheritDoc}
     */
    @Override
    public StringValidation onError(String error) {
        return (StringValidation) super.onError(error);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public StringValidation groupError(String error) {
        return (StringValidation) super.groupError(error);
    }
}
