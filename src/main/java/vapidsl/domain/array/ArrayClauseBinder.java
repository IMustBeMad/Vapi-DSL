package vapidsl.domain.array;

public abstract class ArrayClauseBinder<T, SELF extends ArrayClauseBinder<T, SELF>> extends ArrayConditionBinder<T, SELF> {

    protected ArrayClauseBinder(Class<?> selfType) {
        super(selfType);
    }

    @Override
    public ArrayConditionBinder<T, SELF> or() {
        return (ArrayConditionBinder<T, SELF>) super.or();
    }
}
