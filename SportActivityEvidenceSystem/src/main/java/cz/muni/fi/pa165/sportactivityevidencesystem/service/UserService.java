package cz.muni.fi.pa165.sportactivityevidencesystem.service;

import cz.muni.fi.pa165.sportactivityevidencesystem.entity.User;

/**
 * JPA CRUD operations
 * @author Jan S.
 */
public interface UserService {
    /**
     * Create user
     * @param user - user to be created
     */
    public void createUser(User user);

    /**
     * Remove user
     * @param user - user to be removed
     */
    public void removeUser(User user);

    /**
     * Find user by ID
     * @param id of the user
     * @return retrieved activity
     */
    public User findUser(Long id);

    /**
     * Update user
     * @param user - user to update
     */
    public void updateUser(User user);
}
