package validation.api.demo.validation.domain.list.impl;

import validation.api.demo.exception.SystemMessage;
import validation.api.demo.validation.dict.ErrorMode;
import validation.api.demo.validation.dict.TerminationMode;
import validation.api.demo.validation.domain.list.AbstractListClause;

import java.util.List;

public class ListValidation<T> extends AbstractListClause<T> {

    public ListValidation(List<T> list) {
        super();
        this.obj = list;
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
