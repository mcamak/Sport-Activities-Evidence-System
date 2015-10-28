/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.sportactivityevidencesystem.dao;

import cz.muni.fi.pa165.sportactivityevidencesystem.entity.ActivityRecord;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


/**
 *
 * @author Barborka
 */
public class ActivityRecordDaoImpl implements ActivityRecordDao{
    
     @PersistenceContext
    private EntityManager em;

    @Override
    public void create(ActivityRecord activityRecord) {
        em.persist(activityRecord);
    }

    @Override
    public void delete(ActivityRecord activityRecord) {
        em.remove(activityRecord);
    }

    @Override
    public void update(ActivityRecord activityRecord) {
        em.merge(activityRecord);
   
    }

    @Override
    public ActivityRecord findActivityRecord(Long id) {
        return em.find(ActivityRecord.class, id);
       
    }
    
    
}
