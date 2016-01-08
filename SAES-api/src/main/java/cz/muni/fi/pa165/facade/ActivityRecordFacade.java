package cz.muni.fi.pa165.facade;

import cz.muni.fi.pa165.dto.ActivityRecordCreateDTO;
import cz.muni.fi.pa165.dto.ActivityRecordDTO;

import java.util.List;

/**
 *
 * @author B. Bajtosova
 */
public interface ActivityRecordFacade {

    /**
     * Creates new activity record
     *
     * @param recordCreateDTO - activity record DTO which does not
     * contain ID
     */
    Long create(ActivityRecordCreateDTO recordCreateDTO);

    /**
     * Finds activity record with given ID
     *
     * @param recordId - ID of record to be found
     * @return found activity record
     */
    ActivityRecordDTO findById(Long recordId);

    /**
     * Finds activity record of an user
     *
     * @param userId - ID of user which records will be returned
     * @return found activity records
     */
    List<ActivityRecordDTO> findByUser(Long userId);

    /**
     * Updates given activity record
     *
     * @param recordDTO - activity record DTO
     */
    void update(ActivityRecordDTO recordDTO);

    /**
     * Deletes activity record with given ID
     *
     * @param recordId - ID of activity record to be deleted
     */
    void delete(Long recordId);
    
    /**
     * Finds all activity records
     *
     * @return list of found activity records
     */
    List<ActivityRecordDTO> findAll();
}
