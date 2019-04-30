package validation.api.demo.validation.domain.number;

public abstract class AbstractLongClause extends AbstractLongCondition {

    @Override
    public AbstractLongCondition or() {
        return (AbstractLongCondition) super.or();
    }
}
