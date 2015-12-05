package cz.muni.fi.pa165.facade;

import cz.muni.fi.pa165.dto.UserCreateDTO;
import cz.muni.fi.pa165.dto.UserDTO;
import enums.Gender;
import java.util.List;

/**
 * @author MajoCAM
 */
public interface UserFacade {

    /**
     * Creates new user.
     * @param u user to be created.
     * @return generated id of user
     */
    public Long create(UserCreateDTO u);

    /**
     * Finds an user by given id.
     * @param id of user to find
     * @return found user
     */
    public UserDTO findById(Long id);

    /**
     * Updates user with attribute check.
     * @param u user with new data
     */
    public void update(UserDTO u);
    
    /**
     * Deletes an user by given id.
     * @param id of an user
     */
    public void delete(Long id);

    /**
     * Returns a list of all users.
     * @return
     */
    public List<UserDTO> findAll();

    /**
     * Returns a list of users filtered by criteria. All parameters can be null. 
     * If all parameters are null, a list of all users is returned.
     * @param sex - gender of an user
     * @param minAge - lower threshold for user age
     * @param maxAge - upper threshold for user age
     * @param minWeight - lower threshold for user weight
     * @param maxWeight - upper threshold for user weight
     * @return a list of users filtered by input parameters.
     */
    public List<UserDTO> findByParameters(Gender sex, Integer minAge, Integer maxAge, Integer minWeight, Integer maxWeight);
}
