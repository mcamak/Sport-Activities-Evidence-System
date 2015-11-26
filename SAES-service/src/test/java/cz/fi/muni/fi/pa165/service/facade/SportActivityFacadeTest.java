/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.fi.muni.fi.pa165.service.facade;

import cz.muni.fi.pa165.dto.SportActivityDTO;
import cz.muni.fi.pa165.facade.SportActivityFacade;
import cz.muni.fi.pa165.saes.SportActivitySystemApplicationContext;
import java.util.Collection;
import javax.inject.Inject;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;
import org.mockito.InjectMocks;
import org.springframework.test.context.ContextConfiguration;
import org.testng.annotations.Test;



/**
 *
 * @author B. Bajtosova
 */

@ContextConfiguration(classes = SportActivitySystemApplicationContext.class)
public class SportActivityFacadeTest {
    
    @Inject
    @InjectMocks
    private SportActivityFacade facade;
    
    @Test
    public void createSportActivityTest(){
        SportActivityDTO sactivity = new SportActivityDTO();
        sactivity.setName("Running");
        facade.createSportActivity(sactivity);
        assertTrue(facade.getActivityWithId( sactivity.getId() ).getName().equals( "Running" ));
        
    }
    
    @Test
    public void changeActivityNameTest(){
        SportActivityDTO sactivity = new SportActivityDTO();
        sactivity.setName("Running");
        facade.createSportActivity(sactivity);
        facade.changeActivityName(sactivity, "Jumping");
        assertTrue(facade.getActivityWithId( sactivity.getId() ).getName().equals( "Jumping" ));
    
    }
    
    @Test 
    public void deleteSportActivityTest(){
        SportActivityDTO sactivity = new SportActivityDTO();
        sactivity.setName("Running");
        facade.createSportActivity(sactivity);
        assertEquals(sactivity, facade.getActivityWithId(sactivity.getId()));
        facade.deleteActivity(sactivity.getId()); 
    }
    
    @Test
    public void getAllActivitiesTest(){
        SportActivityDTO running = new SportActivityDTO();
        SportActivityDTO jumping = new SportActivityDTO();
        running.setName("Running");
        jumping.setName("Jumping");
        
        facade.createSportActivity(running);
        facade.createSportActivity(jumping);
        
        Collection<SportActivityDTO> activities = facade.getAllActivities();
        
        assertTrue(activities.contains(running));
        assertTrue(activities.contains(jumping));
    
    }
    
    @Test 
    public void getActivityWithIdTest(){
        SportActivityDTO sactivity = new SportActivityDTO();
        sactivity.setName("Running");
        facade.createSportActivity(sactivity);
        assertEquals(sactivity, facade.getActivityWithId(sactivity.getId()));
    
    }
 
    
 
}
