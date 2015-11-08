package cz.muni.fi.pa165.sportactivityevidencesystem.dao;

import cz.muni.fi.pa165.sportactivityevidencesystem.entity.User;
import java.util.Collection;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Marian Camak
 */
@Repository
public class UserDaoImpl implements UserDao {

    @PersistenceContext
    private EntityManager em;

    @Override
    public void createUser(User user) {
        if (user == null) {
            throw new IllegalArgumentException("User is null. ");
        }
        if (user.getId() != null) {
            throw new IllegalArgumentException("User ID is not null. User is already stored in DB. ");
        }
        if (user.getName() == null || user.getName().isEmpty()) {
            throw new IllegalArgumentException("User name is null or empty. ");
        }
        if (user.getAge() < 0) {
            throw new IllegalArgumentException("User age is negative. ");
        }
        if (user.getSex() == null) {
            throw new IllegalArgumentException("Gender of user is null. ");
        }
        if (user.getWeight() < 0) {
            throw new IllegalArgumentException("User bodyweight is lower than zero. ");
        }
        em.persist(user);
    }

    @Override
    public void deleteUser(User user) {
        if (user == null) {
            throw new IllegalArgumentException("User is null. ");
        }
        if (user.getId() == null) {
            throw new IllegalArgumentException("User ID is null. User is not stored in DB. ");
        }
        em.remove(user);
    }

    @Override
    public User findUser(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("ID is null. ");
        }
        return em.find(User.class, id);
    }

    @Override
    public Collection<User> findAllUsers() {
        return em.createQuery("SELECT u FROM User u", User.class).getResultList();
    }

    @Override
    public void updateUser(User user) {
        if (user == null) {
            throw new IllegalArgumentException("User is null. ");
        }
        if (user.getId() == null) {
            throw new IllegalArgumentException("User ID is null. First create an user. ");
        }
        if (user.getName() == null || user.getName().trim().isEmpty()) {
            throw new IllegalArgumentException("User name is null or empty. ");
        }
        if (user.getAge() < 0) {
            throw new IllegalArgumentException("User age is negative. ");
        }
        if (user.getSex() == null) {
            throw new IllegalArgumentException("Gender of user is null. ");
        }
        if (user.getWeight() < 0) {
            throw new IllegalArgumentException("User bodyweight is lower than zero. ");
        }
        em.merge(user);
    }
}
