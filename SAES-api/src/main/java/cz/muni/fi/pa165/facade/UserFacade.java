package cz.muni.fi.pa165.facade;

import cz.muni.fi.pa165.dto.UserCreateDTO;
import cz.muni.fi.pa165.dto.UserDTO;
import enums.Gender;
import java.util.List;

/**
 * @author MajoCAM
 */
public interface UserFacade {
    
    public Long createUser(UserCreateDTO u);
    public void changeUserData(UserDTO u);
    public void deleteUser(Long id);
    public void saveActivityRecord(Long userId, Long sportActivityId, Long seconds, Integer distance);
    public void removeActivityRecord(Long userId, Long recordId);
    public List<UserDTO> getAllUsers();
    public UserDTO getUserWithId(Long id);
    public List<UserDTO> getUserByParameters(Gender sex, Integer minAge, Integer maxAge, Integer minWeight, Integer maxWeight);
}
