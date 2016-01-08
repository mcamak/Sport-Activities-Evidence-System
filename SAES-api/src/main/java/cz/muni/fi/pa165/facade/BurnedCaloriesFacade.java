package cz.muni.fi.pa165.facade;

import cz.muni.fi.pa165.dto.BurnedCaloriesCreateDTO;
import cz.muni.fi.pa165.dto.BurnedCaloriesDTO;

import java.util.List;

/**
 *
 * @author Jan S.
 */
public interface BurnedCaloriesFacade {

    /**
     * Creates burned calories.
     *
     * @param burnedCalories to be created
     * @return ID of created record.
     */
    Long create(BurnedCaloriesCreateDTO burnedCalories);

    /**
     * Find burned calories by id.
     *
     * @param id of the burned calory
     * @return burned calories with given id
     */
    BurnedCaloriesDTO findById(Long id);

    /**
     * Find burned calories by sport activity.
     *
     * @param activityId of sport activity
     * @return burned calories of a sport activity
     */
    List<BurnedCaloriesDTO> findBySportActivity(Long activityId);

    /**
     * Find all burned calories.
     *
     * @return all burned calories
     */
    List<BurnedCaloriesDTO> findAll();

    /**
     * Update burned calories.
     *
     * @param burnedCaloriesDTO containing data to be updated
     */
    void update(BurnedCaloriesDTO burnedCaloriesDTO);

    /**
     * Deletes burned calories.
     *
     * @param id of burned calory
     */
    void delete(Long id);
}
