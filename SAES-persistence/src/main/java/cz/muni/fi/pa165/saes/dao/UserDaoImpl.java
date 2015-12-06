package cz.muni.fi.pa165.saes.dao;

import cz.muni.fi.pa165.saes.UserFilter;
import cz.muni.fi.pa165.saes.entity.User;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
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
        if (user.getPasswordHash() == null || user.getPasswordHash().isEmpty()) {
            throw new IllegalArgumentException("Password hash is null or empty. ");
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
    public List<User> findAllUsers() {
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
        if (user.getPasswordHash() == null || user.getPasswordHash().isEmpty()) {
            throw new IllegalArgumentException("Password hash is null or empty. ");
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

    @Override
    public List<User> findUsersByParameters(UserFilter filter) {
        String queryString = "SELECT u FROM User u";
        String condition = "";
        if(filter.getMaxAge() != null) {
            if(condition.isEmpty()) {
                condition += " WHERE u.age < :maxAge";
            } else {
                condition += " AND u.age < :maxAge";
            }
        }
        if(filter.getMinAge() != null) {
            if(condition.isEmpty()) {
                condition += " WHERE u.age > :minAge";
            } else {
                condition += " AND u.age > :minAge";
            }
        }
        if(filter.getMaxWeight() != null) {
            if(condition.isEmpty()) {
                condition += " WHERE u.weight < :maxWeight";
            } else {
                condition += " AND u.weight < :maxWeight";
            }
        }
        if(filter.getMinWeight() != null) {
            if(condition.isEmpty()) {
                condition += " WHERE u.weight > :minWeight";
            } else {
                condition += " AND u.weight > :minWeight";
            }
        }
                
        Query query = em.createQuery(queryString + condition);
        if(filter.getMaxAge() != null) {
            query.setParameter(":maxAge", filter.getMaxAge());
        }
        if(filter.getMinAge() != null) {
            query.setParameter(":minAge", filter.getMinAge());
        }
        if(filter.getMaxWeight()!= null) {
            query.setParameter(":maxWeight", filter.getMaxWeight());
        }
        if(filter.getMinWeight()!= null) {
            query.setParameter(":minWeight", filter.getMinWeight());
        }
        return query.getResultList();
    }
}
