package validation.api.demo.data.service;

import org.springframework.stereotype.Service;
import validation.api.demo.data.common.User;

@Service
public class UserService {

    public User getOne(Long id) {
        return new User();
    }
}
