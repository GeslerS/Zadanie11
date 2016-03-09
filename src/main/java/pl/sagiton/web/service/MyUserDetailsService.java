package pl.sagiton.web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pl.sagiton.web.model.MyUser;
import pl.sagiton.web.model.Role;


import java.util.ArrayList;
import java.util.List;

/**
 * Created by szymon on 03.03.16.
 */
@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private UserService userService;

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if(userService.listUser(username) == null) throw new UsernameNotFoundException("MyUser doesn't exist (" + username +")");

        MyUser user = userService.listUser(username);

        List authList = new ArrayList();
        for (Role role:user.getRoles()){
            authList.add(new SimpleGrantedAuthority(role.getRole()));
        }

        return new User(user.getUsername(),user.getPassword(), authList);
    }
}
