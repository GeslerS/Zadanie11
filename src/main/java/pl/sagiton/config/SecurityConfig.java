package pl.sagiton.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import pl.sagiton.web.service.MyUserDetailsService;


/**
 * Created by szymon on 03.03.16.
 */
@Configuration
@EnableWebSecurity
@ComponentScan({"pl.sagiton.web"})
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private MyUserDetailsService myUserDetailsService;

    @Override
    protected void configure(HttpSecurity http)throws Exception{
        http.csrf().disable().authorizeRequests()
                .antMatchers("/home").access("isAuthenticated()")
                .and()
        .formLogin()
                .loginPage("/login")
                .defaultSuccessUrl("/home")
                .failureUrl("/denied")
                .permitAll()
                .and()
        .logout().logoutUrl("/logout");

    }


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(myUserDetailsService);
    }
}
