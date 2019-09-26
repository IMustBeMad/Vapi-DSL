package validation.domain.object;

public abstract class AbstractObjectClause<T> extends AbstractObjectCondition<T> {

    @Override
    public AbstractObjectCondition<T> or() {
       return (AbstractObjectCondition<T>) super.or();
    }
}
