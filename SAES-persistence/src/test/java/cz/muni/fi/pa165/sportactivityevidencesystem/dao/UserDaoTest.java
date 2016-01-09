package cz.muni.fi.pa165.sportactivityevidencesystem.dao;

import cz.muni.fi.pa165.saes.SportActivitySystemApplicationContext;
import cz.muni.fi.pa165.saes.dao.UserDao;
import cz.muni.fi.pa165.saes.entity.User;
import enums.Gender;
import org.springframework.dao.DataAccessException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.testng.Assert;
import org.testng.annotations.Test;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

import static org.testng.Assert.*;

/**
 * Tests for UserDaoImpl
 *
 * @author Tomas Effenberger
 */
@ContextConfiguration(classes = SportActivitySystemApplicationContext.class)
@TestExecutionListeners(TransactionalTestExecutionListener.class)
@Transactional
public class UserDaoTest extends AbstractTestNGSpringContextTests {

    @PersistenceContext
    public EntityManager em;

    @Inject
    private UserDao userDao;

    /**
     * Test creating new user.
     */
    @Test
    public void testCreateUser() {
        User user = createNewRandomUser();
        userDao.createUser(user);
        Assert.assertNotNull(user.getId());
    }

    /**
     * Test creating user with null argument.
     */
    @Test(expectedExceptions = DataAccessException.class)
    public void testCreateUserWithNullArgument() {
        userDao.createUser(null);
    }
    
    /**
     * Test creating user with null name.
     */
    @Test(expectedExceptions = DataAccessException.class)
    public void testCreateUserWithNullName() {
        User u = createNewRandomUser();
        u.setUsername(null);
        userDao.createUser(u);
    }
    
    /**
     * Test creating user with empty name.
     */
    @Test(expectedExceptions = DataAccessException.class)
    public void testCreateUserWithEmptyName() {
        User u = createNewRandomUser();
        u.setUsername("");
        userDao.createUser(u);
    }

    /**
     * Test creating user with null password hash.
     */
    @Test(expectedExceptions = DataAccessException.class)
    public void testCreateUserWithNullPasswordHash() {
        User u = createNewRandomUser();
        u.setPasswordHash(null);
        userDao.createUser(u);
    }
    
    /**
     * Test creating user with negative age.
     */
    @Test(expectedExceptions = DataAccessException.class)
    public void testCreateUserWithNegativeAge() {
        User u = createNewRandomUser();
        u.setAge(-5);
        userDao.createUser(u);
    }
    
    /**
     * Test creating user with null sex.
     */
    @Test(expectedExceptions = DataAccessException.class)
    public void testCreateUserWithNullSex() {
        User u = createNewRandomUser();
        u.setSex(null);
        userDao.createUser(u);
    }
    
    /**
     * Test creating user with negative age.
     */
    @Test(expectedExceptions = DataAccessException.class)
    public void testCreateUserWithNegativeWeight() {
        User u = createNewRandomUser();
        u.setWeight(-20);
        userDao.createUser(u);
    }

    /**
     * Test finding user by ID.
     */
    @Test
    public void testGetUser() {
        User user = new User();
        user.setUsername("Adam");
        user.setPasswordHash("PasswordHash");
        user.setAge(20);
        user.setSex(Gender.MALE);
        user.setWeight(70);
        userDao.createUser(user);
        assertNotNull(user.getId());
        
        User found = userDao.findUser(user.getId());
        Assert.assertEquals("Adam", found.getUsername());
        Assert.assertEquals("PasswordHash", found.getPasswordHash());
        Assert.assertEquals(new Integer(20), found.getAge());
        Assert.assertEquals(Gender.MALE, found.getSex());
        Assert.assertEquals(new Integer(70), found.getWeight());
    }
    
    /**
     * Test get user with null ID.
     */
    @Test(expectedExceptions = DataAccessException.class)
    public void testGetUserWithNullId() {
        userDao.findUser(null);
    }

    /**
     * Test finding user by name.
     */
    @Test
    public void testFindUserByName() {
        User user = new User();
        user.setUsername("Adam");
        user.setPasswordHash("PasswordHash");
        user.setAge(20);
        user.setSex(Gender.MALE);
        user.setWeight(70);
        userDao.createUser(user);
        assertNotNull(user.getId());

        User found = userDao.findUserByName(user.getUsername());
        Assert.assertEquals("Adam", found.getUsername());
        Assert.assertEquals("PasswordHash", found.getPasswordHash());
        Assert.assertEquals(new Integer(20), found.getAge());
        Assert.assertEquals(Gender.MALE, found.getSex());
        Assert.assertEquals(new Integer(70), found.getWeight());
    }

    /**
     * Test get user with null ID.
     */
    @Test(expectedExceptions = DataAccessException.class)
    public void testFindUserByNullName() {
        userDao.findUserByName(null);
    }

    /**
     * Test find all users.
     */
    @Test
    public void testFindAllUsers() {
        User user1 = createNewRandomUser();
        User user2 = createNewRandomUser();
        userDao.createUser(user1);
        List<User> all = userDao.findAllUsers();
        assertEquals(all.size(), 1);
        assertTrue(all.contains(user1));
        userDao.createUser(user2);
        all = userDao.findAllUsers();
        assertEquals(all.size(), 2);
        assertTrue(all.contains(user1));
        assertTrue(all.contains(user2));
    }
    
    /**
     * Test find all users when no users persisted.
     */
    @Test
    public void testFindAllUsersEmpty() {
        List<User> all = userDao.findAllUsers();
        assertTrue(all != null);
        assertTrue(all.isEmpty());
    }

    /**
     * Test deleting user.
     */
    @Test
    public void testDeleteUser() {
        User user = createNewRandomUser();
        userDao.createUser(user);
        Assert.assertNotNull(userDao.findUser(user.getId()));
        userDao.deleteUser(user);
        Assert.assertNull(userDao.findUser(user.getId()));
    }
    
    /**
     * Test delete user with null parameter.
     */
    @Test(expectedExceptions = DataAccessException.class)
    public void testDeleteUserWithNull() {
        userDao.deleteUser(null);
    }
    
    /**
     * Test delete user with null ID.
     */
    @Test(expectedExceptions = DataAccessException.class)
    public void testDeleteUserWithNullId() {
        User u = createNewRandomUser();
        userDao.deleteUser(u);
    }

    /**
     * Test deleting user without ID.
     */
    @Test(expectedExceptions = DataAccessException.class)
    public void testDeleteWithoutId() {
        User user = new User();
        userDao.deleteUser(user);
    }

    /**
     * Test updating user.
     */
    @Test
    public void testUpdateUser() {
        User user = createNewRandomUser();
        userDao.createUser(user);
        Assert.assertNotNull(userDao.findUser(user.getId()));
        user.setUsername("John");
        userDao.updateUser(user);
        Assert.assertEquals("John", em.find(User.class, user.getId()).getUsername());
    }
    
    /**
     * Test updating user with null ID.
     */
    @Test(expectedExceptions = DataAccessException.class)
    public void testUpdateUserWithoutId() {
        User user = createNewRandomUser();
        userDao.updateUser(user);
    }
    
    /**
     * Test updating user with null name.
     */
    @Test(expectedExceptions = DataAccessException.class)
    public void testUpdateUserWithNullName() {
        User user = createNewRandomUser();
        userDao.createUser(user);
        assertNotNull(user.getId());
        user.setUsername(null);
        userDao.updateUser(user);
    }

    /**
     * Test updating user with null password hash.
     */
    @Test(expectedExceptions = DataAccessException.class)
    public void testUpdateUserWithNullPasswordHash() {
        User user = createNewRandomUser();
        userDao.createUser(user);
        assertNotNull(user.getId());
        user.setPasswordHash(null);
        userDao.updateUser(user);
    }
    
    /**
     * Test updating user with empty name.
     */
    @Test(expectedExceptions = DataAccessException.class)
    public void testUpdateUserWithEmptyName() {
        User user = createNewRandomUser();
        userDao.createUser(user);
        assertNotNull(user.getId());
        user.setUsername("");
        userDao.updateUser(user);
    }
    
    /**
     * Test updating user with null sex.
     */
    @Test(expectedExceptions = DataAccessException.class)
    public void testUpdateUserWithNullSex() {
        User user = createNewRandomUser();
        userDao.createUser(user);
        assertNotNull(user.getId());
        user.setSex(null);
        userDao.updateUser(user);
    }
    
    /**
     * Test updating user with negative age.
     */
    @Test(expectedExceptions = DataAccessException.class)
    public void testUpdateUserWithNegativeAge() {
        User user = createNewRandomUser();
        userDao.createUser(user);
        assertNotNull(user.getId());
        user.setAge(-35);
        userDao.updateUser(user);
    }
    
    /**
     * Test updating user with negative weight.
     */
    @Test(expectedExceptions = DataAccessException.class)
    public void testUpdateUserWithNegativeWeight() {
        User user = createNewRandomUser();
        userDao.createUser(user);
        assertNotNull(user.getId());
        user.setWeight(-78);
        userDao.updateUser(user);
    }
    
    private User createNewRandomUser() {
        String[] names = new String[]{"admin", "htsk932", "impotent", "Ignac35"};
        User user = new User();
        user.setUsername(names[(int) (System.nanoTime() % 4)]);
        user.setPasswordHash("PasswordHash");
        user.setAge((int) (System.nanoTime() % 150));
        user.setSex(System.nanoTime() % 2 == 0 ? Gender.MALE : Gender.FEMALE);
        user.setWeight((int) (System.nanoTime() % 150));
        return user;
    }

}
