/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.fi.muni.fi.pa165.service.facade;

import cz.muni.fi.pa165.dto.ActivityRecordDTO;
import cz.muni.fi.pa165.dto.SportActivityDTO;
import cz.muni.fi.pa165.facade.ActivityRecordFacade;
import cz.muni.fi.pa165.saes.entity.ActivityRecord;
import cz.muni.fi.pa165.saes.entity.SportActivity;
import cz.muni.fi.pa165.service.ActivityRecordService;
import cz.muni.fi.pa165.service.mapping.ServiceConfiguration;
import org.springframework.test.context.ContextConfiguration;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import javax.inject.Inject;
import org.mockito.Mock;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;
import org.springframework.aop.framework.Advised;
import org.springframework.aop.support.AopUtils;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.util.ReflectionTestUtils;
import static org.testng.Assert.assertEquals;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;

/**
 *
 * @author B. Bajtosova
 */
@ContextConfiguration(classes = ServiceConfiguration.class)
public class ActivityRecordFacadeTest extends AbstractTestNGSpringContextTests {

    @Inject
    private ActivityRecordFacade activityRecordFacade;

    @Mock
    private ActivityRecordService activityRecordService;

    private SportActivityDTO sportActivityDTO;
    private ActivityRecord activityRecord;
    private ActivityRecordDTO activityRecordDTO;

    @BeforeClass
    public void setup() throws Exception {
        MockitoAnnotations.initMocks(this);
        if (AopUtils.isAopProxy(activityRecordFacade)
                && activityRecordFacade instanceof Advised) {
            activityRecordFacade = (ActivityRecordFacade) ((Advised) activityRecordFacade)
                    .getTargetSource().getTarget();
        }
        ReflectionTestUtils.setField(activityRecordFacade,
                "activityRecordService",
                activityRecordService);
    }

    @BeforeMethod
    public void init() {
        SportActivity sportActivity = new SportActivity();
        sportActivity.setName("Running");

        sportActivityDTO = new SportActivityDTO();
        sportActivityDTO.setName(sportActivity.getName());

        activityRecord = new ActivityRecord();
        activityRecord.setDistance(12000);
        activityRecord.setTimeSeconds(888L);
        activityRecord.setActivity(sportActivity);

        activityRecordDTO = new ActivityRecordDTO();
        activityRecordDTO.setDistance(activityRecord.getDistance());
        activityRecordDTO.setTimeSeconds(activityRecord.getTimeSeconds());
        activityRecordDTO.setActivity(sportActivityDTO);
    }

    @AfterMethod
    public void cleanUp() {
        reset(activityRecordService);
    }

    /**
     * Test creating activity record
     */
    @Test
    public void createTest() {
        activityRecordFacade.create(activityRecordDTO);
        verify(activityRecordService).create(activityRecord);
    }

    /**
     * Test getting activity record by id
     */
    @Test
    public void findByIdTest() {
        Long id = activityRecord.getId();
        activityRecordDTO.setId(id);
        when(activityRecordService.findById(id)).thenReturn(activityRecord);
        ActivityRecordDTO foundactivityRecord = activityRecordFacade.findById(id);
        assertEquals(foundactivityRecord, activityRecordDTO);
    }

    /**
     * Test updating record
     */
    @Test
    public void updateTest() {
        activityRecordDTO.setId(99L);
        activityRecordFacade.updateActivityRecord(activityRecordDTO);
        verify(activityRecordService).update(activityRecord);
    }

    /**
     * Test deleting record
     */
    @Test
    public void testDelete() {
        Long activityRecordId = activityRecordDTO.getId();
        activityRecordFacade.deleteActivityRecord(activityRecordId);
        ActivityRecord foundActivityRecord = activityRecordService.findById(activityRecordId);
        verify(activityRecordService).deleteActivityRecord(foundActivityRecord);
    }
}
