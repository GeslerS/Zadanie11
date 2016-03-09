package pl.sagiton.web;


import org.hibernate.SessionFactory;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import pl.sagiton.config.HibernateConfig;
import pl.sagiton.config.SecurityConfig;
import pl.sagiton.config.SpringWebConfig;
import pl.sagiton.servlet.MyWebInitializer;
import pl.sagiton.web.model.MyUser;
import pl.sagiton.web.model.UserDAO;
import pl.sagiton.web.model.UserDAOImpl;
import pl.sagiton.web.service.MyUserDetailsService;
import pl.sagiton.web.service.UserService;
import pl.sagiton.web.service.UserServiceImpl;

import javax.servlet.Filter;
import javax.sql.DataSource;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.formLogin;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.logout;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.authenticated;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.unauthenticated;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

/**
 * Created by szymon on 01.03.16.
 */


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {MyWebInitializer.class, SecurityConfig.class, SpringWebConfig.class, HibernateConfig.class})
@WebAppConfiguration
public class AccessTest {

    @Autowired
    private WebApplicationContext context;

    @Autowired
    private Filter springSecurityFilterChain;

    private MockMvc mvc;

    @Before
    public void setup() {
        mvc = MockMvcBuilders
                .webAppContextSetup(context)
                .addFilters(springSecurityFilterChain)
                .build();

    }

    @Test
    public void authorizationTest() throws Exception {
        mvc.perform(formLogin().user("Szymon").password("Szymon123")).andExpect(authenticated());

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