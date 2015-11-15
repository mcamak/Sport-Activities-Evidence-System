package cz.muni.fi.pa165.service;

import cz.muni.fi.pa165.saes.dao.SportActivityDao;
import cz.muni.fi.pa165.saes.entity.SportActivity;
import javax.inject.Inject;
import org.springframework.stereotype.Service;

/**
 *
 * @author MajoCAM
 */
@Service
public class SportActivityServiceImpl implements SportActivityService {

    @Inject
    private SportActivityDao sportActivityDao;

    @Override
    public void createSportActivity(SportActivity activity) {
        if (activity == null) {
            throw new IllegalArgumentException("Activity is null. ");
        }
        if (activity.getId() != null) {
            throw new IllegalArgumentException("Activity ID isn't null. It is already stored in database. ");
        }
        sportActivityDao.createSportActivity(activity);
    }

    @Override
    public void removeSportActivity(SportActivity activity) {
        if (activity == null) {
            throw new IllegalArgumentException("Activity is null. ");
        }
        if (activity.getId() == null) {
            throw new IllegalArgumentException("Activity ID is null - not persisted in database. ");
        }
        sportActivityDao.deleteSportActivity(activity);
    }

    @Override
    public SportActivity findSportActivity(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("ID is null. ");
        }
        return sportActivityDao.findSportActivity(id);
    }

    @Override
    public void updateSportActivity(SportActivity activity) {
        if (activity == null) {
            throw new IllegalArgumentException("Activity is null. ");
        }
        if (activity.getId() == null) {
            throw new IllegalArgumentException("Activity ID is null - not persisted in database. ");
        }
        sportActivityDao.updateSportActivity(activity);
    }
}
