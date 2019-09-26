package validation.domain.number.integer.impl;

import validation.dict.ErrorMode;
import validation.dict.MatchMode;
import validation.dict.PurposeMode;
import validation.domain.number.integer.AbstractIntClause;
import validation.exception.SystemMessage;

import java.util.List;

public class IntValidation extends AbstractIntClause {

    public IntValidation(Integer value, MatchMode matchMode, PurposeMode purposeMode) {
        super();
        this.obj = value;
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
    public IntValidation onError(String error) {
        return (IntValidation) super.onError(error);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public IntValidation groupError(String error) {
        return (IntValidation) super.groupError(error);
    }
}
