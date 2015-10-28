package cz.muni.fi.pa165.sportactivityevidencesystem.service;

import cz.muni.fi.pa165.sportactivityevidencesystem.dao.UserDao;
import cz.muni.fi.pa165.sportactivityevidencesystem.entity.User;
import javax.inject.Inject;

/**
 * 
 * @author Jan S.
 */
public class UserServiceImpl implements UserService {
    
    @Inject
    private UserDao userDao;

    @Override
    public void createUser(User user) {
        if(user == null) {
            throw new IllegalArgumentException("User is null. ");
        }
        if(user.getId() != null) {
            throw new IllegalArgumentException("User ID is not null. ");
        }
        userDao.createUser(user);
    }

    @Override
    public void removeUser(User user) {
        if(user == null) {
            throw new IllegalArgumentException("User is null. ");
        }
        if(user.getId() == null) {
            throw new IllegalArgumentException("User ID is null. Cannot be removed. ");
        }
        userDao.deleteUser(user);
    }

    @Override
    public User findUser(Long id) {
        if(id == null) {
            throw new IllegalArgumentException("ID is null. ");
        }
        return userDao.getUser(id);
    }

    @Override
    public void updateUser(User user) {
        if(user == null) {
            throw new IllegalArgumentException("User is null. ");
        }
        if(user.getId() == null) {
            throw new IllegalArgumentException("User ID is null. Persist user first. ");
        }
        userDao.updateUser(user);
    }    
}
