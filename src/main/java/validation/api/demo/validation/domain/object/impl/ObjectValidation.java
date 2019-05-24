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
