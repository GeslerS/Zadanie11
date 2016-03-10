package pl.sagiton.web.service;

import pl.sagiton.web.model.MyUser;

import java.util.List;

/**
 * Created by szymon on 03.03.16.
 */
public interface UserService {

    MyUser getUser(String username);
}
