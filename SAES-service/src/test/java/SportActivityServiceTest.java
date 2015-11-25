
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
import static org.testng.Assert.assertTrue;
import org.testng.annotations.Test;



/**
 *
 * @author B. Bajtosova
 */
public class SportActivityServiceTest {
    
    @Mock
    private SportActivityDao sportDao;
    
    @InjectMocks
    private SportActivityServiceImpl sportActivity;
    
    
    //@Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }
    
    @Test
    public void createSportActivityTest(){
        SportActivity sact = new SportActivity();
        sact.setName("Running");
        sportActivity.createSportActivity(sact);
    
    }
    
    @Test(expectedExceptions = IllegalArgumentException.class)
    public void createSportActivityNullTest(){
        sportActivity.createSportActivity(null);
        verify(sportDao,never()).createSportActivity(null);
        
        
    }
    
    @Test
    public void deleteSportActivityTest(){
        SportActivity sact = new SportActivity();
        sact.setName("Running");
        sportActivity.deleteSportActivity(sact);
        verify(sportDao).deleteSportActivity(sact);
    }
    
    @Test
    public void deleteSportActivityNullTest(){
        sportActivity.deleteSportActivity(null);
        verify(sportDao,never()).deleteSportActivity(null);
    }
    
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
    
    @Test
    public void findByIdNullTest(){
        sportActivity.findById(null);
        verify(sportDao,never()).findSportActivity(null);
    }
    
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
