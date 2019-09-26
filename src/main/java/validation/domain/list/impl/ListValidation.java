package validation.domain.list.impl;

import validation.dict.ErrorMode;
import validation.dict.MatchMode;
import validation.dict.PurposeMode;
import validation.domain.list.AbstractListClause;
import validation.common.ValidationError;
import validation.domain.BaseDataHolder;

import java.util.List;

public class ListValidation<T> extends AbstractListClause<T> {

    public ListValidation(List<T> list, MatchMode matchMode, PurposeMode purposeMode) {
        super();
        this.obj = list;
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
    public ListValidation<T> onError(String error) {
        return (ListValidation<T>) super.onError(error);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ListValidation<T> groupError(String error) {
        return (ListValidation<T>) super.groupError(error);
    }
}
