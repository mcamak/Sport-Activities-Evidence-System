package cz.muni.fi.pa165.sportactivityevidencesystem.dao;

import cz.muni.fi.pa165.saes.SportActivitySystemApplicationContext;
import cz.muni.fi.pa165.saes.dao.BurnedCaloriesDao;
import cz.muni.fi.pa165.saes.dao.SportActivityDao;
import cz.muni.fi.pa165.saes.entity.BurnedCalories;
import cz.muni.fi.pa165.saes.entity.SportActivity;
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
 * @author Marian Camak
 */
@ContextConfiguration(classes = SportActivitySystemApplicationContext.class)
@TestExecutionListeners(TransactionalTestExecutionListener.class)
@Transactional
public class BurnedCaloriesDaoTest extends AbstractTestNGSpringContextTests {

    @PersistenceContext
    public EntityManager em;

    @Inject
    private BurnedCaloriesDao burnCalDao;

    @Inject
    private SportActivityDao activityDao;

    private SportActivity sportActivity;

    @BeforeMethod
    public void setUpMethod() {
        sportActivity = new SportActivity();
        sportActivity.setName("Running");
        activityDao.createSportActivity(sportActivity);
    }

    /**
     * Test creating burned calory.
     */
    @Test
    public void testCreateBurnedCalory() {
        BurnedCalories bCal = createNewRandomBurnedCalory();
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
        BurnedCalories bCal = createNewRandomBurnedCalory();
        bCal.setActivity(null);

        burnCalDao.create(bCal);
    }

    /**
     * Test creating burned calory with negative body weight.
     */
    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testCreateBurnedCaloryWithNegativeBodyWeight() {
        BurnedCalories bCal = createNewRandomBurnedCalory();
        bCal.setBodyWeight(-50);

        burnCalDao.create(bCal);
    }

    /**
     * Test creating burned calory with negative calories burned.
     */
    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testCreateBurnedCaloryWithNegativeCaloriesBurned() {
        BurnedCalories bCal = createNewRandomBurnedCalory();
        bCal.setCaloriesBurned(-50);

        burnCalDao.create(bCal);
    }

    /**
     * Test deleting burned category.
     */
    @Test
    public void testDeleteBurnedCalory() {
        BurnedCalories bCal = createNewRandomBurnedCalory();

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
        BurnedCalories bCal = createNewRandomBurnedCalory();
        burnCalDao.delete(bCal);
    }

    /**
     * Test finding burned category.
     */
    @Test
    public void testFindBurnedCategory() {
        BurnedCalories bCal = createNewRandomBurnedCalory();
        burnCalDao.create(bCal);
        assertNotNull(burnCalDao.findById(bCal.getId()));
    }

    /**
     * Test finding burned category with null ID.
     */
    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testFindBurnedCategoryWithNullId() {
        burnCalDao.findById(null);
    }

    /**
     * Test finding all burned categories.
     */
    @Test
    public void testFindAllBurnedCategories() {
        BurnedCalories bCal1 = createNewRandomBurnedCalory();
        BurnedCalories bCal2 = createNewRandomBurnedCalory();
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
    @Test
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
        BurnedCalories bCal = createNewRandomBurnedCalory();
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
        BurnedCalories bCal = createNewRandomBurnedCalory();
        burnCalDao.update(bCal);
    }

    /**
     * Test updating burned category with null sport activity.
     */
    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testUpdateBurnedCategoryWithNullSportActivity() {
        BurnedCalories bCal = createNewRandomBurnedCalory();
        burnCalDao.create(bCal);
        assertNotNull(burnCalDao.findById(bCal.getId()));

        bCal.setActivity(null);
        burnCalDao.update(bCal);
    }

    /**
     * Test updating burned category with negative body weight.
     */
    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testUpdateBurnedCategoryWithNegativeBodyWeight() {
        BurnedCalories bCal = createNewRandomBurnedCalory();
        burnCalDao.create(bCal);
        assertNotNull(burnCalDao.findById(bCal.getId()));

        bCal.setBodyWeight(-70);
        burnCalDao.update(bCal);
    }

    /**
     * Test updating burned category with negative calories burned.
     */
    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testUpdateBurnedCategoryWithNegativeCaloriesBurned() {
        BurnedCalories bCal = createNewRandomBurnedCalory();
        burnCalDao.create(bCal);
        assertNotNull(burnCalDao.findById(bCal.getId()));

        bCal.setCaloriesBurned(-40);
        burnCalDao.update(bCal);
    }

    private BurnedCalories createNewRandomBurnedCalory() {
        BurnedCalories bCal = new BurnedCalories();
        bCal.setActivity(sportActivity);
        bCal.setBodyWeight((int) (System.nanoTime() % 150));
        bCal.setCaloriesBurned((int) (System.nanoTime() % 2000));
        return bCal;
    }
}
