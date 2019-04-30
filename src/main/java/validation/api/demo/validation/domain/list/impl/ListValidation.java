package validation.api.demo.validation.domain.list.impl;

import validation.api.demo.validation.domain.list.AbstractListClause;

import java.util.List;

public class ListValidation<T> extends AbstractListClause<T> {

    public ListValidation(List<T> list) {
        super();
        this.obj = list;
    }
}
