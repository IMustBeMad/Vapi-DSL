package vapidsl.domain.date.localdate.impl;

import vapidsl.common.ValidationError;
import vapidsl.dict.ErrorMode;
import vapidsl.dict.MatchMode;
import vapidsl.dict.PurposeMode;
import vapidsl.domain.date.localdate.LocalDateClauseBinder;

import java.time.LocalDate;
import java.util.List;

public class LocalDateValidation extends LocalDateClauseBinder {

    public LocalDateValidation(LocalDate date, MatchMode matchMode, PurposeMode purposeMode) {
        super(LocalDateValidation.class);
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
    public LocalDateValidation onError(String error) {
        return super.onError(error);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LocalDateValidation onError(String field, String error) {
        return super.onError(field, error);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LocalDateValidation groupError(String error) {
        return super.groupError(error);
    }
}
