package cz.fi.muni.fi.pa165.service.service;

import cz.muni.fi.pa165.exceptions.EntityReferenceException;
import cz.muni.fi.pa165.saes.dao.ActivityRecordDao;
import cz.muni.fi.pa165.saes.dao.BurnedCaloriesDao;
import cz.muni.fi.pa165.saes.dao.SportActivityDao;
import cz.muni.fi.pa165.saes.entity.ActivityRecord;
import cz.muni.fi.pa165.saes.entity.SportActivity;
import cz.muni.fi.pa165.service.SportActivityServiceImpl;
import cz.muni.fi.pa165.service.mapping.ServiceConfiguration;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.testng.Assert.assertTrue;

/**
 *
 * @author B. Bajtosova
 */
@ContextConfiguration(classes = ServiceConfiguration.class)
public class SportActivityServiceTest extends AbstractTestNGSpringContextTests {

    @Mock
    ActivityRecordDao recordDao;

    @Mock
    BurnedCaloriesDao caloriesDao;

    @Mock
    SportActivityDao activityDao;

    @InjectMocks
    private SportActivityServiceImpl sportActivityService;

    @BeforeMethod
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    /**
     * Tests creating sport activity
     */
    @Test
    public void createTest() {
        SportActivity sportActivity = new SportActivity();
        sportActivity.setName("Running");

        sportActivityService.create(sportActivity);
        Mockito.verify(activityDao).createSportActivity(sportActivity);
    }

    /**
     * Tests creating null activity
     */
    @Test(expectedExceptions = IllegalArgumentException.class)
    public void createSportActivityNullTest() {
        Mockito.doThrow(IllegalArgumentException.class).when(activityDao).createSportActivity(null);
        sportActivityService.create(null);
    }

    /**
     * Tests finding activity by id
     */
    @Test
    public void findByIdTest() {
        SportActivity sportActivity = new SportActivity();
        sportActivity.setId(876L);
        sportActivity.setName("Running");

        when(activityDao.findSportActivity(sportActivity.getId())).thenReturn(sportActivity);
        sportActivityService.findById(sportActivity.getId());
        verify(activityDao).findSportActivity(sportActivity.getId());
    }

    /**
     * Tests finding activity with null
     */
    @Test(expectedExceptions = IllegalArgumentException.class)
    public void findByIdNullTest() {
        Mockito.doThrow(IllegalArgumentException.class).when(activityDao).findSportActivity(null);
        sportActivityService.findById(null);
    }

    /**
     * Tests deleting sport activity
     */
    @Test
    public void deleteTest() throws EntityReferenceException {
        SportActivity sportActivity = new SportActivity();
        sportActivity.setId(999L);
        sportActivity.setName("Running");

        when(activityDao.findSportActivity(sportActivity.getId())).thenReturn(sportActivity);
        when(recordDao.findRecordsBySportActivity(sportActivity)).thenReturn(new ArrayList<ActivityRecord>());

        sportActivityService.delete(sportActivity.getId());
        Mockito.verify(activityDao).deleteSportActivity(sportActivity);
    }

    /**
     * Tests deleting sport activity when assigned to a record
     */
    @Test(expectedExceptions = EntityReferenceException.class)
    public void deleteWhenAssignedToRecordsTest() throws EntityReferenceException {
        SportActivity sportActivity = new SportActivity();
        sportActivity.setId(333L);
        sportActivity.setName("Running");
        Long sportActivityId = sportActivity.getId();

        ActivityRecord record = new ActivityRecord();
        List<ActivityRecord> records = new ArrayList<>();
        records.add(record);
        when(activityDao.findSportActivity(sportActivityId)).thenReturn(sportActivity);
        when(recordDao.findRecordsBySportActivity(sportActivity)).thenReturn(records);
        sportActivityService.delete(sportActivityId);
    }

    /**
     * Tests deleting null activity
     */
    @Test(expectedExceptions = IllegalArgumentException.class)
    public void deleteNullTest() throws EntityReferenceException {
        Mockito.doThrow(IllegalArgumentException.class).when(activityDao).findSportActivity(null);
        Mockito.doThrow(IllegalArgumentException.class).when(activityDao).deleteSportActivity(null);
        sportActivityService.delete(null);
    }

    /**
     * Tests finding all activities
     */
    @Test
    public void findAllTest() {
        SportActivity sportActivity = new SportActivity();
        sportActivity.setName("Running");
        SportActivity sportActivity2 = new SportActivity();
        sportActivity2.setName("Chess");

        List<SportActivity> activities = new ArrayList<>();
        activities.add(sportActivity);
        activities.add(sportActivity2);

        when(activityDao.findAll()).thenReturn(activities);

        // do we find right amount of activities?
        List<SportActivity> foundSportActivityList = sportActivityService.findAll();
        assertTrue(foundSportActivityList.size() >= 2);

        // do we have right activities?
        assertTrue(foundSportActivityList.contains(sportActivity));
        assertTrue(foundSportActivityList.contains(sportActivity2));
    }

    /**
     * Tests update activity
     */
    @Test
    public void updateActivityTest() {
        SportActivity sportActivity = new SportActivity();
        sportActivity.setId(765L);
        sportActivity.setName("Chess");

        sportActivityService.update(sportActivity);
        Mockito.verify(activityDao).updateSportActivity(sportActivity);
    }

}
