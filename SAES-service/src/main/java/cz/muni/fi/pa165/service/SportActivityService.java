package cz.muni.fi.pa165.service;

import cz.muni.fi.pa165.saes.entity.SportActivity;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 * @author MajoCAM
 */
@Service
public interface SportActivityService {

    /**
     * Creates new sport activity
     *
     * @param activity
     * @return ID of newly created activity
     */
    public SportActivity create(SportActivity activity);

    /**
     * Finds sport activity with given ID
     *
     * @param id
     * @return found sport activity
     */
    public SportActivity findById(Long id);

    /**
     * Changes name of activity
     *
     * @param activity
     * @param newName
     */
    public void changeName(SportActivity activity, String newName);

    /**
     * Deletes sport activity
     *
     * @param activity
     */
    public void delete(SportActivity activity);

    /**
     * Finds all sport activities
     *
     * @return list of all found sport activities
     */
    public List<SportActivity> findAll();

}
