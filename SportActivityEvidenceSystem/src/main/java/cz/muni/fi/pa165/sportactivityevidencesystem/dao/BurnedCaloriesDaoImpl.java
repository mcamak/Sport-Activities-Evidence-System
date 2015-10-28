package cz.muni.fi.pa165.sportactivityevidencesystem.dao;

import cz.muni.fi.pa165.sportactivityevidencesystem.entity.BurnedCalories;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Implementation of BurnedCaloriesDao
 *
 * @author Tomas Effenberger
 */
public class BurnedCaloriesDaoImpl implements BurnedCaloriesDao {

	@PersistenceContext
	private EntityManager em;

	@Override
	public void create(BurnedCalories burnedCalories) {
		em.persist(burnedCalories);
	}

	@Override
	public void delete(BurnedCalories burnedCalories) {
		em.remove(burnedCalories);
	}

	@Override
	public BurnedCalories findById(Long id) {
		return em.find(BurnedCalories.class, id);
	}

	@Override
	public void update(BurnedCalories burnedCalories) {
		em.merge(burnedCalories);
	}

	@Override
	public List<BurnedCalories> findAll() {
		return em.createQuery("SELECT b FROM BurnedCalories b",
		    BurnedCalories.class)
		    .getResultList();
	}

}
