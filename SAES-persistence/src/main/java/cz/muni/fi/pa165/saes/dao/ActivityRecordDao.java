/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.saes.dao;

import cz.muni.fi.pa165.saes.entity.ActivityRecord;
import java.util.List;

/**
 *
 * @author Barborka
 */
public interface ActivityRecordDao {

    /**
     * Create activity record in DB
     *
     * @param activityRecord
     */
    public void create(ActivityRecord activityRecord);

    /**
     * Find activity record by id
     *
     * @param id
     * @return activity record with given id
     */
    public ActivityRecord findActivityRecord(Long id);

    /**
     * Delete activity record form DB
     *
     * @param actvityRecord
     */
    public void delete(ActivityRecord actvityRecord);

    /**
     * Update activity record in DB
     *
     * @param activityRecord
     */
    public void update(ActivityRecord activityRecord);

    /**
     * Finds all records.
     *
     * @return list of found activity records
     */
    public List<ActivityRecord> findAll();

}
