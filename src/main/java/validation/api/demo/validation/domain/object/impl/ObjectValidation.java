package validation.api.demo.validation.domain.object.impl;

import validation.api.demo.validation.domain.object.AbstractObjectValidation;

public class ObjectValidation extends AbstractObjectValidation<Object> {

    public ObjectValidation(Object obj) {
        super();
        this.obj = obj;
    }
}
