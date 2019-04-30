package validation.api.demo.validation.domain.array.impl;

import validation.api.demo.validation.domain.array.AbstractArrayClause;

public class ArrayValidation<T> extends AbstractArrayClause<T> {

    public ArrayValidation(T[] array) {
        super();
        this.obj = array;
    }
}
