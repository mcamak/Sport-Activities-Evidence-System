package cz.fi.muni.fi.pa165.service.facade;

import cz.muni.fi.pa165.dto.UserCreateDTO;
import cz.muni.fi.pa165.dto.UserDTO;
import cz.muni.fi.pa165.dto.UserLogInDTO;
import cz.muni.fi.pa165.facade.UserFacade;
import cz.muni.fi.pa165.saes.entity.User;
import cz.muni.fi.pa165.service.UserService;
import cz.muni.fi.pa165.service.facade.UserFacadeImpl;
import cz.muni.fi.pa165.service.mapping.BeanMappingService;
import cz.muni.fi.pa165.service.mapping.ServiceConfiguration;
import enums.Gender;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
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

import static org.mockito.Mockito.*;
import static org.testng.Assert.assertTrue;

/**
 *
 * @author Jan S.
 */
@ContextConfiguration(classes = ServiceConfiguration.class)
public class UserFacadeTest extends AbstractTestNGSpringContextTests {

    @Mock
    private UserService userService;

    @Mock
    private BeanMappingService beanMappingService;

    @Inject
    @InjectMocks
    private UserFacade userFacade;

    private User user;
    private UserDTO userDTO;
    private UserLogInDTO userLogInDTO;
    private UserCreateDTO userCreateDTO;

    @BeforeClass
    public void setup() throws Exception {
        MockitoAnnotations.initMocks(this);
        if (AopUtils.isAopProxy(userFacade)
                && userFacade instanceof Advised) {
            userFacade = (UserFacadeImpl) ((Advised) userFacade)
                    .getTargetSource().getTarget();
        }
        ReflectionTestUtils.setField(userFacade,
                "userService",
                userService);
    }

    @BeforeMethod
    public void setUpMethod() {
        MockitoAnnotations.initMocks(this);

        user = new User();
        user.setAge(100);
        user.setUsername("Pepa");
        user.setSex(Gender.MALE);
        user.setWeight(100);
        user.setAdmin(true);
        user.setId(12899L);
        user.setPasswordHash("kobliha");

        userDTO = new UserDTO();
        userDTO.setAge(user.getAge());
        userDTO.setUsername(user.getUsername());
        userDTO.setSex(user.getSex());
        userDTO.setWeight(user.getWeight());
        userDTO.setId(user.getId());
        
        userLogInDTO = new UserLogInDTO();
        userLogInDTO.setUsername(user.getUsername());
        userLogInDTO.setPassword("Rohozec");

        userCreateDTO = new UserCreateDTO();
        userCreateDTO.setPassword("pass");
        userCreateDTO.setAge(user.getAge());
        userCreateDTO.setUsername(user.getUsername());
        userCreateDTO.setSex(user.getSex());
        userCreateDTO.setWeight(user.getWeight());
    }

    @AfterMethod
    public void cleanUp() {
        reset(userService);
    }

    @Test
    public void createTest() {
        userFacade.create(userCreateDTO);
        verify(beanMappingService).mapTo(userCreateDTO, User.class);
        verify(userService).register(Matchers.any(User.class), Matchers.same("pass"));
    }

    @Test
    public void logInTest() {
        when(userService.findByUsername(user.getUsername())).thenReturn(user);
        userFacade.logIn(userLogInDTO);
        verify(userService).authenticate(user, userLogInDTO.getPassword());
    }

    @Test
    public void findByIdTest() {
        when(userService.findById(user.getId())).thenReturn(user);
        userFacade.findById(user.getId());
        verify(userService).findById(user.getId());
        verify(beanMappingService).mapTo(user, UserDTO.class);
    }

    @Test
    public void findByUsernameTest() {
        when(userService.findByUsername(user.getUsername())).thenReturn(user);
        userFacade.findByUsername(user.getUsername());
        verify(userService).findByUsername(user.getUsername());
        verify(beanMappingService).mapTo(user, UserDTO.class);
    }

    @Test
    public void isAdminTest() {
        Long userId = user.getId();
        when(userService.isAdmin(userId)).thenReturn(true);
        assertTrue(userFacade.isAdmin(userId));
        verify(userService).isAdmin(userId);
    }

    @Test
    public void updateTest() {
        userDTO.setAge(33);
        userFacade.update(userDTO);

        verify(beanMappingService).mapTo(userDTO, User.class);
        verify(userService).update(Matchers.any(User.class));
    }

    @Test
    public void deleteTest() {
        Long userId = user.getId();
        when(userService.findById(userId)).thenReturn(user);
        userFacade.delete(userId);
        verify(userService).delete(userId);
    }

    @Test
    public void findAllTest() {
        userFacade.findAll();
        verify(userService).findAll();
    }
}
