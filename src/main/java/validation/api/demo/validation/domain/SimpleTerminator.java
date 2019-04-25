package validation.api.demo.validation.domain;

import validation.api.demo.exception.SystemMessage;

import java.util.List;

public interface SimpleTerminator extends Terminator {

    void failFast();

    void failSafe();

    List<SystemMessage> examine();
}
