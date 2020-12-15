package vapidsl.domain.list;

public abstract class ListClauseBinder<T, SELF extends ListClauseBinder<T, SELF>> extends ListConditionBinder<T, SELF> {

    protected ListClauseBinder(Class<?> selfType) {
        super(selfType);
    }

    @Override
    public ListConditionBinder<T, SELF> or() {
        return (ListConditionBinder<T, SELF>) super.or();
    }
}
