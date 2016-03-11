package pl.sagiton.web.model;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserDAOImpl implements UserDAO {


    @Autowired
    private SessionFactory sessionFactory;


    public MyUser getUser(String username) {

      return (MyUser) sessionFactory.getCurrentSession()
                .createQuery("FROM MyUser E join fetch E.roles WHERE E.username = :username").
                      setParameter("username",username).uniqueResult();

    }

}