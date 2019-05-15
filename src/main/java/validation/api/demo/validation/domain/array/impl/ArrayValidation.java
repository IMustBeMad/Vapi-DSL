package validation.api.demo.validation.domain.array.impl;

import validation.api.demo.exception.SystemMessage;
import validation.api.demo.validation.domain.array.AbstractArrayClause;

import java.util.List;

public class ArrayValidation<T> extends AbstractArrayClause<T> {

    public ArrayValidation(T[] array) {
        super();
        this.obj = array;
    }

    @Override
    public List<SystemMessage> examine() {
        return super.examine();
    }

    @Override
    public ArrayValidation<T> onError(String error) {
        return (ArrayValidation<T>) super.onError(error);
    }
}
