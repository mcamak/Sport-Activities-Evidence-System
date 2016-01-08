package cz.muni.fi.pa165.service;

import cz.muni.fi.pa165.saes.dao.ActivityRecordDao;
import cz.muni.fi.pa165.saes.dao.UserDao;
import cz.muni.fi.pa165.saes.entity.ActivityRecord;
import cz.muni.fi.pa165.saes.entity.User;
import cz.muni.fi.pa165.service.exceptions.SaesServiceException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.util.List;

/**
 * Implementation of service for sport activity records.
 *
 * @author Marian Camak
 */
@Service
@Transactional
public class ActivityRecordServiceImpl implements ActivityRecordService {

    @Inject
    private ActivityRecordDao activityRecordDao;

    @Inject
    private UserDao userDao;

    @Override
    public void create(ActivityRecord activityRecord) {
        activityRecordDao.create(activityRecord);
    }

    @Override
    public void delete(Long id) {
        ActivityRecord record = activityRecordDao.findActivityRecord(id);
        if (record != null) {
            activityRecordDao.delete(record);
        }
    }

    @Override
    public ActivityRecord findById(Long id) {
        return activityRecordDao.findActivityRecord(id);
    }

    @Override
    public List<ActivityRecord> findByUser(Long id) {
        User user = userDao.findUser(id);
        if (user == null) {
            throw new SaesServiceException("User with ID: " + id + " wasn't found. ");
        }
        return activityRecordDao.findRecordsByUser(user);
    }

    @Override
    public void update(ActivityRecord activityRecord) {
        activityRecordDao.update(activityRecord);
    }

    @Override
    public List<ActivityRecord> findAll() {
        return activityRecordDao.findAll();
    }
}
