package vapidsl.domain.list.impl;

import vapidsl.dict.ErrorMode;
import vapidsl.dict.MatchMode;
import vapidsl.dict.PurposeMode;
import vapidsl.domain.list.AbstractListClause;
import vapidsl.common.ValidationError;
import vapidsl.domain.BaseDataHolder;

import java.util.List;

public class ListValidation<T, SELF extends ListValidation<T, SELF>> extends AbstractListClause<T, SELF> {

    public ListValidation(List<T> list, MatchMode matchMode, PurposeMode purposeMode) {
        super(ListValidation.class);
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
