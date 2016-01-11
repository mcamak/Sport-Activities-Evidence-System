package cz.muni.fi.pa165.service.facade;

import cz.muni.fi.pa165.dto.UserCreateDTO;
import cz.muni.fi.pa165.dto.UserDTO;
import cz.muni.fi.pa165.dto.UserLogInDTO;
import cz.muni.fi.pa165.facade.UserFacade;
import cz.muni.fi.pa165.saes.entity.User;
import cz.muni.fi.pa165.service.UserService;
import cz.muni.fi.pa165.service.mapping.BeanMappingService;
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
    public void create(UserCreateDTO u) {
        if (u == null) {
            throw new IllegalArgumentException("User is null. ");
        }
        if (u.getPassword() == null || u.getPassword().isEmpty()) {
            throw new IllegalArgumentException("Password is null or empty. ");
        }

        userService.register(bms.mapTo(u, User.class), u.getPassword());
    }

    @Override
    public boolean logIn(UserLogInDTO logIn) {
        return userService.authenticate(userService.findByUsername(logIn.getUsername()), logIn.getPassword());
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
        userService.delete(id);
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
    public UserDTO findByUsername(String username) {
        return bms.mapTo(userService.findByUsername(username), UserDTO.class);
    }

    @Override
    public boolean isAdmin(Long id) {
        return userService.isAdmin(id);
    }
}
