package vapidsl.domain.map.impl;

import vapidsl.common.ValidationError;
import vapidsl.dict.ErrorMode;
import vapidsl.dict.MatchMode;
import vapidsl.dict.PurposeMode;
import vapidsl.domain.Binder;
import vapidsl.domain.map.MapClauseBinder;

import java.util.List;
import java.util.Map;

public class MapValidation<K, V> extends MapClauseBinder<K, V, MapValidation<K, V>> {

    public MapValidation(Map<K, V> map, MatchMode matchMode, PurposeMode purposeMode) {
        super(MapValidation.class);
        this.obj = map;
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
    public MapValidation<K, V> onError(String error) {
        return super.onError(error);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public MapValidation<K, V> onError(String field, String error) {
        return super.onError(field, error);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public MapValidation<K, V> groupError(String error) {
        return super.groupError(error);
    }
}
