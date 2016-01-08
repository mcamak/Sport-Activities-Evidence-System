package cz.muni.fi.pa165.service.facade;

import cz.muni.fi.pa165.dto.SportActivityCreateDTO;
import cz.muni.fi.pa165.dto.SportActivityDTO;
import cz.muni.fi.pa165.exceptions.EntityReferenceException;
import cz.muni.fi.pa165.facade.SportActivityFacade;
import cz.muni.fi.pa165.saes.entity.SportActivity;
import cz.muni.fi.pa165.service.SportActivityService;
import cz.muni.fi.pa165.service.mapping.BeanMappingService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.util.List;

/**
 *
 * @author MajoCAM
 */
@Service
@Transactional
public class SportActivityFacadeImpl implements SportActivityFacade {

    @Inject
    private SportActivityService sportActivityService;

    @Inject
    private BeanMappingService bms;

    @Override
    public Long create(SportActivityCreateDTO sDTO) {
        SportActivity created = bms.mapTo(sDTO, SportActivity.class);
        sportActivityService.create(created);
        return created.getId();
    }

    @Override
    public void update(SportActivityDTO activity) {
        sportActivityService.update(bms.mapTo(activity, SportActivity.class));
    }

    @Override
    public void delete(Long activityId) throws EntityReferenceException {
        sportActivityService.delete(activityId);
    }

    @Override
    public List<SportActivityDTO> findAll() {
        return bms.mapTo(sportActivityService.findAll(), SportActivityDTO.class);
    }

    @Override
    public SportActivityDTO findById(Long id) {
        return bms.mapTo(sportActivityService.findById(id), SportActivityDTO.class);
    }
}
