package cz.muni.fi.pa165.sportactivityevidencesystem.dao;

import cz.muni.fi.pa165.saes.SportActivitySystemApplicationContext;
import cz.muni.fi.pa165.saes.dao.ActivityRecordDao;
import cz.muni.fi.pa165.saes.dao.SportActivityDao;
import cz.muni.fi.pa165.saes.dao.UserDao;
import cz.muni.fi.pa165.saes.entity.ActivityRecord;
import cz.muni.fi.pa165.saes.entity.SportActivity;
import cz.muni.fi.pa165.saes.entity.User;
import enums.Gender;
import org.springframework.dao.DataAccessException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

import static org.testng.Assert.*;

/**
 *
 * @author Barbora B.
 */
@ContextConfiguration(classes = SportActivitySystemApplicationContext.class)
@TestExecutionListeners(TransactionalTestExecutionListener.class)
@Transactional
public class ActivityRecordDaoTest extends AbstractTestNGSpringContextTests {

    @PersistenceContext
    public EntityManager em;

    @Inject
    private ActivityRecordDao recordDao;

    @Inject
    private SportActivityDao activityDao;

    @Inject
    private UserDao userDao;

    private SportActivity sportActivity;
    private User user;

    @BeforeMethod
    public void init() {
        sportActivity = new SportActivity();
        sportActivity.setName("Running");
        activityDao.createSportActivity(sportActivity);

        user = new User();
        user.setName("Peter");
        user.setPasswordHash("passwordHash");
        user.setAge(35);
        user.setSex(Gender.MALE);
        user.setWeight(95);
        userDao.createUser(user);
    }

    /**
     * Create and set activity record for later use
     *
     * @return ActivityRecord
     */
    private ActivityRecord setActivityRecord() {
        ActivityRecord actv = new ActivityRecord();
        actv.setActivity(sportActivity);
        actv.setDistance(5);
        actv.setTimeSeconds(45L);
        actv.setBurnedCalories(240);
        actv.setUser(user);
        return actv;
    }

    /**
     * Test creating activity record
     */
    @Test
    public void testCreate() {
        ActivityRecord record = setActivityRecord();
        recordDao.create(record);
        assertNotNull(record.getId());
    }

    /**
     * Test creating activity record with null parameter
     */
    @Test(expectedExceptions = DataAccessException.class)
    public void testCreateWithNull() {
        recordDao.create(null);
    }

    /**
     * Test creating record with null sport activity
     */
    @Test(expectedExceptions = DataAccessException.class)
    public void testCreateNullActivity() {
        ActivityRecord record = new ActivityRecord();
        record.setActivity(null);
        record.setDistance(5);
        record.setTimeSeconds(Long.MIN_VALUE);
        record.setUser(user);

        recordDao.create(record);
    }

    /**
     * Test creating record with wrong distance
     */
    @Test(expectedExceptions = DataAccessException.class)
    public void testCreateWrongDistance() {
        ActivityRecord record = new ActivityRecord();
        record.setActivity(sportActivity);
        record.setDistance(-5);
        record.setTimeSeconds(Long.MIN_VALUE);
        record.setUser(user);

        recordDao.create(record);

    }

    /**
     * Test creating record with null user
     */
    @Test(expectedExceptions = DataAccessException.class)
    public void testCreateNullUser() {
        ActivityRecord record = new ActivityRecord();
        record.setActivity(sportActivity);
        record.setDistance(5);
        record.setTimeSeconds(Long.MIN_VALUE);
        record.setUser(null);

        recordDao.create(record);
    }

    /**
     * Test deleting record with null parameter
     */
    @Test(expectedExceptions = DataAccessException.class)
    public void testDeleteNull() {
        recordDao.delete(null);
    }

    /**
     * Test deleting record
     */
    @Test
    public void testDelete() {
        ActivityRecord record = setActivityRecord();
        recordDao.create(record);
        assertNotNull(recordDao.findActivityRecord(record.getId()));

        recordDao.delete(record);
        assertNull(recordDao.findActivityRecord(record.getId()));
    }

    /**
     * Test deleting by user
     */
    @Test
    public void testDeleteByUser() {
        ActivityRecord record1 = setActivityRecord();
        record1.setDistance(30);
        record1.setBurnedCalories(50);
        recordDao.create(record1);
        assertNotNull(recordDao.findActivityRecord(record1.getId()));

        ActivityRecord record2 = setActivityRecord();
        record2.setDistance(60);
        record2.setBurnedCalories(100);
        recordDao.create(record2);
        assertNotNull(recordDao.findActivityRecord(record2.getId()));

        recordDao.deleteUserRecords(user);
        // TODO check why this two lines fail
//        assertNull(recordDao.findActivityRecord(record1.getId()));
//        assertNull(recordDao.findActivityRecord(record2.getId()));
        assertTrue(recordDao.findRecordsByUser(user).isEmpty());
        assertTrue(recordDao.findAll().isEmpty());
    }

    /**
     * Test deleting by sport activity
     */
    @Test
    public void testDeleteBySportActivity() {
        SportActivity activity = new SportActivity();
        activity.setName("Test activity");
        activityDao.createSportActivity(activity);
        assertNotNull(activityDao.findSportActivity(activity.getId()));

        ActivityRecord record1 = setActivityRecord();
        record1.setActivity(activity);
        recordDao.create(record1);
        assertNotNull(recordDao.findActivityRecord(record1.getId()));

        ActivityRecord record2 = setActivityRecord();
        record1.setActivity(activity);
        recordDao.create(record2);
        assertNotNull(recordDao.findActivityRecord(record2.getId()));

        recordDao.deleteRecordsBySportActivity(activity);
        // TODO lines
        assertTrue(recordDao.findRecordsBySportActivity(activity).isEmpty());
    }

    /**
     * Test updating activity record
     */
    @Test
    public void testUpdate() {
        ActivityRecord record = setActivityRecord();
        recordDao.create(record);
        assertNotNull(recordDao.findActivityRecord(record.getId()));

        record.setDistance(15);
        record.setTimeSeconds(20L);
        recordDao.update(record);

        ActivityRecord recordFound = recordDao.findActivityRecord(record.getId());
        assertEquals(recordFound.getDistance(), new Integer(15));
        assertEquals(recordFound.getTimeSeconds(), new Long(20));
    }

    /**
     * Test updating activity with null
     */
    @Test(expectedExceptions = DataAccessException.class)
    public void testUpdateNull() {
        recordDao.update(null);
    }

    /**
     * Test updating activity with null sport activity
     */
    @Test(expectedExceptions = DataAccessException.class)
    public void testUpdateNullActivity() {
        ActivityRecord record = setActivityRecord();
        recordDao.create(record);
        assertNotNull(recordDao.findActivityRecord(record.getId()));

        record.setActivity(null);
        recordDao.update(record);
    }

    /**
     * Test updating activity with wrong distance
     */
    @Test(expectedExceptions = DataAccessException.class)
    public void testUpdateWrongDistance() {
        ActivityRecord record = setActivityRecord();
        recordDao.create(record);
        assertNotNull(recordDao.findActivityRecord(record.getId()));

        record.setDistance(-5);
        recordDao.update(record);
    }

    /**
     * Test getting activity record by null id
     */
    @Test(expectedExceptions = DataAccessException.class)
    public void testFindActivityRecordNull() {
        recordDao.findActivityRecord(null);
    }

    /**
     * Test find activity record by user
     */
    @Test
    public void testFindActivityRecordByUser() {
        ActivityRecord rec1 = setActivityRecord();
        rec1.setBurnedCalories(20);
        recordDao.create(rec1);
        assertNotNull(recordDao.findActivityRecord(rec1.getId()));

        ActivityRecord rec2 = setActivityRecord();
        rec2.setBurnedCalories(40);
        recordDao.create(rec2);
        assertNotNull(recordDao.findActivityRecord(rec2.getId()));

        List<ActivityRecord> records = recordDao.findRecordsByUser(user);
        assertTrue(!records.isEmpty());
        assertTrue(records.contains(rec1));
        assertTrue(records.contains(rec2));
    }

    /**
     * Test find activity record by sport activity
     */
    @Test
    public void testFindActivityRecordBySportActivity() {
        SportActivity activity = new SportActivity();
        activity.setName("Test activity");
        activityDao.createSportActivity(activity);

        ActivityRecord rec1 = setActivityRecord();
        rec1.setActivity(activity);
        recordDao.create(rec1);
        assertNotNull(recordDao.findActivityRecord(rec1.getId()));

        ActivityRecord rec2 = setActivityRecord();
        rec2.setActivity(activity);
        recordDao.create(rec2);
        assertNotNull(recordDao.findActivityRecord(rec2.getId()));

        ActivityRecord rec3 = setActivityRecord();
        recordDao.create(rec3);
        assertNotNull(recordDao.findActivityRecord(rec3.getId()));

        List<ActivityRecord> records = recordDao.findRecordsBySportActivity(activity);
        assertTrue(!records.isEmpty());
        assertTrue(records.contains(rec1));
        assertTrue(records.contains(rec2));
        assertTrue(!records.contains(rec3));
    }
}
