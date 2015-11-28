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
import cz.muni.fi.pa165.service.mapping.ServiceConfiguration;
import javax.inject.Inject;
import org.mockito.Mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;
import org.springframework.aop.framework.Advised;
import org.springframework.aop.support.AopUtils;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.util.ReflectionTestUtils;
import static org.testng.Assert.assertEquals;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 *
 * @author Jan S.
 */
@ContextConfiguration(classes = ServiceConfiguration.class)
public class BurnedCaloriesFacadeTest extends AbstractTestNGSpringContextTests {

    @Inject
    private BurnedCaloriesFacade burnedCaloriesFacade;

    @Mock
    private BurnedCaloriesService burnedCaloriesService;

    private BurnedCalories burnedCalories;
    private BurnedCaloriesDTO burnedCaloriesDTO;
    private BurnedCaloriesCreateDTO burnedCaloriesCreateDTO;

    @BeforeClass
    public void setup() throws Exception {
        MockitoAnnotations.initMocks(this);
        if (AopUtils.isAopProxy(burnedCaloriesFacade) && burnedCaloriesFacade instanceof Advised) {
            burnedCaloriesFacade = (BurnedCaloriesFacade) ((Advised) burnedCaloriesFacade).getTargetSource().getTarget();
        }
        ReflectionTestUtils.setField(burnedCaloriesFacade, "burnedCaloriesService", burnedCaloriesService);
    }

    @BeforeMethod
    public void setUpMethod() {

        SportActivity sportActivity = new SportActivity();
        sportActivity.setName("Test");

        SportActivityDTO sportActivityDTO = new SportActivityDTO();
        sportActivityDTO.setName(sportActivity.getName());
        sportActivityDTO.setId(99L);

        burnedCalories = new BurnedCalories();
        burnedCalories.setBodyWeight(100);
        burnedCalories.setCaloriesBurned(5000);
        burnedCalories.setActivity(sportActivity);

        burnedCaloriesDTO = new BurnedCaloriesDTO();
        burnedCaloriesDTO.setBodyWeight(burnedCalories.getBodyWeight());
        burnedCaloriesDTO.setCaloriesBurned(burnedCalories.getCaloriesBurned());
        burnedCaloriesDTO.setActivity(sportActivityDTO);

        burnedCaloriesCreateDTO = new BurnedCaloriesCreateDTO();
        burnedCaloriesCreateDTO.setBodyWeight(burnedCalories.getBodyWeight());
        burnedCaloriesCreateDTO.setCaloriesBurned(burnedCalories.getCaloriesBurned());
        burnedCaloriesCreateDTO.setActivity(sportActivityDTO);
    }

    @Test
    public void testCreate() {
        burnedCaloriesFacade.create(burnedCaloriesCreateDTO);
        verify(burnedCaloriesService).create(burnedCalories);
    }

    @Test
    public void testFindById() {
        Long id = burnedCalories.getId();
        burnedCaloriesDTO.setId(id);
        when(burnedCaloriesService.findById(id)).thenReturn(burnedCalories);
        BurnedCaloriesDTO findedBurnedCalories = burnedCaloriesFacade.findById(id);
        assertEquals(findedBurnedCalories, burnedCaloriesDTO);
    }

    @Test
    public void testUpdate() {
        // TODO
    }

    @Test
    public void testDelete() {
        // TODO
    }

}
