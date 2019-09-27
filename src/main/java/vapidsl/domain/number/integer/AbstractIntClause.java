package vapidsl.domain.number.integer;

public abstract class AbstractIntClause extends AbstractIntCondition {

    @Override
    protected AbstractIntCondition or() {
        return (AbstractIntCondition) super.or();
    }
}
