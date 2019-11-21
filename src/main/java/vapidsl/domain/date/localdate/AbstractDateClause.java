package vapidsl.domain.date.localdate;

public abstract class AbstractDateClause extends AbstractDateCondition {

    protected AbstractDateClause(Class<?> selfType) {
        super(selfType);
    }

    @Override
    public AbstractDateCondition or() {
        return (AbstractDateCondition) super.or();
    }
}
