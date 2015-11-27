/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.service.facade;

import cz.muni.fi.pa165.dto.BurnedCaloriesCreateDTO;
import cz.muni.fi.pa165.dto.BurnedCaloriesDTO;
import cz.muni.fi.pa165.facade.BurnedCaloriesFacade;
import cz.muni.fi.pa165.saes.entity.BurnedCalories;
import cz.muni.fi.pa165.service.BurnedCaloriesService;
import cz.muni.fi.pa165.service.mapping.BeanMappingService;
import javax.inject.Inject;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Jan S.
 */
@Service
@Transactional
public class BurnedCaloriesFacadeImpl implements BurnedCaloriesFacade {

    @Inject
    private BurnedCaloriesService burnedCaloriesService;

    @Inject
    private BeanMappingService bms;

    @Override
    public void create(BurnedCaloriesCreateDTO burnedCalories) {
        burnedCaloriesService.create(bms.mapTo(burnedCalories, BurnedCalories.class));
    }

    @Override
    public BurnedCaloriesDTO findById(Long id) {
        return bms.mapTo(burnedCaloriesService.findById(id), BurnedCaloriesDTO.class);
    }

    @Override
    public void update(BurnedCaloriesDTO burnedCalories) {
        burnedCaloriesService.update(bms.mapTo(burnedCalories, BurnedCalories.class));
    }

    @Override
    public void delete(BurnedCaloriesDTO burnedCalories) {
        burnedCaloriesService.delete(bms.mapTo(burnedCalories, BurnedCalories.class));
    }
}
