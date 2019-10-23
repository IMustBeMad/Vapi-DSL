package vapidsl.domain.list;

import vapidsl.common.Condition;
import vapidsl.common.LinkedCondition;
import vapidsl.common.SingleCondition;
import vapidsl.dict.Clause;
import vapidsl.domain.AbstractBaseValidation;
import vapidsl.domain.list.impl.ListValidation;

import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public abstract class AbstractListCondition<T, SELF extends ListValidation<T, SELF>> extends AbstractBaseValidation<List<T>, SELF> {

    AbstractListCondition(Class<?> selfType) {
        super(selfType);
    }

    public SELF contains(T element) {
        this.registerCondition(ListConditions.contains(element));

        return self;
    }

    public SELF ofSize(int size) {
        this.registerCondition(ListConditions.ofSize(size));

        return self;
    }

    public SELF hasNoDuplicates() {
        this.registerCondition(ListConditions.hasNoDuplicates());

        return self;
    }

    public SELF isEmpty() {
        this.registerCondition(ListConditions.isEmpty());

        return self;
    }

    public SELF isNotEmpty() {
        this.registerCondition(ListConditions.isNotEmpty());

        return self;
    }

    public SELF every(SingleCondition<T> condition) {
        LinkedCondition<List<T>> linkedCondition = new LinkedCondition<>(this.toSerialCondition(condition.getPredicate()), Clause.AND);
        this.registerCondition(linkedCondition);

        return self;
    }

    public <OTHER extends AbstractBaseValidation<T, OTHER>> SELF every(Function<T, AbstractBaseValidation<T, OTHER>> validator) {
        LinkedCondition<List<T>> linkedCondition = new LinkedCondition<>(this.toSerialCondition(validator), Clause.AND);
        this.registerCondition(linkedCondition);

        return self;
    }

    @Override
    public SELF isNull() {
        return super.isNull();
    }

    @Override
    public SELF isNotNull() {
        return super.isNotNull();
    }

    @Override
    public SELF isEqualTo(List<T> otherList) {
        return super.isEqualTo(otherList);
    }

    @Override
    public SELF isNotEqualTo(List<T> otherList) {
        return super.isNotEqualTo(otherList);
    }

    @Override
    public SELF withTerm(Predicate<List<T>> predicate) {
        return super.withTerm(predicate);
    }

    @Override
    public SELF withTerm(Supplier<Boolean> supplier) {
        return super.withTerm(supplier);
    }

    @Override
    @SafeVarargs
    public final SELF satisfiesAny(SingleCondition<List<T>>... conditions) {
        return super.satisfiesAny(conditions);
    }

    @Override
    @SafeVarargs
    public final SELF satisfiesAll(SingleCondition<List<T>>... conditions) {
        return super.satisfiesAll(conditions);
    }

    @Override
    public SELF log(String msg, Object... values) {
        return super.log(msg, values);
    }

    @Override
    public <R> SELF inspecting(Function<List<T>, R> mapper, Predicate<R> predicate) {
        return super.inspecting(mapper, predicate);
    }

    @Override
    public <R> SELF inspecting(Function<List<T>, R> mapper, Supplier<SingleCondition<R>> condition) {
        return super.inspecting(mapper, condition);
    }

    @Override
    public <R, OTHER extends AbstractBaseValidation<R, OTHER>> SELF deepInspecting(Function<List<T>, R> mapper, Function<R, AbstractBaseValidation<R, OTHER>> validator) {
        return super.deepInspecting(mapper, validator);
    }

    private <OTHER extends AbstractBaseValidation<T, OTHER>> List<Condition<List<T>>> toSerialCondition(Function<T, AbstractBaseValidation<T, OTHER>> validator) {
        return this.obj.stream()
                       .map(it -> this.toCondition(it, validator))
                       .collect(Collectors.toList());
    }

    private List<Condition<List<T>>> toSerialCondition(Predicate<T> predicate) {
        return this.obj.stream()
                       .map(it -> this.toCondition(it, predicate))
                       .collect(Collectors.toList());
    }
}
