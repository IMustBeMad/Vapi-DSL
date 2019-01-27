package validation.api.demo.validation.domain.date.impl;

import validation.api.demo.validation.domain.date.AbstractDateValidation;

import java.time.LocalDate;

public class DateValidation extends AbstractDateValidation {

    public DateValidation(LocalDate date) {
        super();
        this.obj = date;
    }
}
