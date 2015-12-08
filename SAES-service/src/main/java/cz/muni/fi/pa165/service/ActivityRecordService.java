package cz.muni.fi.pa165.service;

import cz.muni.fi.pa165.saes.entity.ActivityRecord;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 * Interface to service for sport activity records.
 *
 * @author Tomas Effenberger
 */
@Service
public interface ActivityRecordService {

    /**
     * Creates new activity record
     *
     * @param activityRecord
     * @return ID of newly created activity record
     */
    public ActivityRecord create(ActivityRecord activityRecord);

    /**
     * Finds activity record with given ID
     *
     * @param id
     * @return found activity record
     */
    public ActivityRecord findById(Long id);

    /**
     * Updates given activity record
     *
     * @param activityRecord
     */
    public void update(ActivityRecord activityRecord);

    /**
     * Deletes given activity record
     *
     * @param activityRecord
     */
    public void delete(ActivityRecord activityRecord);

    /**
     * Finds all activity records
     *
     * @return list of found activity records
     */
    public List<ActivityRecord> findAll();

    /**
     * Removes user from given activity record
     *
     * @param userId - ID of user to be removed from activity record
     * @param activityId - ID of record from which user will be removed
     */
    public void removeUserFromActivityRecord(Long activityId, Long userId);

}
