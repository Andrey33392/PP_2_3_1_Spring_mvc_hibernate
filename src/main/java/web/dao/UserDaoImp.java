package web.dao;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import web.model.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.Valid;
import java.util.List;

@Component
@Transactional
public class UserDaoImp implements UserDao {

    @PersistenceContext
    private EntityManager entityManager;


    @Override
    public List<User> getUsers() {
        return entityManager.createQuery("SELECT user from User user", User.class).getResultList();
    }

    @Override
    public void addUser(@Valid User user) {
        entityManager.persist(user);
    }

    @Override
    public User getUserById(long id) {
        return entityManager.find(User.class, id);
    }

    @Override
    public void removeUser(long id) {
        entityManager.remove(getUserById(id));
    }

    @Override
    public void updateUser( @Valid User user) {
        entityManager.merge(user);
    }
}
