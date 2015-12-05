package cz.muni.fi.pa165.service;

import cz.muni.fi.pa165.saes.UserFilter;
import cz.muni.fi.pa165.saes.entity.User;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 * @author Jan S.
 */
@Service
public interface UserService {
    
    /**
     * Creates new user.
     * @param user - user to be created
     * @param password - plain password
     * @return ID assigned to newly created user
     */
    public Long signIn(User user, String password);
    
    /**
     * Log in user.
     * @param userId id of user logging in
     * @param password plain password
     * @return true if user was succefully logged in, false otherwise
     */
    public boolean authenticate(Long userId, String password);
    
    /**
     * Change user password.
     * @param userId id of user changing password
     * @param oldPassword old password to be changed
     * @param newPassword new password
     */
    public void changePassword(Long userId, String oldPassword, String newPassword);

    /**
     * Remove given user.
     * @param user to be removed
     */
    public void remove(User user);

    /**
     * Finds an user by given id.
     * @param id of user to be found
     * @return found user
     */
    public User findById(Long id);    

    /**
     * Returns a list of all users.
     * @return list of all users.
     */
    public List<User> findAll();

    /**
     * Updates an user with new data.
     * @param user containing new user data.
     */
    public void update(User user);

    /**
     * Returns a list of users filtered by given filter.
     * @param filter - custom filter
     * @return a list of users filtered by given custom filter
     */
    public List<User> getUsersByFilter(UserFilter filter);
}
