package cz.muni.fi.pa165.service;

import cz.muni.fi.pa165.saes.dao.UserDao;
import cz.muni.fi.pa165.saes.entity.User;
import javax.inject.Inject;
import org.springframework.stereotype.Service;

/**
 *
 * @author Jan S.
 */
@Service
public class UserServiceImpl implements UserService {

    @Inject
    private UserDao userDao;

    @Override
    public void createUser(User user) {
        if (user == null) {
            throw new IllegalArgumentException("User is null. ");
        }
        if (user.getId() != null) {
            throw new IllegalArgumentException("User ID is not null. ");
        }
        userDao.createUser(user);
    }

    @Override
    public void removeUser(User user) {
        if (user == null) {
            throw new IllegalArgumentException("User is null. ");
        }
        if (user.getId() == null) {
            throw new IllegalArgumentException("User ID is null. Cannot be removed. ");
        }
        userDao.deleteUser(user);
    }

    @Override
    public User findUser(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("ID is null. ");
        }
        return userDao.findUser(id);
    }

    @Override
    public void updateUser(User user) {
        if (user == null) {
            throw new IllegalArgumentException("User is null. ");
        }
        if (user.getId() == null) {
            throw new IllegalArgumentException("User ID is null. Persist user first. ");
        }
        userDao.updateUser(user);
    }
}
