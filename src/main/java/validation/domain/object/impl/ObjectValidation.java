package validation.domain.object.impl;

import validation.dict.ErrorMode;
import validation.dict.MatchMode;
import validation.dict.PurposeMode;
import validation.domain.object.AbstractObjectClause;
import validation.exception.SystemMessage;

import java.util.List;

public class ObjectValidation<T> extends AbstractObjectClause<T> {

    public ObjectValidation(T obj, MatchMode matchMode, PurposeMode purposeMode) {
        super();
        this.obj = obj;
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

    /**
     * {@inheritDoc}
     */
    @Override
    public ObjectValidation<T> onError(String error) {
        return (ObjectValidation<T>) super.onError(error);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ObjectValidation<T> groupError(String error) {
        return (ObjectValidation<T>) super.groupError(error);
    }
}
