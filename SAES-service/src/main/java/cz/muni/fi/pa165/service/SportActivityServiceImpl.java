package cz.muni.fi.pa165.service;

import cz.muni.fi.pa165.saes.dao.SportActivityDao;
import cz.muni.fi.pa165.saes.entity.SportActivity;
import exceptions.SaesServiceException;
import java.util.List;
import javax.inject.Inject;
import org.springframework.stereotype.Service;

/**
 * Implementation of the {@link SportActivityService}.
 * 
 * @author MajoCAM
 */
@Service
public class SportActivityServiceImpl implements SportActivityService {

    @Inject
    private SportActivityDao sportActivityDao;

    @Override
    public void createSportActivity(SportActivity activity) {
        sportActivityDao.createSportActivity(activity);
    }

    @Override
    public void deleteSportActivity(SportActivity activity) {
        sportActivityDao.deleteSportActivity(activity);
    }

    @Override
    public SportActivity findById(Long id) {
        return sportActivityDao.findSportActivity(id);
    }
    
    @Override
    public List<SportActivity> findAll() {
        return sportActivityDao.findAll();
    }

    @Override
    public void changeName(SportActivity activity, String newName) {
        if(activity.getName().equals(newName)) {
            throw new SaesServiceException("Activity is already named as '" + newName + "'. ");
        }
        sportActivityDao.updateSportActivity(activity);
    }
}
