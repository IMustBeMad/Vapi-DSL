package validation.api.demo.data.validator.user;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import validation.api.demo.data.common.Client;
import validation.api.demo.data.common.User;
import validation.api.demo.data.service.UserService;
import validation.api.demo.validation.Validation;
import validation.api.demo.validation.dict.TerminationMode;

import java.util.Optional;

import static validation.api.demo.validation.domain.number.biginteger.LongConditions.isEqualTo;
import static validation.api.demo.validation.domain.number.biginteger.LongConditions.isGt;

@Slf4j
@Service
public class UserValidatorNew {

    @Autowired
    private UserService userService;

    public void verifyUserNew(Long userId, Long orderClientId) {
        if (userId == null) {
            log.debug("Current user is null, nothing to verify");

            return;
        }

        Validation.verifyIf(userService.getOne(userId))
                  .log("Validating user with id [{}]", userId)
                  .isNotNull().onError("User not found")
                  .deepInspecting(
                          this::getClientId,
                          id -> Validation.verifyIf(id)
                                          .isAnyOf(isGt(1L), isEqualTo(orderClientId)).onError("invalid user")
                                          .failOn(TerminationMode.FIRST_ERROR_ENCOUNTERED)
                  )
                  .failOn(TerminationMode.FIRST_ERROR_ENCOUNTERED)
                  .examine();
    }

    private Long getClientId(User user) {
        return Optional.ofNullable(user)
                       .map(User::getClient)
                       .map(Client::getId)
                       .orElse(null);
    }
}
