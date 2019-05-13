package validation.api.demo.validation.domain.object.impl;

import lombok.extern.slf4j.Slf4j;
import validation.api.demo.exception.SystemMessage;
import validation.api.demo.validation.dict.ErrorMode;
import validation.api.demo.validation.dict.TerminationMode;
import validation.api.demo.validation.domain.object.AbstractObjectClause;

import java.util.List;

@Slf4j
public class ObjectValidation<T> extends AbstractObjectClause<T> {

    public ObjectValidation(T obj) {
        super();
        this.obj = obj;
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
