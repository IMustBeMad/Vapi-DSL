package vapidsl.domain.bool;

public abstract class AbstractBoolClause extends AbstractBoolCondition {

    protected AbstractBoolClause(Class<?> selfType) {
        super(selfType);
    }

    @Override
    public AbstractBoolCondition or() {
        return (AbstractBoolCondition) super.or();
    }
}
