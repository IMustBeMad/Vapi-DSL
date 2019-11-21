package vapidsl.domain.date.localdate.impl;

import vapidsl.common.ValidationError;
import vapidsl.dict.ErrorMode;
import vapidsl.dict.MatchMode;
import vapidsl.dict.PurposeMode;
import vapidsl.domain.date.localdate.AbstractDateClause;

import java.time.LocalDate;
import java.util.List;

public class DateValidation extends AbstractDateClause {

    public DateValidation(LocalDate date, MatchMode matchMode, PurposeMode purposeMode) {
        super(DateValidation.class);
        this.obj = date;
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
    public DateValidation onError(String error) {
        return super.onError(error);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public DateValidation onError(String field, String error) {
        return super.onError(field, error);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public DateValidation groupError(String error) {
        return super.groupError(error);
    }
}
