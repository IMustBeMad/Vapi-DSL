package vapidsl.domain.number.integer;

public abstract class IntClauseBinder extends IntConditionBinder {

    protected IntClauseBinder(Class<?> selfType) {
        super(selfType);
    }

    @Override
    public IntConditionBinder or() {
        return (IntConditionBinder) super.or();
    }
}
