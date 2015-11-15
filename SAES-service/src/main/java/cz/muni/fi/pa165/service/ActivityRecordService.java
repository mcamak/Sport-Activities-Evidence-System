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

    /**
     * Create activity record
     *
     * @param activityRecord - activity record to create
     */
    public void create(ActivityRecord activityRecord);

    /**
     * Remove activity record
     *
     * @param activityRecord - activity record to remove
     */
    public void removeSportActivity(ActivityRecord activityRecord);

    /**
     * Retrieve activity record by ID
     *
     * @param id - ID of an activity record
     * @return activity record with given ID
     */
    public ActivityRecord findById(Long id);

    /**
     * Update activity record
     *
     * @param activityRecord- activity record to update
     */
    public void updateSportActivity(ActivityRecord activityRecord);

}
