package vapidsl.domain.map;

import vapidsl.domain.map.impl.MapValidation;

public abstract class AbstractMapClause<K, V, SELF extends AbstractMapClause<K, V, SELF>> extends AbstractMapCondition<K, V, SELF> {

    protected AbstractMapClause(Class<?> selfType) {
        super(selfType);
    }

    @Override
    public AbstractMapCondition<K, V, SELF> or() {
        return (AbstractMapCondition<K, V, SELF>) super.or();
    }
}
