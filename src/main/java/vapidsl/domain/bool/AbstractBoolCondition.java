package vapidsl.domain.bool;

import vapidsl.domain.AbstractBaseValidation;
import vapidsl.domain.bool.impl.BoolValidation;

public abstract class AbstractBoolCondition extends AbstractBaseValidation<Boolean, BoolValidation> {

    AbstractBoolCondition(Class<?> selfType) {
        super(selfType);
    }

    public BoolValidation isTrue() {
        this.registerCondition(BoolConditions.isTrue());

        return self;
    }

    public BoolValidation isFalse() {
        this.registerCondition(BoolConditions.isFalse());

        return self;
    }

    @Override
    public BoolValidation isNull() {
        return super.isNull();
    }

    @Override
    public BoolValidation isNotNull() {
        return super.isNotNull();
    }
}
