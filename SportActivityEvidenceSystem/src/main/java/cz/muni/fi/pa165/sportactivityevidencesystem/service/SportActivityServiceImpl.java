package cz.muni.fi.pa165.sportactivityevidencesystem.service;

import cz.muni.fi.pa165.sportactivityevidencesystem.dao.SportActivityDao;
import cz.muni.fi.pa165.sportactivityevidencesystem.entity.SportActivity;
import javax.inject.Inject;

/**
 *
 * @author MajoCAM
 */
public class SportActivityServiceImpl implements SportActivityService {
    
    @Inject
    private SportActivityDao sportActivityDao;

    @Override
    public void createSportActivity(SportActivity activity) {
        if(activity == null) {
            throw new IllegalArgumentException("Activity is null. ");
        }
        if(activity.getId() != null) {
            throw new IllegalArgumentException("Activity ID isn't null. It is already stored in database. ");
        }
        sportActivityDao.createSportActivity(activity);
    }

    @Override
    public void removeSportActivity(SportActivity activity) {
        if(activity == null) {
            throw new IllegalArgumentException("Activity is null. ");
        }
        if(activity.getId() == null) {
            throw new IllegalArgumentException("Activity ID is null - not persisted in database. ");
        }
        sportActivityDao.deleteSportActivity(activity);
    }

    @Override
    public SportActivity findSportActivity(Long id) {
        if(id == null) {
            throw new IllegalArgumentException("ID is null. ");
        }
        return sportActivityDao.findSportActivity(id);
    }

    @Override
    public void updateSportActivity(SportActivity activity) {
        if(activity == null) {
            throw new IllegalArgumentException("Activity is null. ");
        }
        if(activity.getId() == null) {
            throw new IllegalArgumentException("Activity ID is null - not persisted in database. ");
        }
        sportActivityDao.updateSportActivity(activity);
    }    
}
