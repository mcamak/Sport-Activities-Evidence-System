package cz.fi.muni.fi.pa165.service.service;

import cz.muni.fi.pa165.saes.entity.BurnedCalories;
import cz.muni.fi.pa165.saes.entity.SportActivity;
import cz.muni.fi.pa165.service.BurnedCaloriesService;
import cz.muni.fi.pa165.service.SportActivityService;
import cz.muni.fi.pa165.service.exceptions.SaesDataAccessException;
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
 * @author Marian Camak
 */
@ContextConfiguration(classes = ServiceConfiguration.class)
public class BurnedCaloriesServiceTest extends AbstractTestNGSpringContextTests {

    @Inject
    private SportActivityService sportActivityService;

    @Inject
    private BurnedCaloriesService burnedCaloriesService;

    BurnedCalories burnedCalories;
    BurnedCalories burnedCalories2;

    @BeforeMethod
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        SportActivity sportActivity = new SportActivity();
        sportActivity.setName("Running");

        sportActivityService.create(sportActivity);

        burnedCalories = new BurnedCalories();
        burnedCalories.setActivity(sportActivity);
        burnedCalories.setBodyWeight(100);
        burnedCalories.setCaloriesBurned(10000);

        burnedCalories2 = new BurnedCalories();
        burnedCalories2.setActivity(sportActivity);
        burnedCalories2.setBodyWeight(100);
        burnedCalories2.setCaloriesBurned(822);
    }

    /**
     * Tests creating burned calories
     */
    @Test
    public void createTest() {

        burnedCaloriesService.create(burnedCalories);
        burnedCaloriesService.create(burnedCalories2);

        // do we find righ ammount of activities? 
        List<BurnedCalories> foundSportActivityList = burnedCaloriesService.findAll();
        assertTrue(foundSportActivityList.size() >= 2);

        // do we have right activities?
        assertTrue(foundSportActivityList.contains(burnedCalories));
        assertTrue(foundSportActivityList.contains(burnedCalories2));
    }

    /**
     * Tests creating burned calories with null argument
     */
    @Test(expectedExceptions = IllegalArgumentException.class)
    public void createNullTest() {
        burnedCaloriesService.create(null);
    }

    /**
     * Tests finding burned calories by id
     */
    @Test
    public void findByIdTest() {
        burnedCaloriesService.create(burnedCalories);
        boolean isRightBurnedCaloriesFound
                = burnedCalories.equals(burnedCaloriesService.findById(burnedCalories.getId()));

        assertTrue(isRightBurnedCaloriesFound);
    }

    /**
     * Tests finding burned calories by null id
     */
    @Test(expectedExceptions = IllegalArgumentException.class)
    public void findByIdNullTest() {
        burnedCaloriesService.findById(null);
    }

    /**
     * Tests updating of burned calories
     */
    @Test
    public void updateTest() {
        burnedCaloriesService.create(burnedCalories);
        burnedCalories.setCaloriesBurned(99999);
        burnedCaloriesService.update(burnedCalories);
        BurnedCalories burnedCaloriesFound = burnedCaloriesService.findById(burnedCalories.getId());

        assertEquals(burnedCaloriesFound, burnedCalories);
    }

    /**
     * Tests updating of burned calories
     */
    @Test(expectedExceptions = IllegalArgumentException.class)
    public void updateNullTest() {
        burnedCaloriesService.update(null);
    }

    /**
     * Tests deleting burned calories
     */
    @Test
    public void deleteTest() {

        burnedCaloriesService.create(burnedCalories);
        Long burdnedCaloriesId = burnedCalories.getId();

        boolean containsBeforeDelete = burnedCaloriesService.findById(burdnedCaloriesId) != null;
        burnedCaloriesService.delete(burnedCalories);
        boolean containsAfterDelete = burnedCaloriesService.findById(burdnedCaloriesId) != null;

        System.out.print("POL " + containsAfterDelete);
        assertNotEquals(containsBeforeDelete, containsAfterDelete);
    }

    /**
     * Tests deleting burned calories
     */
    @Test(expectedExceptions = IllegalArgumentException.class)
    public void deleteNullTest() {
        burnedCaloriesService.delete(null);
    }

    /**
     * Tests finding all activities
     */
    @Test
    public void findAllTest() {
        burnedCaloriesService.create(burnedCalories);
        burnedCaloriesService.create(burnedCalories2);

        // do we find righ ammount of activities?
        List<BurnedCalories> foundBurnedCaloriesList = burnedCaloriesService.findAll();
        assertTrue(foundBurnedCaloriesList.size() >= 2);

        // do we have right activities?
        assertTrue(foundBurnedCaloriesList.contains(burnedCalories));
        assertTrue(foundBurnedCaloriesList.contains(burnedCalories2));
    }
}
