package cz.muni.fi.pa165.data;

import cz.muni.fi.pa165.saes.entity.ActivityRecord;
import cz.muni.fi.pa165.saes.entity.BurnedCalories;
import cz.muni.fi.pa165.saes.entity.SportActivity;
import cz.muni.fi.pa165.saes.entity.User;
import cz.muni.fi.pa165.service.ActivityRecordService;
import cz.muni.fi.pa165.service.BurnedCaloriesService;
import cz.muni.fi.pa165.service.SportActivityService;
import cz.muni.fi.pa165.service.UserService;
import enums.Gender;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import javax.transaction.Transactional;
import java.io.IOException;

/**
 * Created by Marian Camak (inQool) on 12. 12. 2015.
 */
@Component
@Transactional
public class DataLoaderImpl implements DataLoader {

    @Inject
    UserService userService;

    @Inject
    SportActivityService activityService;

    @Inject
    BurnedCaloriesService caloriesService;

    @Inject
    ActivityRecordService recordService;

    @Override
    public void loadData() throws IOException {
        userService.create(constructUser("admin", 22, 75, Gender.MALE), "admin");
        userService.create(constructUser("Peter", 25, 85, Gender.MALE), "heslo1");
        userService.create(constructUser("Maria", 22, 58, Gender.FEMALE), "heslo2");

        SportActivity a1 = constructActivity("Football");
        activityService.create(a1);
        caloriesService.create(constructBurnedCalory(a1, 30, 433));
        caloriesService.create(constructBurnedCalory(a1, 50, 504));
        caloriesService.create(constructBurnedCalory(a1, 85, 726));

        SportActivity a2 = constructActivity("Jogging");
        activityService.create(a2);
        caloriesService.create(constructBurnedCalory(a1, 50, 336));
        caloriesService.create(constructBurnedCalory(a1, 85, 420));

        SportActivity a3 = constructActivity("Skiing");
        activityService.create(a3);
        caloriesService.create(constructBurnedCalory(a1, 50, 392));
        caloriesService.create(constructBurnedCalory(a1, 85, 490));

        //TODO add user to createRecordService, so we can trace records by user
//        recordService.create(constructRecord(a1, 30, 1800));
    }

    private ActivityRecord constructRecord(SportActivity activity, int distance, long time) {
        ActivityRecord record = new ActivityRecord();
        record.setActivity(activity);
        record.setDistance(distance);
        record.setTimeSeconds(time);
        return record;
    }

    private BurnedCalories constructBurnedCalory(SportActivity activity, int bodyWeight, int caloriesBurned) {
        BurnedCalories calories = new BurnedCalories();
        calories.setActivity(activity);
        calories.setBodyWeight(bodyWeight);
        calories.setCaloriesBurned(caloriesBurned);
        return calories;
    }

    private User constructUser(String name, int age, int weight, Gender sex) {
        User u = new User();
        u.setName(name);
        u.setAge(age);
        u.setWeight(weight);
        u.setSex(sex);
        return u;
    }

    private SportActivity constructActivity(String name) {
        SportActivity activity = new SportActivity();
        activity.setName(name);
        return activity;
    }
}
