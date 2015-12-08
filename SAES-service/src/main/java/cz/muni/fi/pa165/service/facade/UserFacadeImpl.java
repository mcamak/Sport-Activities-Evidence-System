package cz.muni.fi.pa165.service.facade;

import cz.muni.fi.pa165.dto.UserCreateDTO;
import cz.muni.fi.pa165.dto.UserDTO;
import cz.muni.fi.pa165.dto.UserLogInDTO;
import cz.muni.fi.pa165.facade.UserFacade;
import cz.muni.fi.pa165.saes.UserFilter;
import cz.muni.fi.pa165.saes.entity.User;
import cz.muni.fi.pa165.service.UserService;
import cz.muni.fi.pa165.service.mapping.BeanMappingService;
import enums.Gender;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.util.List;

/**
 * @author MajoCAM
 */
@Service
@Transactional
public class UserFacadeImpl implements UserFacade {

    @Inject
    private UserService userService;

    @Inject
    private BeanMappingService bms;

    @Override
    public Long create(UserCreateDTO u, String password) {
        if (u == null) {
            throw new IllegalArgumentException("User is null. ");
        }
        if (password == null || password.isEmpty()) {
            throw new IllegalArgumentException("Password is null or empty. ");
        }
        
        User user = bms.mapTo(u, User.class);

        return userService.create(user, password);
    }

    @Override
    public boolean logIn(UserLogInDTO logIn) {
        return userService.authenticate(logIn.getId(), logIn.getPassword());
    }

    @Override
    public void changePassword(Long id, String oldPassword, String newPassword) {
        userService.changePassword(id, oldPassword, newPassword);
    }

    @Override
    public void update(UserDTO u) {
        if (u == null) {
            throw new IllegalArgumentException("User is null. ");
        }
        userService.update(bms.mapTo(u, User.class));
    }

    @Override
    public void delete(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("ID is null. ");
        }
        userService.delete(userService.findById(id));
    }

    @Override
    public List<UserDTO> findAll() {
        return bms.mapTo(userService.findAll(), UserDTO.class);
    }

    @Override
    public UserDTO findById(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("ID is null. ");
        }
        return bms.mapTo(userService.findById(id), UserDTO.class);
    }

    @Override
    public List<UserDTO> findByParameters(enums.Gender sex, Integer minAge, Integer maxAge, Integer minWeight, Integer maxWeight) {
        UserFilter filter = new UserFilter();
        if (sex != null) {
            if (sex.name().equals(Gender.MALE.name())) {
                filter.addGender(Gender.MALE);
            }
            if (sex.name().equals(Gender.FEMALE.name())) {
                filter.addGender(Gender.FEMALE);
            }

        }
        if (minAge != null) {
            filter.setMinAge(minAge);
        }
        if (maxAge != null) {
            filter.setMaxAge(maxAge);
        }
        if (minWeight != null) {
            filter.setMinWeight(minWeight);
        }
        if (maxWeight != null) {
            filter.setMaxWeight(maxWeight);
        }

        return bms.mapTo(userService.findByParameters(filter), UserDTO.class);
    }
}
