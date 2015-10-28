package cz.muni.fi.pa165.sportactivityevidencesystem.dao;

import cz.muni.fi.pa165.sportactivityevidencesystem.entity.BurnedCalories;

/**
 *  DAO for burned calories.
 * 
 * @author Tomas Effenberger
 */
public interface BurnedCaloriesDao {
	
	/**
	 * Store burnedCalories entity to the database
	 *
	 * @param burnedCalories - instance of BurnedCalories to store
	 */
	public void createBurnedCalories(BurnedCalories burnedCalories);

	/**
	 * Remove burnedCalories from database
	 *
	 * @param burnedCalories - instance of BurnedCalories to remove
	 */
	public void deleteBurnedCalories(BurnedCalories burnedCalories);

	/**
	 * Retrieve burnedCalories with given id from database
	 *
	 * @param id - id of the burnedCalories to retrieve
	 * @return burned calories with given id
	 */
	public BurnedCalories getBurnedCalories(Long id);

	/**
	 * Update burnedCalories in the database
	 *
	 * @param burnedCalories - instance of BurnedCalories to update
	 */
	public void updateBurnedCalories(BurnedCalories burnedCalories);
	
}
