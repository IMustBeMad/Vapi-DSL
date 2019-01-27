package validation.api.demo.validation.domain.object.impl;

import validation.api.demo.validation.domain.object.AbstractObjectValidation;

public class ObjectValidation<T> extends AbstractObjectValidation<T> {

    public ObjectValidation(T obj) {
        super();
        this.obj = obj;
    }
}
