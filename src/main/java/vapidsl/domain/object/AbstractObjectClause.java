package vapidsl.domain.object;

import vapidsl.domain.object.impl.ObjectValidation;

public abstract class AbstractObjectClause<T, SELF extends ObjectValidation<T, SELF>> extends AbstractObjectCondition<T, SELF> {

    protected AbstractObjectClause(Class<?> selfType) {
        super(selfType);
    }

    @Override
    public AbstractObjectCondition<T, SELF> or() {
       return (AbstractObjectCondition<T, SELF>) super.or();
    }
}
