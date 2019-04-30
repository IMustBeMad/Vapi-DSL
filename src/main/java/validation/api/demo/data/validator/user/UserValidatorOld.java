package validation.api.demo.data.validator.user;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import validation.api.demo.data.common.Client;
import validation.api.demo.data.common.User;
import validation.api.demo.data.service.UserService;

import java.util.Optional;

@Log4j2
@Service
public class UserValidatorOld {

    @Autowired
    private UserService userService;

    public void verifyUserOld(Long userId, Long orderClientId) {
        if (userId == null) {
            log.debug("Current user is null, nothing to verify");

            return;
        }

        log.debug("Validating user with id [{}]", userId);

        boolean isValidUser = Optional.ofNullable(userService.getOne(userId))
                                      .map(User::getClient)
                                      .map(Client::getId)
                                      .map(it -> isValidUserByClientId(it, orderClientId))
                                      .orElse(false);

        if (!isValidUser) {
            throw new RuntimeException("User not found");
        }
    }

    private boolean isValidUserByClientId(Long userClientId, Long orderClientId) {
        return userClientId.equals(orderClientId);
    }
}
