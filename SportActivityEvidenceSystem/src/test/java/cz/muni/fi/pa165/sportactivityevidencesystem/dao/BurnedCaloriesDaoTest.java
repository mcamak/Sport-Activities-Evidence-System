package cz.muni.fi.pa165.sportactivityevidencesystem.dao;

import cz.muni.fi.pa165.sportactivityevidencesystem.SportActivitySystemApplicationContext;
import cz.muni.fi.pa165.sportactivityevidencesystem.entity.BurnedCalories;
import cz.muni.fi.pa165.sportactivityevidencesystem.entity.SportActivity;
import java.util.List;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import org.junit.runner.RunWith;
import org.testng.annotations.Test;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertNull;
import static org.testng.Assert.assertTrue;

/**
 *
 * @author Marian Camak
 */
@ContextConfiguration(classes = SportActivitySystemApplicationContext.class)
@TestExecutionListeners(TransactionalTestExecutionListener.class)
@Transactional
@RunWith(MockitoJUnitRunner.class)
public class BurnedCaloriesDaoTest extends AbstractTestNGSpringContextTests {
    
    @PersistenceContext
    public EntityManager em;

    @Inject
    private BurnedCaloriesDao burnCalDao;

    @Mock
    private final SportActivity sportActivity = new SportActivity();

    /**
     * Test creating burned calory.
     */
    @Test
    public void testCreateBurnedCalory() {
        BurnedCalories bCal = new BurnedCalories();
        bCal.setActivity(sportActivity);
        bCal.setBodyWeight(50);
        bCal.setCaloriesBurned(50);

        burnCalDao.create(bCal);
        assertNotNull(bCal.getId());
    }

    /**
     * Test creating burned calory with null calory.
     */
    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testCreateBurnedCaloryWithNullParameter() {
        burnCalDao.create(null);
    }

    /**
     * Test creating burned calory with null sport activity.
     */
    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testCreateBurnedCaloryWithNullSportActivity() {
        BurnedCalories bCal = new BurnedCalories();
        bCal.setActivity(null);
        bCal.setBodyWeight(50);
        bCal.setCaloriesBurned(50);

        burnCalDao.create(bCal);
    }

    /**
     * Test creating burned calory with negative body weight.
     */
    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testCreateBurnedCaloryWithNegativeBodyWeight() {
        BurnedCalories bCal = new BurnedCalories();
        bCal.setActivity(sportActivity);
        bCal.setBodyWeight(-50);
        bCal.setCaloriesBurned(50);

        burnCalDao.create(bCal);
    }

    /**
     * Test creating burned calory with negative calories burned.
     */
    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testCreateBurnedCaloryWithNegativeCaloriesBurned() {
        BurnedCalories bCal = new BurnedCalories();
        bCal.setActivity(sportActivity);
        bCal.setBodyWeight(50);
        bCal.setCaloriesBurned(-50);

        burnCalDao.create(bCal);
    }

    /**
     * Test deleting burned category.
     */
    @Test
    public void testDeleteBurnedCalory() {
        BurnedCalories bCal = new BurnedCalories();
        bCal.setActivity(sportActivity);
        bCal.setBodyWeight(50);
        bCal.setCaloriesBurned(50);

        burnCalDao.create(bCal);
        assertNotNull(burnCalDao.findById(bCal.getId()));

        burnCalDao.delete(bCal);
        assertNull(burnCalDao.findById(bCal.getId()));
    }

    /**
     * Test deleting burned category with null category .
     */
    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testDeleteBurnedCaloryWithNullCategory() {
        burnCalDao.delete(null);
    }

    /**
     * Test deleting burned category with null category id.
     */
    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testDeleteBurnedCaloryWithNullId() {
        BurnedCalories bCal = new BurnedCalories();
        bCal.setActivity(sportActivity);
        bCal.setBodyWeight(50);
        bCal.setCaloriesBurned(50);

        burnCalDao.delete(bCal);
    }

    /**
     * Test finding burned category.
     */
    @Test
    public void testFindBurnedCategory() {
        BurnedCalories bCal = new BurnedCalories();
        bCal.setActivity(sportActivity);
        bCal.setBodyWeight(50);
        bCal.setCaloriesBurned(50);

        burnCalDao.create(bCal);
        assertNotNull(burnCalDao.findById(bCal.getId()));
    }

    /**
     * Test finding burned category with null ID.
     */
    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testFindBurnedCategoryWithNullId() {
        BurnedCalories bCal = new BurnedCalories();
        bCal.setActivity(sportActivity);
        bCal.setBodyWeight(50);
        bCal.setCaloriesBurned(50);

        burnCalDao.create(bCal);
        assertNotNull(burnCalDao.findById(bCal.getId()));

        burnCalDao.findById(null);
    }
    
    /**
     * Test finding all burned categories.
     */
    public void testFindAllBurnedCategories() {
        BurnedCalories bCal1 = new BurnedCalories();
        bCal1.setActivity(sportActivity);
        bCal1.setBodyWeight(50);
        bCal1.setCaloriesBurned(50);
        
        BurnedCalories bCal2 = new BurnedCalories();
        bCal2.setActivity(sportActivity);
        bCal2.setBodyWeight(30);
        bCal2.setCaloriesBurned(40);

        burnCalDao.create(bCal1);
        List<BurnedCalories> all = burnCalDao.findAll();
        assertEquals(all.size(), 1);
        assertEquals(all.get(0), bCal1);
        burnCalDao.create(bCal2);
        all = burnCalDao.findAll();
        assertEquals(all.size(), 2);
        assertTrue(all.contains(bCal1));
        assertTrue(all.contains(bCal2));
    }
    
    /**
     * Test finding all burned categories with no categories.
     */
    public void testFindAllBurnedCategoriesWithEmpty() {
        List<BurnedCalories> all = burnCalDao.findAll();
        assertTrue(all != null);
        assertTrue(all.isEmpty());
    }

    /**
     * Test updating burned category.
     */
    @Test
    public void testUpdateBurnedCategory() {
        BurnedCalories bCal = new BurnedCalories();
        bCal.setActivity(sportActivity);
        bCal.setBodyWeight(50);
        bCal.setCaloriesBurned(50);

        burnCalDao.create(bCal);
        assertNotNull(burnCalDao.findById(bCal.getId()));

        bCal.setBodyWeight(70);
        bCal.setCaloriesBurned(40);
        burnCalDao.update(bCal);

        BurnedCalories found = burnCalDao.findById(bCal.getId());
        assertEquals(70, found.getBodyWeight());
        assertEquals(40, found.getCaloriesBurned());
    }

    /**
     * Test updating burned category with null category.
     */
    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testUpdateBurnedCategoryWithNullCategory() {
        burnCalDao.update(null);
    }

    /**
     * Test updating burned category with null category id.
     */
    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testUpdateBurnedCategoryWithNullCategoryId() {
        BurnedCalories bCal = new BurnedCalories();
        bCal.setActivity(sportActivity);
        bCal.setBodyWeight(70);
        bCal.setCaloriesBurned(40);
        burnCalDao.update(bCal);
    }

    /**
     * Test updating burned category with null sport activity.
     */
    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testUpdateBurnedCategoryWithNullSportActivity() {
        BurnedCalories bCal = new BurnedCalories();
        bCal.setActivity(sportActivity);
        bCal.setBodyWeight(50);
        bCal.setCaloriesBurned(50);

        burnCalDao.create(bCal);
        assertNotNull(burnCalDao.findById(bCal.getId()));

        bCal.setActivity(null);
        bCal.setBodyWeight(70);
        bCal.setCaloriesBurned(40);
        burnCalDao.update(bCal);
    }

    /**
     * Test updating burned category with negative body weight.
     */
    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testUpdateBurnedCategoryWithNegativeBodyWeight() {
        BurnedCalories bCal = new BurnedCalories();
        bCal.setActivity(sportActivity);
        bCal.setBodyWeight(50);
        bCal.setCaloriesBurned(50);

        burnCalDao.create(bCal);
        assertNotNull(burnCalDao.findById(bCal.getId()));

        bCal.setBodyWeight(-70);
        bCal.setCaloriesBurned(40);
        burnCalDao.update(bCal);
    }

    /**
     * Test updating burned category with negative calories burned.
     */
    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testUpdateBurnedCategoryWithNegativeCaloriesBurned() {
        BurnedCalories bCal = new BurnedCalories();
        bCal.setActivity(sportActivity);
        bCal.setBodyWeight(50);
        bCal.setCaloriesBurned(50);

        burnCalDao.create(bCal);
        assertNotNull(burnCalDao.findById(bCal.getId()));

        bCal.setBodyWeight(70);
        bCal.setCaloriesBurned(-40);
        burnCalDao.update(bCal);
    }
}
