package vapidsl.domain.number.bigdecimal.impl;

import vapidsl.common.ValidationError;
import vapidsl.dict.ErrorMode;
import vapidsl.dict.MatchMode;
import vapidsl.dict.PurposeMode;
import vapidsl.domain.number.bigdecimal.BigDecimalClauseBinder;

import java.math.BigDecimal;
import java.util.List;

public class BigDecimalValidation extends BigDecimalClauseBinder {

    public BigDecimalValidation(BigDecimal value, MatchMode matchMode, PurposeMode purposeMode) {
        super(BigDecimalValidation.class);
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
    public BigDecimalValidation onError(String error) {
        return super.onError(error);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public BigDecimalValidation onError(String field, String error) {
        return super.onError(field, error);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public BigDecimalValidation groupError(String error) {
        return super.groupError(error);
    }
}
