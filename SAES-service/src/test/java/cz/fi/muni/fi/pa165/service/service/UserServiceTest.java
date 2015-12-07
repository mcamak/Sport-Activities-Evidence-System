package cz.fi.muni.fi.pa165.service.service;

import cz.muni.fi.pa165.saes.UserFilter;
import cz.muni.fi.pa165.saes.dao.UserDao;
import cz.muni.fi.pa165.saes.entity.User;
import cz.muni.fi.pa165.service.UserServiceImpl;
import cz.muni.fi.pa165.service.mapping.ServiceConfiguration;
import enums.Gender;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

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
        user1.setName("František Ukažho");
        user1.setWeight(85);
        user1.setPasswordHash("divnejHash");

        user2 = new User();
        user2.setAge(99);
        user2.setSex(Gender.MALE);
        user2.setName("Petr Močůvka");
        user2.setWeight(72);
        user2.setPasswordHash("ještěDivnějšíHash");

        allUsers = new ArrayList<>();
        allUsers.add(user1);
        allUsers.add(user2);
    }

    /**
     * Tests creating
     */
    @Test
    public void createTest() {
        userService.create(user1);
        verify(userDao).createUser(user1);
    }

    /**
     * Tests creating with null argument
     */
    @Test(expectedExceptions = IllegalArgumentException.class)
    public void createNullTest() {
        Mockito.doThrow(new IllegalArgumentException()).when(userDao).createUser(null);
        userService.create(null);
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
        when(userDao.findUser(null)).thenThrow(IllegalArgumentException.class);
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
        Mockito.doThrow(new IllegalArgumentException()).when(userDao).updateUser(null);
        userService.update(null);
    }

    /**
     * Tests deleting
     */
    @Test
    public void deleteTest() {
        user1.setId(1l);
        userService.delete(user1);
        verify(userDao).deleteUser(user1);
    }

    /**
     * Tests deleting with null argument
     */
    @Test(expectedExceptions = IllegalArgumentException.class)
    public void deleteNullTest() {
        Mockito.doThrow(new IllegalArgumentException()).when(userDao).deleteUser(null);
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
     * Tests finding by parameters
     */
    @Test
    public void findByParametersTest() {

        UserFilter userFilter = new UserFilter();
        userFilter.setMinAge(user1.getAge() - 1);
        userFilter.setMaxAge(user2.getAge() + 1);

        when(userDao.findUsersByParameters(any(UserFilter.class))).thenReturn(allUsers);
        assertTrue(userService.findByParameters(userFilter).size() == 2);
    }

    // TODO
    /**
     * Tests authenticate
     */
//    @Test
//    public void authenticateTest() {
//        user1.setId(userService.create(user1));
//        assertTrue(userService.authenticate(user1.getId(), user1.getPasswordHash()));
//    }
}
