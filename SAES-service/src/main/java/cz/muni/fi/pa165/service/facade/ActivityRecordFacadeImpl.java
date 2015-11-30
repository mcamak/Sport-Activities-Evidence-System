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
import cz.muni.fi.pa165.service.mapping.BeanMappingService;
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
    public Long create(ActivityRecordDTO recordDTO) {
        ActivityRecord newActivity = bms.mapTo(recordDTO, ActivityRecord.class);
        activityRecordService.create(newActivity);
        return newActivity.getId();
        
    }

    @Override
    public void deleteActivityRecord(Long activityRecordId) {
        activityRecordService.deleteActivityRecord(activityRecordService.findById(activityRecordId));
    }


    @Override
    public ActivityRecordDTO findById(Long id) {
        return bms.mapTo(activityRecordService.findById(id), ActivityRecordDTO.class);
    }
    
    @Override
    public void updateActivityRecord(ActivityRecordDTO activityRecord){
        activityRecordService.update(bms.mapTo(activityRecord, ActivityRecord.class));
    
    }
    
}
