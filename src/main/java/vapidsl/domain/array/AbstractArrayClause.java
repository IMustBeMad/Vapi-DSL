package vapidsl.domain.array;

import vapidsl.domain.array.impl.ArrayValidation;

public abstract class AbstractArrayClause<T, SELF extends ArrayValidation<T, SELF>> extends AbstractArrayCondition<T, SELF> {

    protected AbstractArrayClause(Class<?> selfType) {
        super(selfType);
    }

    @Override
    public AbstractArrayCondition<T, SELF> or() {
        return (AbstractArrayCondition<T, SELF>) super.or();
    }
}
