/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.sportactivityevidencesystem.service;

import cz.muni.fi.pa165.sportactivityevidencesystem.dao.BurnedCaloriesDao;
import cz.muni.fi.pa165.sportactivityevidencesystem.entity.BurnedCalories;
import javax.inject.Inject;

/**
 *
 * @author Barborka
 */
public class BurnedCaloriesServiceImpl implements BurnedCaloriesService{
    
    @Inject
    private BurnedCaloriesDao burnedCaloriesDao;

    @Override
    public void createBurnedCalories(BurnedCalories burnedCalories) {
        if (burnedCalories == null){
            throw new IllegalArgumentException("Object of burned calories is null. Cannot be created.");
        }
        if(burnedCalories.getId() != null){
            throw new IllegalArgumentException("Burned calories with this ID already exists.");
        }
        
        burnedCaloriesDao.createBurnedCalories(burnedCalories);
        
       
    }

    @Override
    public void deleteBurnedCalories(BurnedCalories burnedCalories) {
        if (burnedCalories == null){
            throw new IllegalArgumentException("Object of burned calories is null. Cannot be removed.");
        }
        if (burnedCalories.getId() == null){
            throw new IllegalArgumentException("Id of burned calories is null. Cannot be removed.");
        }
        burnedCaloriesDao.deleteBurnedCalories(burnedCalories);
    }

    @Override
    public void updateBurnedCalories(BurnedCalories burnedCalories) {
        if (burnedCalories == null){
            throw new IllegalArgumentException("Object of burned calories is null. Cannot be updated.");
        }
        if (burnedCalories.getId() == null){
            throw new IllegalArgumentException("Id of burned calories is null. Cannot be updated.");
        }
        burnedCaloriesDao.updateBurnedCalories(burnedCalories);
        
    }

    @Override
    public BurnedCalories getBurnedCalories(Long id) {
        if (id == null){
            throw new IllegalArgumentException("ID is null");
        }
        return burnedCaloriesDao.getBurnedCalories(id);
        
    }
    
    
}
    
    

