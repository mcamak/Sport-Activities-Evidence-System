package cz.muni.fi.pa165.service;

import cz.muni.fi.pa165.saes.entity.ActivityRecord;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Interface to service for sport activity records.
 *
 * @author Marian Camak
 */
@Service
public interface ActivityRecordService {

    /**
     * Creates new activity record
     *
     * @param record record to be created
     */
    void create(ActivityRecord record);

    /**
     * Finds activity record with given ID
     *
     * @param id of activity
     * @return found activity record
     */
    ActivityRecord findById(Long id);

    /**
     * Finds activity records of an user
     *
     * @param id of user
     * @return found activity records of the user
     */
    List<ActivityRecord> findByUser(Long id);

    /**
     * Updates given activity record
     *
     * @param record to be updated
     */
    void update(ActivityRecord record);

    /**
     * Deletes given activity record
     *
     * @param id of the record to be deleted
     */
    void delete(Long id);

    /**
     * Finds all activity records
     *
     * @return list of found activity records
     */
    List<ActivityRecord> findAll();
}
