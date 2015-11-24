/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.facade;

import cz.muni.fi.pa165.dto.ActivityRecordDTO;
import java.util.List;

/**
 *
 * @author Barborka
 */
public interface ActivityRecordFacade {
    public Long createSportActivityRecord(ActivityRecordDTO createDTO);
    public void deleteActivityRecord(Long activityRecordId);
    public List<ActivityRecordDTO> getAllActivities();
    public ActivityRecordDTO getActivityWithId(Long id);
    
}
