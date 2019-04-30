package validation.api.demo.validation.domain.date.impl;

import validation.api.demo.exception.SystemMessage;
import validation.api.demo.validation.domain.date.AbstractDateClause;

import java.time.LocalDate;
import java.util.List;

public class DateValidation extends AbstractDateClause {

    public DateValidation(LocalDate date) {
        super();
        this.obj = date;
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
}
