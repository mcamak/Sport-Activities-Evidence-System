package cz.muni.fi.pa165.sportactivityevidencesystem.dao;

import cz.muni.fi.pa165.sportactivityevidencesystem.entity.User;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Marian Camak
 */
public class UserDaoImpl implements UserDao {
    
    @PersistenceContext
    private EntityManager em;

    @Override
    public void createUser(User user) {
        if(user == null) {
            throw new IllegalArgumentException("User is null. ");
        }
        if(user.getId() != null) {
            throw new IllegalArgumentException("User ID is not null. User is already stored in DB. ");
        }
        em.persist(user);
    }

    @Override
    public void deleteUser(User user) {
        if(user == null) {
            throw new IllegalArgumentException("User is null. ");
        }
        if(user.getId() == null) {
            throw new IllegalArgumentException("User ID is null. User is not stored in DB. ");
        }
        em.remove(user);
    }

    @Override
    public User getUser(Long id) {
        if(id == null) {
            throw new IllegalArgumentException("ID is null. ");
        }
        return em.find(User.class, id);
    }

    @Override
    public void updateUser(User user) {
        if(user == null) {
            throw new IllegalArgumentException("User is null. ");
        }
        if(user.getId() == null) {
            throw new IllegalArgumentException("User ID is null. First create an user. ");
        }
        em.merge(user);
    }
}
