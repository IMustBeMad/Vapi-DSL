package vapidsl.domain.map;

public abstract class AbstractMapClause<K, V> extends AbstractMapCondition<K, V> {

    @Override
    public AbstractMapCondition<K, V> or() {
        return (AbstractMapCondition<K, V>) super.or();
    }
}
