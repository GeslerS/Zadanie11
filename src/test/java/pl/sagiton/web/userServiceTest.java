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
import static junit.framework.TestCase.assertNotNull;
import static org.junit.Assert.assertNull;

/**
 * Created by szymon on 05.03.16.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {SpringWebConfig.class, HibernateConfigTest.class})
@WebAppConfiguration
public class userServiceTest {

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



    }


    @Test
    public void noUserTest(){
        MyUser user = userService.listUser("Kamil");
        assertNull(user);
    }


}


