package cz.muni.fi.pa165.service;

import cz.muni.fi.pa165.exceptions.SaesServiceException;
import cz.muni.fi.pa165.saes.dao.ActivityRecordDao;
import cz.muni.fi.pa165.saes.dao.BurnedCaloriesDao;
import cz.muni.fi.pa165.saes.dao.UserDao;
import cz.muni.fi.pa165.saes.entity.ActivityRecord;
import cz.muni.fi.pa165.saes.entity.BurnedCalories;
import cz.muni.fi.pa165.saes.entity.SportActivity;
import cz.muni.fi.pa165.saes.entity.User;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.Collections;
import java.util.List;

/**
 * Implementation of service for sport activity records.
 *
 * @author Marian Camak
 */
@Service
public class ActivityRecordServiceImpl implements ActivityRecordService {

    @Inject
    private ActivityRecordDao activityRecordDao;

    @Inject
    private UserDao userDao;

    @Inject
    private BurnedCaloriesDao caloriesDao;

    @Override
    public void create(ActivityRecord record) {
        calculateCaloriesBurned(record);
        activityRecordDao.create(record);
    }

    @Override
    public void delete(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("Id is null. ");
        }
        ActivityRecord record = activityRecordDao.findActivityRecord(id);
        if (record != null) {
            activityRecordDao.delete(record);
        }
    }

    @Override
    public ActivityRecord findById(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("Id is null. ");
        }
        return activityRecordDao.findActivityRecord(id);
    }

    @Override
    public List<ActivityRecord> findByUser(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("User id is null. ");
        }
        User user = userDao.findUser(id);
        if (user == null) {
            throw new SaesServiceException("User with ID: " + id + " wasn't found. ");
        }
        List<ActivityRecord> records = activityRecordDao.findRecordsByUser(user);
        if (records != null && records.size() > 1) {
            Collections.sort(records);
        }
        return records;
    }

    @Override
    public void update(ActivityRecord record) {
        calculateCaloriesBurned(record);
        activityRecordDao.update(record);
    }

    @Override
    public List<ActivityRecord> findAll() {
        List<ActivityRecord> records = activityRecordDao.findAll();
        if (records != null && records.size() > 1) {
            Collections.sort(records);
        }
        return records;
    }

    private void checkRecord(ActivityRecord record) {
        if (record == null) {
            throw new IllegalArgumentException("Record is null. ");
        }
        if (record.getUser() == null) {
            throw new IllegalArgumentException("Record's user is null. ");
        }
        if (record.getActivity() == null) {
            throw new IllegalArgumentException("Record's sport activity is null. ");
        }
        if (record.getTime() == null) {
            throw new IllegalArgumentException("Record's time is null. ");
        }
    }

    private void calculateCaloriesBurned(ActivityRecord record) {
        checkRecord(record);
        User user = record.getUser();
        SportActivity activity = record.getActivity();
        Double burned = interpolateCalories(caloriesDao.findBySportActivity(activity), user.getWeight());
        switch (user.getSex()) {
            case MALE:
                burned += (40 - user.getAge()) * 2.3;
                break;
            case FEMALE:
                burned += (40 - user.getAge()) * 1.6;
                break;
        }
        burned *= record.getTime() / 60F;
        record.setBurnedCalories(burned.intValue());
    }

    private double interpolateCalories(List<BurnedCalories> calories, double weight) {
        if (calories == null || calories.isEmpty()) {
            throw new SaesServiceException("There is no burned calories assigned to your sport activity, " +
                    "cannot calculate calories you've burned. ");
        }
        for (BurnedCalories calorie : calories) {
            double calWeight = calorie.getBodyWeight();
            double calBurned = calorie.getCaloriesBurned();
            if (weight < calWeight) {
                return weight / calWeight * calBurned;
            }
        }
        BurnedCalories lastCalorie = calories.get(calories.size() - 1);
        double calWeight = lastCalorie.getBodyWeight();
        double calBurned = lastCalorie.getCaloriesBurned();

        return weight / calWeight * calBurned;
    }
}
