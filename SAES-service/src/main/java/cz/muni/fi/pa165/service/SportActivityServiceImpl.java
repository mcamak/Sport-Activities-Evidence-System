package cz.muni.fi.pa165.service;

import cz.muni.fi.pa165.saes.dao.ActivityRecordDao;
import cz.muni.fi.pa165.saes.dao.BurnedCaloriesDao;
import cz.muni.fi.pa165.saes.dao.SportActivityDao;
import cz.muni.fi.pa165.saes.entity.ActivityRecord;
import cz.muni.fi.pa165.saes.entity.SportActivity;
import cz.muni.fi.pa165.service.exceptions.EntityReferenceException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.util.List;

/**
 * Implementation of the {@link SportActivityService}.
 *
 * @author MajoCAM
 */
@Service
@Transactional
public class SportActivityServiceImpl implements SportActivityService {

    @Inject
    private SportActivityDao sportActivityDao;

    @Inject
    private BurnedCaloriesDao burnedCaloriesDao;

    @Inject
    private ActivityRecordDao activityRecordDao;

    @Override
    public SportActivity create(SportActivity activity) {
        sportActivityDao.createSportActivity(activity);

        return activity;
    }

    @Override
    public void delete(Long id) throws EntityReferenceException {
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
        return sportActivityDao.findSportActivity(id);
    }

    @Override
    public List<SportActivity> findAll() {
        return sportActivityDao.findAll();
    }

    @Override
    public void update(SportActivity activity) {
        sportActivityDao.updateSportActivity(activity);
    }
}
