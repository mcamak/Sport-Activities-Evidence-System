/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.service.facade;

import cz.muni.fi.pa165.dto.ActivityRecordDTO;
import cz.muni.fi.pa165.facade.ActivityRecordFacade;
import cz.muni.fi.pa165.saes.entity.ActivityRecord;
import cz.muni.fi.pa165.service.ActivityRecordService;
//import cz.muni.fi.pa165.service.SportActivityService;
import cz.muni.fi.pa165.service.mapping.BeanMappingService;
import java.util.List;
import javax.inject.Inject;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author B. Bajtosova
 */

@Service
@Transactional
public class ActivityRecordFacadeImpl implements ActivityRecordFacade{
    
    @Inject 
    private ActivityRecordService activityRecordService;
    
    
    @Inject
    private BeanMappingService bms;

    @Override
    public Long createSportActivityRecord(ActivityRecordDTO recordDTO) {
        ActivityRecord newActivity;
        newActivity = activityRecordService.create(bms.mapTo(recordDTO, ActivityRecord.class));
        return newActivity.getId();
        
    }

    @Override
    public void deleteActivityRecord(Long activityRecordId) {
        activityRecordService.removeSportActivity(activityRecordService.findById(activityRecordId));
    }


    @Override
    public ActivityRecordDTO getActivityWithId(Long id) {
        return bms.mapTo(activityRecordService.findById(id), ActivityRecordDTO.class);
    }
    
}
