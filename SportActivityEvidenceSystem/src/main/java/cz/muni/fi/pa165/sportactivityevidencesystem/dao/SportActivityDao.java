package cz.muni.fi.pa165.sportactivityevidencesystem.dao;

import cz.muni.fi.pa165.sportactivityevidencesystem.entity.SportActivity;

/**
 * This class contains basic 
 * @author Jan S.
 */
public interface SportActivityDao {

    /**
     * Create sport activity in DB
     * @param activity to be created
     */
    public void createSportActivity(SportActivity activity);

    /**
     * Delete sport activity from DB
     * @param activity to be deleted
     */
    public void deleteSportActivity(SportActivity activity);

    /**
     * Update sport activity in DB
     * @param activity to be updated
     */
    public void updateSportActivity(SportActivity activity);

    /**
     * Find sport activity by id
     * @param id
     * @return found SportActivity
     */
    public SportActivity findSportActivity(Long id);
}
