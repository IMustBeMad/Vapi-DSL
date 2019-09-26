package validation.api.demo.validation.domain.list.impl;

import validation.api.demo.validation.dict.ErrorMode;
import validation.api.demo.validation.dict.MatchMode;
import validation.api.demo.validation.dict.PurposeMode;
import validation.api.demo.validation.domain.list.AbstractListClause;
import validation.api.demo.validation.common.ValidationError;

import java.util.List;

public class ListValidation<T> extends AbstractListClause<T> {

    public ListValidation(List<T> list, MatchMode matchMode, PurposeMode purposeMode) {
        super();
        this.obj = list;
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
