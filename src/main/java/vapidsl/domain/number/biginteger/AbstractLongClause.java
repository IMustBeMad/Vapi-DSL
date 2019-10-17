package vapidsl.domain.number.biginteger;

public abstract class AbstractLongClause extends AbstractLongCondition {

    protected AbstractLongClause(Class<?> selfType) {
        super(selfType);
    }

    @Override
    public AbstractLongCondition or() {
        return (AbstractLongCondition) super.or();
    }
}
