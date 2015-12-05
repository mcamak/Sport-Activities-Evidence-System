/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.fi.muni.fi.pa165.service.facade;

import cz.muni.fi.pa165.dto.SportActivityDTO;
import cz.muni.fi.pa165.facade.SportActivityFacade;
import cz.muni.fi.pa165.saes.entity.SportActivity;
import cz.muni.fi.pa165.service.SportActivityService;
import cz.muni.fi.pa165.service.mapping.ServiceConfiguration;
import javax.inject.Inject;
import org.mockito.Mock;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;
import org.springframework.aop.framework.Advised;
import org.springframework.aop.support.AopUtils;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.util.ReflectionTestUtils;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 *
 * @author B. Bajtosova
 */
@ContextConfiguration(classes = ServiceConfiguration.class)
public class SportActivityFacadeTest extends AbstractTestNGSpringContextTests {

    @Inject
    private SportActivityFacade sportActivityFacade;

    @Mock
    private SportActivityService sportActivityService;

    private SportActivity sportActivity;
    private SportActivityDTO sportActivityDTO;
    //private SportActivityCreateDTO sportActivityCreateDTO;

    @BeforeClass
    public void setup() throws Exception {
        MockitoAnnotations.initMocks(this);
        if (AopUtils.isAopProxy(sportActivityFacade)
                && sportActivityFacade instanceof Advised) {
            sportActivityFacade = (SportActivityFacade) ((Advised) sportActivityFacade)
                    .getTargetSource().getTarget();
        }
        ReflectionTestUtils.setField(sportActivityFacade,
                "sportActivityService",
                sportActivityService);
    }

    @BeforeMethod
    public void setUpMethod() {

        sportActivity = new SportActivity();
        sportActivity.setName("Chess");

        sportActivityDTO = new SportActivityDTO();
        sportActivityDTO.setName(sportActivity.getName());
        sportActivityDTO.setId(999L);
    }
    
    @AfterMethod
    public void cleanUp()
    {
        reset(sportActivityService);
    }

    /**
     * Test creating sport activity
     */
    @Test
    public void createSportActivityTest() {
        sportActivityFacade.create(sportActivityDTO);
        verify(sportActivityService).createSportActivity(sportActivity);
    }

    /**
     * Test changing name of activity
     */
    @Test
    public void changeActivityNameTest() {
        sportActivityFacade.update(sportActivityDTO, "Jumping");
        verify(sportActivityService).changeName(sportActivity, "Jumping");
    }

    /**
     * Test deleting sport activity
     */
    @Test
    public void deleteSportActivityTest() {
        Long sportActivityId = sportActivityDTO.getId();
        sportActivityFacade.delete(sportActivityId);
        SportActivity foundSportActivity = sportActivityService.findById(sportActivityId);
        verify(sportActivityService).deleteSportActivity(foundSportActivity);
    }

    /**
     * Test getting all activities
     */
    @Test
    public void getAllActivitiesTest() {
        sportActivityFacade.findAll();
        verify(sportActivityService).findAll();
    }

    /**
     * Test getting activity with id
     */
    @Test
    public void getActivityWithIdTest() {
        Long sportActivityId = sportActivityDTO.getId();
        when(sportActivityService.findById(sportActivityId)).thenReturn(sportActivity);
        sportActivityFacade.findById(sportActivityId);
        verify(sportActivityService).findById(sportActivityId);
    }
}
