package pl.sagiton.web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.sagiton.web.model.MyUser;
import pl.sagiton.web.model.UserDAO;

/**
 * Created by szymon on 03.03.16.
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserDAO userDAO;

    @Transactional
    public MyUser getUser(String username) {
        return userDAO.getUser(username);    }
}
