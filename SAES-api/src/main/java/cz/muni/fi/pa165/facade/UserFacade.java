package cz.muni.fi.pa165.facade;

import cz.muni.fi.pa165.dto.UserCreateDTO;
import cz.muni.fi.pa165.dto.UserDTO;
import cz.muni.fi.pa165.dto.UserLogInDTO;

import java.util.List;

/**
 * @author MajoCAM
 */
public interface UserFacade {

    /**
     * Signs in new user with password.
     *
     * @param u user to be signed in
     */
    void create(UserCreateDTO u);

    /**
     * Logs in an user
     *
     * @param logIn login info containing user id and password
     * @return true if user was successfully logged in, false otherwise
     */
    boolean logIn(UserLogInDTO logIn);

    /**
     * Finds an user by given id.
     *
     * @param id of user to find
     * @return found user
     */
    UserDTO findById(Long id);

    /**
     * Finds an user by username.
     *
     * @param username of user
     * @return found user
     */
    UserDTO findByUsername(String username);

    /**
     * Finds whether user is admin or not.
     *
     * @param id of user to check
     * @return true if user is admin, false otherwise
     */
    boolean isAdmin(Long id);

    /**
     * Updates user with attribute check.
     *
     * @param u user with new data
     */
    void update(UserDTO u);

    /**
     * Deletes an user by given id.
     *
     * @param id of an user
     */
    void delete(Long id);

    /**
     * Returns a list of all users.
     *
     * @return list of found users
     */
    List<UserDTO> findAll();
}
