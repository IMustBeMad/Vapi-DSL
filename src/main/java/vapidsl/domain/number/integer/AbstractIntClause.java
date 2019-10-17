package vapidsl.domain.number.integer;

public abstract class AbstractIntClause extends AbstractIntCondition {

    protected AbstractIntClause(Class<?> selfType) {
        super(selfType);
    }

    @Override
    protected AbstractIntCondition or() {
        return (AbstractIntCondition) super.or();
    }
}
