package cz.muni.fi.pa165.service;

import cz.muni.fi.pa165.saes.entity.ActivityRecord;
import org.springframework.stereotype.Service;

/**
 * Interface to service for sport activity records.
 *
 * @author Tomas Effenberger
 */
@Service
public interface ActivityRecordService {

    public ActivityRecord create(ActivityRecord activityRecord);

    public ActivityRecord findById(Long id);

    public void update(ActivityRecord activityRecord);

    public void delete(ActivityRecord activityRecord);

    public void removeUserFromActivityRecord(Long activityId, Long userId);

}
