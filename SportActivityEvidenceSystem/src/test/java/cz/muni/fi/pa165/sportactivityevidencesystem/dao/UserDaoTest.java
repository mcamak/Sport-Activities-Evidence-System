package cz.muni.fi.pa165.sportactivityevidencesystem.dao;

import cz.muni.fi.pa165.sportactivityevidencesystem.SportActivitySystemApplicationContext;
import cz.muni.fi.pa165.sportactivityevidencesystem.entity.BurnedCalories;
import cz.muni.fi.pa165.sportactivityevidencesystem.entity.User;
import cz.muni.fi.pa165.sportactivityevidencesystem.enums.Gender;
import java.util.Collection;
import java.util.List;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.test.context.ContextConfiguration;
import org.testng.Assert;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertTrue;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * Tests for UserDaoImpl
 *
 * @author Tomas Effenberger
 */
@RunWith(MockitoJUnitRunner.class)
@ContextConfiguration(classes = SportActivitySystemApplicationContext.class)
@Transactional
public class UserDaoTest {

    @PersistenceContext
    public EntityManager em;

    @Inject
    private UserDao userDao;

    private User user1;
    private User user2;

    @BeforeMethod
    public void createUsers() {
        user1 = new User();
        user1.setAge(20);
        user1.setName("Adam");
        user1.setSex(Gender.MALE);
        user1.setWeight(70);
        userDao.createUser(user1);

        user2 = new User();
        user2.setAge(21);
        user2.setName("Michael");
        user2.setSex(Gender.MALE);
        user2.setWeight(68);
        userDao.createUser(user2);
    }

    /**
     * Test creating new user.
     */
    @Test
    public void testCreateUser() {
        Assert.assertNotNull(user1.getId());
        Assert.assertNotNull(user2.getId());
    }

    /**
     * Test creating user with null argument.
     */
    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testCreateUserWithNullArgument() {
        userDao.createUser(null);
    }
    
    /**
     * Test creating user with null name.
     */
    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testCreateUserWithNullName() {
        User u = createNewRandomUser();
        u.setName(null);
        userDao.createUser(u);
    }
    
    /**
     * Test creating user with empty name.
     */
    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testCreateUserWithEmptyName() {
        User u = createNewRandomUser();
        u.setName("");
        userDao.createUser(u);
    }
    
    /**
     * Test creating user with negative age.
     */
    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testCreateUserWithNegativeAge() {
        User u = createNewRandomUser();
        u.setAge(-5);
        userDao.createUser(u);
    }
    
    /**
     * Test creating user with null sex.
     */
    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testCreateUserWithNullSex() {
        User u = createNewRandomUser();
        u.setSex(null);
        userDao.createUser(u);
    }
    
    /**
     * Test creating user with negative age.
     */
    @Test(expectedExceptions = IllegalArgumentException.class)
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
        User user = userDao.findUser(user1.getId());
        Assert.assertEquals("Adam", user.getName());
        Assert.assertEquals(20, user.getAge());
        Assert.assertEquals(Gender.MALE, user.getSex());
        Assert.assertEquals(70, user.getWeight());
    }
    
    /**
     * Test get user with null ID.
     */
    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testGetUserWithNullId() {
        userDao.findUser(null);
    }
    
    /**
     * Test find all users.
     */
    public void testFindAllUsers() {
        Collection<User> all = userDao.findAllUsers();
        assertEquals(all.size(), 2);
        assertTrue(all.contains(user1));
        assertTrue(all.contains(user2));
    }

    /**
     * Test deleting user.
     */
    @Test
    public void testDeleteUser() {
        Assert.assertNotNull(userDao.findUser(user1.getId()));
        userDao.deleteUser(user1);
        Assert.assertNull(userDao.findUser(user1.getId()));
    }
    
    /**
     * Test delete user with null parameter.
     */
    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testDeleteUserWithNull() {
        userDao.deleteUser(null);
    }
    
    /**
     * Test delete user with null ID.
     */
    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testDeleteUserWithNullId() {
        User u = createNewRandomUser();
        userDao.deleteUser(u);
    }

    /**
     * Test deleting user without ID.
     */
    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testDeleteWithoutId() {
        User user = new User();
        userDao.deleteUser(user);
    }

    /**
     * Test updating user.
     */
    @Test
    public void testUpdateUser() {
        user2.setName("John");
        userDao.updateUser(user2);
        Assert.assertEquals("John", em.find(User.class, user2.getId()));
    }
    
    /**
     * Test updating user with null ID.
     */
    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testUpdateUserWithoutId() {
        User user = createNewRandomUser();
        userDao.updateUser(user);
    }
    
    /**
     * Test updating user with null name.
     */
    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testUpdateUserWithNullName() {
        User user = createNewRandomUser();
        userDao.createUser(user);
        assertNotNull(user.getId());
        user.setName(null);
        userDao.updateUser(user);
    }
    
    /**
     * Test updating user with empty name.
     */
    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testUpdateUserWithEmptyName() {
        User user = createNewRandomUser();
        userDao.createUser(user);
        assertNotNull(user.getId());
        user.setName("");
        userDao.updateUser(user);
    }
    
    /**
     * Test updating user with null sex.
     */
    @Test(expectedExceptions = IllegalArgumentException.class)
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
    @Test(expectedExceptions = IllegalArgumentException.class)
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
    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testUpdateUserWithNegativeWeight() {
        User user = createNewRandomUser();
        userDao.createUser(user);
        assertNotNull(user.getId());
        user.setWeight(-78);
        userDao.updateUser(user);
    }
    
    private User createNewRandomUser() {
        String[] names = new String[]{"Peter", "Jan", "Martina", "Maria"};
        User user = new User();        
        user.setName(names[(int) System.currentTimeMillis() % 4]);
        user.setAge((int) (System.currentTimeMillis() % 150));
        user.setSex(System.currentTimeMillis() % 2 == 0 ? Gender.MALE : Gender.FEMALE);
        user.setWeight((int) (System.currentTimeMillis() % 150));
        return user;
    }

}
