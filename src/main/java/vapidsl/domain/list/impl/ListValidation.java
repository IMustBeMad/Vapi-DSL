package vapidsl.domain.list.impl;

import vapidsl.dict.ErrorMode;
import vapidsl.dict.MatchMode;
import vapidsl.dict.PurposeMode;
import vapidsl.domain.list.ListClauseBinder;
import vapidsl.common.ValidationError;
import vapidsl.domain.Binder;

import java.util.List;

public class ListValidation<T> extends ListClauseBinder<T, ListValidation<T>> {

    public ListValidation(List<T> list, MatchMode matchMode, PurposeMode purposeMode) {
        super(ListValidation.class);
        this.obj = list;
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
    public ListValidation<T> onError(String error) {
        return super.onError(error);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ListValidation<T> onError(String field, String error) {
        return super.onError(field, error);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ListValidation<T> groupError(String error) {
        return super.groupError(error);
    }
}
