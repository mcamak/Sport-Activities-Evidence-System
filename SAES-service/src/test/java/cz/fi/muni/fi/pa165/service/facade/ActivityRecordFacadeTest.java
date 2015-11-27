/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.fi.muni.fi.pa165.service.facade;

import cz.muni.fi.pa165.dto.ActivityRecordDTO;
import cz.muni.fi.pa165.dto.SportActivityDTO;
import cz.muni.fi.pa165.dto.UserCreateDTO;
import cz.muni.fi.pa165.dto.UserDTO;
import cz.muni.fi.pa165.facade.ActivityRecordFacade;
import cz.muni.fi.pa165.facade.SportActivityFacade;
import cz.muni.fi.pa165.facade.UserFacade;
import cz.muni.fi.pa165.saes.SportActivitySystemApplicationContext;
import enums.Gender;
import cz.muni.fi.pa165.service.facade.ActivityRecordFacadeImpl;
import org.mockito.InjectMocks;
import org.springframework.test.context.ContextConfiguration;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import javax.inject.Inject;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertNull;
import static org.testng.Assert.assertEquals;

/**
 *
 * @author B. Bajtosova
 */
@ContextConfiguration(classes = SportActivitySystemApplicationContext.class)
public class ActivityRecordFacadeTest {

    @Mock
    private SportActivityFacade sportFacade;

    @Mock
    private UserFacade userFacade;

    @InjectMocks
    private ActivityRecordFacade recordFacade = new ActivityRecordFacadeImpl();

    @Inject
    private UserCreateDTO newUser;

    @Inject
    private UserDTO existingUser;

    @Inject
    private SportActivityDTO activity;

    @BeforeMethod
    public void init() {
        MockitoAnnotations.initMocks(this);

        activity = new SportActivityDTO();
        activity.setName("Running");
        sportFacade.createSportActivity(activity);

        newUser = new UserCreateDTO();
        newUser.setName("Peter");
        newUser.setAge(35);
        newUser.setSex(Gender.MALE);
        newUser.setWeight(95);
        userFacade.createUser(newUser);

        existingUser = new UserDTO();
        existingUser.setId(1L);
        existingUser.setName("Peter");
        existingUser.setAge(35);
        existingUser.setSex(Gender.MALE);
        existingUser.setWeight(95);

    }

    /**
     * Create and set activity record for later use
     *
     * @return ActivityRecordDTO
     */
    private ActivityRecordDTO setActivityRecord() {
        ActivityRecordDTO actv = new ActivityRecordDTO();
        actv.setActivity(activity);
        actv.setDistance(5);
        actv.setTimeSeconds(45L);
        actv.addUser(existingUser);
        return actv;
    }

    /**
     * Test creating activity record
     */
    @Test
    public void createTest() {
        ActivityRecordDTO record = new ActivityRecordDTO();
        recordFacade.create(record);
        assertNotNull(record.getId());

    }

    /**
     * Test deleting record
     */
    @Test
    public void deleteActivityRecordTest() {

        ActivityRecordDTO record = setActivityRecord();
        recordFacade.create(record);
        assertNotNull(recordFacade.findById(record.getId()));

        recordFacade.deleteActivityRecord(record.getId());
        assertNull(recordFacade.findById(record.getId()));

    }

    /**
     * Test getting activity record by id
     */
    @Test
    public void findByIdTest() {
        ActivityRecordDTO record = setActivityRecord();
        recordFacade.create(record);

        assertNotNull(recordFacade.findById(record.getId()));
    }

    /**
     * @Test public void removeUserFromRecordTest(){ ActivityRecordDTO record =
     * setActivityRecord(); recordFacade.create(record);
     *
     *
     * }
     *
     */
    /**
     * Test updating record
     */
    @Test
    public void updateActivityRecordTest() {
        ActivityRecordDTO record = setActivityRecord();
        recordFacade.create(record);
        assertNotNull(recordFacade.findById(record.getId()));

        record.setDistance(15);
        record.setTimeSeconds(20L);
        recordFacade.updateActivityRecord(record);

        ActivityRecordDTO recordFound = recordFacade.findById(record.getId());
        assertEquals(recordFound.getDistance(), 15);
        assertEquals(recordFound.getTimeSeconds(), new Long(20));

    }

}
