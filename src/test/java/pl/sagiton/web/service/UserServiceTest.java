package pl.sagiton.web.service;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;
import pl.sagiton.servlet.RootConfig;
import pl.sagiton.web.model.MyUser;
import pl.sagiton.web.model.Role;

import java.util.HashSet;
import java.util.Set;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.fail;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {RootConfig.class})
@WebAppConfiguration
@Transactional
public class UserServiceTest {

    @Autowired
    SessionFactory sessionFactory;

    @Autowired
    UserService userService;

    private Session session = null;


    @Before
    public void beforeTest(){
        session = sessionFactory.openSession();
    }

    @After
    public void afterTest(){
        session.close();
    }

    @Test
    public void userServiceTest(){

        Role role = new Role();
        role.setRole("ROLE_USER");

        Role role2 = new Role();
        role2.setRole("ROLE_ADMIN");

        MyUser user = new MyUser();
        user.setUsername("Donna");
        user.setPassword("Donna123");

        Set<Role> roleSet = new HashSet<Role>();
        roleSet.add(role2);
        roleSet.add(role);

        Set<MyUser> userSet = new HashSet<MyUser>();
        userSet.add(user);

        role.setUsers(userSet);
        role2.setUsers(userSet);
        user.setRoles(roleSet);

        session.save(role);
        session.save(user);
        session.flush();
        session.clear();


        MyUser testUser = userService.getUser("Donna");
        assertEquals("Donna", testUser.getUsername());
        assertEquals("Donna123", testUser.getPassword());


        assertTrue(testUser.getRoles().size() == 2);
        for(Role testRole: testUser.getRoles()){
            if(!(testRole.getRole().equals("ROLE_ADMIN") || testRole.getRole().equals("ROLE_USER"))){
                fail();
            }
        }
    }


    @Test
    public void setUserServiceFailTest(){
        assertNull(userService.getUser("Jimmy"));
    }
}


