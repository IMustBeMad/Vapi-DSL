package validation.api.demo.validation.domain.object.impl;

import lombok.extern.slf4j.Slf4j;
import validation.api.demo.validation.dict.*;
import validation.api.demo.validation.domain.object.AbstractObjectClause;
import validation.api.demo.validation.exception.SystemMessage;

import java.util.List;

@Slf4j
public class ObjectValidation<T> extends AbstractObjectClause<T> {

    public ObjectValidation(T obj, MatchMode matchMode, PurposeMode purposeMode) {
        super();
        this.obj = obj;
        this.modeManager = new ModeManager(matchMode, purposeMode);
    }

    @Override
    public List<SystemMessage> examine() {
        return super.examine();
    }

    @Override
    public List<SystemMessage> examine(ErrorMode errorMode) {
        return super.examine(errorMode);
    }

    @Override
    public ObjectValidation<T> onError(String error) {
        return (ObjectValidation<T>) super.onError(error);
    }

    @Override
    public ObjectValidation<T> onGroupError(String error) {
        return (ObjectValidation<T>) super.onGroupError(error);
    }
}
