package validation.api.demo.validation.domain.date.impl;

import validation.api.demo.validation.exception.SystemMessage;
import validation.api.demo.validation.domain.date.AbstractDateClause;

import java.time.LocalDate;
import java.util.List;

public class DateValidation extends AbstractDateClause {

    public DateValidation(LocalDate date) {
        super();
        this.obj = date;
    }

    @Override
    public List<SystemMessage> examine() {
        return super.examine();
    }

    @Override
    public DateValidation onError(String error) {
        return (DateValidation) super.onError(error);
    }

    @Override
    public DateValidation onGroupError(String error) {
        return (DateValidation) super.onGroupError(error);
    }
}
