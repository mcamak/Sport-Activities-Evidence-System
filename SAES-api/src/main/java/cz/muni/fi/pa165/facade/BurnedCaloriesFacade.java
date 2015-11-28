/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.facade;

import cz.muni.fi.pa165.dto.BurnedCaloriesDTO;
import cz.muni.fi.pa165.dto.BurnedCaloriesCreateDTO;

/**
 *
 * @author Jan S.
 */
public interface BurnedCaloriesFacade {

    /**
     * Creates burned calories.
     *
     * @param burnedCalories
     * @return ID of created record.
     */
    public Long create(BurnedCaloriesCreateDTO burnedCalories);

    /**
     * Find burned calories by id.
     *
     * @param id
     * @return burned calories with given id
     */
    public BurnedCaloriesDTO findById(Long id);

    /**
     * Update burned calories.
     *
     * @param burnedCaloriesDTO
     */
    public void update(BurnedCaloriesDTO burnedCaloriesDTO);

    /**
     * Deletes burned calories.
     *
     * @param burnedCaloriesDTO
     */
    public void delete(BurnedCaloriesDTO burnedCaloriesDTO);

}
