package cz.muni.fi.pa165.service;

import cz.muni.fi.pa165.exceptions.EntityReferenceException;
import cz.muni.fi.pa165.saes.dao.ActivityRecordDao;
import cz.muni.fi.pa165.saes.dao.BurnedCaloriesDao;
import cz.muni.fi.pa165.saes.dao.SportActivityDao;
import cz.muni.fi.pa165.saes.entity.ActivityRecord;
import cz.muni.fi.pa165.saes.entity.SportActivity;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.Collections;
import java.util.List;

/**
 * Implementation of the {@link SportActivityService}.
 *
 * @author MajoCAM
 */
@Service
public class SportActivityServiceImpl implements SportActivityService {

    @Inject
    private SportActivityDao sportActivityDao;

    @Inject
    private BurnedCaloriesDao burnedCaloriesDao;

    @Inject
    private ActivityRecordDao activityRecordDao;

    @Override
    public void create(SportActivity activity) {
        if (activity == null) {
            throw new IllegalArgumentException("Sport activity is null. ");
        }
        sportActivityDao.createSportActivity(activity);
    }

    @Override
    public void delete(Long id) throws EntityReferenceException {
        if (id == null) {
            throw new IllegalArgumentException("ID is null. ");
        }
        SportActivity activity = sportActivityDao.findSportActivity(id);
        if (activity != null) {
            List<ActivityRecord> recordsBySportActivity = activityRecordDao.findRecordsBySportActivity(activity);
            if (recordsBySportActivity.isEmpty()) {
                sportActivityDao.deleteSportActivity(activity);
                burnedCaloriesDao.deleteBySportActivity(activity);
            } else {
                throw new EntityReferenceException("Cannot delete sport activity, there are " +
                        recordsBySportActivity.size() + " referenced to it. ");
            }
        }
    }

    @Override
    public SportActivity findById(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("ID is null. ");
        }
        return sportActivityDao.findSportActivity(id);
    }

    @Override
    public List<SportActivity> findAll() {
        List<SportActivity> activities = sportActivityDao.findAll();
        if (activities != null && activities.size() > 1) {
            Collections.sort(activities);
        }
        return activities;
    }

    @Override
    public void update(SportActivity activity) {
        if (activity == null) {
            throw new IllegalArgumentException("Sport activity is null. ");
        }
        sportActivityDao.updateSportActivity(activity);
    }
}
