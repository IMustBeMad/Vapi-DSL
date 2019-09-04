package validation.api.demo.validation.dict;

import validation.api.demo.validation.common.ConditionCluster;
import validation.api.demo.validation.domain.AbstractBaseValidation;

/**
 * Is used for cases when validation has {@link AbstractBaseValidation#isNotNull()} condition in {@link ConditionCluster}
 * and the object that is being validated is actually a {@code null} but there are more conditions in cluster
 * that can result in {@link NullPointerException}. To avoid such cases {@code EARLY_EXIT} is used.
 * <p>
 * Note: since all conditions are lazy, the next check after {@link AbstractBaseValidation#isNotNull()}
 * will no be performed, if the object that is being validated is {@code null}.
 */
public enum FlowType {

    /**
     * Standard flow. All check will be performed, as expected.
     */
    COMMON,

    /**
     * If validation has {@link AbstractBaseValidation#isNotNull()} condition and value is being
     * checked is {@code null} then this current error will be returned without performing further checks.
     */
    EARLY_EXIT
}
