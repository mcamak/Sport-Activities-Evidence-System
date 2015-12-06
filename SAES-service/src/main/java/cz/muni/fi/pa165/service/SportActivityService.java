package cz.muni.fi.pa165.service;

import cz.muni.fi.pa165.saes.entity.SportActivity;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 * @author MajoCAM
 */
@Service
public interface SportActivityService {

    public SportActivity create(SportActivity activity);

    public SportActivity findById(Long id);

    public void changeName(SportActivity activity, String newName);

    public void delete(SportActivity activity);

    public List<SportActivity> findAll();

}
