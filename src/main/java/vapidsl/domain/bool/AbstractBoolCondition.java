package vapidsl.domain.bool;

import vapidsl.domain.AbstractBaseValidation;
import vapidsl.domain.bool.impl.BoolValidation;

public abstract class AbstractBoolCondition extends AbstractBaseValidation<Boolean> {

    public BoolValidation isTrue() {
        this.registerCondition(BoolConditions.isTrue());

        return self();
    }

    public BoolValidation isFalse() {
        this.registerCondition(BoolConditions.isFalse());

        return self();
    }

    @Override
    public BoolValidation isNull() {
        return (BoolValidation) super.isNull();
    }

    @Override
    public BoolValidation isNotNull() {
        return (BoolValidation) super.isNotNull();
    }

    private BoolValidation self() {
        return (BoolValidation) this;
    }
}
