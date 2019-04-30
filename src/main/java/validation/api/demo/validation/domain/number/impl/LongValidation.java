package validation.api.demo.validation.domain.number.impl;

import validation.api.demo.exception.SystemMessage;
import validation.api.demo.validation.domain.number.AbstractLongClause;

import java.util.List;

public class LongValidation extends AbstractLongClause {

    public LongValidation(Long aLong) {
        super();
        this.obj = aLong;
    }

    @Override
    public void failFast() {
        super.failFast();
    }

    @Override
    public void failSafe() {
        super.failSafe();
    }

    @Override
    public List<SystemMessage> examine() {
        return super.examine();
    }

    @Override
    protected void failIfNoneGroupMatch() {
        super.failIfNoneGroupMatch();
    }
}
