package cz.muni.fi.pa165.facade;

import cz.muni.fi.pa165.dto.SportActivityCreateDTO;
import cz.muni.fi.pa165.dto.SportActivityDTO;
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
    public Long create(SportActivityCreateDTO sportActivityCreateDTO);

    /**
     * Finds sport activity with given ID
     *
     * @param id - if of sport activity to be found
     * @return found sport activity
     */
    public SportActivityDTO findById(Long id);

    /**
     * Updates sport activity
     *
     * @param old - sport activity DTO
     * @param newName - new name of sport activity
     */
    public void update(SportActivityDTO old, String newName);

    /**
     * Deletes sport activity with given ID
     *
     * @param activityId - ID of activity to be deleted
     */
    public void delete(Long activityId);

    /**
     * Finds all sport activities
     *
     * @return list of found activities
     */
    public List<SportActivityDTO> findAll();

}
