package cz.muni.fi.pa165.service;

import cz.muni.fi.pa165.saes.dao.BurnedCaloriesDao;
import cz.muni.fi.pa165.saes.dao.SportActivityDao;
import cz.muni.fi.pa165.saes.entity.BurnedCalories;
import cz.muni.fi.pa165.saes.entity.SportActivity;
import cz.muni.fi.pa165.service.exceptions.SaesServiceException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.util.List;

/**
 * @author Barborka
 */
@Service
@Transactional
public class BurnedCaloriesServiceImpl implements BurnedCaloriesService {

    @Inject
    private BurnedCaloriesDao burnedCaloriesDao;

    @Inject
    private SportActivityDao sportActivityDao;

    @Override
    public void create(BurnedCalories burnedCalories) {
        burnedCaloriesDao.create(burnedCalories);
    }

    @Override
    public void delete(Long id) {
        BurnedCalories burnedCalories = burnedCaloriesDao.findById(id);
        if (burnedCalories != null) {
            burnedCaloriesDao.delete(burnedCalories);
        }
    }

    @Override
    public void update(BurnedCalories burnedCalories) {
        burnedCaloriesDao.update(burnedCalories);
    }

    @Override
    public BurnedCalories findById(Long id) {
        return burnedCaloriesDao.findById(id);
    }

    @Override
    public List<BurnedCalories> findBySportActivity(Long activityId) {
        SportActivity activity = sportActivityDao.findSportActivity(activityId);
        if (activity == null) {
            throw new SaesServiceException("Sport activity with ID: " + activityId + " was not found. ");
        }
        return burnedCaloriesDao.findBySportActivity(activity);
    }

    @Override
    public List<BurnedCalories> findAll() {
        return burnedCaloriesDao.findAll();
    }

}
