package validation.api.demo.data.service;

import lombok.extern.log4j.Log4j2;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import validation.api.demo.data.validator.user.UserValidatorOld;

@Log4j2
@SpringBootTest
@RunWith(SpringRunner.class)
public class UserValidatorTest {

    @Autowired
    private UserValidatorOld userValidatorOld;

    @MockBean
    private UserService userService;

    @Test
    public void verifyUserOld_should_() {

    }
}