package vapidsl.domain.date.impl;

import vapidsl.common.ValidationError;
import vapidsl.dict.ErrorMode;
import vapidsl.dict.MatchMode;
import vapidsl.dict.PurposeMode;
import vapidsl.domain.date.AbstractDateClause;

import java.time.LocalDate;
import java.util.List;

public class DateValidation extends AbstractDateClause {

    public DateValidation(LocalDate date, MatchMode matchMode, PurposeMode purposeMode) {
        super();
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
        return (DateValidation) super.onError(error);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public DateValidation onError(String field, String error) {
        return (DateValidation) super.onError(field, error);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public DateValidation groupError(String error) {
        return (DateValidation) super.groupError(error);
    }
}