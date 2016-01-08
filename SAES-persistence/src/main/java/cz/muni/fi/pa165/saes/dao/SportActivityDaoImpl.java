package cz.muni.fi.pa165.saes.dao;

import cz.muni.fi.pa165.saes.entity.SportActivity;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Implementation of SportActivityDao
 *
 * @author Jan S.
 */
@Transactional
@Repository
public class SportActivityDaoImpl implements SportActivityDao {

    @PersistenceContext
    private EntityManager em;

    @Override
    public void createSportActivity(SportActivity activity) {
        checkSportActivity(activity);
        if (activity.getId() != null) {
            throw new IllegalArgumentException("Sport activity's ID is not null. Entity is already persisted. ");
        }
        em.persist(activity);
    }

    @Override
    public void deleteSportActivity(SportActivity activity) {
        if (activity == null) {
            throw new IllegalArgumentException("Sport activity is null. ");
        }
        em.remove(activity);
    }

    @Override
    public void updateSportActivity(SportActivity activity) {
        checkSportActivity(activity);
        if (activity.getId() == null) {
            throw new IllegalArgumentException("Sport activity's ID is null. Create activity first. ");
        }
        em.merge(activity);
    }

    @Override
    public SportActivity findSportActivity(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("ID is null. ");
        }
        return em.find(SportActivity.class, id);
    }

    @Override
    public List<SportActivity> findAll() {
        return em.createQuery("SELECT a FROM SportActivity a", SportActivity.class).getResultList();
    }

    private void checkSportActivity(SportActivity activity) {
        if (activity == null) {
            throw new IllegalArgumentException("Sport activity is null. ");
        }
        if (activity.getName() == null || activity.getName().isEmpty()) {
            throw new IllegalArgumentException("Sport activity's name is null or empty. ");
        }
    }
}
