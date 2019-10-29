package vapidsl.domain.array;

import vapidsl.common.Condition;
import vapidsl.common.LinkedCondition;
import vapidsl.common.SingleCondition;
import vapidsl.dict.Clause;
import vapidsl.domain.AbstractBaseValidation;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public abstract class AbstractArrayCondition<T, SELF extends AbstractArrayCondition<T, SELF>> extends AbstractBaseValidation<T[], SELF> {

    AbstractArrayCondition(Class<?> selfType) {
        super(selfType);
    }

    public SELF contains(T element) {
        this.registerCondition(ArrayConditions.contains(element));

        return self;
    }

    public SELF ofSize(int size) {
        this.registerCondition(ArrayConditions.ofSize(size));

        return self;
    }

    public SELF isEmpty() {
        this.registerCondition(ArrayConditions.isEmpty());

        return self;
    }

    public SELF isNotEmpty() {
        this.registerCondition(ArrayConditions.isNotEmpty());

        return self;
    }

    public SELF every(Supplier<SingleCondition<T>> condition) {
        LinkedCondition<T[]> linkedCondition = new LinkedCondition<>(() -> this.toSerialCondition(condition.get().getPredicate()), Clause.AND);
        this.registerCondition(linkedCondition);

        return self;
    }

    public <OTHER extends AbstractBaseValidation<T, OTHER>> SELF every(Function<T, AbstractBaseValidation<T, OTHER>> validator) {
        LinkedCondition<T[]> linkedCondition = new LinkedCondition<>(() -> this.toSerialCondition(validator), Clause.AND);
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
    public SELF isEqualTo(T[] otherObj) {
        return super.isEqualTo(otherObj);
    }

    @Override
    public SELF isNotEqualTo(T[] otherObj) {
        return super.isNotEqualTo(otherObj);
    }

    @Override
    public SELF withTerm(Predicate<T[]> predicate) {
        return super.withTerm(predicate);
    }

    @Override
    public SELF withTerm(Supplier<Boolean> supplier) {
        return super.withTerm(supplier);
    }

    @Override
    @SafeVarargs
    public final SELF satisfiesAny(SingleCondition<T[]>... conditions) {
        return super.satisfiesAny(conditions);
    }

    @Override
    @SafeVarargs
    public final SELF satisfiesAll(SingleCondition<T[]>... conditions) {
        return super.satisfiesAll(conditions);
    }

    @Override
    public SELF log(String msg, Object... values) {
        return super.log(msg, values);
    }

    @Override
    public <R> SELF inspecting(Function<T[], R> mapper, Predicate<R> predicate) {
        return super.inspecting(mapper, predicate);
    }

    @Override
    public <R> SELF inspecting(Function<T[], R> mapper, Supplier<SingleCondition<R>> condition) {
        return super.inspecting(mapper, condition);
    }

    @Override
    public <R, OTHER extends AbstractBaseValidation<R, OTHER>> SELF deepInspecting(Function<T[], R> mapper, Function<R, AbstractBaseValidation<R, OTHER>> validator) {
        return super.deepInspecting(mapper, validator);
    }

    private <OTHER extends AbstractBaseValidation<T, OTHER>> List<Condition<T[]>> toSerialCondition(Function<T, AbstractBaseValidation<T, OTHER>> validator) {
        return Arrays.stream(this.obj)
                     .map(it -> this.toCondition(() -> validator.apply(it)))
                     .collect(Collectors.toList());
    }

    private List<Condition<T[]>> toSerialCondition(Predicate<T> predicate) {
        return Arrays.stream(this.obj)
                     .map(it -> this.toCondition(it, predicate))
                     .collect(Collectors.toList());
    }
}
