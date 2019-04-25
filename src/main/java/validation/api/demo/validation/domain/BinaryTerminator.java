package validation.api.demo.validation.domain;

import validation.api.demo.exception.SystemMessage;

import java.util.List;

public interface BinaryTerminator extends Terminator {

    List<SystemMessage> matched();
}
