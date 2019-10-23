package vapidsl.domain.number.bigdecimal;

public class AbstractBigDecimalClause extends AbstractBigDecimalCondition {

    protected AbstractBigDecimalClause(Class<?> selfType) {
        super(selfType);
    }

    @Override
    public AbstractBigDecimalClause or() {
        return (AbstractBigDecimalClause) super.or();
    }
}
