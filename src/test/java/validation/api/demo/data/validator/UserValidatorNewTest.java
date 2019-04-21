package validation.api.demo.data.validator;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import validation.api.demo.data.common.Client;
import validation.api.demo.data.common.User;
import validation.api.demo.data.service.UserService;
import validation.api.demo.exception.ValidationException;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

@SpringBootTest
@RunWith(SpringRunner.class)
public class UserValidatorNewTest {

    private static final Long USER_ID = 1L;
    private static final Long INVAlID_CLIENT_ID = -10L;
    private static final Long CLIENT_ID = 2L;

    @Autowired
    private UserValidatorNew userValidatorNew;

    @MockBean
    private UserService userService;

    @Test
    public void should_beNoErrors_when_userClientIdIsEqualToOrderClientId() {
        Mockito.when(userService.getOne(anyLong())).thenReturn(getUser(CLIENT_ID));

        userValidatorNew.verifyUserNew(USER_ID, CLIENT_ID);
    }

    @Test
    public void should_beNoErrors_when_userIdIsNull() {
        userValidatorNew.verifyUserNew(null, CLIENT_ID);

        verify(userService, never()).getOne(anyLong());
    }

    @Test
    public void should_throwError_when_serviceReturnsNull() {
        Mockito.when(userService.getOne(anyLong())).thenReturn(null);

        Assertions.assertThatThrownBy(() -> userValidatorNew.verifyUserNew(USER_ID, CLIENT_ID))
                  .isInstanceOf(ValidationException.class);
    }

    @Test
    public void should_throwError_when_serviceReturnsNegativeClientId() {
        Mockito.when(userService.getOne(anyLong())).thenReturn(getUser(INVAlID_CLIENT_ID));

        Assertions.assertThatThrownBy(() -> userValidatorNew.verifyUserNew(USER_ID, CLIENT_ID))
                  .isInstanceOf(ValidationException.class);
    }

    private User getUser(Long clientId) {
        Client client = new Client();
        client.setId(clientId);

        User user = new User();
        user.setClient(client);
        user.setId(USER_ID);

        return user;
    }
}
