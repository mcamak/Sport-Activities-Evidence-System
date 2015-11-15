package cz.muni.fi.pa165.service;

import cz.muni.fi.pa165.saes.entity.SportActivity;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 * JPA implementation of CRUD operations
 *
 * @author MajoCAM
 */
@Service
public interface SportActivityService {

    public void createSportActivity(SportActivity activity);

    public void deleteSportActivity(SportActivity activity);

    public SportActivity findById(Long id);
    
    public List<SportActivity> findAll();

    public void changeName(SportActivity activity, String newName);

}
