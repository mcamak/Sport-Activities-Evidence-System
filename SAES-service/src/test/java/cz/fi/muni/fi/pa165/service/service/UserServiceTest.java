package cz.fi.muni.fi.pa165.service.service;

import cz.muni.fi.pa165.saes.dao.UserDao;
import cz.muni.fi.pa165.saes.entity.User;
import cz.muni.fi.pa165.service.UserServiceImpl;
import cz.muni.fi.pa165.service.mapping.ServiceConfiguration;
import enums.Gender;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.dao.DataAccessException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.testng.Assert.*;

/**
 *
 * @author Marian Camak
 */
@ContextConfiguration(classes = ServiceConfiguration.class)
public class UserServiceTest extends AbstractTestNGSpringContextTests {

    @Mock
    private UserDao userDao;

    @InjectMocks
    private UserServiceImpl userService;

    User user1;
    User user2;
    List<User> allUsers;

    @BeforeMethod
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        user1 = new User();
        user1.setAge(21);
        user1.setSex(Gender.MALE);
        user1.setUsername("František Ukažho");
        user1.setWeight(85);
        user1.setAdmin(true);

        user2 = new User();
        user2.setAge(99);
        user2.setSex(Gender.MALE);
        user2.setUsername("Petr Močůvka");
        user2.setWeight(72);
        user2.setAdmin(false);

        allUsers = new ArrayList<>();
        allUsers.add(user1);
        allUsers.add(user2);
    }

    /**
     * Tests creating
     */
    @Test
    public void createTest() {
        userService.register(user1, "pass");
        verify(userDao).createUser(user1);
    }

    /**
     * Tests creating with null user
     */
    @Test(expectedExceptions = IllegalArgumentException.class)
    public void createNullUserTest() {
        userService.register(null, "pass");
    }

    /**
     * Tests creating with null password
     */
    @Test(expectedExceptions = IllegalArgumentException.class)
    public void createNullPassTest() {
        userService.register(user1, null);
    }

    /**
     * Tests creating with empty password
     */
    @Test(expectedExceptions = IllegalArgumentException.class)
    public void createEmptyPassTest() {
        userService.register(user1, "");
    }

    /**
     * Tests finding by id
     */
    @Test
    public void findByIdTest() {
        when(userDao.findUser(0L)).thenReturn(this.user1);

        User result = userService.findById(0L);
        verify(userDao).findUser(0L);
        verifyNoMoreInteractions(userDao);

        assertEquals(result, user1);
    }

    /**
     * Tests finding by null id
     */
    @Test(expectedExceptions = IllegalArgumentException.class)
    public void findByIdNullTest() {
        Mockito.doThrow(DataAccessException.class).when(userDao).findUser(null);
        userService.findById(null);
    }

    /**
     * Tests updating
     */
    @Test
    public void updateTest() {
        user1.setId(1L);
        userService.update(user1);
        verify(userDao).updateUser(user1);
    }

    /**
     * Tests updating with null argument
     */
    @Test(expectedExceptions = IllegalArgumentException.class)
    public void updateNullTest() {
        Mockito.doThrow(DataAccessException.class).when(userDao).updateUser(null);
        userService.update(null);
    }

    /**
     * Tests deleting
     */
    @Test
    public void deleteTest() {
        user1.setId(1l);
        when(userDao.findUser(1l)).thenReturn(user1);
        userService.delete(user1.getId());
        verify(userDao).deleteUser(user1);
    }

    /**
     * Tests deleting with null argument
     */
    @Test(expectedExceptions = IllegalArgumentException.class)
    public void deleteNullTest() {
        Mockito.doThrow(DataAccessException.class).when(userDao).findUser(null);
        userService.delete(null);
    }

    /**
     * Tests finding all records
     */
    @Test
    public void findAllTest() {
        when(userDao.findAllUsers()).thenReturn(allUsers);

        List<User> result = userService.findAll();
        assertEquals(result.size(), 2);
        assertTrue(result.contains(user1));
        assertTrue(result.contains(user2));
    }

    /**
     * Tests authenticate
     */
    @Test
    public void authenticateTest() {
        Mockito.doNothing().when(userDao).createUser(user1);
        userService.register(user1, "password");
        user1.setId(1L);

        when(userDao.findUser(1L)).thenReturn(user1);
        assertTrue(userService.authenticate(user1, "password"));
    }

    /**
     * Tests authenticate with wrong pass
     */
    @Test
    public void authenticateWithWrongPassTest() {
        Mockito.doNothing().when(userDao).createUser(user1);
        userService.register(user1, "password");
        user1.setId(1L);

        when(userDao.findUser(1L)).thenReturn(user1);
        assertFalse(userService.authenticate(user1, "passWrong"));
    }

    /**
     * Tests authenticate with empty pass
     */
    @Test
    public void authenticateWithEmptyPassTest() {
        Mockito.doNothing().when(userDao).createUser(user1);
        userService.register(user1, "password");
        user1.setId(1L);

        when(userDao.findUser(1L)).thenReturn(user1);
        assertFalse(userService.authenticate(user1, "passWrong"));
    }

    /**
     * Tests isAdmin
     */
    @Test
    public void isAdminTest() {
        user1.setId(66L);
        user2.setId(67L);
        when(userDao.findUser(user1.getId())).thenReturn(user1);
        assertTrue(userService.isAdmin(user1.getId()));

        when(userDao.findUser(user2.getId())).thenReturn(user2);
        assertFalse(userService.isAdmin(user2.getId()));
    }
}
