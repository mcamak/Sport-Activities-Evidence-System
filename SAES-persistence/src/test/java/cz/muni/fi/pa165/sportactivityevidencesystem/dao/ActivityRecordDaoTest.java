package cz.muni.fi.pa165.sportactivityevidencesystem.dao;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import cz.muni.fi.pa165.saes.dao.SportActivityDao;
import cz.muni.fi.pa165.saes.dao.UserDao;
import cz.muni.fi.pa165.saes.dao.ActivityRecordDao;
import cz.muni.fi.pa165.saes.SportActivitySystemApplicationContext;
import cz.muni.fi.pa165.saes.entity.ActivityRecord;
import cz.muni.fi.pa165.saes.entity.SportActivity;
import cz.muni.fi.pa165.saes.entity.User;
import enums.Gender;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import org.testng.annotations.Test;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertNull;
import org.testng.annotations.BeforeMethod;

/**
 *
 * @author Barbora B.
 */
@ContextConfiguration(classes = SportActivitySystemApplicationContext.class)
@TestExecutionListeners(TransactionalTestExecutionListener.class)
@Transactional
public class ActivityRecordDaoTest extends AbstractTestNGSpringContextTests {

    //@Rule
    //public final ExpectedException expectedException = ExpectedException.none();
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
        actv.addUser(user);
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
    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testCreateWithNull() {
        recordDao.create(null);
    }

    /**
     * Test creating record with null sport activity
     */
    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testCreateNullActivity() {
        ActivityRecord record = new ActivityRecord();
        record.setActivity(null);
        record.setDistance(5);
        record.setTimeSeconds(Long.MIN_VALUE);
        record.addUser(user);

        recordDao.create(record);

    }

    /**
     * Test creating record with wrong distance
     */
    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testCreateWrongDistance() {
        ActivityRecord record = new ActivityRecord();
        record.setActivity(sportActivity);
        record.setDistance(-5);
        record.setTimeSeconds(Long.MIN_VALUE);
        record.addUser(user);

        recordDao.create(record);

    }

    /**
     * Test creating record with wrong time
     */
    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testCreateWrongTime() {
        ActivityRecord record = new ActivityRecord();
        record.setActivity(sportActivity);
        record.setDistance(5);
        record.setTimeSeconds(null);
        record.addUser(user);

        recordDao.create(record);

    }

    /**
     * Test creating record with null user
     */
    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testCreateNullUser() {
        ActivityRecord record = new ActivityRecord();
        record.setActivity(sportActivity);
        record.setDistance(5);
        record.setTimeSeconds(Long.MIN_VALUE);
        record.addUser(null);

        recordDao.create(record);

    }

    /**
     * Test deleting record with null parameter
     */
    @Test(expectedExceptions = IllegalArgumentException.class)
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
        assertEquals(recordFound.getDistance(), 15);
        assertEquals(recordFound.getTimeSeconds(), new Long(20));
    }

    /**
     * Test updating activity with null
     */
    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testUpdateNull() {
        recordDao.update(null);
    }

    /**
     * Test updating activity with null sport activity
     */
    @Test(expectedExceptions = IllegalArgumentException.class)
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
    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testUpdateWrongDistance() {
        ActivityRecord record = setActivityRecord();
        recordDao.create(record);
        assertNotNull(recordDao.findActivityRecord(record.getId()));
        
        record.setDistance(-5);
        recordDao.update(record);
    }

    /**
     * Test updating activity with null time
     */
    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testUpdateWrongTime() {
        ActivityRecord record = setActivityRecord();
        recordDao.create(record);
        assertNotNull(recordDao.findActivityRecord(record.getId()));
        
        record.setTimeSeconds(null);
        recordDao.update(record);
    }

    /**
     * Test getting activity record by null id
     */
    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testFindActivityRecordNull() {
        recordDao.findActivityRecord(null);
    }

}
