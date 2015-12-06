package cz.muni.fi.pa165.saes.dao;

import cz.muni.fi.pa165.saes.entity.SportActivity;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Implementation of SportActivityDao
 *
 * @author Jan S.
 */
@Repository
@Transactional
public class SportActivityDaoImpl implements SportActivityDao {

    @PersistenceContext
    private EntityManager em;

    @Override
    public void createSportActivity(SportActivity activity) {
        em.persist(activity);
    }

    @Override
    public void deleteSportActivity(SportActivity activity) {
        em.remove(em.contains(activity) ? activity : em.merge(activity));
    }

    @Override
    public void updateSportActivity(SportActivity activity) {
        em.merge(activity);
    }

    @Override
    public SportActivity findSportActivity(Long id) {
        return em.find(SportActivity.class, id);
    }

    @Override
    public List<SportActivity> findAll() {
        return em.createQuery("SELECT a FROM SportActivity a", SportActivity.class).getResultList();
    }
}
