package validation.api.demo.validation.domain.list.impl;

import validation.api.demo.validation.domain.list.AbstractListValidation;

import java.util.List;

public class ListValidation<T> extends AbstractListValidation<T> {

    public ListValidation(List<T> list) {
        super();
        this.obj = list;
    }
}
