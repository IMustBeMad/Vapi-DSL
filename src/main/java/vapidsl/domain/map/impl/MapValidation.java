package vapidsl.domain.map.impl;

import vapidsl.common.ValidationError;
import vapidsl.dict.ErrorMode;
import vapidsl.dict.MatchMode;
import vapidsl.dict.PurposeMode;
import vapidsl.domain.BaseDataHolder;
import vapidsl.domain.map.AbstractMapClause;

import java.util.List;
import java.util.Map;

public class MapValidation<K, V, SELF extends MapValidation<K, V, SELF>> extends AbstractMapClause<K, V, SELF> {

    public MapValidation(Map<K, V> map, MatchMode matchMode, PurposeMode purposeMode) {
        super(MapValidation.class);
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
