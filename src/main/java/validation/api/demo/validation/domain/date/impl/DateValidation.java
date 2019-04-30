package validation.api.demo.validation.domain.date.impl;

import validation.api.demo.validation.domain.date.AbstractDateClause;

import java.time.LocalDate;

public class DateValidation extends AbstractDateClause {

    public DateValidation(LocalDate date) {
        super();
        this.obj = date;
    }
}
