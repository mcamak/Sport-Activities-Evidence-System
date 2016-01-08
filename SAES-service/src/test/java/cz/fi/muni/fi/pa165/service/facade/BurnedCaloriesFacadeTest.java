/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.fi.muni.fi.pa165.service.facade;

import cz.muni.fi.pa165.dto.BurnedCaloriesCreateDTO;
import cz.muni.fi.pa165.dto.BurnedCaloriesDTO;
import cz.muni.fi.pa165.dto.SportActivityDTO;
import cz.muni.fi.pa165.facade.BurnedCaloriesFacade;
import cz.muni.fi.pa165.saes.entity.BurnedCalories;
import cz.muni.fi.pa165.saes.entity.SportActivity;
import cz.muni.fi.pa165.service.BurnedCaloriesService;
import cz.muni.fi.pa165.service.mapping.BeanMappingService;
import cz.muni.fi.pa165.service.mapping.ServiceConfiguration;
import org.mockito.InjectMocks;
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
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

/**
 *
 * @author Jan S.
 */
@ContextConfiguration(classes = ServiceConfiguration.class)
public class BurnedCaloriesFacadeTest extends AbstractTestNGSpringContextTests {

    @Mock
    private BurnedCaloriesService burnedCaloriesService;

    @Mock
    private BeanMappingService mappingService;

    @Inject
    @InjectMocks
    private BurnedCaloriesFacade burnedCaloriesFacade;

    private BurnedCalories burnedCalories;
    private BurnedCaloriesDTO burnedCaloriesDTO;
    private BurnedCaloriesCreateDTO burnedCaloriesCreateDTO;

    @BeforeClass
    public void setup() throws Exception {
        MockitoAnnotations.initMocks(this);
        if (AopUtils.isAopProxy(burnedCaloriesFacade)
                && burnedCaloriesFacade instanceof Advised) {
            burnedCaloriesFacade = (BurnedCaloriesFacade) ((Advised) burnedCaloriesFacade)
                    .getTargetSource().getTarget();
        }
        ReflectionTestUtils.setField(burnedCaloriesFacade,
                "burnedCaloriesService",
                burnedCaloriesService);
    }

    @BeforeMethod
    public void setUpMethod() {
        MockitoAnnotations.initMocks(this);

        SportActivity sportActivity = new SportActivity();
        sportActivity.setName("Test");
        sportActivity.setId(99L);

        SportActivityDTO sportActivityDTO = new SportActivityDTO();
        sportActivityDTO.setName(sportActivity.getName());
        sportActivityDTO.setId(99L);

        burnedCalories = new BurnedCalories();
        burnedCalories.setId(890L);
        burnedCalories.setBodyWeight(100);
        burnedCalories.setCaloriesBurned(5000);
        burnedCalories.setActivity(sportActivity);

        burnedCaloriesDTO = new BurnedCaloriesDTO();
        burnedCaloriesDTO.setId(burnedCalories.getId());
        burnedCaloriesDTO.setBodyWeight(burnedCalories.getBodyWeight());
        burnedCaloriesDTO.setCaloriesBurned(burnedCalories.getCaloriesBurned());
        burnedCaloriesDTO.setActivity(sportActivityDTO);

        burnedCaloriesCreateDTO = new BurnedCaloriesCreateDTO();
        burnedCaloriesCreateDTO.setBodyWeight(burnedCalories.getBodyWeight());
        burnedCaloriesCreateDTO.setCaloriesBurned(burnedCalories.getCaloriesBurned());
        burnedCaloriesCreateDTO.setActivity(sportActivityDTO);
    }

    @AfterMethod
    public void cleanUp() {
        reset(burnedCaloriesService);
    }

    @Test
    public void testCreate() {
        when(mappingService.mapTo(burnedCaloriesCreateDTO, BurnedCalories.class)).thenReturn(burnedCalories);
        burnedCaloriesFacade.create(burnedCaloriesCreateDTO);
        verify(burnedCaloriesService).create(burnedCalories);
    }

    @Test
    public void testFindById() {
        when(burnedCaloriesService.findById(burnedCalories.getId())).thenReturn(burnedCalories);
        burnedCaloriesFacade.findById(burnedCalories.getId());
        verify(mappingService).mapTo(burnedCalories, BurnedCaloriesDTO.class);
    }

    @Test
    public void testFindBySportActivity() {
        List<BurnedCalories> calories = new ArrayList<>();
        calories.add(burnedCalories);
        when(burnedCaloriesService.findBySportActivity(55L)).thenReturn(calories);
        burnedCaloriesFacade.findBySportActivity(55L);
        verify(burnedCaloriesService).findBySportActivity(55L);
        verify(mappingService).mapTo(calories, BurnedCaloriesDTO.class);
    }

    @Test
    public void testUpdate() {
        when(mappingService.mapTo(burnedCaloriesDTO, BurnedCalories.class)).thenReturn(burnedCalories);
        burnedCaloriesFacade.update(burnedCaloriesDTO);
        verify(mappingService).mapTo(burnedCaloriesDTO, BurnedCalories.class);
        verify(burnedCaloriesService).update(burnedCalories);
    }

    @Test
    public void testDelete() {
        burnedCaloriesFacade.delete(burnedCaloriesDTO.getId());
        verify(burnedCaloriesService).delete(burnedCalories.getId());
    }

}
