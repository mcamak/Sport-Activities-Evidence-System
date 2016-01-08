package cz.muni.fi.pa165.service;

import cz.muni.fi.pa165.saes.entity.User;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Jan S.
 */
@Service
public interface UserService {
    
    /**
     * Register new user.
     * @param user - user to be created
     * @param password - plain password
     */
    void register(User user, String password);
    
    /**
     * Log in user.
     * @param user user logging in
     * @param password plain password
     * @return true if user was successfully logged in, false otherwise
     */
    boolean authenticate(User user, String password);

    /**
     * Check whether the user is admin or not.
     *
     * @param id id of user
     */
    boolean isAdmin(Long id);

    /**
     * Remove given user.
     * @param id of user to be removed
     */
    void delete(Long id);

    /**
     * Finds an user by given id.
     * @param id of user to be found
     * @return found user
     */
    User findById(Long id);

    /**
     * Returns a list of all users.
     * @return list of all users.
     */
    List<User> findAll();

    /**
     * Updates an user with new data.
     * @param user containing new user data.
     */
    void update(User user);
}
