package validation.api.demo.validation.domain.array.impl;

import validation.api.demo.validation.dict.ErrorMode;
import validation.api.demo.validation.dict.MatchMode;
import validation.api.demo.validation.dict.PurposeMode;
import validation.api.demo.validation.domain.array.AbstractArrayClause;
import validation.api.demo.validation.common.ValidationError;

import java.util.List;

public class ArrayValidation<T> extends AbstractArrayClause<T> {

    public ArrayValidation(T[] array, MatchMode matchMode, PurposeMode purposeMode) {
        super();
        this.obj = array;
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
    public ArrayValidation<T> onError(String error) {
        return (ArrayValidation<T>) super.onError(error);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ArrayValidation<T> groupError(String error) {
        return (ArrayValidation<T>) super.groupError(error);
    }
}
