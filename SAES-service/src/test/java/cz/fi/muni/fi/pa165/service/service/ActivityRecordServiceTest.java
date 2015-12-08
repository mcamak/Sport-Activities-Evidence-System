package cz.fi.muni.fi.pa165.service.service;

import cz.muni.fi.pa165.saes.dao.ActivityRecordDao;
import cz.muni.fi.pa165.saes.dao.UserDao;
import cz.muni.fi.pa165.saes.entity.ActivityRecord;
import cz.muni.fi.pa165.saes.entity.SportActivity;
import cz.muni.fi.pa165.saes.entity.User;
import cz.muni.fi.pa165.service.ActivityRecordServiceImpl;
import cz.muni.fi.pa165.service.mapping.ServiceConfiguration;
import enums.Gender;
import java.util.ArrayList;
import java.util.List;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 *
 * @author Jan S.
 */
@ContextConfiguration(classes = ServiceConfiguration.class)
public class ActivityRecordServiceTest extends AbstractTestNGSpringContextTests {

    @InjectMocks
    private ActivityRecordServiceImpl activityRecordService;

    @Mock
    private UserDao userDAO;

    @Mock
    private ActivityRecordDao activityRecordDAO;

    private ActivityRecord activityRecord1;
    private ActivityRecord activityRecord2;
    private List<ActivityRecord> activityRecordList;

    private User user;

    @BeforeMethod
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        SportActivity sportActivity = new SportActivity();
        sportActivity.setName("Running");

        // FIXME it should be possible to add one user to multiple activity records
        user = new User();
        user.setAge(100);
        user.setName("Pepa z Depa");
        user.setPasswordHash("LOL");
        user.setSex(Gender.MALE);
        user.setWeight(56);

        activityRecord1 = new ActivityRecord();
        activityRecord1.setActivity(sportActivity);
        activityRecord1.setDistance(10);
        activityRecord1.setTimeSeconds(1000L);
        activityRecord1.addUser(user);

        activityRecord2 = new ActivityRecord();
        activityRecord2.setActivity(sportActivity);
        activityRecord2.setDistance(10);
        activityRecord2.setTimeSeconds(1000L);
        activityRecord2.addUser(user);

        activityRecordList = new ArrayList<>();
        activityRecordList.add(activityRecord1);
        activityRecordList.add(activityRecord2);
    }

    /**
     * Tests creating
     */
    @Test
    public void createTest() {

        activityRecordService.create(activityRecord1);
        verify(activityRecordDAO).create(activityRecord1);
    }

    /**
     * Tests creating with null argument
     */
    @Test(expectedExceptions = IllegalArgumentException.class)
    public void createNullTest() {
        Mockito.doThrow(new IllegalArgumentException()).when(activityRecordDAO).create(null);
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
        Mockito.doThrow(new IllegalArgumentException()).when(activityRecordDAO).findActivityRecord(null);
        activityRecordService.findById(null);
    }

    /**
     * Tests updating
     */
    @Test
    public void updateTest() {
        activityRecordService.update(activityRecord1);
        verify(activityRecordDAO).update(activityRecord1);
    }

    /**
     * Tests updating with null argument
     */
    @Test(expectedExceptions = IllegalArgumentException.class)
    public void updateNullTest() {
        Mockito.doThrow(new IllegalArgumentException()).when(activityRecordDAO).update(null);
        activityRecordService.update(null);
    }

    /**
     * Tests deleting
     */
    @Test
    public void deleteTest() {
        activityRecordService.delete(activityRecord1);
        verify(activityRecordDAO).delete(activityRecord1);
    }

    /**
     * Tests deleting with null argument
     */
    @Test(expectedExceptions = IllegalArgumentException.class)
    public void deleteNullTest() {
        Mockito.doThrow(new IllegalArgumentException()).when(activityRecordDAO).delete(null);
        activityRecordService.delete(null);
    }

    /**
     * Tests finding all records
     */
    @Test
    public void findAllTest() {
        when(activityRecordDAO.findAll()).thenReturn(activityRecordList);

        List<ActivityRecord> result = activityRecordService.findAll();
        assertEquals(result.size(), 2);
        assertTrue(result.contains(activityRecord1));
        assertTrue(result.contains(activityRecord2));
    }

    /**
     * Tests removing user from record
     */
    @Test
    public void removeUserFromActivityRecordTest() {
        when(activityRecordDAO.findActivityRecord(activityRecord1.getId())).thenReturn(activityRecord1);
        when(userDAO.findUser(user.getId())).thenReturn(user);

        Integer usersBeforeRemoval = activityRecord1.getUsers().size();
        activityRecordService.removeUserFromActivityRecord(user.getId(), activityRecord1.getId());
        Integer usersAfterRemoval = activityRecord1.getUsers().size();

        assertTrue(usersAfterRemoval == (usersBeforeRemoval - 1));

        verify(activityRecordDAO).findActivityRecord(user.getId());
        verify(userDAO).findUser(user.getId());
        verify(activityRecordDAO).update(activityRecord1);
    }

}
