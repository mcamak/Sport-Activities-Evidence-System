/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.fi.muni.fi.pa165.service.facade;

import cz.muni.fi.pa165.dto.UserCreateDTO;
import cz.muni.fi.pa165.dto.UserDTO;
import cz.muni.fi.pa165.dto.UserLogInDTO;
import cz.muni.fi.pa165.facade.UserFacade;
import cz.muni.fi.pa165.saes.UserFilter;
import cz.muni.fi.pa165.saes.entity.User;
import cz.muni.fi.pa165.service.UserService;
import cz.muni.fi.pa165.service.mapping.ServiceConfiguration;
import enums.Gender;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import org.mockito.Mock;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;
import org.springframework.aop.framework.Advised;
import org.springframework.aop.support.AopUtils;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.util.ReflectionTestUtils;
import static org.testng.Assert.assertEquals;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 *
 * @author Jan S.
 */
@ContextConfiguration(classes = ServiceConfiguration.class)
public class UserFacadeTest extends AbstractTestNGSpringContextTests {

    @Inject
    private UserFacade userFacade;

    @Mock
    private UserService userService;

    private User user;
    private UserDTO userDTO;
    private UserLogInDTO userLogInDTO;
    private UserCreateDTO userCreateDTO;

    @BeforeClass
    public void setup() throws Exception {
        MockitoAnnotations.initMocks(this);
        if (AopUtils.isAopProxy(userFacade)
                && userFacade instanceof Advised) {
            userFacade = (UserFacade) ((Advised) userFacade)
                    .getTargetSource().getTarget();
        }
        ReflectionTestUtils.setField(userFacade,
                "userService",
                userService);
    }

    @BeforeMethod
    public void setUpMethod() {

        user = new User();
        user.setAge(100);
        user.setName("Pepa");
        user.setSex(Gender.MALE);
        user.setWeight(100);
        user.setId(12899L);

        userDTO = new UserDTO();
        userDTO.setAge(user.getAge());
        userDTO.setName(user.getName());
        userDTO.setSex(user.getSex());
        userDTO.setWeight(user.getWeight());
        userDTO.setId(user.getId());
        
        userLogInDTO = new UserLogInDTO();
        userLogInDTO.setId(user.getId());
        userLogInDTO.setPassword("Rohozec");

        userCreateDTO = new UserCreateDTO();
        userCreateDTO.setAge(user.getAge());
        userCreateDTO.setName(user.getName());
        userCreateDTO.setSex(user.getSex());
        userCreateDTO.setWeight(user.getWeight());
    }

    @AfterMethod
    public void cleanUp() {
        reset(userService);
    }

    @Test
    public void signInTest() {
        userFacade.signIn(userCreateDTO, "password");
        verify(userService).signIn(user, "password");
    }

    @Test
    public void logInTest() {
        userFacade.logIn(userLogInDTO);
        verify(userService).authenticate(userLogInDTO.getId(), userLogInDTO.getPassword());
    }

    @Test
    public void changePasswordTest() {
        userFacade.changePassword(user.getId(), "pes", "kocka");
        verify(userService).changePassword(user.getId(), "pes", "kocka");
    }

    @Test
    public void findByIdTest() {
        Long userId = user.getId();
        userDTO.setId(userId);
        when(userService.findById(userId)).thenReturn(user);
        UserDTO foundBurnedCalories = userFacade.findById(userId);
        assertEquals(foundBurnedCalories, userDTO);
    }

    @Test
    public void updateTest() {
        user.setId(99L);
        userDTO.setId(user.getId());
        userFacade.update(userDTO);
        verify(userService).update(user);
    }

    @Test
    public void deleteTest() {
        Long userId = user.getId();
        when(userService.findById(userId)).thenReturn(user);
        userFacade.delete(userId);
        verify(userService).remove(user);
    }

    @Test
    public void findAllTest() {
        userFacade.findAll();
        verify(userService).findAll();
    }

    @Test
    public void findByParametersTest() {

        UserFilter userFilter = new UserFilter();
        userFilter.addGender(user.getSex());
        userFilter.setMinAge(user.getAge());
        userFilter.setMaxAge(user.getAge());
        userFilter.setMinWeight(user.getWeight());
        userFilter.setMaxWeight(user.getWeight());

        userFacade.findByParameters(user.getSex(),
                user.getAge(),
                user.getAge(),
                user.getWeight(),
                user.getWeight());

        verify(userService).getUsersByFilter(userFilter);
    }

}
