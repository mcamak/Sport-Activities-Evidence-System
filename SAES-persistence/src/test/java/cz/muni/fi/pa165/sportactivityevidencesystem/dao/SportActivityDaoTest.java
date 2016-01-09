package cz.muni.fi.pa165.sportactivityevidencesystem.dao;

import cz.muni.fi.pa165.saes.SportActivitySystemApplicationContext;
import cz.muni.fi.pa165.saes.dao.SportActivityDao;
import cz.muni.fi.pa165.saes.entity.SportActivity;
import org.springframework.dao.DataAccessException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.testng.annotations.Test;

import javax.inject.Inject;
import javax.transaction.Transactional;

import static org.testng.Assert.*;

/**
 *
 * @author Jan S.
 */
@ContextConfiguration(classes = SportActivitySystemApplicationContext.class)
@TestExecutionListeners(TransactionalTestExecutionListener.class)
@Transactional
public class SportActivityDaoTest extends AbstractTestNGSpringContextTests {

    @Inject
    SportActivityDao sportActivityDao;

    /**
     * Test creating sport activity
     */
    @Test
    public void testCreateSportActivity(){
        SportActivity sact = new SportActivity();
        sact.setName("Running");
        sportActivityDao.createSportActivity(sact);
        assertTrue( sportActivityDao.findSportActivity( sact.getId() ).getName().equals( "Running" ) );
        sportActivityDao.deleteSportActivity(sact);
    }

    /**
     * Test creating null sport activity
     */
    @Test(expectedExceptions = DataAccessException.class)
    public void testCreateSportActivityNull(){
        sportActivityDao.createSportActivity(null);
    }

    /**
     * Test deleting sport activity
     */
    @Test
    public void deleteSportActivity(){
        SportActivity sact = new SportActivity();
        sact.setName("Running");
        sportActivityDao.deleteSportActivity(sact);
    }

    /**
     * Test deleting null sport activity
     */
    @Test(expectedExceptions = DataAccessException.class)
    public void deleteSportActivityNull(){
        sportActivityDao.deleteSportActivity(null);
    }

    /**
     * Find sport activity by id
     */
    @Test
    public void testFindSportActivity(){
        SportActivity sact = new SportActivity();
        sact.setName("Running");
        sportActivityDao.createSportActivity(sact);

        assertNotNull(sportActivityDao.findSportActivity(sact.getId()));
    }

    /**
     * Test find sport activity with null
     */
    @Test(expectedExceptions = DataAccessException.class)
    public void testFindSportActivityNull(){
        sportActivityDao.findSportActivity(null);
    }

    /**
     * Test updating sport activity
     */
    @Test
    public void testUpdateSportActivity(){
        SportActivity sact = new SportActivity();
        sact.setName("Running");
        sportActivityDao.createSportActivity(sact);

        sact.setName("Football");
        sportActivityDao.updateSportActivity(sact);
        assertEquals("Football",sact.getName());
    }

    /**
     * Test updating null
     */
    @Test(expectedExceptions = DataAccessException.class)
    public void testUpdateSportActivityNull(){
        sportActivityDao.updateSportActivity(null);
    }

}