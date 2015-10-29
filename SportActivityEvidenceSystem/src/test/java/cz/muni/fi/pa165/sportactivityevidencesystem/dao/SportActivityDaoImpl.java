/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.sportactivityevidencesystem.dao;

import cz.muni.fi.pa165.sportactivityevidencesystem.PersistenceSampleApplicationContext;
import cz.muni.fi.pa165.sportactivityevidencesystem.entity.SportActivity;
import javax.inject.Inject;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Rule;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.test.context.ContextConfiguration;
import org.testng.annotations.BeforeMethod;
import org.junit.runner.RunWith;

/**
 *
 * @author Jan S.
 */
@RunWith(MockitoJUnitRunner.class)
@ContextConfiguration(classes = PersistenceSampleApplicationContext.class)

public class SportActivityDaoImpl {
    @Rule
    public final ExpectedException expectedException = ExpectedException.none();
    
    @Inject
    SportActivityDao sportActivity;
    
    //@Mock
    
    /**
     * Test creating sport activity
     */
    @Test
    public void testCreateSportActivity(){
        SportActivity sact = new SportActivity();
        sact.setName("Running");
        sportActivity.createSportActivity(sact);
    }
    
    /**
     * Test creating null sport activity
     */
    @Test
    public void testCreateSportActivityNull(){
        sportActivity.createSportActivity(null);
    }
    
    /**
     * Test deleting sport activity
     */
    @Test
    public void deleteSportActivity(){
        SportActivity sact = new SportActivity();
        sact.setName("Running");
        sportActivity.deleteSportActivity(sact);
    }
    
    /**
     * Test deleting null sport activity
     */
    @Test
    public void deleteSportActivityNull(){
        sportActivity.deleteSportActivity(null);
    }
    
    /**
     * Find sport activity by id
     */
    @Test
    public void testFindSportActivity(){
        SportActivity sact = new SportActivity();
        sact.setName("Running");
        sportActivity.createSportActivity(sact);
        
        assertNotNull(sportActivity.findSportActivity(sact.getId()));
    }
    
    /**
     * Test find sport activity with null
     */
    @Test
    public void testFindSportActivityNull(){
        sportActivity.findSportActivity(null);
    }
    
    /**
     * Test updating sport activity
     */
    @Test
    public void testUpdateSportActivity(){
        SportActivity sact = new SportActivity();
        sact.setName("Running");
        sportActivity.createSportActivity(sact);
        
        sact.setName("Football");
        sportActivity.updateSportActivity(sact);
        assertEquals("Football",sact.getName());
    }
    
    /**
     * Test updating null
     */
    @Test
    public void testUpdateSportActivityNull(){
        sportActivity.updateSportActivity(null);
    }
    
}
