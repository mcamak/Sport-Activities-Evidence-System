package cz.muni.fi.pa165.mvc.config;

import cz.muni.fi.pa165.dto.UserDTO;
import cz.muni.fi.pa165.facade.UserFacade;
import org.springframework.core.convert.converter.Converter;

/**
 * Created by Marian Camak on 11. 1. 2016.
 */
public class UserConverter implements Converter<String, UserDTO> {

    private UserFacade userFacade;

    public UserConverter(UserFacade userFacade) {
        this.userFacade = userFacade;
    }

    @Override
    public UserDTO convert(String id) {
        if (id == null || id.isEmpty()) {
            return null;
        }
        return userFacade.findById(Long.valueOf(id));
    }
}
