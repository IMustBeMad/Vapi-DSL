package validation.api.demo.validation.domain.array.impl;

import validation.api.demo.validation.dict.ErrorMode;
import validation.api.demo.validation.dict.MatchMode;
import validation.api.demo.validation.dict.PurposeMode;
import validation.api.demo.validation.domain.array.AbstractArrayClause;
import validation.api.demo.validation.exception.SystemMessage;

import java.util.List;

public class ArrayValidation<T> extends AbstractArrayClause<T> {

    public ArrayValidation(T[] array, MatchMode matchMode, PurposeMode purposeMode) {
        super();
        this.obj = array;
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
    public ArrayValidation<T> onError(String error) {
        return (ArrayValidation<T>) super.onError(error);
    }

    @Override
    public ArrayValidation<T> onGroupError(String error) {
        return (ArrayValidation<T>) super.onGroupError(error);
    }
}
