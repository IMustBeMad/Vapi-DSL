package validation.domain.number.biginteger.impl;

import validation.dict.ErrorMode;
import validation.dict.MatchMode;
import validation.dict.PurposeMode;
import validation.domain.number.biginteger.AbstractLongClause;
import validation.exception.SystemMessage;

import java.util.List;

public class LongValidation extends AbstractLongClause {

    public LongValidation(Long value, MatchMode matchMode, PurposeMode purposeMode) {
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
    public LongValidation onError(String error) {
        return (LongValidation) super.onError(error);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LongValidation groupError(String error) {
        return (LongValidation) super.groupError(error);
    }
}