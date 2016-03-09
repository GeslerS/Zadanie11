package pl.sagiton.web.model;

import org.hibernate.SessionFactory;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by szymon on 03.03.16.
 */
@Repository
public class UserDAOImpl implements UserDAO {


    @Autowired
    private SessionFactory sessionFactory;


    public MyUser listUser(String username) {

       List list = sessionFactory.getCurrentSession()
                .createQuery("FROM MyUser E WHERE E.username = '" +username + "'").list();
        if(!list.isEmpty())
            return (MyUser)list.get(0);

        return null;

    }

}