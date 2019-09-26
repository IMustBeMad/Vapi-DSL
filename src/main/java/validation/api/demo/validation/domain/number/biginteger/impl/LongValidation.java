package validation.api.demo.validation.domain.number.biginteger.impl;

import validation.api.demo.validation.dict.*;
import validation.api.demo.validation.common.ValidationError;
import validation.api.demo.validation.domain.number.biginteger.AbstractLongClause;

import java.util.List;

public class LongValidation extends AbstractLongClause {

    public LongValidation(Long value, MatchMode matchMode, PurposeMode purposeMode) {
        super();
        this.obj = value;
        this.modeManager = new ModeManager(matchMode, purposeMode);
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
