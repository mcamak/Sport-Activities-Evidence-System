/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.saes.dao;

import cz.muni.fi.pa165.saes.entity.ActivityRecord;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Barbora B.
 */
@Repository
public class ActivityRecordDaoImpl implements ActivityRecordDao {

    @PersistenceContext
    private EntityManager em;

    @Override
    public void create(ActivityRecord activityRecord) {
        if (activityRecord == null) {
            throw new IllegalArgumentException("Activity record is null. ");
        }
        if (activityRecord.getId() != null) {
            throw new IllegalArgumentException("Activity record ID is not null. ");
        }
        if (activityRecord.getTimeSeconds() == null) {
            throw new IllegalArgumentException("Time seconds is null. ");
        }
        if (activityRecord.getTimeSeconds() < 0) {
            throw new IllegalArgumentException("Time seconds is negative. ");
        }
        if (activityRecord.getDistance() < 0) {
            throw new IllegalArgumentException("Distance is negative. ");
        }
        if (activityRecord.getActivity() == null) {
            throw new IllegalArgumentException("Sport activity is null. ");
        }
        if (activityRecord.getUsers() == null || activityRecord.getUsers().isEmpty()) {
            throw new IllegalArgumentException("User list is null or empty. ");
        }
        em.persist(activityRecord);
    }

    @Override
    public void delete(ActivityRecord activityRecord) {
        if (activityRecord == null) {
            throw new IllegalArgumentException("Activity record is null. ");
        }
        if (activityRecord.getId() == null) {
            throw new IllegalArgumentException("Activity record ID is null. ");
        }
        em.remove(em.contains(activityRecord) ? activityRecord : em.merge(activityRecord));
    }

    @Override
    public void update(ActivityRecord activityRecord) {
        if (activityRecord == null) {
            throw new IllegalArgumentException("Activity record is null. ");
        }
        if (activityRecord.getId() == null) {
            throw new IllegalArgumentException("Activity record ID is null. ");
        }
        if (activityRecord.getTimeSeconds() == null) {
            throw new IllegalArgumentException("Time seconds is null. ");
        }
        if (activityRecord.getTimeSeconds() < 0) {
            throw new IllegalArgumentException("Time seconds is negative. ");
        }
        if (activityRecord.getDistance() < 0) {
            throw new IllegalArgumentException("Distance is negative. ");
        }
        if (activityRecord.getActivity() == null) {
            throw new IllegalArgumentException("Sport activity is null. ");
        }
        if (activityRecord.getUsers() == null || activityRecord.getUsers().isEmpty()) {
            throw new IllegalArgumentException("User list is null or empty. ");
        }
        em.merge(activityRecord);
    }

    @Override
    public ActivityRecord findActivityRecord(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("Parameter ID is null. ");
        }
        return em.find(ActivityRecord.class, id);
    }
}
