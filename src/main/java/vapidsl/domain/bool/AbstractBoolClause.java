package vapidsl.domain.bool;

public abstract class AbstractBoolClause extends AbstractBoolCondition {

    @Override
    public AbstractBoolCondition or() {
        return (AbstractBoolCondition) super.or();
    }
}
