package cz.fi.muni.fi.pa165.service.facade;

import cz.muni.fi.pa165.dto.ActivityRecordCreateDTO;
import cz.muni.fi.pa165.dto.ActivityRecordDTO;
import cz.muni.fi.pa165.dto.SportActivityDTO;
import cz.muni.fi.pa165.dto.UserDTO;
import cz.muni.fi.pa165.facade.ActivityRecordFacade;
import cz.muni.fi.pa165.saes.entity.ActivityRecord;
import cz.muni.fi.pa165.saes.entity.SportActivity;
import cz.muni.fi.pa165.saes.entity.User;
import cz.muni.fi.pa165.service.ActivityRecordService;
import cz.muni.fi.pa165.service.mapping.BeanMappingService;
import cz.muni.fi.pa165.service.mapping.ServiceConfiguration;
import enums.Gender;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.aop.framework.Advised;
import org.springframework.aop.support.AopUtils;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.util.ReflectionTestUtils;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

/**
 *
 * @author B. Bajtosova
 */
@ContextConfiguration(classes = ServiceConfiguration.class)
public class ActivityRecordFacadeTest extends AbstractTestNGSpringContextTests {

    @Mock
    private ActivityRecordService activityRecordService;

    @Mock
    private BeanMappingService mappingService;

    @Inject
    @InjectMocks
    private ActivityRecordFacade activityRecordFacade;

    private SportActivityDTO sportActivityDTO;
    private UserDTO userDTO;
    private ActivityRecord activityRecord;
    private ActivityRecordDTO activityRecordDTO;
    private ActivityRecordCreateDTO activityRecordCreateDTO;

    @BeforeClass
    public void setup() throws Exception {
        MockitoAnnotations.initMocks(this);
        if (AopUtils.isAopProxy(activityRecordFacade)
                && activityRecordFacade instanceof Advised) {
            activityRecordFacade = (ActivityRecordFacade) ((Advised) activityRecordFacade)
                    .getTargetSource().getTarget();
        }
        ReflectionTestUtils.setField(activityRecordFacade,
                "activityRecordService",
                activityRecordService);
    }

    @BeforeMethod
    public void init() {
        MockitoAnnotations.initMocks(this);

        SportActivity sportActivity = new SportActivity();
        sportActivity.setName("Running");

        sportActivityDTO = new SportActivityDTO();
        sportActivityDTO.setName(sportActivity.getName());

        User user = new User();
        user.setId(245L);
        user.setAdmin(false);
        user.setAge(25);
        user.setWeight(85);
        user.setSex(Gender.MALE);
        user.setUsername("Peter Konvicka");

        userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setUsername(user.getUsername());
        userDTO.setSex(user.getSex());
        userDTO.setWeight(user.getWeight());
        userDTO.setAge(user.getWeight());

        activityRecord = new ActivityRecord();
        activityRecord.setDistance(12000);
        activityRecord.setTime(888L);
        activityRecord.setActivity(sportActivity);
        activityRecord.setUser(user);

        activityRecordDTO = new ActivityRecordDTO();
        activityRecordDTO.setDistance(activityRecord.getDistance());
        activityRecordDTO.setTime(activityRecord.getTime());
        activityRecordDTO.setActivity(sportActivityDTO);
        activityRecordDTO.setId(activityRecord.getId());
        activityRecordDTO.setUser(userDTO);

        activityRecordCreateDTO = new ActivityRecordCreateDTO();
        activityRecordCreateDTO.setDistance(activityRecord.getDistance());
        activityRecordCreateDTO.setTime(activityRecord.getTime());
        activityRecordCreateDTO.setActivity(sportActivityDTO);
        activityRecordCreateDTO.setUser(userDTO);
    }

    @AfterMethod
    public void cleanUp() {
        reset(activityRecordService);
    }

    /**
     * Test creating activity record
     */
    @Test
    public void createTest() {
        when(mappingService.mapTo(activityRecordCreateDTO, ActivityRecord.class)).thenReturn(activityRecord);
        activityRecordFacade.create(activityRecordCreateDTO);
        verify(mappingService).mapTo(activityRecordCreateDTO, ActivityRecord.class);
        verify(activityRecordService).create(activityRecord);
    }

    /**
     * Test getting activity record by id
     */
    @Test
    public void findByIdTest() {
        when(activityRecordService.findById(activityRecordDTO.getId())).thenReturn(activityRecord);
        activityRecordFacade.findById(activityRecordDTO.getId());
        verify(activityRecordService).findById(activityRecordDTO.getId());
        verify(mappingService).mapTo(activityRecord, ActivityRecordDTO.class);
    }

    /**
     * Test getting activity records by user
     */
    @Test
    public void findByUser() {
        List<ActivityRecord> records = new ArrayList<>();
        records.add(activityRecord);
        when(activityRecordService.findByUser(userDTO.getId())).thenReturn(records);
        activityRecordFacade.findByUser(userDTO.getId());
        verify(activityRecordService).findByUser(userDTO.getId());
        verify(mappingService).mapTo(records, ActivityRecordDTO.class);
    }

    /**
     * Test updating record
     */
    @Test
    public void updateTest() {
        when(mappingService.mapTo(activityRecordDTO, ActivityRecord.class)).thenReturn(activityRecord);
        activityRecordFacade.update(activityRecordDTO);
        verify(mappingService).mapTo(activityRecordDTO, ActivityRecord.class);
        verify(activityRecordService).update(activityRecord);
    }

    /**
     * Test deleting record
     */
    @Test
    public void testDelete() {
        activityRecordFacade.delete(activityRecord.getId());
        verify(activityRecordService).delete(activityRecord.getId());
    }
}
