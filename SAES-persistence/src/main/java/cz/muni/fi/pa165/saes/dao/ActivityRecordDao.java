/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.saes.dao;

import cz.muni.fi.pa165.saes.entity.ActivityRecord;
import cz.muni.fi.pa165.saes.entity.SportActivity;
import cz.muni.fi.pa165.saes.entity.User;

import java.util.List;

/**
 *
 * @author Barborka
 */
public interface ActivityRecordDao {

    /**
     * Create activity record in DB
     *
     * @param activityRecord record to be created
     */
    void create(ActivityRecord activityRecord);

    /**
     * Find activity record by id
     *
     * @param id of the record
     * @return activity record with given id
     */
    ActivityRecord findActivityRecord(Long id);

    /**
     * Find records of an user.
     *
     * @param user which record will be returned
     * @return collection of user's records
     */
    List<ActivityRecord> findRecordsByUser(User user);

    /**
     * Find records by Sport Activity
     *
     * @param activity which records will be returned
     * @return collection of records of activity
     */
    List<ActivityRecord> findRecordsBySportActivity(SportActivity activity);

    /**
     * Delete activity record form DB
     *
     * @param actvityRecord record to be deleted
     */
    void delete(ActivityRecord actvityRecord);

    /**
     * Delete all user's records
     *
     * @param user which records will be deleted
     */
    void deleteUserRecords(User user);

    /**
     * Update activity record in DB
     *
     * @param activityRecord record to be updated
     */
    void update(ActivityRecord activityRecord);

    /**
     * Finds all records.
     *
     * @return list of found activity records
     */
    List<ActivityRecord> findAll();
}
