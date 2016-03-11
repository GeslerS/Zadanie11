package pl.sagiton.web;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.context.support.WithSecurityContextTestExecutionListener;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.test.context.web.ServletTestExecutionListener;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import pl.sagiton.config.HibernateConfig;
import pl.sagiton.config.SecurityConfig;
import pl.sagiton.config.SpringWebConfig;
import pl.sagiton.servlet.MyWebInitializer;
import pl.sagiton.servlet.RootConfig;
import pl.sagiton.web.model.MyUser;
import pl.sagiton.web.model.Role;
import pl.sagiton.web.service.MyUserDetailsService;
import pl.sagiton.web.service.UserService;

import javax.servlet.Filter;

import java.util.HashSet;
import java.util.Set;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.formLogin;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.authenticated;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.unauthenticated;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

/**
 * Created by szymon on 01.03.16.
 */


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {RootConfig.class})
@WebAppConfiguration
@TestExecutionListeners(listeners={ServletTestExecutionListener.class,
        DependencyInjectionTestExecutionListener.class,
        DirtiesContextTestExecutionListener.class,
        TransactionalTestExecutionListener.class,
        WithSecurityContextTestExecutionListener.class})
public class AccessTest {

    @Autowired
    private WebApplicationContext context;

    @Autowired
    private Filter springSecurityFilterChain;

    private MockMvc mvc;

    @Autowired
    SessionFactory sessionFactory;

    @Autowired
    UserService userService;

    private Session session = null;



    @After
    public void clear(){
        session.close();
    }

    @Before
    public void setup() {
        mvc = MockMvcBuilders
                .webAppContextSetup(context)
                .addFilters(springSecurityFilterChain)
                .build();
        session = sessionFactory.openSession();
    }

    @Test
    public void authorizationTest() throws Exception {
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


        mvc.perform(formLogin().user("Donna").password("Donna123")).andExpect(authenticated().withRoles("USER","ADMIN"));
    }


    @Test
    public void invalidAuthorizationTest() throws Exception {
        mvc.perform(formLogin().user("Szymon").password("InvalidPassword")).andExpect(unauthenticated());
    }


    @Test
    public void noAuthorizationTest() throws Exception {
        mvc.perform(get("/home")).andExpect(unauthenticated());
    }


}