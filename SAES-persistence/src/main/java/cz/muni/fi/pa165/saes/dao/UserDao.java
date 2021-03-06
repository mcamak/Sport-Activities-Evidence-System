package cz.muni.fi.pa165.saes.dao;

import cz.muni.fi.pa165.saes.entity.User;

import java.util.List;

/**
 * This class provides functionality for storing and retrieving User entity
 * to/from database.
 *
 * @author Marian Camak
 */
public interface UserDao {

    /**
     * Persist User entity to the database
     *
     * @param user - instance of User that will be stored
     */
    void createUser(User user);

    /**
     * Removes user from database
     *
     * @param user - instance of User to be removed from database
     */
    void deleteUser(User user);

    /**
     * Retrieves User with proper id from database
     *
     * @param id - id of the User stored in database
     * @return retrieved user
     */
    User findUser(Long id);

    /**
     * Searches for User by his username
     *
     * @param username - id of the User stored in database
     * @return retrieved user
     */
    User findUserByName(String username);

    /**
     * Retrieves a collection of all users
     *
     * @return collection of users
     */
    List<User> findAllUsers();

    /**
     * Updates user in the database
     *
     * @param user - all changes to this user will be projected to the database
     */
    void updateUser(User user);
}
