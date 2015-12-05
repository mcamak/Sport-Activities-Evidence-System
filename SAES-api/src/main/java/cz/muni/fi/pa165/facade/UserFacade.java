package cz.muni.fi.pa165.facade;

import cz.muni.fi.pa165.dto.UserCreateDTO;
import cz.muni.fi.pa165.dto.UserDTO;
import enums.Gender;
import java.util.List;

/**
 * @author MajoCAM
 */
public interface UserFacade {

    public Long create(UserCreateDTO u);

    public UserDTO findById(Long id);

    public void update(UserDTO u);

    public void delete(Long id);

    public List<UserDTO> findAll();

    public List<UserDTO> findByParameters(Gender sex, Integer minAge, Integer maxAge, Integer minWeight, Integer maxWeight);
}
