package cz.muni.fi.pa165.service;

import cz.muni.fi.pa165.saes.dao.ActivityRecordDao;
import cz.muni.fi.pa165.saes.dao.UserDao;
import cz.muni.fi.pa165.saes.entity.ActivityRecord;
import cz.muni.fi.pa165.saes.entity.User;
import javax.inject.Inject;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Implementation of service for sport activity records.
 *
 * @author Tomas Effenberger
 */
@Service
@Transactional
public class ActivityRecordServiceImpl implements ActivityRecordService {

    @Inject
    private ActivityRecordDao activityRecordDao;
    
    @Inject
    private UserDao userDao;

    @Override
    public ActivityRecord create(ActivityRecord activityRecord) {
        activityRecordDao.create(activityRecord);
        return activityRecord;
    }

    @Override
    public void delete(ActivityRecord activityRecord) {
        activityRecordDao.delete(activityRecord);
    }

    @Override
    public ActivityRecord findById(Long id) {
        return activityRecordDao.findActivityRecord(id);
    }

    @Override
    public void update(ActivityRecord activityRecord) {
        activityRecordDao.update(activityRecord);
    }

    @Override
    public void removeUserFromActivityRecord(Long activityId, Long userId) {
        ActivityRecord record = activityRecordDao.findActivityRecord(activityId);
        User user = userDao.findUser(userId);
        record.removeUser(user);
        activityRecordDao.update(record);
    }
    
}
