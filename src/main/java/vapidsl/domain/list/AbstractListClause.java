package vapidsl.domain.list;

public abstract class AbstractListClause<T> extends AbstractListCondition<T> {

    @Override
    public AbstractListCondition<T> or() {
        return (AbstractListCondition<T>) super.or();
    }
}
