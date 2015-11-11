package cz.muni.fi.pa165.sportactivityevidencesystem.dao;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import cz.muni.fi.pa165.sportactivityevidencesystem.SportActivitySystemApplicationContext;
import cz.muni.fi.pa165.sportactivityevidencesystem.entity.ActivityRecord;
import cz.muni.fi.pa165.sportactivityevidencesystem.entity.SportActivity;
import cz.muni.fi.pa165.sportactivityevidencesystem.entity.User;
import cz.muni.fi.pa165.sportactivityevidencesystem.enums.Gender;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import org.junit.Before;
import org.junit.Test;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertNull;
import org.testng.annotations.BeforeMethod;
import org.junit.Rule;
import org.junit.rules.ExpectedException;

/**
 *
 * @author Barbora B.
 */
@ContextConfiguration(classes = SportActivitySystemApplicationContext.class)
@TestExecutionListeners(TransactionalTestExecutionListener.class)
@Transactional
public class ActivityRecordDaoTest extends AbstractTestNGSpringContextTests{
    
    @Rule
    public final ExpectedException expectedException = ExpectedException.none();
    
    @PersistenceContext
    public EntityManager em;
    
    @Inject
    private ActivityRecordDao activityRecord;
    
    @Inject 
    private SportActivity sportActivity;
    private SportActivityDao activityDao;
    private User user;
    private UserDao userDao;
    
    
    
   @BeforeMethod
   public void init(){
       sportActivity = new SportActivity();
       sportActivity.setName("Running");
       activityDao.createSportActivity(sportActivity);
       
       user = new User();
       user.setName("Peter");
       user.setAge(35);
       user.setSex(Gender.MALE);
       user.setWeight(95);
       userDao.createUser(user);
   }
   
   /**
    * Create and set activity record for later use
    * @return ActivityRecord
    */
   public ActivityRecord setActivityRecord(){
       ActivityRecord actv = new ActivityRecord();
       actv.setActivity(sportActivity);
       actv.setDistance(5);
       actv.setTimeSeconds(Long.MIN_VALUE);
       actv.addUser(user);
       activityRecord.create(actv);
       return actv;
   }
   
    
    /**
     * Test creating activity record with null parameter
     */
    @Test(expected = IllegalArgumentException.class)
    public void testCreateWithNull(){
        activityRecord.create(null);
    }
    
    /**
     * Test creating activity record
     */
    
    @Test(expected = IllegalArgumentException.class)
    public void testCreate(){
        ActivityRecord record= setActivityRecord();
        assertNotNull(record.getId());
    }
    
    /**
     * Test creating record with null sport activity
     */
    @Test(expected = IllegalArgumentException.class)
    public void testCreateNullActivity(){
        ActivityRecord record= new ActivityRecord();
        record.setActivity(null);
        record.setDistance(5);
        record.setTimeSeconds(Long.MIN_VALUE);
        record.addUser(user);
        
        activityRecord.create(record);   
    
    }
    
    /**
     * Test creating record with wrong distance
     */
    @Test(expected = IllegalArgumentException.class)
    public void testCreateWrongDistance(){
        ActivityRecord record= new ActivityRecord();
        record.setActivity(sportActivity);
        record.setDistance(-5);
        record.setTimeSeconds(Long.MIN_VALUE);
        record.addUser(user);
        
        activityRecord.create(record);   
    
    }
    
    /**
     * Test creating record with wrong time
     */
    @Test(expected = IllegalArgumentException.class)
    public void testCreateWrongTime(){
        ActivityRecord record= new ActivityRecord();
        record.setActivity(sportActivity);
        record.setDistance(5);
        record.setTimeSeconds(null);
        record.addUser(user);
        
        activityRecord.create(record);   
    
    }
    
    /**
     * Test creating record with null user
     */
    @Test(expected = IllegalArgumentException.class)
    public void testCreateNullUser(){
        ActivityRecord record= new ActivityRecord();
        record.setActivity(sportActivity);
        record.setDistance(5);
        record.setTimeSeconds(Long.MIN_VALUE);
        record.addUser(null);
        
        activityRecord.create(record);   
    
    }
    
    /**
     * Test deleting record with null parameter
     */
    @Test(expected = IllegalArgumentException.class)
    public void testDeleteNull(){
        activityRecord.delete(null);
    }
    
    /**
     * Test deleting record
     */
    @Test(expected = IllegalArgumentException.class)
    public void testDelete(){
        ActivityRecord record= setActivityRecord();
        
        Long id = record.getId();
        assertNotNull(activityRecord.findActivityRecord(id));
        
        activityRecord.delete(record);
        assertNull(activityRecord.findActivityRecord(id));
        
        
    }
    
    /**
     * Test updating activity record
     */
    @Test(expected = IllegalArgumentException.class)
    public void testUpdate(){
        ActivityRecord record= setActivityRecord();
   
        Long id = record.getId();
        assertNotNull(activityRecord.findActivityRecord(id));
        
        record.setDistance(15);
        record.setTimeSeconds(Long.MAX_VALUE);
        activityRecord.update(record);
        
        ActivityRecord recordFound = activityRecord.findActivityRecord(id);
        assertEquals(15,recordFound.getDistance());
    //    assertEquals(Long.MAX_VALUE,recordFound.getTimeSeconds());
    }
    
    /**
     * Test updating activity with null
     */
    @Test(expected = IllegalArgumentException.class)
    public void testUpdateNull(){
        activityRecord.update(null);
    }
    
    /**
     * Test updating activity with null sport activity
     */
    @Test(expected = IllegalArgumentException.class)
    public void testUpdateNullActivity(){
        ActivityRecord record= setActivityRecord();
        record.setActivity(null);
        activityRecord.update(record);
    }
    
    /**
     * Test updating activity with wrong distance
     */
    @Test(expected = IllegalArgumentException.class)
    public void testUpdateWrongDistance(){
        ActivityRecord record= setActivityRecord();
        record.setDistance(-5);
        activityRecord.update(record);
    }
    
     /**
     * Test updating activity with null time
     */
    @Test(expected = IllegalArgumentException.class)
    public void testUpdateWrongTime(){
        ActivityRecord record= setActivityRecord();
        record.setTimeSeconds(null);
        activityRecord.update(record);
    }
    
     /**
     * Test updating activity with null user
     */
    @Test(expected = IllegalArgumentException.class)
    public void testUpdateNullUser(){
        ActivityRecord record= setActivityRecord();
        record.addUser(null);
        activityRecord.update(record);
    }
    
    
    /**
     * Test getting activity record by null id
     */
    @Test(expected = IllegalArgumentException.class)
    public void testFindActivityRecordNull(){
        activityRecord.findActivityRecord(null);
        
    }
    

}
