package cz.muni.fi.pa165.sportactivityevidencesystem.dao;

import cz.muni.fi.pa165.sportactivityevidencesystem.entity.BurnedCalories;
import cz.muni.fi.pa165.sportactivityevidencesystem.entity.SportActivity;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

/**
 *
 * @author Marian Camak
 */
@RunWith(MockitoJUnitRunner.class)
public class BurnedCaloriesDaoTest {
    
//    @Inject
    private BurnedCaloriesDao burnCalDao;
    
    @Mock
    private SportActivity sportActivity;
    
    @Before
    public void init() {
        burnCalDao = new BurnedCaloriesDaoImpl();
    }
    
    /**
     * Test of createUser method, of class UserDaoImpl.
     */
    @Test
    public void testCreateUser() {
        BurnedCalories bCal = new BurnedCalories();
        bCal.setActivity(sportActivity);
        bCal.setBodyWeight(50);
        bCal.setCaloriesBurned(50);
        
        burnCalDao.create(bCal);
        assertNotNull(bCal.getId());
    }

    /**
     * Test of deleteUser method, of class UserDaoImpl.
     */
    @Test
    public void testDeleteUser() {
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
     * Test of getUser method, of class UserDaoImpl.
     */
    @Test
    public void testGetUser() {
        BurnedCalories bCal = new BurnedCalories();
        bCal.setActivity(sportActivity);
        bCal.setBodyWeight(50);
        bCal.setCaloriesBurned(50);
        
        burnCalDao.create(bCal);
        assertNotNull(burnCalDao.findById(bCal.getId()));
    }

    /**
     * Test of updateUser method, of class UserDaoImpl.
     */
    @Test
    public void testUpdateUser() {
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
    
}
