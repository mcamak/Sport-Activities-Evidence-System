package cz.muni.fi.pa165.service;

import cz.muni.fi.pa165.exceptions.SaesServiceException;
import cz.muni.fi.pa165.saes.dao.BurnedCaloriesDao;
import cz.muni.fi.pa165.saes.dao.SportActivityDao;
import cz.muni.fi.pa165.saes.entity.BurnedCalories;
import cz.muni.fi.pa165.saes.entity.SportActivity;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.Collections;
import java.util.List;

/**
 * @author Barborka
 */
@Service
public class BurnedCaloriesServiceImpl implements BurnedCaloriesService {

    @Inject
    private BurnedCaloriesDao burnedCaloriesDao;

    @Inject
    private SportActivityDao sportActivityDao;

    @Override
    public void create(BurnedCalories calories) {
        if (calories == null) {
            throw new IllegalArgumentException("Burned calories is null. ");
        }
        burnedCaloriesDao.create(calories);
    }

    @Override
    public void delete(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("ID is null. ");
        }
        BurnedCalories calories = burnedCaloriesDao.findById(id);
        if (calories != null) {
            burnedCaloriesDao.delete(calories);
        }
    }

    @Override
    public void update(BurnedCalories calories) {
        if (calories == null) {
            throw new IllegalArgumentException("Burned calories is null. ");
        }
        burnedCaloriesDao.update(calories);
    }

    @Override
    public BurnedCalories findById(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("ID is null. ");
        }
        return burnedCaloriesDao.findById(id);
    }

    @Override
    public List<BurnedCalories> findBySportActivity(Long activityId) {
        if (activityId == null) {
            throw new IllegalArgumentException("Sport activity ID is null. ");
        }
        SportActivity activity = sportActivityDao.findSportActivity(activityId);
        if (activity == null) {
            throw new SaesServiceException("Sport activity with ID: " + activityId + " was not found. ");
        }
        List<BurnedCalories> calories = burnedCaloriesDao.findBySportActivity(activity);
        if (calories != null && calories.size() > 1) {
            Collections.sort(calories);
        }
        return calories;
    }

    @Override
    public List<BurnedCalories> findAll() {
        List<BurnedCalories> calories = burnedCaloriesDao.findAll();
        if (calories != null && calories.size() > 1) {
            Collections.sort(calories);
        }
        return calories;
    }
}
