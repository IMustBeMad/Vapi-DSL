package vapidsl.domain.object;

public abstract class AbstractObjectClause<T, SELF extends AbstractObjectClause<T, SELF>> extends AbstractObjectCondition<T, SELF> {

    protected AbstractObjectClause(Class<?> selfType) {
        super(selfType);
    }

    @Override
    public AbstractObjectCondition<T, SELF> or() {
       return (AbstractObjectCondition<T, SELF>) super.or();
    }
}
