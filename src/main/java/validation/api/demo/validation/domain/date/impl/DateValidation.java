package validation.api.demo.validation.domain.date.impl;

import validation.api.demo.exception.SystemMessage;
import validation.api.demo.validation.dict.ErrorMode;
import validation.api.demo.validation.dict.TerminationMode;
import validation.api.demo.validation.domain.date.AbstractDateClause;

import java.time.LocalDate;
import java.util.List;

public class DateValidation extends AbstractDateClause {

    public DateValidation(LocalDate date) {
        super();
        this.obj = date;
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
