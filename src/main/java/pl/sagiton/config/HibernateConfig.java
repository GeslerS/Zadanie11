package pl.sagiton.config;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import pl.sagiton.web.model.UserDAOImpl;
import pl.sagiton.web.service.UserService;
import pl.sagiton.web.service.UserServiceImpl;

import javax.sql.DataSource;
import java.util.Properties;

/**
 * Created by szymon on 05.03.16.
 */
@Configuration
public class HibernateConfig {


    @Bean
    public DataSource dataSource(){
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://127.0.0.1:3306/UsersDB");
        dataSource.setUsername("root");
        dataSource.setPassword("haslo12345");
        return dataSource;
    }

    @Bean
    public UserDAOImpl userDAOImpl(){
        UserDAOImpl userDAOImpl = new UserDAOImpl();
        return userDAOImpl;
    }



    @Bean
    public UserService userService(){
        return new UserServiceImpl();
    }

    @Bean
    public LocalSessionFactoryBean sessionFactory() {
        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
        sessionFactory.setDataSource(dataSource());
        sessionFactory.setPackagesToScan(new String[] { "pl.sagiton.web.model" });
        sessionFactory.setHibernateProperties(hibernateProperties());

        return sessionFactory;
    }

    @Bean
    @Autowired
    public HibernateTransactionManager transactionManager(SessionFactory sessionFactory) {
        HibernateTransactionManager txManager = new HibernateTransactionManager();
        txManager.setSessionFactory(sessionFactory);

        return txManager;
    }
    @Bean
    public PersistenceExceptionTranslationPostProcessor exceptionTranslation() {
        return new PersistenceExceptionTranslationPostProcessor();
    }

    Properties hibernateProperties() {
        return new Properties() {
            {
                setProperty("hibernate.dialect","org.hibernate.dialect.MySQL5Dialect");
                setProperty("hibernate.globally_quoted_identifiers", "true");

            }
        };
    }
}
