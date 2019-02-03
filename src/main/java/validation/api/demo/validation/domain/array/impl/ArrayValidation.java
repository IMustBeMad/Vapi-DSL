package validation.api.demo.validation.domain.array.impl;

import validation.api.demo.validation.domain.array.AbstractArrayValidation;

public class ArrayValidation<T> extends AbstractArrayValidation<T> {

    public ArrayValidation(T[] array) {
        super();
        this.obj = array;
    }
}
