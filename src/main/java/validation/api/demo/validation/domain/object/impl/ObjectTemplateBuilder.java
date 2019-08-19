package validation.api.demo.validation.domain.object.impl;

import validation.api.demo.validation.domain.object.AbstractObjectClause;

public class ObjectTemplateBuilder<T> extends AbstractObjectClause<T> {

    public ObjectTemplateBuilder() {
        super();
    }

    public ObjectTemplate<T> build() {
        return new ObjectTemplate<>(
                this.conditionClusters,
                this.currentCluster,
                this.currentCondition
        );
    }
}
