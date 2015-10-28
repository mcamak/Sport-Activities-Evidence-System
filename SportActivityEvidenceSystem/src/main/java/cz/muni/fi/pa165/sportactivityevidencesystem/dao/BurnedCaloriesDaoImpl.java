package cz.muni.fi.pa165.sportactivityevidencesystem.dao;

import cz.muni.fi.pa165.sportactivityevidencesystem.entity.BurnedCalories;
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
	public void createBurnedCalories(BurnedCalories burnedCalories) {
		em.persist(burnedCalories);
	}

	@Override
	public void deleteBurnedCalories(BurnedCalories burnedCalories) {
		em.remove(burnedCalories);
	}

	@Override
	public BurnedCalories getBurnedCalories(Long id) {
		return em.find(BurnedCalories.class, id);
	}

	@Override
	public void updateBurnedCalories(BurnedCalories burnedCalories) {
		em.merge(burnedCalories);
	}

}
