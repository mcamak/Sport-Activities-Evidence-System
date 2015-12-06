/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.service;

import cz.muni.fi.pa165.saes.entity.BurnedCalories;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 *
 * @author Barbora B.
 */
@Service
public interface BurnedCaloriesService {

    /**
     * create burned calories
     *
     * @param burnedCalories
     */
    public void create(BurnedCalories burnedCalories);

    /**
     * find burned calories by id
     *
     * @param id
     * @return burned calories with given id
     */
    public BurnedCalories findById(Long id);

    /**
     * update burned calories
     *
     * @param burnedCalories
     */
    public void update(BurnedCalories burnedCalories);

    /**
     * delete burned calories
     *
     * @param burnedCalories
     */
    public void delete(BurnedCalories burnedCalories);

    /**
     * finds all records for burned calories
     *
     * @return list of all records
     */
    public List<BurnedCalories> findAll();

}
