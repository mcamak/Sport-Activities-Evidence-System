package cz.muni.fi.pa165.saes.dao;

import cz.muni.fi.pa165.saes.entity.BurnedCalories;

import java.util.List;

/**
 * DAO for burned calories.
 *
 * @author Tomas Effenberger
 */
public interface BurnedCaloriesDao {

    /**
     * Store burnedCalories entity to the database
     *
     * @param burnedCalories - instance of BurnedCalories to store
     */
    void create(BurnedCalories burnedCalories);

    /**
     * Remove burnedCalories from database
     *
     * @param burnedCalories - instance of BurnedCalories to remove
     */
    void delete(BurnedCalories burnedCalories);

    /**
     * Remove burnedCalories belonging to a sport activity
     *
     * @param activityId - sport activity which burned calories will be removed
     */
    void deleteBySportActivity(Long activityId);

    /**
     * Retrieve burnedCalories with given id from database
     *
     * @param id - id of the burnedCalories to retrieve
     * @return burned calories with given id
     */
    BurnedCalories findById(Long id);

    /**
     * Retrieve burnedCalories by sport activity they belong to.
     *
     * @param activityId - id of the sport activity
     * @return burned calories of a sport activity
     */
    List<BurnedCalories> findBySportActivity(Long activityId);

    /**
     * Retrieve all BurnedCalories in the database.
     *
     * @return List of BurnedCalories
     */
    List<BurnedCalories> findAll();

    /**
     * Update burnedCalories in the database
     *
     * @param burnedCalories - instance of BurnedCalories to update
     */
    void update(BurnedCalories burnedCalories);

}
