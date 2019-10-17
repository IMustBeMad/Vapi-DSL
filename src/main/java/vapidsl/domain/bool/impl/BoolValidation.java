package vapidsl.domain.bool.impl;

import vapidsl.common.ValidationError;
import vapidsl.dict.ErrorMode;
import vapidsl.dict.MatchMode;
import vapidsl.dict.PurposeMode;
import vapidsl.domain.BaseDataHolder;
import vapidsl.domain.bool.AbstractBoolClause;

import java.util.List;

public class BoolValidation extends AbstractBoolClause {

    public BoolValidation(Boolean obj, MatchMode matchMode, PurposeMode purposeMode) {
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
    public BoolValidation onError(String error) {
        return (BoolValidation) super.onError(error);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public BoolValidation onError(String field, String error) {
        return (BoolValidation) super.onError(field, error);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public BoolValidation groupError(String error) {
        return (BoolValidation) super.groupError(error);
    }
}
