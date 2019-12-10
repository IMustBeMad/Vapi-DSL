package vapidsl.domain.number.biginteger;

public abstract class LongClauseBinder extends LongConditionBinder {

    protected LongClauseBinder(Class<?> selfType) {
        super(selfType);
    }

    @Override
    public LongConditionBinder or() {
        return (LongConditionBinder) super.or();
    }
}
