package validation.api.demo.validation.domain.object.impl;

import lombok.extern.slf4j.Slf4j;
import validation.api.demo.validation.domain.object.AbstractObjectTerminatorHolder;

@Slf4j
public class ObjectValidation<T> extends AbstractObjectTerminatorHolder<T> {

    public ObjectValidation(T obj) {
        super();
        this.obj = obj;
    }
}
