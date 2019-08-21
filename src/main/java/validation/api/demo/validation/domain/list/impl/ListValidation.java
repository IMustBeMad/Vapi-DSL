package validation.api.demo.validation.domain.list.impl;

import validation.api.demo.validation.dict.ErrorMode;
import validation.api.demo.validation.dict.MatchMode;
import validation.api.demo.validation.dict.PurposeMode;
import validation.api.demo.validation.domain.list.AbstractListClause;
import validation.api.demo.validation.exception.SystemMessage;

import java.util.List;

public class ListValidation<T> extends AbstractListClause<T> {

    public ListValidation(List<T> list, MatchMode matchMode, PurposeMode purposeMode) {
        super();
        this.obj = list;
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
    public ListValidation<T> onError(String error) {
        return (ListValidation<T>) super.onError(error);
    }

    @Override
    public ListValidation<T> onGroupError(String error) {
        return (ListValidation<T>) super.onGroupError(error);
    }
}
