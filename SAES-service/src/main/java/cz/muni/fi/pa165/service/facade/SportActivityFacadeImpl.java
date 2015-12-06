package cz.muni.fi.pa165.service.facade;

import cz.muni.fi.pa165.dto.SportActivityCreateDTO;
import cz.muni.fi.pa165.dto.SportActivityDTO;
import cz.muni.fi.pa165.facade.SportActivityFacade;
import cz.muni.fi.pa165.saes.entity.SportActivity;
import cz.muni.fi.pa165.service.SportActivityService;
import cz.muni.fi.pa165.service.mapping.BeanMappingService;
import java.util.List;
import javax.inject.Inject;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    public void update(SportActivityDTO old, String newName) {
        sportActivityService.changeName(bms.mapTo(old, SportActivity.class), newName);
    }

    @Override
    public void delete(Long activityId) {
        sportActivityService.delete(sportActivityService.findById(activityId));
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
