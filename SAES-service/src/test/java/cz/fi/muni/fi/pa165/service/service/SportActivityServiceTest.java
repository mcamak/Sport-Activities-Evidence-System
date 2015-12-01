package cz.fi.muni.fi.pa165.service.service;

import cz.muni.fi.pa165.saes.entity.SportActivity;
import cz.muni.fi.pa165.service.SportActivityService;
import cz.muni.fi.pa165.service.mapping.ServiceConfiguration;
import java.util.List;
import javax.inject.Inject;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotEquals;
import static org.testng.Assert.assertTrue;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 *
 * @author B. Bajtosova
 */
@ContextConfiguration(classes = ServiceConfiguration.class)
public class SportActivityServiceTest extends AbstractTestNGSpringContextTests {

    @Inject
    private SportActivityService sportActivityService;

    @BeforeMethod
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    /**
     * Test creating sport activity
     */
    @Test
    public void createSportActivityTest() {
        SportActivity sportActivity = new SportActivity();
        sportActivity.setName("Running");
        SportActivity sportActivity2 = new SportActivity();
        sportActivity2.setName("Chess");

        sportActivityService.createSportActivity(sportActivity);
        sportActivityService.createSportActivity(sportActivity2);

        // do we find righ ammount of activities?
        List<SportActivity> foundSportActivityList = sportActivityService.findAll();
        assertTrue(foundSportActivityList.size() >= 2);

        // do we have right activities?
        assertTrue(foundSportActivityList.contains(sportActivity));
        assertTrue(foundSportActivityList.contains(sportActivity2));
    }

    /**
     * Test creating null activity
     */
    @Test(expectedExceptions = IllegalArgumentException.class)
    public void createSportActivityNullTest() {
        sportActivityService.createSportActivity(null);
    }

    /**
     * Test deleting sport activity
     */
    @Test
    public void deleteSportActivityTest() {
        SportActivity sportActivity = new SportActivity();
        sportActivity.setName("Running");
        sportActivityService.createSportActivity(sportActivity);
        Long sportActivityId = sportActivity.getId();

        boolean containsBeforeDelete = sportActivityService.findById(sportActivityId) != null;
        sportActivityService.deleteSportActivity(sportActivity);
        boolean containsAfterDelete = sportActivityService.findById(sportActivityId) != null;

        assertNotEquals(containsBeforeDelete, containsAfterDelete);
    }

    /**
     * Test deleting null activity
     */
    @Test(expectedExceptions = IllegalArgumentException.class)
    public void deleteSportActivityNullTest() {
        sportActivityService.deleteSportActivity(null);
    }

    /**
     * Test finding activity by id
     */
    @Test
    public void findByIdTest() {
        SportActivity sportActivity = new SportActivity();
        sportActivity.setName("Running");
        sportActivityService.createSportActivity(sportActivity);
        boolean isRightSportActivityFound
                = sportActivity.equals(sportActivityService.findById(sportActivity.getId()));

        assertTrue(isRightSportActivityFound);
    }

    /**
     * Test finding activity with null
     */
    @Test(expectedExceptions = IllegalArgumentException.class)
    public void findByIdNullTest() {
        sportActivityService.findById(null);
    }

    /**
     * Test finding all activities
     */
    @Test
    public void findAllTest() {
        SportActivity sportActivity = new SportActivity();
        sportActivity.setName("Running");
        SportActivity sportActivity2 = new SportActivity();
        sportActivity2.setName("Chess");

        sportActivityService.createSportActivity(sportActivity);
        sportActivityService.createSportActivity(sportActivity2);

        // do we find righ ammount of activities?
        List<SportActivity> foundSportActivityList = sportActivityService.findAll();
        assertTrue(foundSportActivityList.size() >= 2);

        // do we have right activities?
        assertTrue(foundSportActivityList.contains(sportActivity));
        assertTrue(foundSportActivityList.contains(sportActivity2));
    }

    /**
     * Test change name of activity
     */
    @Test
    public void changeNameTest() {
        SportActivity sportActivity = new SportActivity();
        sportActivity.setName("Chess");
        sportActivityService.createSportActivity(sportActivity);
        sportActivityService.changeName(sportActivity, "Jumping");
        sportActivity = sportActivityService.findById(sportActivity.getId());
        
        assertEquals(sportActivity.getName(), "Jumping");
    }

}
