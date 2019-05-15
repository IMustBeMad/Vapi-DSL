package validation.api.demo.validation.domain.list.impl;

import validation.api.demo.exception.SystemMessage;
import validation.api.demo.validation.domain.list.AbstractListClause;

import java.util.List;

public class ListValidation<T> extends AbstractListClause<T> {

    public ListValidation(List<T> list) {
        super();
        this.obj = list;
    }

    @Override
    public List<SystemMessage> examine() {
        return super.examine();
    }
}
