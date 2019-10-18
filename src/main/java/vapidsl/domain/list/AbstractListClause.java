package vapidsl.domain.list;

import vapidsl.domain.list.impl.ListValidation;

public abstract class AbstractListClause<T, SELF extends ListValidation<T, SELF>> extends AbstractListCondition<T, SELF> {

    protected AbstractListClause(Class<?> selfType) {
        super(selfType);
    }

    @Override
    public AbstractListCondition<T, SELF> or() {
        return (AbstractListCondition<T, SELF>) super.or();
    }
}
