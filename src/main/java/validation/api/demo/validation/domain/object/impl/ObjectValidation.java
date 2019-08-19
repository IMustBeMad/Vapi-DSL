package validation.api.demo.validation.domain.object.impl;

import lombok.extern.slf4j.Slf4j;
import validation.api.demo.validation.exception.SystemMessage;
import validation.api.demo.validation.domain.object.AbstractObjectClause;

import java.util.List;

@Slf4j
public class ObjectValidation<T> extends AbstractObjectClause<T> {

    public ObjectValidation(T obj) {
        super();
        this.obj = obj;
    }

    ObjectValidation(T obj, ObjectTemplate<T> template) {
        this.obj = obj;
        this.currentCondition = template.getCurrentCondition();
        this.currentCluster = template.getCurrentCluster();
        this.conditionClusters = template.getConditionClusters();
    }

    @Override
    public List<SystemMessage> examine() {
        return super.examine();
    }

    @Override
    public ObjectValidation<T> onError(String error) {
        return (ObjectValidation<T>) super.onError(error);
    }

    @Override
    public ObjectValidation<T> onGroupError(String error) {
        return (ObjectValidation<T>) super.onGroupError(error);
    }
}
