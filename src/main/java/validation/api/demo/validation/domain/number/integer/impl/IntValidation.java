package validation.api.demo.validation.domain.number.integer.impl;

import validation.api.demo.validation.dict.*;
import validation.api.demo.validation.domain.number.integer.AbstractIntClause;
import validation.api.demo.validation.exception.SystemMessage;

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

    @Override
    public IntValidation onError(String error) {
        return (IntValidation) super.onError(error);
    }

    @Override
    public IntValidation groupError(String error) {
        return (IntValidation) super.groupError(error);
    }
}
