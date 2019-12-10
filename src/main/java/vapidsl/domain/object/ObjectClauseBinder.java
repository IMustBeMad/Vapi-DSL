package vapidsl.domain.object;

public abstract class ObjectClauseBinder<T, SELF extends ObjectClauseBinder<T, SELF>> extends ObjectConditionBinder<T, SELF> {

    protected ObjectClauseBinder(Class<?> selfType) {
        super(selfType);
    }

    @Override
    public ObjectConditionBinder<T, SELF> or() {
       return (ObjectConditionBinder<T, SELF>) super.or();
    }
}
