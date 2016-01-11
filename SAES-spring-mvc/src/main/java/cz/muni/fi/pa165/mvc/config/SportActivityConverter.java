package cz.muni.fi.pa165.mvc.config;

import cz.muni.fi.pa165.dto.SportActivityDTO;
import cz.muni.fi.pa165.facade.SportActivityFacade;
import org.springframework.core.convert.converter.Converter;

/**
 * Created by Marian Camak on 11. 1. 2016.
 */
public class SportActivityConverter implements Converter<String, SportActivityDTO> {

    private SportActivityFacade activityFacade;

    public SportActivityConverter(SportActivityFacade activityFacade) {
        this.activityFacade = activityFacade;
    }

    @Override
    public SportActivityDTO convert(String id) {
        if (id == null || id.isEmpty()) {
            return null;
        }
        return activityFacade.findById(Long.valueOf(id));
    }
}
