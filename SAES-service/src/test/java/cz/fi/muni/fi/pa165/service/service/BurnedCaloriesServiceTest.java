package cz.fi.muni.fi.pa165.service.service;

import cz.muni.fi.pa165.saes.dao.BurnedCaloriesDao;
import cz.muni.fi.pa165.saes.dao.SportActivityDao;
import cz.muni.fi.pa165.saes.entity.BurnedCalories;
import cz.muni.fi.pa165.saes.entity.SportActivity;
import cz.muni.fi.pa165.service.BurnedCaloriesServiceImpl;
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
 * @author Marian Camak
 */
@ContextConfiguration(classes = ServiceConfiguration.class)
public class BurnedCaloriesServiceTest extends AbstractTestNGSpringContextTests {

    @Mock
    private SportActivityDao activityDao;

    @Mock
    private BurnedCaloriesDao caloriesDao;

    @InjectMocks
    private BurnedCaloriesServiceImpl burnedCaloriesService;

    BurnedCalories burnedCalories;
    BurnedCalories burnedCalories2;

    List<BurnedCalories> allCalories;

    SportActivity sportActivity;

    @BeforeMethod
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        sportActivity = new SportActivity();
        sportActivity.setId(898L);
        sportActivity.setName("Running");

        burnedCalories = new BurnedCalories();
        burnedCalories.setId(334L);
        burnedCalories.setActivity(sportActivity);
        burnedCalories.setBodyWeight(100);
        burnedCalories.setCaloriesBurned(10000);

        burnedCalories2 = new BurnedCalories();
        burnedCalories2.setId(335L);
        burnedCalories2.setActivity(sportActivity);
        burnedCalories2.setBodyWeight(100);
        burnedCalories2.setCaloriesBurned(822);

        allCalories = new ArrayList<>();
        allCalories.add(burnedCalories);
        allCalories.add(burnedCalories2);
    }

    /**
     * Tests creating burned calories
     */
    @Test
    public void createTest() {
        burnedCaloriesService.create(burnedCalories);
        verify(caloriesDao).create(burnedCalories);
    }

    /**
     * Tests creating burned calories with null argument
     */
    @Test(expectedExceptions = IllegalArgumentException.class)
    public void createNullTest() {
        Mockito.doThrow(IllegalArgumentException.class).when(caloriesDao).create(null);
        burnedCaloriesService.create(null);
    }

    /**
     * Tests finding burned calories by id
     */
    @Test
    public void findByIdTest() {
        burnedCaloriesService.findById(burnedCalories.getId());
        verify(caloriesDao).findById(burnedCalories.getId());
    }

    /**
     * Tests finding burned calories by null id
     */
    @Test(expectedExceptions = IllegalArgumentException.class)
    public void findByIdNullTest() {
        Mockito.doThrow(IllegalArgumentException.class).when(caloriesDao).findById(null);
        burnedCaloriesService.findById(null);
    }

    /**
     * Tests finding burned calories by sport activity
     */
    @Test
    public void findBySportActivityTest() {
        when(activityDao.findSportActivity(sportActivity.getId())).thenReturn(sportActivity);
        when(caloriesDao.findBySportActivity(sportActivity)).thenReturn(allCalories);
        List<BurnedCalories> result = burnedCaloriesService.findBySportActivity(sportActivity.getId());
        assertTrue(result.size() == allCalories.size());
        assertTrue(result.contains(burnedCalories));
        assertTrue(result.contains(burnedCalories2));
    }

    /**
     * Tests updating of burned calories
     */
    @Test
    public void updateTest() {
        burnedCaloriesService.update(burnedCalories);
        verify(caloriesDao).update(burnedCalories);
    }

    /**
     * Tests updating of burned calories
     */
    @Test(expectedExceptions = IllegalArgumentException.class)
    public void updateNullTest() {
        Mockito.doThrow(IllegalArgumentException.class).when(caloriesDao).update(null);
        burnedCaloriesService.update(null);
    }

    /**
     * Tests deleting burned calories
     */
    @Test
    public void deleteTest() {
        when(caloriesDao.findById(burnedCalories.getId())).thenReturn(burnedCalories);
        burnedCaloriesService.delete(burnedCalories.getId());
        verify(caloriesDao).delete(burnedCalories);
    }

    /**
     * Tests deleting burned calories
     */
    @Test(expectedExceptions = IllegalArgumentException.class)
    public void deleteNullTest() {
        Mockito.doThrow(IllegalArgumentException.class).when(caloriesDao.findById(null));
        burnedCaloriesService.delete(null);
    }

    /**
     * Tests finding all activities
     */
    @Test
    public void findAllTest() {
        burnedCaloriesService.findAll();
        verify(caloriesDao).findAll();
    }
}
