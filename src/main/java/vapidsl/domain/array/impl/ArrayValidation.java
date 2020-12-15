package vapidsl.domain.array.impl;

import vapidsl.dict.ErrorMode;
import vapidsl.dict.MatchMode;
import vapidsl.dict.PurposeMode;
import vapidsl.domain.array.ArrayClauseBinder;
import vapidsl.common.ValidationError;
import vapidsl.domain.Binder;

import java.util.List;

public class ArrayValidation<T> extends ArrayClauseBinder<T, ArrayValidation<T>> {

    public ArrayValidation(T[] array, MatchMode matchMode, PurposeMode purposeMode) {
        super(ArrayValidation.class);
        this.obj = array;
        this.modeManager = new Binder.ModeManager(matchMode, purposeMode);
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
    public ArrayValidation<T> onError(String error) {
        return super.onError(error);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ArrayValidation<T> onError(String field, String error) {
        return super.onError(field, error);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ArrayValidation<T> groupError(String error) {
        return super.groupError(error);
    }
}
