package validation.domain.array;

public abstract class AbstractArrayClause<T> extends AbstractArrayCondition<T> {

    @Override
    public AbstractArrayCondition<T> or() {
        return (AbstractArrayCondition<T>) super.or();
    }
}
