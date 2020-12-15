package vapidsl.domain.number.bigdecimal;

public abstract class BigDecimalClauseBinder extends BigDecimalConditionBinder {

    protected BigDecimalClauseBinder(Class<?> selfType) {
        super(selfType);
    }

    @Override
    public BigDecimalClauseBinder or() {
        return (BigDecimalClauseBinder) super.or();
    }
}
