package cz.muni.fi.pa165.sportactivityevidencesystem.dao;

import cz.muni.fi.pa165.sportactivityevidencesystem.entity.User;

/**
 * This class provides functionality for storing and retrieving User entity
 * to/from database.
 * @author MajoCAM
 */
public interface UserDao {

    /**
     * Persist User entity to the database
     * @param user - instance of User that will be stored
     */
    public void createUser(User user);

    /**
     * Removes user from database 
     * @param user - instance of User to be removed from database
     */
    public void deleteUser(User user);

    /**
     * Retrieves User with proper id from database 
     * @param id - id of the User stored in database
     * @return retrieved user
     */
    public User getUser(Long id);

    /**
     * Updates user in the database
     * @param user - all changes to this user will be projected to the database
     */
    public void updateUser(User user);
}
