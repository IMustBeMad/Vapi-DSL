package validation.api.demo.validation.domain.number.impl;

import validation.api.demo.exception.SystemMessage;
import validation.api.demo.validation.dict.ErrorMode;
import validation.api.demo.validation.dict.TerminationMode;
import validation.api.demo.validation.domain.number.AbstractLongClause;

import java.util.List;

public class LongValidation extends AbstractLongClause {

    public LongValidation(Long aLong) {
        super();
        this.obj = aLong;
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
