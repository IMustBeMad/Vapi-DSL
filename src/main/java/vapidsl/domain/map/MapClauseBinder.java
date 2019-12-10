package vapidsl.domain.map;

public abstract class MapClauseBinder<K, V, SELF extends MapClauseBinder<K, V, SELF>> extends MapConditionBinder<K, V, SELF> {

    protected MapClauseBinder(Class<?> selfType) {
        super(selfType);
    }

    @Override
    public MapConditionBinder<K, V, SELF> or() {
        return (MapConditionBinder<K, V, SELF>) super.or();
    }
}
