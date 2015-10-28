/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.sportactivityevidencesystem.dao;
import cz.muni.fi.pa165.sportactivityevidencesystem.entity.ActivityRecord;

/**
 *
 * @author Barborka
 */
public interface ActivityRecordDao {
    
    /**
     * Create activity record in DB
     * @param activityRecord 
     */
    public void create(ActivityRecord activityRecord);
    
    /**
     * Delete activity record form DB
     * @param actvityRecord 
     */
    public void delete(ActivityRecord actvityRecord);
    
    /**
     * Update activity record in DB
     * @param activityRecord 
     */
    public void update(ActivityRecord activityRecord);
    
    /**
     * Find activity record by id 
     * @param id
     * @return activity record with given id
     */
    public ActivityRecord findActivityRecord(Long id);
    
    
}
