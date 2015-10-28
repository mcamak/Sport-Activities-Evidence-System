package cz.muni.fi.pa165.sportactivityevidencesystem.service;

import cz.muni.fi.pa165.sportactivityevidencesystem.entity.SportActivity;

/**
 * JPA implementation of CRUD operations
 * @author MajoCAM
 */
public interface SportActivityService {
    
    /**
     * Creates sport activity
     * @param activity - activity to be created
     */
    public void createSportActivity(SportActivity activity);

    /**
     * Removes sport activity
     * @param activity - activity to be removed
     */
    public void removeSportActivity(SportActivity activity);

    /**
     * Retrieves sport activity
     * @param id of the activity, which will be retrieved
     * @return retrieved activity
     */
    public SportActivity findSportActivity(Long id);

    /**
     * Updates sport activity
     * @param activity - activity to update
     */
    public void updateSportActivity(SportActivity activity);
    
}
