package cz.muni.fi.pa165.sportactivityevidencesystem.dao;

import cz.muni.fi.pa165.sportactivityevidencesystem.entity.SportActivity;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Jan S.
 */
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
