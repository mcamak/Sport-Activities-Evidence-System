package cz.muni.fi.pa165.saes.dao;

import cz.muni.fi.pa165.saes.entity.BurnedCalories;
import cz.muni.fi.pa165.saes.entity.SportActivity;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Implementation of BurnedCaloriesDao
 *
 * @author Marian Camak
 */
@Transactional
@Repository
public class BurnedCaloriesDaoImpl implements BurnedCaloriesDao {

    @PersistenceContext
    private EntityManager em;

    @Override
    public void create(BurnedCalories burnedCalories) {
        checkBurnedCalories(burnedCalories);
        if (burnedCalories.getId() != null) {
            throw new IllegalArgumentException("BurnedCalories's ID is not null, object is already persisted in DB. ");
        }
        em.persist(burnedCalories);
    }

    @Override
    public void delete(BurnedCalories burnedCalories) {
        if (burnedCalories == null) {
            throw new IllegalArgumentException("BurnedCalories is null. ");
        }
        if (burnedCalories.getId() == null) {
            throw new IllegalArgumentException("BurnedCalories's ID is null, object isn't persisted in DB. ");
        }

        em.remove(em.contains(burnedCalories) ? burnedCalories : em.merge(burnedCalories));
    }

    @Override
    public void deleteBySportActivity(SportActivity activity) {
        if (activity == null) {
            throw new IllegalArgumentException("Activity is null. ");
        }
        em.createQuery("DELETE FROM BurnedCalories b WHERE b.activity = :activity")
                .setParameter("activity", activity)
                .executeUpdate();
    }

    @Override
    public BurnedCalories findById(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("ID is null. ");
        }
        return em.find(BurnedCalories.class, id);
    }

    @Override
    public List<BurnedCalories> findBySportActivity(SportActivity activity) {
        if (activity == null) {
            throw new IllegalArgumentException("Activity is null. ");
        }
        return em.createQuery("SELECT b FROM BurnedCalories b WHERE b.activity = :activity", BurnedCalories.class)
                .setParameter("activity", activity)
                .getResultList();
    }

    @Override
    public void update(BurnedCalories burnedCalories) {
        checkBurnedCalories(burnedCalories);
        if (burnedCalories.getId() == null) {
            throw new IllegalArgumentException("BurnedCalories's ID is null, first create object. ");
        }
        em.merge(burnedCalories);
    }

    @Override
    public List<BurnedCalories> findAll() {
        return em.createQuery("SELECT b FROM BurnedCalories b",
                BurnedCalories.class)
                .getResultList();
    }

    private void checkBurnedCalories(BurnedCalories calories) {
        if (calories == null) {
            throw new IllegalArgumentException("BurnedCalories is null. ");
        }
        if (calories.getActivity() == null) {
            throw new IllegalArgumentException("BurnedCalories's sport activity is null. ");
        }
        if (calories.getBodyWeight() == null) {
            throw new IllegalArgumentException("BurnedCalories's body weight is null. ");
        }
        if (calories.getBodyWeight() < 0) {
            throw new IllegalArgumentException("BurnedCalories's body weight is negative. ");
        }
        if (calories.getCaloriesBurned() == null) {
            throw new IllegalArgumentException("BurnedCalories's calories burned is null. ");
        }
        if (calories.getCaloriesBurned() < 0) {
            throw new IllegalArgumentException("BurnedCalories's calories burned is negative. ");
        }
    }
}
