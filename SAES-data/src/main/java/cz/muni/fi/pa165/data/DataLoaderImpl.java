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
 * Created by Marian Camak on 12. 12. 2015.
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

        SportActivity cycling1 = createActivity("Cycling, 12-13.9 mph, moderate");
        BurnedCalories bc01 = createBurnedCalory(cycling1, 60, 472);
        BurnedCalories bc02 = createBurnedCalory(cycling1, 70, 563);
        BurnedCalories bc03 = createBurnedCalory(cycling1, 80, 654);
        BurnedCalories bc04 = createBurnedCalory(cycling1, 90, 745);

        SportActivity cycling2 = createActivity("Cycling, <10 mph, leisure bicycling");
        BurnedCalories bc05 = createBurnedCalory(cycling2, 60, 236);
        BurnedCalories bc06 = createBurnedCalory(cycling2, 70, 281);
        BurnedCalories bc07 = createBurnedCalory(cycling2, 80, 327);
        BurnedCalories bc08 = createBurnedCalory(cycling2, 90, 372);

        SportActivity cycling3 = createActivity("Cycling, mountain bike, bmx");
        BurnedCalories bc09 = createBurnedCalory(cycling3, 60, 502);
        BurnedCalories bc10 = createBurnedCalory(cycling3, 70, 598);
        BurnedCalories bc11 = createBurnedCalory(cycling3, 80, 695);
        BurnedCalories bc12 = createBurnedCalory(cycling3, 90, 791);

        SportActivity running1 = createActivity("Running, general");
        BurnedCalories bc13 = createBurnedCalory(running1, 60, 472);
        BurnedCalories bc14 = createBurnedCalory(running1, 70, 563);
        BurnedCalories bc15 = createBurnedCalory(running1, 80, 654);
        BurnedCalories bc16 = createBurnedCalory(running1, 90, 745);

        SportActivity running2 = createActivity("Running, stairs, up");
        BurnedCalories bc17 = createBurnedCalory(running2, 60, 885);
        BurnedCalories bc18 = createBurnedCalory(running2, 70, 1056);
        BurnedCalories bc19 = createBurnedCalory(running2, 80, 1226);
        BurnedCalories bc20 = createBurnedCalory(running2, 90, 1396);

        SportActivity walking = createActivity("Walking 3.0 mph, moderate");
        BurnedCalories bc21 = createBurnedCalory(walking, 60, 195);
        BurnedCalories bc22 = createBurnedCalory(walking, 70, 232);
        BurnedCalories bc23 = createBurnedCalory(walking, 80, 270);
        BurnedCalories bc24 = createBurnedCalory(walking, 90, 307);

        SportActivity hiking = createActivity("Backpacking, Hiking with pack");
        BurnedCalories bc25 = createBurnedCalory(hiking, 60, 413);
        BurnedCalories bc26 = createBurnedCalory(hiking, 70, 493);
        BurnedCalories bc27 = createBurnedCalory(hiking, 80, 572);
        BurnedCalories bc28 = createBurnedCalory(hiking, 90, 651);

        SportActivity swimming = createActivity("Swimming butterfly");
        BurnedCalories bc29 = createBurnedCalory(swimming, 60, 649);
        BurnedCalories bc30 = createBurnedCalory(swimming, 70, 774);
        BurnedCalories bc31 = createBurnedCalory(swimming, 80, 899);
        BurnedCalories bc32 = createBurnedCalory(swimming, 90, 1024);

        SportActivity aerobics = createActivity("Aerobics, general");
        BurnedCalories bc33 = createBurnedCalory(aerobics, 60, 348);
        BurnedCalories bc34 = createBurnedCalory(aerobics, 70, 457);
        BurnedCalories bc35 = createBurnedCalory(aerobics, 80, 531);
        BurnedCalories bc36 = createBurnedCalory(aerobics, 90, 605);

        SportActivity motorcycling = createActivity("Riding motorcyle");
        BurnedCalories bc37 = createBurnedCalory(motorcycling, 60, 148);
        BurnedCalories bc38 = createBurnedCalory(motorcycling, 70, 176);
        BurnedCalories bc39 = createBurnedCalory(motorcycling, 80, 204);
        BurnedCalories bc40 = createBurnedCalory(motorcycling, 90, 233);

        SportActivity housework = createActivity("General housework, moderate");
        BurnedCalories bc41 = createBurnedCalory(housework, 60, 207);
        BurnedCalories bc42 = createBurnedCalory(housework, 70, 246);
        BurnedCalories bc43 = createBurnedCalory(housework, 80, 286);
        BurnedCalories bc44 = createBurnedCalory(housework, 90, 326);

        SportActivity hunting = createActivity("Hunting, general");
        BurnedCalories bc45 = createBurnedCalory(hunting, 60, 295);
        BurnedCalories bc46 = createBurnedCalory(hunting, 70, 352);
        BurnedCalories bc47 = createBurnedCalory(hunting, 80, 409);
        BurnedCalories bc48 = createBurnedCalory(hunting, 90, 465);

        SportActivity musicPlaying = createActivity("Music, playing guitar");
        BurnedCalories bc49 = createBurnedCalory(musicPlaying, 60, 177);
        BurnedCalories bc50 = createBurnedCalory(musicPlaying, 70, 211);
        BurnedCalories bc51 = createBurnedCalory(musicPlaying, 80, 245);
        BurnedCalories bc52 = createBurnedCalory(musicPlaying, 90, 279);

        SportActivity trash = createActivity("Taking out trash");
        BurnedCalories bc53 = createBurnedCalory(trash, 60, 177);
        BurnedCalories bc54 = createBurnedCalory(trash, 70, 211);
        BurnedCalories bc55 = createBurnedCalory(trash, 80, 245);
        BurnedCalories bc56 = createBurnedCalory(trash, 90, 279);

        SportActivity iceSkating = createActivity("Ice skating, average speed");
        BurnedCalories bc57 = createBurnedCalory(iceSkating, 60, 413);
        BurnedCalories bc58 = createBurnedCalory(iceSkating, 70, 493);
        BurnedCalories bc59 = createBurnedCalory(iceSkating, 80, 572);
        BurnedCalories bc60 = createBurnedCalory(iceSkating, 90, 651);

        User admin = createUser("admin", "admin", Gender.MALE, 40, 80, true);
        User erik = createUser("erik", "erik", Gender.MALE, 28, 75, false);
        User maria = createUser("maria", "maria", Gender.FEMALE, 22, 62, false);

        createRecord(admin, musicPlaying, 35L, null);
        createRecord(admin, motorcycling, 15L, 3570);
        createRecord(admin, trash, 3L, 100);

        createRecord(erik, iceSkating, 80L, 8500);
        createRecord(erik, hunting, 180L, 6000);
        createRecord(erik, cycling3, 60L, 22000);
        createRecord(erik, running2, 25L, 5500);

        createRecord(maria, cycling1, 30L, 15000);
        createRecord(maria, cycling1, 35L, 17500);
        createRecord(maria, aerobics, 40L, null);
        createRecord(maria, trash, 5L, 50);
        createRecord(maria, housework, 75L, null);
    }

    private SportActivity createActivity(String name) {
        SportActivity activity = new SportActivity();
        activity.setName(name);

        activityService.create(activity);
        return activity;
    }

    private BurnedCalories createBurnedCalory(SportActivity activity, Integer bodyWeight, Integer caloriesBurned) {
        BurnedCalories calories = new BurnedCalories();
        calories.setActivity(activity);
        calories.setBodyWeight(bodyWeight);
        calories.setCaloriesBurned(caloriesBurned);

        caloriesService.create(calories);
        return calories;
    }

    private User createUser(String name, String password, Gender sex, Integer age, Integer weight, boolean admin) {
        User user = new User();
        user.setUsername(name);
        user.setSex(sex);
        user.setAge(age);
        user.setWeight(weight);
        user.setAdmin(admin);

        userService.register(user, password);
        return user;
    }

    private ActivityRecord createRecord(User user, SportActivity activity, Long time, Integer distance) {
        ActivityRecord record = new ActivityRecord();
        record.setActivity(activity);
        record.setDistance(distance);
        record.setTime(time);
        record.setUser(user);
        recordService.create(record);

        return record;
    }
}
