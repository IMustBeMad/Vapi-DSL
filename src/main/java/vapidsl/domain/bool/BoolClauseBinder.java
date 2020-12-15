package vapidsl.domain.bool;

public abstract class BoolClauseBinder extends BoolConditionBinder {

    protected BoolClauseBinder(Class<?> selfType) {
        super(selfType);
    }

    @Override
    public BoolConditionBinder or() {
        return (BoolConditionBinder) super.or();
    }
}
