package vapidsl.domain.object.impl;

import vapidsl.common.ValidationError;
import vapidsl.dict.ErrorMode;
import vapidsl.dict.MatchMode;
import vapidsl.dict.PurposeMode;
import vapidsl.domain.object.ObjectClauseBinder;

import java.util.List;

public class ObjectValidation<T> extends ObjectClauseBinder<T, ObjectValidation<T>> {

    public ObjectValidation(T obj, MatchMode matchMode, PurposeMode purposeMode) {
        super(ObjectValidation.class);
        this.obj = obj;
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
    public ObjectValidation<T> onError(String error) {
        return super.onError(error);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ObjectValidation<T> onError(String field, String error) {
        return super.onError(field, error);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ObjectValidation<T> groupError(String error) {
        return super.groupError(error);
    }
}
