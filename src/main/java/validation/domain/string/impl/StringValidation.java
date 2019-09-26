package validation.domain.string.impl;

import validation.dict.ErrorMode;
import validation.dict.MatchMode;
import validation.dict.PurposeMode;
import validation.domain.string.AbstractStringClause;
import validation.common.ValidationError;
import validation.domain.BaseDataHolder;

import java.util.List;

public class StringValidation extends AbstractStringClause {

    public StringValidation(String obj, MatchMode matchMode, PurposeMode purposeMode) {
        super();
        this.obj = obj;
        this.modeManager = new BaseDataHolder.ModeManager(matchMode, purposeMode);
    }

    @Override
    public List<ValidationError> examine() {
        return super.examine();
    }

    @Override
    public List<ValidationError> examine(ErrorMode errorMode) {
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
