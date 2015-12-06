package cz.muni.fi.pa165.facade;

import cz.muni.fi.pa165.dto.UserCreateDTO;
import cz.muni.fi.pa165.dto.UserDTO;
import cz.muni.fi.pa165.dto.UserLogInDTO;
import enums.Gender;
import java.util.List;

/**
 * @author MajoCAM
 */
public interface UserFacade {

    /**
     * Sign in new user with password.
     *
     * @param u user to be signed in
     * @param password - unencrypted password
     * @return ID assigned to the user
     */
    public Long signIn(UserCreateDTO u, String password);

    /**
     * Log in an user
     *
     * @param logIn login info containing user id and password
     * @return true if user was succefully logged in, false otherwise
     */
    public boolean logIn(UserLogInDTO logIn);

    /**
     * Changes password of user.
     *
     * @param id of user changing its password
     * @param oldPassword old user password to be changed
     * @param newPassword new user password
     */
    public void changePassword(Long id, String oldPassword, String newPassword);

    /**
     * Finds an user by given id.
     *
     * @param id of user to find
     * @return found user
     */
    public UserDTO findById(Long id);

    /**
     * Updates user with attribute check.
     *
     * @param u user with new data
     */
    public void update(UserDTO u);

    /**
     * Deletes an user by given id.
     *
     * @param id of an user
     */
    public void delete(Long id);

    /**
     * Returns a list of all users.
     *
     * @return
     */
    public List<UserDTO> findAll();

    /**
     * Returns a list of users filtered by criteria. All parameters can be null.
     * If all parameters are null, a list of all users is returned.
     *
     * @param sex - gender of an user
     * @param minAge - lower threshold for user age
     * @param maxAge - upper threshold for user age
     * @param minWeight - lower threshold for user weight
     * @param maxWeight - upper threshold for user weight
     * @return a list of users filtered by input parameters.
     */
    public List<UserDTO> findByParameters(Gender sex, Integer minAge, Integer maxAge, Integer minWeight, Integer maxWeight);
}
