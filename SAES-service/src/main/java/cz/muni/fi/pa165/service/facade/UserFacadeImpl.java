package cz.muni.fi.pa165.service.facade;

import cz.muni.fi.pa165.dto.UserCreateDTO;
import cz.muni.fi.pa165.dto.UserDTO;
import cz.muni.fi.pa165.facade.UserFacade;
import cz.muni.fi.pa165.saes.UserFilter;
import cz.muni.fi.pa165.saes.entity.ActivityRecord;
import cz.muni.fi.pa165.saes.entity.User;
import enums.Gender;
import cz.muni.fi.pa165.service.ActivityRecordService;
import cz.muni.fi.pa165.service.SportActivityService;
import cz.muni.fi.pa165.service.UserService;
import cz.muni.fi.pa165.service.mapping.BeanMappingService;
import java.util.List;
import javax.inject.Inject;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author MajoCAM
 */
@Service
@Transactional
public class UserFacadeImpl implements UserFacade {
    
    @Inject
    private UserService userService;
    
    @Inject
    private ActivityRecordService recordService;
    
    @Inject
    private SportActivityService activityService;

    @Inject
    private BeanMappingService bms;

    @Override
    public Long create(UserCreateDTO u) {
        return userService.createUser(bms.mapTo(u, User.class));
    }

    @Override
    public void update(UserDTO u) {
        userService.updateUser(bms.mapTo(u, User.class));
    }

    @Override
    public void delete(Long id) {
        userService.removeUser(userService.findUser(id));
    }

    @Override
    public void saveActivityRecord(Long userId, Long sportActivityId, Long seconds, Integer distance) {
        ActivityRecord record = new ActivityRecord();
        record.addUser(userService.findUser(userId));
        record.setActivity(activityService.findById(sportActivityId));
        record.setDistance(distance);
        record.setTimeSeconds(seconds);
        
        recordService.create(record);
    }

    @Override
    public void removeActivityRecord(Long userId, Long recordId) {
        recordService.removeUserFromRecord(userId, recordId);
    }

    @Override
    public List<UserDTO> findAll() {
        return bms.mapTo(userService.findAll(), UserDTO.class);
    }

    @Override
    public UserDTO findById(Long id) {
        return bms.mapTo(userService.findUser(id), UserDTO.class);
    }

    @Override
    public List<UserDTO> findByParameters(enums.Gender sex, Integer minAge, Integer maxAge, Integer minWeight, Integer maxWeight) {
        UserFilter filter = new UserFilter();
        if (sex != null) {
            if(sex.name().equals(Gender.MALE.name())) {
                filter.addGender(Gender.MALE);
            }
            if(sex.name().equals(Gender.FEMALE.name())) {
                filter.addGender(Gender.FEMALE);
            }
            
        }
        if (minAge != null) {
            filter.setMinAge(minAge);
        }
        if (maxAge != null) {
            filter.setMaxAge(maxAge);
        }
        if (minWeight != null) {
            filter.setMinWeight(minWeight);
        }
        if (maxWeight != null) {
            filter.setMaxWeight(maxWeight);
        }
        
        return bms.mapTo(userService.getUsersByFilter(filter), UserDTO.class);
    }
}
