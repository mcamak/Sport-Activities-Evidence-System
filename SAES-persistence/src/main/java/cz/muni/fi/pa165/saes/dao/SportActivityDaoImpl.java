package cz.muni.fi.pa165.saes.dao;

import cz.muni.fi.pa165.saes.entity.SportActivity;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Jan S.
 */
@Repository
public class SportActivityDaoImpl implements SportActivityDao {
    
    @PersistenceContext
    private EntityManager em;

    @Override
    public void createSportActivity(SportActivity activity) {
        em.persist(activity);
    }

    @Override
    public void deleteSportActivity(SportActivity activity) {
        em.remove(activity);
    }

    @Override
    public void updateSportActivity(SportActivity activity) {
        em.merge(activity);
    }

    @Override
    public SportActivity findSportActivity(Long id) {
        return em.find(SportActivity.class, id);
    }
    
}
