package cz.muni.fi.pa165.saes.dao;

import cz.muni.fi.pa165.saes.entity.ActivityRecord;
import cz.muni.fi.pa165.saes.entity.SportActivity;
import cz.muni.fi.pa165.saes.entity.User;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 *
 * @author Barbora B.
 */
@Transactional
@Repository
public class ActivityRecordDaoImpl implements ActivityRecordDao {

    @PersistenceContext
    private EntityManager em;

    @Override
    public void create(ActivityRecord activityRecord) {
        checkActivityRecord(activityRecord);
        if (activityRecord.getId() != null) {
            throw new IllegalArgumentException("Activity record ID is not null. ");
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
        em.remove(activityRecord);
    }

    @Override
    public void deleteUserRecords(User user) {
        if (user == null) {
            throw new IllegalArgumentException("User is null. ");
        }
        em.createQuery("DELETE FROM ActivityRecord a WHERE a.user = :user")
                .setParameter("user", user)
                .executeUpdate();
    }

    @Override
    public void deleteRecordsBySportActivity(SportActivity activity) {
        if (activity == null) {
            throw new IllegalArgumentException("Activity is null. ");
        }
        em.createQuery("DELETE FROM ActivityRecord a WHERE a.activity = :activity")
                .setParameter("activity", activity)
                .executeUpdate();
    }

    @Override
    public void update(ActivityRecord activityRecord) {
        checkActivityRecord(activityRecord);
        if (activityRecord.getId() == null) {
            throw new IllegalArgumentException("Activity record ID is null. ");
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

    @Override
    public List<ActivityRecord> findRecordsByUser(User user) {
        if (user == null) {
            throw new IllegalArgumentException("User is null. ");
        }
        return em.createQuery("SELECT a FROM ActivityRecord a WHERE a.user = :user", ActivityRecord.class)
                .setParameter("user", user)
                .getResultList();
    }

    @Override
    public List<ActivityRecord> findRecordsBySportActivity(SportActivity activity) {
        if (activity == null) {
            throw new IllegalArgumentException("Activity ID is null. ");
        }
        return em.createQuery("SELECT a FROM ActivityRecord a WHERE a.activity = :activity", ActivityRecord.class)
                .setParameter("activity", activity)
                .getResultList();
    }

    @Override
    public List<ActivityRecord> findAll() {
        return em.createQuery("SELECT a FROM ActivityRecord a", ActivityRecord.class).getResultList();
    }

    private void checkActivityRecord(ActivityRecord record) throws IllegalArgumentException {
        if (record == null) {
            throw new IllegalArgumentException("Activity record is null. ");
        }
        if (record.getActivity() == null) {
            throw new IllegalArgumentException("Sport activity is null. ");
        }
        if (record.getUser() == null) {
            throw new IllegalArgumentException("User is null. ");
        }
        if (record.getTime() == null) {
            throw new IllegalArgumentException("Time seconds is null. ");
        }
        if (record.getTime() < 0) {
            throw new IllegalArgumentException("Time seconds is negative. ");
        }
        if (record.getDistance() != null && record.getDistance() < 0) {
            throw new IllegalArgumentException("Distance is negative. ");
        }
        if (record.getBurnedCalories() == null) {
            throw new IllegalArgumentException("Burned calories are null. ");
        }
        if (record.getBurnedCalories() < 0) {
            throw new IllegalArgumentException("Burned calories are negative. ");
        }
    }
}
