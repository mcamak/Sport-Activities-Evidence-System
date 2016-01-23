package cz.muni.fi.pa165.rest.controllers;

import cz.muni.fi.pa165.dto.UserCreateDTO;
import cz.muni.fi.pa165.dto.UserDTO;
import cz.muni.fi.pa165.facade.UserFacade;
import cz.muni.fi.pa165.rest.exceptions.InvalidParameterException;
import cz.muni.fi.pa165.rest.exceptions.ResourceNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import java.util.Collection;

/**
 * User rest controller.
 *
 * Created by Marian Camak on 12. 12. 2015.
 */
@RestController
@RequestMapping("/users")
public class UsersController {

    final static Logger logger = LoggerFactory.getLogger(UsersController.class);

    @Inject
    private UserFacade userFacade;

    @RequestMapping(value = "/list", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public final Collection<UserDTO> list() throws Exception {

        logger.info("REST: listing all users...");
        try {
            return userFacade.findAll();
        } catch (IllegalArgumentException e) {
            throw new InvalidParameterException(e.getMessage());
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public final UserDTO get(@PathVariable("id") long id) throws Exception {

        logger.info("REST: getting user " + id + ". ");
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

    @RequestMapping(value = "/create", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public final void create(@RequestBody UserCreateDTO dto) throws Exception {

        logger.info("REST: creating user.");
        try {
            userFacade.create(dto);
        } catch (IllegalArgumentException e) {
            throw new InvalidParameterException(e.getMessage());
        }
    }

    @RequestMapping(value = "/update", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    public final void update(@RequestBody UserDTO dto) {

        logger.info("REST: updateUser with id '" + dto.getId() + "'. ");
        userFacade.update(dto);
    }

    @RequestMapping(value = "/admin/{id}", method = RequestMethod.GET)
    public final boolean isAdmin(@PathVariable("id") long id) {

        logger.info("REST: checking if user " + id + " is admin. ");
        return userFacade.isAdmin(id);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public final void delete(@PathVariable("id") long id) throws Exception {

        logger.info("REST: deleteUser with id '" + id + "'. ");
        try {
            userFacade.delete(id);
        } catch (IllegalArgumentException e) {
            throw new InvalidParameterException(e.getMessage());
        }
    }
}
