package cz.fi.muni.fi.pa165.service.service;

import cz.muni.fi.pa165.enums.Gender;
import cz.muni.fi.pa165.saes.dao.ActivityRecordDao;
import cz.muni.fi.pa165.saes.dao.BurnedCaloriesDao;
import cz.muni.fi.pa165.saes.dao.UserDao;
import cz.muni.fi.pa165.saes.entity.ActivityRecord;
import cz.muni.fi.pa165.saes.entity.BurnedCalories;
import cz.muni.fi.pa165.saes.entity.SportActivity;
import cz.muni.fi.pa165.saes.entity.User;
import cz.muni.fi.pa165.service.ActivityRecordServiceImpl;
import cz.muni.fi.pa165.service.mapping.ServiceConfiguration;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.dao.DataAccessException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.testng.Assert.*;

/**
 *
 * @author Jan S.
 */
@ContextConfiguration(classes = ServiceConfiguration.class)
public class ActivityRecordServiceTest extends AbstractTestNGSpringContextTests {

    @Mock
    private UserDao userDAO;

    @Mock
    private BurnedCaloriesDao caloriesDao;

    @Mock
    private ActivityRecordDao activityRecordDAO;

    @InjectMocks
    private ActivityRecordServiceImpl activityRecordService;

    private ActivityRecord activityRecord1;
    private ActivityRecord activityRecord2;
    private List<ActivityRecord> activityRecordList;

    private SportActivity activity;
    private BurnedCalories calory;
    private List<BurnedCalories> calories;

    private User user;

    @BeforeMethod
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        activity = new SportActivity();
        activity.setId(87L);
        activity.setName("Music, playing guitar");

        calory = new BurnedCalories();
        calory.setId(88L);
        calory.setActivity(activity);
        calory.setBodyWeight(80);
        calory.setCaloriesBurned(530);

        calories = new ArrayList<>();
        BurnedCalories c1 = new BurnedCalories();
        c1.setId(400L);
        c1.setActivity(activity);
        c1.setCaloriesBurned(177);
        c1.setBodyWeight(60);
        calories.add(c1);

        BurnedCalories c2 = new BurnedCalories();
        c2.setId(401L);
        c2.setActivity(activity);
        c2.setCaloriesBurned(211);
        c2.setBodyWeight(70);
        calories.add(c2);

        BurnedCalories c3 = new BurnedCalories();
        c3.setId(402L);
        c3.setActivity(activity);
        c3.setCaloriesBurned(245);
        c3.setBodyWeight(80);
        calories.add(c3);

        BurnedCalories c4 = new BurnedCalories();
        c4.setId(403L);
        c4.setActivity(activity);
        c4.setCaloriesBurned(279);
        c4.setBodyWeight(90);
        calories.add(c4);

        // FIXME it should be possible to add one user to multiple activity records
        user = new User();
        user.setAge(100);
        user.setUsername("Pepa z Depa");
        user.setPassword("LOL");
        user.setSex(Gender.MALE);
        user.setWeight(56);

        activityRecord1 = new ActivityRecord();
        activityRecord1.setActivity(activity);
        activityRecord1.setDistance(10);
        activityRecord1.setTime(1000L);
        activityRecord1.setUser(user);

        activityRecord2 = new ActivityRecord();
        activityRecord2.setActivity(activity);
        activityRecord2.setTime(1000L);
        activityRecord2.setUser(user);

        activityRecordList = new ArrayList<>();
        activityRecordList.add(activityRecord1);
        activityRecordList.add(activityRecord2);
    }

    /**
     * Tests creating
     */
    @Test
    public void createTest() {
        when(caloriesDao.findBySportActivity(activityRecord1.getActivity())).thenReturn(calories);

        activityRecordService.create(activityRecord1);
        assertNotNull(activityRecord1.getBurnedCalories());
        assertTrue(activityRecord1.getBurnedCalories() > 0);
        verify(activityRecordDAO).create(activityRecord1);
    }

    /**
     * Tests creating with null argument
     */
    @Test(expectedExceptions = IllegalArgumentException.class)
    public void createNullTest() {
        Mockito.doThrow(DataAccessException.class).when(activityRecordDAO).create(null);
        activityRecordService.create(null);
    }

    /**
     * Tests finding by id
     */
    @Test
    public void findByIdTest() {
        Long id = 5154L;
        when(activityRecordDAO.findActivityRecord(id)).thenReturn(this.activityRecord1);

        ActivityRecord result = activityRecordService.findById(id);
        verify(activityRecordDAO).findActivityRecord(id);
        verifyNoMoreInteractions(activityRecordDAO);

        assertEquals(result, activityRecord1);
    }

    /**
     * Tests finding by null id
     */
    @Test(expectedExceptions = IllegalArgumentException.class)
    public void findByIdNullTest() {
        Mockito.doThrow(DataAccessException.class).when(activityRecordDAO).findActivityRecord(null);
        activityRecordService.findById(null);
    }

    /**
     * Tests updating
     */
    @Test
    public void updateTest() {
        when(caloriesDao.findBySportActivity(activity)).thenReturn(calories);
        activityRecordService.update(activityRecord1);
        assertNotNull(activityRecord1.getBurnedCalories());
        assertTrue(activityRecord1.getBurnedCalories() > 0);
        verify(activityRecordDAO).update(activityRecord1);
    }

    /**
     * Tests updating with null argument
     */
    @Test(expectedExceptions = IllegalArgumentException.class)
    public void updateNullTest() {
        Mockito.doThrow(DataAccessException.class).when(activityRecordDAO).update(null);
        activityRecordService.update(null);
    }

    /**
     * Tests deleting
     */
    @Test
    public void deleteTest() {
        activityRecord1.setId(900L);
        when(activityRecordDAO.findActivityRecord(activityRecord1.getId())).thenReturn(activityRecord1);
        activityRecordService.delete(activityRecord1.getId());
        verify(activityRecordDAO).delete(activityRecord1);
    }

    /**
     * Tests deleting with non-existing ID
     */
    @Test
    public void deleteTestWithNotExistingId() {
        when(activityRecordDAO.findActivityRecord(73626L)).thenReturn(null);
        activityRecordService.delete(73626L);
        verify(activityRecordDAO, never()).delete(null);
    }

    /**
     * Tests deleting with null argument
     */
    @Test(expectedExceptions = IllegalArgumentException.class)
    public void deleteNullTest() {
        Mockito.doThrow(DataAccessException.class).when(activityRecordDAO).findActivityRecord(null);
        activityRecordService.delete(null);
    }

    /**
     * Tests finding records of user
     */
    @Test
    public void findRecordsOfUser() {
        user.setId(800L);
        activityRecord1.setBurnedCalories(200);
        activityRecord2.setBurnedCalories(230);
        when(userDAO.findUser(user.getId())).thenReturn(user);
        when(activityRecordDAO.findRecordsByUser(user)).thenReturn(activityRecordList);

        List<ActivityRecord> result = activityRecordService.findByUser(user.getId());
        verify(userDAO).findUser(user.getId());
        verify(activityRecordDAO).findRecordsByUser(user);
        assertEquals(result.size(), 2);
        assertTrue(result.contains(activityRecord1));
        assertTrue(result.contains(activityRecord2));
    }

    /**
     * Tests finding all records
     */
    @Test
    public void findAllTest() {
        activityRecord1.setBurnedCalories(20);
        activityRecord2.setBurnedCalories(30);
        when(activityRecordDAO.findAll()).thenReturn(activityRecordList);

        List<ActivityRecord> result = activityRecordService.findAll();
        assertEquals(result.size(), 2);
        assertTrue(result.contains(activityRecord1));
        assertTrue(result.contains(activityRecord2));
    }
}
