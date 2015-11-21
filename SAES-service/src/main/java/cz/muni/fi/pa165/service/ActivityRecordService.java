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

    public void create(ActivityRecord activityRecord);
    public void removeSportActivity(ActivityRecord activityRecord);
    public void removeUserFromRecord(Long activityId, Long userId);
    public ActivityRecord findById(Long id);
    public void updateSportActivity(ActivityRecord activityRecord);

}
