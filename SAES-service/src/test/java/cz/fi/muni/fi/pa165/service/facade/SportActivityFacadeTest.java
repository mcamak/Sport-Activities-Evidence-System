/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.fi.muni.fi.pa165.service.facade;

import cz.muni.fi.pa165.dto.SportActivityCreateDTO;
import cz.muni.fi.pa165.dto.SportActivityDTO;
import cz.muni.fi.pa165.exceptions.EntityReferenceException;
import cz.muni.fi.pa165.facade.SportActivityFacade;
import cz.muni.fi.pa165.saes.entity.SportActivity;
import cz.muni.fi.pa165.service.SportActivityService;
import cz.muni.fi.pa165.service.mapping.BeanMappingService;
import cz.muni.fi.pa165.service.mapping.ServiceConfiguration;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
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

import javax.inject.Inject;

import static org.mockito.Mockito.*;

/**
 * @author B. Bajtosova
 */
@ContextConfiguration(classes = ServiceConfiguration.class)
public class SportActivityFacadeTest extends AbstractTestNGSpringContextTests {

    @Mock
    private SportActivityService sportActivityService;

    @Mock
    private BeanMappingService mappingService;

    @Inject
    @InjectMocks
    private SportActivityFacade sportActivityFacade;

    private SportActivity sportActivity;
    private SportActivityDTO sportActivityDTO;
    private SportActivityCreateDTO sportActivityCreateDTO;

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
        MockitoAnnotations.initMocks(this);

        sportActivity = new SportActivity();
        sportActivity.setId(777L);
        sportActivity.setName("Chess");

        sportActivityDTO = new SportActivityDTO();
        sportActivityDTO.setName(sportActivity.getName());
        sportActivityDTO.setId(sportActivity.getId());

        sportActivityCreateDTO = new SportActivityCreateDTO();
        sportActivityCreateDTO.setName(sportActivity.getName());
    }

    @AfterMethod
    public void cleanUp() {
        reset(sportActivityService);
    }

    /**
     * Test creating sport activity
     */
    @Test
    public void createSportActivityTest() {
        when(mappingService.mapTo(sportActivityCreateDTO, SportActivity.class)).thenReturn(sportActivity);
        sportActivityFacade.create(sportActivityCreateDTO);
        verify(mappingService).mapTo(sportActivityCreateDTO, SportActivity.class);
        verify(sportActivityService).create(Matchers.any(SportActivity.class));
    }

    /**
     * Test update activity
     */
    @Test
    public void updateActivityTest() {
        sportActivityFacade.update(sportActivityDTO);
        verify(mappingService).mapTo(sportActivityDTO, SportActivity.class);
        verify(sportActivityService).update(Matchers.any(SportActivity.class));
    }

    /**
     * Test deleting sport activity
     */
    @Test
    public void deleteSportActivityTest() throws EntityReferenceException {
        sportActivityFacade.delete(sportActivity.getId());
        verify(sportActivityService).delete(sportActivity.getId());
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
        when(sportActivityService.findById(sportActivity.getId())).thenReturn(sportActivity);
        sportActivityFacade.findById(sportActivity.getId());
        verify(sportActivityService).findById(sportActivity.getId());
        verify(mappingService).mapTo(sportActivity, SportActivityDTO.class);
    }
}
