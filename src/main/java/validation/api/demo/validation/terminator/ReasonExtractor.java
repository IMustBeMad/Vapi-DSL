package validation.api.demo.validation.terminator;

import validation.api.demo.validation.exception.SystemMessage;
import validation.api.demo.validation.result.ValidationResult;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static org.apache.commons.lang3.StringUtils.isEmpty;

public interface ReasonExtractor {

    default List<SystemMessage> getErrorReason(List<ValidationResult> results, String onError) {
        if (!isEmpty(onError)) {
            return Collections.singletonList(SystemMessage.withError("group", onError));
        }

        List<SystemMessage> messages = results.stream()
                                              .map(ValidationResult::getReason)
                                              .collect(Collectors.toList());

        return messages.isEmpty() ? Collections.singletonList(SystemMessage.withError("validation", "validation.error.no.error.code"))
                                  : messages;
    }
}
