package cz.muni.fi.pa165.facade;

import cz.muni.fi.pa165.dto.SportActivityDTO;
import java.util.List;

/**
 *
 * @author MajoCAM
 */
public interface SportActivityFacade {

    public Long create(SportActivityDTO createDTO);

    public SportActivityDTO findById(Long id);

    public void update(SportActivityDTO old, String newName);

    public void delete(Long activityId);

    public List<SportActivityDTO> findAll();

}
