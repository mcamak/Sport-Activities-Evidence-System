package cz.muni.fi.pa165.service;

import cz.muni.fi.pa165.saes.dao.SportActivityDao;
import cz.muni.fi.pa165.saes.entity.SportActivity;
import cz.muni.fi.pa165.service.exceptions.SaesDataAccessException;
import cz.muni.fi.pa165.service.exceptions.SaesServiceException;
import java.util.List;
import javax.inject.Inject;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Implementation of the {@link SportActivityService}.
 *
 * @author MajoCAM
 */
@Service
@Transactional
public class SportActivityServiceImpl implements SportActivityService {

    @Inject
    private SportActivityDao sportActivityDao;

    @Override
    public SportActivity create(SportActivity activity) {
        try {
            sportActivityDao.createSportActivity(activity);
        } catch (Exception e) {
            throw new SaesDataAccessException("Failed when creating sport activity. ", e);
        }

        return activity;
    }

    @Override
    public void delete(SportActivity activity) {
        try {
            sportActivityDao.deleteSportActivity(activity);
        } catch (Exception e) {
            throw new SaesDataAccessException("Failed when deleting sport activity. ", e);
        }
    }

    @Override
    public SportActivity findById(Long id) {
        try {
            return sportActivityDao.findSportActivity(id);
        } catch (Exception e) {
            throw new SaesDataAccessException("Failed when finding sport activity. ", e);
        }
    }

    @Override
    public List<SportActivity> findAll() {
        try {
            return sportActivityDao.findAll();
        } catch (Exception e) {
            throw new SaesDataAccessException("Failed when finding all sport activities. ", e);
        }
    }

    @Override
    public void changeName(SportActivity activity, String newName) {
        if (activity.getName().equals(newName)) {
            throw new SaesServiceException("Activity is already named as '" + newName + "'. ");
        }
        activity.setName(newName);

        try {
            sportActivityDao.updateSportActivity(activity);
        } catch (Exception e) {
            throw new SaesDataAccessException("Failed when changing name of sport activity. ", e);
        }
    }
}
