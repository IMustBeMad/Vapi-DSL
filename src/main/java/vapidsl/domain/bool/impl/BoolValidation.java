package vapidsl.domain.bool.impl;

import vapidsl.common.ValidationError;
import vapidsl.dict.ErrorMode;
import vapidsl.dict.MatchMode;
import vapidsl.dict.PurposeMode;
import vapidsl.domain.Binder;
import vapidsl.domain.bool.BoolClauseBinder;

import java.util.List;

public class BoolValidation extends BoolClauseBinder {

    public BoolValidation(Boolean obj, MatchMode matchMode, PurposeMode purposeMode) {
        super(BoolValidation.class);
        this.obj = obj;
        this.modeManager = new Binder.ModeManager(matchMode, purposeMode);
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
        return super.onError(error);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public BoolValidation onError(String field, String error) {
        return super.onError(field, error);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public BoolValidation groupError(String error) {
        return super.groupError(error);
    }
}
