package vapidsl.domain.string;

public abstract class AbstractStringClause extends AbstractStringCondition {

    protected AbstractStringClause(Class<?> selfType) {
        super(selfType);
    }

    @Override
    public AbstractStringCondition or() {
        return (AbstractStringCondition) super.or();
    }
}
