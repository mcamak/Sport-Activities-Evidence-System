/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
     * @param activityRecordCreateDTO - activity record DTO which does not
     * contain ID
     * @return ID of newly created activity record
     */
    public Long create(ActivityRecordCreateDTO activityRecordCreateDTO);

    /**
     * Finds activity record with given ID
     *
     * @param activityRecordId - ID of record to be found
     * @return found activity record
     */
    public ActivityRecordDTO findById(Long activityRecordId);

    /**
     * Updates given activity record
     *
     * @param activityRecordDTO - activity record DTO
     */
    public void update(ActivityRecordDTO activityRecordDTO);

    /**
     * Deletes activity record with given ID
     *
     * @param activityRecordId - ID of activity record to be deleted
     */
    public void delete(Long activityRecordId);
    
    /**
     * Finds all activity records
     *
     * @return list of found activity records
     */
    public List<ActivityRecordDTO> findAll();

    /**
     * Removes user from given activity record
     *
     * @param userId - ID of user to be removed from activity record
     * @param recordId - ID of record from which user will be removed
     */
    public void removeUserFromActivityRecord(Long userId, Long recordId);

}
