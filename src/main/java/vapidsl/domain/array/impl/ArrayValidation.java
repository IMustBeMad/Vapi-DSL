package vapidsl.domain.array.impl;

import vapidsl.dict.ErrorMode;
import vapidsl.dict.MatchMode;
import vapidsl.dict.PurposeMode;
import vapidsl.domain.array.AbstractArrayClause;
import vapidsl.common.ValidationError;
import vapidsl.domain.BaseDataHolder;

import java.util.List;

public class ArrayValidation<T, SELF extends ArrayValidation<T, SELF>> extends AbstractArrayClause<T, SELF> {

    public ArrayValidation(T[] array, MatchMode matchMode, PurposeMode purposeMode) {
        super(ArrayValidation.class);
        this.obj = array;
        this.modeManager = new BaseDataHolder.ModeManager(matchMode, purposeMode);
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
    public SELF onError(String error) {
        return super.onError(error);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public SELF onError(String field, String error) {
        return super.onError(field, error);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public SELF groupError(String error) {
        return super.groupError(error);
    }
}
