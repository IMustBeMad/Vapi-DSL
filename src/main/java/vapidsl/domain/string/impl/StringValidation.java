package vapidsl.domain.string.impl;

import vapidsl.dict.ErrorMode;
import vapidsl.dict.MatchMode;
import vapidsl.dict.PurposeMode;
import vapidsl.domain.string.AbstractStringClause;
import vapidsl.common.ValidationError;
import vapidsl.domain.BaseDataHolder;

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
    public StringValidation onError(String field, String error) {
        return (StringValidation) super.onError(field, error);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public StringValidation groupError(String error) {
        return (StringValidation) super.groupError(error);
    }
}
