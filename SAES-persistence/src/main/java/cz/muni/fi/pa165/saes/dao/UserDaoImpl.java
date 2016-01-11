package cz.muni.fi.pa165.saes.dao;

import cz.muni.fi.pa165.saes.entity.User;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 *
 * @author Marian Camak
 */
@Transactional
@Repository
public class UserDaoImpl implements UserDao {

    @PersistenceContext
    private EntityManager em;

    @Override
    public void createUser(User user) {
        checkUser(user);
        if (user.getId() != null) {
            throw new IllegalArgumentException("User ID is not null. User is already stored in DB. ");
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
        em.remove(em.contains(user) ? user : em.merge(user));
    }

    @Override
    public User findUser(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("ID is null. ");
        }
        return em.find(User.class, id);
    }

    @Override
    public User findUserByName(String username) {
        if (username == null || username.isEmpty()) {
            throw new IllegalArgumentException("Username is null or empty. ");
        }
        List<User> users = em.createQuery("SELECT u FROM User u WHERE u.username = :username", User.class)
                .setParameter("username", username)
                .getResultList();
        if (users.isEmpty()) {
            return null;
        } else if (users.size() == 1) {
            return users.get(0);
        } else {
            throw new DataIntegrityViolationException("More users with username '" + username + "' has been found. ");
        }
    }

    @Override
    public List<User> findAllUsers() {
        return em.createQuery("SELECT u FROM User u", User.class).getResultList();
    }

    @Override
    public void updateUser(User user) {
        checkUser(user);
        if (user.getId() == null) {
            throw new IllegalArgumentException("User ID is null. First create an user. ");
        }
        em.merge(user);
    }

    private void checkUser(User user) {
        if (user == null) {
            throw new IllegalArgumentException("User is null. ");
        }
        if (user.getUsername() == null || user.getUsername().trim().isEmpty()) {
            throw new IllegalArgumentException("User name is null or empty. ");
        }
        if (user.getPassword() == null || user.getPassword().isEmpty()) {
            throw new IllegalArgumentException("Password hash is null or empty. ");
        }
        if (user.getAge() == null) {
            throw new IllegalArgumentException("User age is null. ");
        }
        if (user.getAge() < 0) {
            throw new IllegalArgumentException("User age is negative. ");
        }
        if (user.getSex() == null) {
            throw new IllegalArgumentException("Gender of user is null. ");
        }
        if (user.getWeight() == null) {
            throw new IllegalArgumentException("User bodyweight is null. ");
        }
        if (user.getWeight() < 0) {
            throw new IllegalArgumentException("User bodyweight is lower than zero. ");
        }
    }
}
