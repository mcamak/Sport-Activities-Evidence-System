package cz.fi.muni.fi.pa165.service.service;

import cz.muni.fi.pa165.saes.entity.ActivityRecord;
import cz.muni.fi.pa165.saes.entity.SportActivity;
import cz.muni.fi.pa165.saes.entity.User;
import cz.muni.fi.pa165.service.ActivityRecordService;
import cz.muni.fi.pa165.service.SportActivityService;
import cz.muni.fi.pa165.service.UserService;
import cz.muni.fi.pa165.service.mapping.ServiceConfiguration;
import enums.Gender;
import java.util.List;
import javax.inject.Inject;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import static org.testng.Assert.assertTrue;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 *
 * @author Jan S.
 */
@ContextConfiguration(classes = ServiceConfiguration.class)
public class ActivityRecordServiceTest extends AbstractTestNGSpringContextTests {

    @Inject
    private ActivityRecordService activityRecordService;

    @Inject
    private SportActivityService sportActivityService;

    @Inject
    private UserService userService;

    ActivityRecord activityRecord1;
    ActivityRecord activityRecord2;

    @BeforeMethod
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        SportActivity sportActivity = new SportActivity();
        sportActivity.setName("Running");
        sportActivityService.create(sportActivity);
        
        // FIXME it should be possible to add one user to multiple activity records
        User user = new User();
        user.setAge(100);
        user.setName("Pepa z Depa");
        user.setPasswordHash("LOL");
        user.setSex(Gender.MALE);
        user.setWeight(56);

        User user2 = new User();
        user2.setAge(100);
        user2.setName("Pepa z Dola");
        user2.setPasswordHash("LOL");
        user2.setSex(Gender.MALE);
        user2.setWeight(56);

        userService.create(user);
        userService.create(user2);

        activityRecord1 = new ActivityRecord();
        activityRecord1.setActivity(sportActivity);
        activityRecord1.setDistance(10);
        activityRecord1.setTimeSeconds(1000L);
        activityRecord1.addUser(user);

        activityRecord2 = new ActivityRecord();
        activityRecord2.setActivity(sportActivity);
        activityRecord2.setDistance(10);
        activityRecord2.setTimeSeconds(1000L);
        activityRecord2.addUser(user2);
    }

    /**
     * Tests creating
     */
    @Test
    public void createTest() {

        activityRecordService.create(activityRecord1);
        activityRecordService.create(activityRecord2);

        // do we find righ ammount of activities? 
        List<ActivityRecord> foundRecords = activityRecordService.findAll();
        assertTrue(foundRecords.size() >= 2);

        // do we have right activities?
        assertTrue(foundRecords.contains(activityRecord1));
        assertTrue(foundRecords.contains(activityRecord2));
    }
//
//    /**
//     * Tests creating with null argument
//     */
//    @Test(expectedExceptions = IllegalArgumentException.class)
//    public void createNullTest() {
//        activityRecordService.create(null);
//    }
//
//    /**
//     * Tests finding by id
//     */
//    @Test
//    public void findByIdTest() {
//        activityRecordService.create(activityRecord1);
//        boolean isRightUserFound
//                = activityRecord1.equals(activityRecordService.findById(activityRecord1.getId()));
//
//        assertTrue(isRightUserFound);
//    }
//
//    /**
//     * Tests finding by null id
//     */
//    @Test(expectedExceptions = IllegalArgumentException.class)
//    public void findByIdNullTest() {
//        activityRecordService.findById(null);
//    }
//
//    /**
//     * Tests updating
//     */
//    @Test
//    public void updateTest() {
//        activityRecordService.create(activityRecord1);
//        activityRecord1.setSex(Gender.FEMALE);
//        activityRecordService.update(activityRecord1);
//        User userFound = activityRecordService.findById(activityRecord1.getId());
//
//        assertEquals(userFound, activityRecord1);
//    }
//
//    /**
//     * Tests updating with null argument
//     */
//    @Test(expectedExceptions = IllegalArgumentException.class)
//    public void updateNullTest() {
//        activityRecordService.update(null);
//    }
//
//    /**
//     * Tests deleting
//     */
//    @Test
//    public void deleteTest() {
//
//        activityRecordService.create(activityRecord1);
//        Long burdnedCaloriesId = activityRecord1.getId();
//
//        boolean containsBeforeDelete = activityRecordService.findById(burdnedCaloriesId) != null;
//        activityRecordService.delete(activityRecord1);
//        boolean containsAfterDelete = activityRecordService.findById(burdnedCaloriesId) != null;
//
//        System.out.print("POL " + containsAfterDelete);
//        assertNotEquals(containsBeforeDelete, containsAfterDelete);
//    }
//
//    /**
//     * Tests deleting with null argument
//     */
//    @Test(expectedExceptions = IllegalArgumentException.class)
//    public void deleteNullTest() {
//        activityRecordService.delete(null);
//    }
//
//    /**
//     * Tests finding all records
//     */
//    @Test
//    public void findAllTest() {
//        activityRecordService.create(activityRecord1);
//        activityRecordService.create(activityRecord2);
//
//        // do we find righ ammount of activities?
//        List<User> foundRecordsList = activityRecordService.findAll();
//        assertTrue(foundRecordsList.size() >= 2);
//
//        // do we have right activities?
//        assertTrue(foundRecordsList.contains(activityRecord1));
//        assertTrue(foundRecordsList.contains(activityRecord2));
//    }

    /**
     * Tests finding by parameters
     */
//    @Test
//    public void findByParametersTest() {
//        userService.create(activityRecord1);
//        userService.create(activityRecord2);
//
//        UserFilter userFilter = new UserFilter();
//        userFilter.setMinAge(activityRecord1.getAge() - 1);
//        userFilter.setMaxAge(activityRecord2.getAge() + 1);
//        
//        System.out.println("BEKL "+userService.findByParameters(userFilter).size());
//
//        assertTrue(userService.findByParameters(userFilter).size() == 2);
//
//    }
    // TODO
    /**
     * Tests finding all records
     */
//    @Test
//    public void authenticateTest() {
//        userService.create(activityRecord1);
//        assertTrue(userService.authenticate(activityRecord1.getId(), activityRecord1.getPasswordHash()));
//    }
}
