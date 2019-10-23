package vapidsl.domain.array;

public abstract class AbstractArrayClause<T, SELF extends AbstractArrayClause<T, SELF>> extends AbstractArrayCondition<T, SELF> {

    protected AbstractArrayClause(Class<?> selfType) {
        super(selfType);
    }

    @Override
    public AbstractArrayCondition<T, SELF> or() {
        return (AbstractArrayCondition<T, SELF>) super.or();
    }
}
