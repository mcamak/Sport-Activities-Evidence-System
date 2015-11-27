
import cz.muni.fi.pa165.saes.dao.SportActivityDao;
import cz.muni.fi.pa165.saes.entity.SportActivity;
import cz.muni.fi.pa165.service.SportActivityServiceImpl;
import java.util.ArrayList;
import java.util.Collection;
import org.mockito.InjectMocks;
import org.mockito.Mock;
//import org.junit.Before;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import org.mockito.MockitoAnnotations;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;



/**
 *
 * @author B. Bajtosova
 */
public class SportActivityServiceTest {
    
    @Mock
    private SportActivityDao sportDao;
    
    @InjectMocks
    private SportActivityServiceImpl sportActivity;
    
    
    @BeforeMethod
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }
    
    /**
     * Test creating sport activity 
     */
    
    @Test
    public void createSportActivityTest(){
        SportActivity sact = new SportActivity();
        sact.setName("Running");
        sportActivity.createSportActivity(sact);
    
    }
    
    /**
     * Test creating null activity
     */
    @Test(expectedExceptions = IllegalArgumentException.class)
    public void createSportActivityNullTest(){
        sportActivity.createSportActivity(null);
        verify(sportDao,never()).createSportActivity(null);
        
        
    }
    
    /**
     * Test deleting sport activity
     */
    @Test
    public void deleteSportActivityTest(){
        SportActivity sact = new SportActivity();
        sact.setName("Running");
        sportActivity.deleteSportActivity(sact);
        verify(sportDao).deleteSportActivity(sact);
    }
    
    /**
     * Test deleting null activity
     */
    @Test(expectedExceptions = IllegalArgumentException.class)
    public void deleteSportActivityNullTest(){
        sportActivity.deleteSportActivity(null);
        verify(sportDao,never()).deleteSportActivity(null);
    }
    
    /**
     * Test finding activity by id
     */
    @Test
    public void findByIdTest(){
        SportActivity sact = new SportActivity();
        sact.setName("Running");
        sportActivity.createSportActivity(sact);
       
        doReturn(sact).when(sportDao).findSportActivity(1L);
        
        SportActivity returnActivity = sportActivity.findById(1L);
        verify(sportDao).findSportActivity(1L);
        assertNotNull(returnActivity);
        assertEquals(sact,returnActivity);
    }
    
    /**
     * Test finding activity with null 
     */
    @Test(expectedExceptions = IllegalArgumentException.class)
    public void findByIdNullTest(){
        sportActivity.findById(null);
        verify(sportDao,never()).findSportActivity(null);
    }
    
    /**
     * Test finding all activities
     */
    @Test
    public void findAllTest(){
        SportActivity running = new SportActivity();
        running.setName("Running");
        sportActivity.createSportActivity(running);
        SportActivity jumping = new SportActivity();
        jumping.setName("Jumping");
        sportActivity.createSportActivity(jumping);
        
        Collection<SportActivity> activities = new ArrayList<>();
        activities.add(running);
        activities.add(jumping);
        
        doReturn(activities).when(sportDao).findAll();
        
        Collection<SportActivity> returnActivity = sportActivity.findAll();
        assertNotNull(returnActivity);
        assertEquals(activities,returnActivity);
        
    }
    
    /**
     * Test change name of activity
     */
    @Test
    public void changeNameTest(){
        SportActivity sact = new SportActivity();
        sact.setName("Running");
        sportActivity.createSportActivity(sact);
        sportActivity.changeName(sact, "jumping");
        
        verify(sportDao).updateSportActivity(sact);
        
        assertEquals(sact.getName(),"jumping");
    }
    
    
 
}
