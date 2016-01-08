package cz.muni.fi.pa165.service;

import cz.muni.fi.pa165.saes.entity.SportActivity;
import cz.muni.fi.pa165.service.exceptions.EntityReferenceException;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author MajoCAM
 */
@Service
public interface SportActivityService {

    /**
     * Creates new sport activity
     *
     * @param activity to be created
     * @return ID of newly created activity
     */
    SportActivity create(SportActivity activity);

    /**
     * Finds sport activity with given ID
     *
     * @param id of the activity
     * @return found sport activity
     */
    SportActivity findById(Long id);

    /**
     * Update activity
     *
     * @param activity to be updated
     */
    void update(SportActivity activity);

    /**
     * Delete sport activity. Throws {@link EntityReferenceException}
     * if there exist a record referencing this activity.
     *
     * @param id id of activity to be deleted
     * @throws EntityReferenceException when there exist a record with this sport activity
     */
    void delete(Long id) throws EntityReferenceException;

    /**
     * Finds all sport activities
     *
     * @return list of all found sport activities
     */
    List<SportActivity> findAll();

}
