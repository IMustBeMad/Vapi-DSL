package vapidsl.domain.date.localdate;

public abstract class LocalDateClauseBinder extends LocalDateConditionBinder {

    protected LocalDateClauseBinder(Class<?> selfType) {
        super(selfType);
    }

    @Override
    public LocalDateConditionBinder or() {
        return (LocalDateConditionBinder) super.or();
    }
}
