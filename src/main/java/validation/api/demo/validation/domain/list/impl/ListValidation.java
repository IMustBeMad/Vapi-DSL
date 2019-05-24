package validation.api.demo.validation.domain.list.impl;

import validation.api.demo.validation.exception.SystemMessage;
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

    @Override
    public ListValidation<T> onError(String error) {
        return (ListValidation<T>) super.onError(error);
    }

    @Override
    public ListValidation<T> onGroupError(String error) {
        return (ListValidation<T>) super.onGroupError(error);
    }
}
