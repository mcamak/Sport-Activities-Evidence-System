package cz.muni.fi.pa165.facade;

import cz.muni.fi.pa165.dto.SportActivityDTO;
import java.util.List;

/**
 *
 * @author MajoCAM
 */
public interface SportActivityFacade {
    public Long createSportActivity(SportActivityDTO createDTO);
    public void changeActivityName(SportActivityDTO old, String newName);
    public void deleteActivity(Long activityId);
    public List<SportActivityDTO> getAllActivities();
    public SportActivityDTO getActivityWithId(Long id);
}
