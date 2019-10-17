package vapidsl.domain.map.impl;

import vapidsl.common.ValidationError;
import vapidsl.dict.ErrorMode;
import vapidsl.dict.MatchMode;
import vapidsl.dict.PurposeMode;
import vapidsl.domain.BaseDataHolder;
import vapidsl.domain.map.AbstractMapClause;

import java.util.List;
import java.util.Map;

public class MapValidation<K, V> extends AbstractMapClause<K, V> {

    public MapValidation(Map<K, V> map, MatchMode matchMode, PurposeMode purposeMode) {
        super();
        this.obj = map;
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
    public MapValidation<K, V> onError(String error) {
        return (MapValidation<K, V>) super.onError(error);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public MapValidation<K, V> onError(String field, String error) {
        return (MapValidation<K, V>) super.onError(field, error);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public MapValidation<K, V> groupError(String error) {
        return (MapValidation<K, V>) super.groupError(error);
    }
}
