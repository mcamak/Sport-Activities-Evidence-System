/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.sportactivityevidencesystem.dao;

import cz.muni.fi.pa165.sportactivityevidencesystem.SportActivitySystemApplicationContext;
import cz.muni.fi.pa165.sportactivityevidencesystem.entity.SportActivity;
import javax.inject.Inject;
import javax.transaction.Transactional;
import org.testng.annotations.Test;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertTrue;
import org.junit.Rule;
import org.junit.rules.ExpectedException;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.test.context.ContextConfiguration;
import org.junit.runner.RunWith;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;

/**
 *
 * @author Jan S.
 */
@RunWith(MockitoJUnitRunner.class)
@ContextConfiguration(classes = SportActivitySystemApplicationContext.class)
@TestExecutionListeners(TransactionalTestExecutionListener.class)
@Transactional
public class SportActivityDaoTest extends AbstractTestNGSpringContextTests {
    @Rule
    public final ExpectedException expectedException = ExpectedException.none();
    
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
    @Test(expectedExceptions = IllegalArgumentException.class)
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
    @Test(expectedExceptions = IllegalArgumentException.class)
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
    @Test(expectedExceptions = IllegalArgumentException.class)
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
    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testUpdateSportActivityNull(){
        sportActivityDao.updateSportActivity(null);
    }
    
}