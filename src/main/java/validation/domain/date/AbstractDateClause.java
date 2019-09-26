package validation.domain.date;

public abstract class AbstractDateClause extends AbstractDateCondition {

    @Override
    public AbstractDateCondition or() {
        return (AbstractDateCondition) super.or();
    }
}
