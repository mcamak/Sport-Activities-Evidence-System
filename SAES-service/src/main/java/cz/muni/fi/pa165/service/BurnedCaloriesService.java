package cz.muni.fi.pa165.service;

import cz.muni.fi.pa165.saes.entity.BurnedCalories;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *
 * @author Barbora B.
 */
@Service
public interface BurnedCaloriesService {

    /**
     * create burned calories
     *
     * @param burnedCalories to be created
     */
    void create(BurnedCalories burnedCalories);

    /**
     * find burned calories by id
     *
     * @param id of the burned calory
     * @return burned calories with given id
     */
    BurnedCalories findById(Long id);

    /**
     * find burned calories by sport activity
     *
     * @param activityId id of sport activity
     * @return burned calories with given id
     */
    List<BurnedCalories> findBySportActivity(Long activityId);

    /**
     * update burned calories
     *
     * @param burnedCalories to be updated
     */
    void update(BurnedCalories burnedCalories);

    /**
     * delete burned calories
     *
     * @param id id of the burned calories
     */
    void delete(Long id);

    /**
     * finds all records for burned calories
     *
     * @return list of all records
     */
    List<BurnedCalories> findAll();
}
