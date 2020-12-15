package vapidsl.domain.string;

public abstract class StringClauseBinder extends StringConditionBinder {

    protected StringClauseBinder(Class<?> selfType) {
        super(selfType);
    }

    @Override
    public StringConditionBinder or() {
        return (StringConditionBinder) super.or();
    }
}
