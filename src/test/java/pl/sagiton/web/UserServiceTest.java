package pl.sagiton.web;

import junit.framework.Assert;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.AnnotationConfiguration;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;
import pl.sagiton.config.*;
import pl.sagiton.servlet.MyWebInitializer;
import pl.sagiton.web.model.MyUser;
import pl.sagiton.web.model.Role;
import pl.sagiton.web.service.UserService;
import pl.sagiton.web.service.UserServiceImpl;

import java.util.HashSet;
import java.util.Set;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.fail;
import static junit.framework.TestCase.assertNotNull;
import static org.junit.Assert.assertNull;

/**
 * Created by szymon on 05.03.16.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {SpringWebConfig.class, HibernateConfigTest.class})
@WebAppConfiguration
@Transactional
public class UserServiceTest {

    @Autowired
    SessionFactory sessionFactory;

    @Autowired
    UserService userService;

    private Session session = null;


    @Before
    public void test(){
        session = sessionFactory.openSession();
    }


    @Test
    public void userServiceTest(){

        Role role = new Role();
      //  role.setRoleId(1);
        role.setRole("ROLE_USER");



        Role role2 = new Role();
      //  role2.setRoleId(2);
        role2.setRole("ROLE_ADMIN");

        MyUser user = new MyUser();
   //     user.setId(1);
        user.setUsername("Szymon");
        user.setPassword("Szymon123");

        HashSet<Role> set = new HashSet<Role>();
        set.add(role2);
        set.add(role);

        HashSet<MyUser> userSet = new HashSet<MyUser>();
        userSet.add(user);

        role2.setUsers(userSet);
        role.setUsers(userSet);
        user.setRoles(set);

        session.save(role);
        session.save(user);
            session.flush();
        session.clear();




        MyUser testedUser = userService.listUser("Szymon");
        assertEquals("Szymon", testedUser.getUsername());
        assertEquals("Szymon123", testedUser.getPassword());


        if(testedUser.getRoles().isEmpty())
            fail();
        for(Role userRole: testedUser.getRoles()) {
            assertEquals("ROLE_ADMIN", userRole.getRole());
            assertEquals("ROLE_USER", userRole.getRole());
        }


    }


}


