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
 * @author Barbora B.
 */
public interface BurnedCaloriesService {
    
    /**
     * create burned calories
     * @param burnedCalories 
     */
    public void create(BurnedCalories burnedCalories);
    
    /**
     * delete burned calories
     * @param burnedCalories 
     */
    public void delete(BurnedCalories burnedCalories);
    
    /**
     * update burned calories
     * @param burnedCalories 
     */
    public void update(BurnedCalories burnedCalories);
    
    /**
     * find burned calories by id
     * @param id
     * @return burned calories with given id
     */
    public BurnedCalories findById(Long id);
    
    
        
    
}
