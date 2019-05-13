package validation.api.demo.validation.domain.array.impl;

import validation.api.demo.exception.SystemMessage;
import validation.api.demo.validation.dict.ErrorMode;
import validation.api.demo.validation.dict.TerminationMode;
import validation.api.demo.validation.domain.array.AbstractArrayClause;

import java.util.List;

public class ArrayValidation<T> extends AbstractArrayClause<T> {

    public ArrayValidation(T[] array) {
        super();
        this.obj = array;
    }

    @Override
    public List<SystemMessage> failOn(TerminationMode terminationMode) {
        return super.failOn(terminationMode);
    }

    @Override
    public List<SystemMessage> failOn(TerminationMode terminationMode, ErrorMode errorMode) {
        return super.failOn(terminationMode, errorMode);
    }
}
