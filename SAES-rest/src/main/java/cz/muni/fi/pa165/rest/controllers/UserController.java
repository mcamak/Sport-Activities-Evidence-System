package cz.muni.fi.pa165.rest.controllers;

import cz.muni.fi.pa165.dto.UserCreateDTO;
import cz.muni.fi.pa165.dto.UserDTO;
import cz.muni.fi.pa165.dto.UserLogInDTO;
import cz.muni.fi.pa165.facade.UserFacade;
import cz.muni.fi.pa165.rest.exceptions.InvalidParameterException;
import cz.muni.fi.pa165.rest.exceptions.ResourceNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;
import java.util.Collection;

/**
 * User rest controller.
 * <p/>
 * Created by Marian Camak on 12. 12. 2015.
 */
@RestController
@RequestMapping("/user")
public class UserController {

    final static Logger logger = LoggerFactory.getLogger(UserController.class);

    @Inject
    private UserFacade userFacade;

    @RequestMapping(value = "/{pass}", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public final void createUser(UserCreateDTO dto, @PathVariable("pass") String password) throws Exception {

        logger.info("REST: createUser...");
        try {
            userFacade.create(dto, password);
        } catch (IllegalArgumentException e) {
            throw new InvalidParameterException(e.getMessage());
        }
    }

    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public final boolean logIn(UserLogInDTO dto) throws Exception {

        logger.info("REST: log in user with id '" + dto.getId() + "'. ");
        try {
            return userFacade.logIn(dto);
        } catch (IllegalArgumentException e) {
            throw new InvalidParameterException(e.getMessage());
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public final UserDTO getUser(@PathVariable("id") long id) throws Exception {

        logger.info("REST: getUser with id '" + id + "'. ");
        try {
            UserDTO userDTO = userFacade.findById(id);
            if (userDTO == null) {
                throw new ResourceNotFoundException("User");
            }
            return userDTO;
        } catch (IllegalArgumentException e) {
            throw new InvalidParameterException(e.getMessage());
        }
    }

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public final Collection<UserDTO> getUsers() throws Exception {

        logger.info("REST: getAllUsers...");
        try {
            return userFacade.findAll();
        } catch (IllegalArgumentException e) {
            throw new InvalidParameterException(e.getMessage());
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public final void deleteUser(@PathVariable("id") long id) throws Exception {

        logger.info("REST: deleteUser with id '" + id + "'. ");
        try {
            userFacade.delete(id);
        } catch (IllegalArgumentException e) {
            throw new InvalidParameterException(e.getMessage());
        }
    }

    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public final void updateUser(UserDTO dto) {

        logger.info("REST: updateUser with id '" + dto.getId() + "'. ");
        userFacade.update(dto);
    }
}
