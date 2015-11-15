package cz.muni.fi.pa165.service.facade;

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
    private SportActivityService activityService;

    @Inject
    private BeanMappingService bms;

    @Override
    public Long createSportActivity(SportActivityDTO sDTO) {
        SportActivity created = activityService.createSportActivity(bms.mapTo(sDTO, SportActivity.class));
        return created.getId();
    }

    @Override
    public void changeActivityName(SportActivityDTO old, String newName) {
        activityService.changeName(bms.mapTo(old, SportActivity.class), newName);
    }

    @Override
    public void deleteActivity(Long activityId) {
        activityService.deleteSportActivity(activityService.findById(activityId));
    }

    @Override
    public List<SportActivityDTO> getAllActivities() {
        return bms.mapTo(activityService.findAll(), SportActivityDTO.class);
    }

    @Override
    public SportActivityDTO getActivityWithId(Long id) {
        return bms.mapTo(activityService.findById(id), SportActivityDTO.class);
    }
}
