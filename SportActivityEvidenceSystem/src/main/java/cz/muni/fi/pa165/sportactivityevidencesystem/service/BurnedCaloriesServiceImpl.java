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
    public void create(BurnedCalories burnedCalories) {
        if (burnedCalories == null){
            throw new IllegalArgumentException("Object of burned calories is null. Cannot be created.");
        }
        if(burnedCalories.getId() != null){
            throw new IllegalArgumentException("Burned calories with this ID already exists.");
        }
        
        burnedCaloriesDao.create(burnedCalories);
        
       
    }

    @Override
    public void delete(BurnedCalories burnedCalories) {
        if (burnedCalories == null){
            throw new IllegalArgumentException("Object of burned calories is null. Cannot be removed.");
        }
        if (burnedCalories.getId() == null){
            throw new IllegalArgumentException("Id of burned calories is null. Cannot be removed.");
        }
        burnedCaloriesDao.delete(burnedCalories);
    }

    @Override
    public void update(BurnedCalories burnedCalories) {
        if (burnedCalories == null){
            throw new IllegalArgumentException("Object of burned calories is null. Cannot be updated.");
        }
        if (burnedCalories.getId() == null){
            throw new IllegalArgumentException("Id of burned calories is null. Cannot be updated.");
        }
        burnedCaloriesDao.update(burnedCalories);
        
    }

    @Override
    public BurnedCalories findById(Long id) {
        if (id == null){
            throw new IllegalArgumentException("ID is null");
        }
        return burnedCaloriesDao.findById(id);
        
    }
    
    
}
    
    

