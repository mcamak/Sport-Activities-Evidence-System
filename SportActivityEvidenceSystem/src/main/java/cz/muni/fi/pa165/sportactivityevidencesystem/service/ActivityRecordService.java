package cz.muni.fi.pa165.sportactivityevidencesystem.service;

import cz.muni.fi.pa165.sportactivityevidencesystem.entity.ActivityRecord;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 * Interface to service for sport activity records.
 *
 * @author Tomas Effenberger
 */
@Service
public interface ActivityRecordService {
	
	/**
	 * Create activity record
	 *
	 * @param activityRecord - activity record to create
	 */
	public void create(ActivityRecord activityRecord);

	/**
	 * Remove activity record
	 *
	 * @param activityRecord - activity record to remove
	 */
	public void removeSportActivity(ActivityRecord activityRecord);

	/**
	 * Retrieve activity record by ID
	 *
	 * @param id - ID of an activity record
	 * @return activity record with given ID
	 */
	public ActivityRecord findById(Long id);
	
	/**
	 * Retrieve all activity records
	 *
	 * @return list of all activity records
	 */
	public List<ActivityRecord> findAll();


	/**
	 * Update activity record
	 *
	 * @param activityRecord- activity record to update
	 */
	public void updateSportActivity(ActivityRecord activityRecord);

}
