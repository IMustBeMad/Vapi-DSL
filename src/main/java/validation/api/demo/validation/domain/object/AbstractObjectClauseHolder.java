package validation.api.demo.validation.domain.object;

public abstract class AbstractObjectClauseHolder<T> extends AbstractObjectConditionHolder<T> {

    @Override
    public AbstractObjectConditionHolder<T> or() {
       return (AbstractObjectConditionHolder<T>) super.or();
    }
}
