package cz.muni.fi.pa165.facade;

import cz.muni.fi.pa165.dto.SportActivityCreateDTO;
import cz.muni.fi.pa165.dto.SportActivityDTO;
import cz.muni.fi.pa165.exceptions.EntityReferenceException;

import java.util.List;

/**
 *
 * @author MajoCAM
 */
public interface SportActivityFacade {

    /**
     * Creates new sport activity
     *
     * @param sportActivityCreateDTO - sport activity DTO without ID
     * @return ID of newly created sport activity
     */
    Long create(SportActivityCreateDTO sportActivityCreateDTO);

    /**
     * Finds sport activity with given ID
     *
     * @param id - if of sport activity to be found
     * @return found sport activity
     */
    SportActivityDTO findById(Long id);

    /**
     * Updates sport activity
     *
     * @param activity - sport activity DTO
     */
    void update(SportActivityDTO activity);

    /**
     * Deletes sport activity with given ID and all records that have this activity set
     *
     * @param activityId - ID of activity to be deleted
     */
    void delete(Long activityId) throws EntityReferenceException;

    /**
     * Finds all sport activities
     *
     * @return list of found activities
     */
    List<SportActivityDTO> findAll();
}
