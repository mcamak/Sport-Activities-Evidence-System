package cz.muni.fi.pa165.service.facade;

import cz.muni.fi.pa165.dto.ActivityRecordCreateDTO;
import cz.muni.fi.pa165.dto.ActivityRecordDTO;
import cz.muni.fi.pa165.facade.ActivityRecordFacade;
import cz.muni.fi.pa165.saes.entity.ActivityRecord;
import cz.muni.fi.pa165.service.ActivityRecordService;
import cz.muni.fi.pa165.service.mapping.BeanMappingService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.util.List;

/**
 *
 * @author B. Bajtosova
 */
@Service
@Transactional
public class ActivityRecordFacadeImpl implements ActivityRecordFacade {

    @Inject
    private ActivityRecordService activityRecordService;

    @Inject
    private BeanMappingService bms;

    @Override
    public Long create(ActivityRecordCreateDTO recordCreateDTO) {
        ActivityRecord created = bms.mapTo(recordCreateDTO, ActivityRecord.class);
        activityRecordService.create(created);
        return created.getId();
    }

    @Override
    public void delete(Long recordId) {
        activityRecordService.delete(recordId);
    }

    @Override
    public ActivityRecordDTO findById(Long recordId) {
        return bms.mapTo(activityRecordService.findById(recordId), ActivityRecordDTO.class);
    }

    @Override
    public List<ActivityRecordDTO> findByUser(Long userId) {
        return bms.mapTo(activityRecordService.findByUser(userId), ActivityRecordDTO.class);
    }

    @Override
    public void update(ActivityRecordDTO recordDTO) {
        activityRecordService.update(bms.mapTo(recordDTO, ActivityRecord.class));
    }

    @Override
    public List<ActivityRecordDTO> findAll() {
        return bms.mapTo(activityRecordService.findAll(), ActivityRecordDTO.class);
    }
}
