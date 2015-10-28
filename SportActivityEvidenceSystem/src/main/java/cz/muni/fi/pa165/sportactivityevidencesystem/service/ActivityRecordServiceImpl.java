package cz.muni.fi.pa165.sportactivityevidencesystem.service;

import cz.muni.fi.pa165.sportactivityevidencesystem.dao.ActivityRecordDao;
import cz.muni.fi.pa165.sportactivityevidencesystem.entity.ActivityRecord;
import java.util.List;
import javax.inject.Inject;
import org.springframework.stereotype.Service;


/**
 * Implementation of service for sport activity records.
 *
 * @author Tomas Effenberger
 */
@Service
public class ActivityRecordServiceImpl implements ActivityRecordService {
	
	@Inject
	private ActivityRecordDao activityRecordDao;

	@Override
	public void create(ActivityRecord activityRecord) {
		if(activityRecord == null) {
		    throw new IllegalArgumentException("Activity record is null. ");
		}
		if(activityRecord.getId() != null) {
		    throw new IllegalArgumentException("Activity record ID isn't null. It is already stored in database. ");
		}
		activityRecordDao.create(activityRecord);
	}

	@Override
	public void removeSportActivity(ActivityRecord activityRecord) {
		if(activityRecord == null) {
		    throw new IllegalArgumentException("Activity record is null. ");
		}
		activityRecordDao.delete(activityRecord);
	}

	@Override
	public ActivityRecord findById(Long id) {
		if(id == null) {
		    throw new IllegalArgumentException("Id is null. ");
		}
		return activityRecordDao.findActivityRecord(id);
	}

	@Override
	public void updateSportActivity(ActivityRecord activityRecord) {
		if(activityRecord == null) {
		    throw new IllegalArgumentException("Activity record is null. ");
		}
		activityRecordDao.update(activityRecord);
	}
	
}
