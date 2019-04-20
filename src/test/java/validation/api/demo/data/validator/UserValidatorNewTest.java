package validation.api.demo.data.validator;

import org.hamcrest.Matchers;
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

import java.util.regex.Matcher;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class UserValidatorNewTest {

    private static final Long USER_ID = 1L;
    private static final Long CLIENT_ID = 2L;

    @Autowired
    private UserValidatorNew userValidatorNew;

    @MockBean
    private UserService userService;

    @Test
    public void should_beNoErrors_when_userClientIdIsEqualToOrderClientId() {
        Mockito.when(userService.getOne(anyLong())).thenReturn(getUser());

        userValidatorNew.verifyUserNew(USER_ID, CLIENT_ID);
    }

    @Test
    public void should_beNoErrors_when_userIdIsNull() {
        userValidatorNew.verifyUserNew(null, CLIENT_ID);

        verify(userService, never()).getOne(anyLong());
    }


    private User getUser() {
        Client client = new Client();
        client.setId(CLIENT_ID);

        User user = new User();
        user.setClient(client);
        user.setId(USER_ID);

        return user;
    }
}
