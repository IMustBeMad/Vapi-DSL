package validation.api.demo.validation.domain.date.impl;

import validation.api.demo.validation.dict.*;
import validation.api.demo.validation.exception.SystemMessage;
import validation.api.demo.validation.domain.date.AbstractDateClause;

import java.time.LocalDate;
import java.util.List;

public class DateValidation extends AbstractDateClause {

    public DateValidation(LocalDate date, MatchMode matchMode, PurposeMode purposeMode) {
        super();
        this.obj = date;
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
    public DateValidation onError(String error) {
        return (DateValidation) super.onError(error);
    }

    @Override
    public DateValidation groupError(String error) {
        return (DateValidation) super.groupError(error);
    }
}
