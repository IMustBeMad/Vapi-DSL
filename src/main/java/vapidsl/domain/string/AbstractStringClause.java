package vapidsl.domain.string;

public abstract class AbstractStringClause extends AbstractStringCondition {

    @Override
    public AbstractStringCondition or() {
        return (AbstractStringCondition) super.or();
    }
}
