package cz.fi.muni.fi.pa165.service.service;

import cz.muni.fi.pa165.saes.UserFilter;
import cz.muni.fi.pa165.saes.entity.User;
import cz.muni.fi.pa165.service.UserService;
import cz.muni.fi.pa165.service.mapping.ServiceConfiguration;
import enums.Gender;
import java.util.List;
import javax.inject.Inject;
import static org.mockito.Mockito.verify;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotEquals;
import static org.testng.Assert.assertTrue;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 *
 * @author Marian Camak
 */
@ContextConfiguration(classes = ServiceConfiguration.class)
public class UserServiceTest extends AbstractTestNGSpringContextTests {

    @Inject
    private UserService userService;

    User user1;
    User user2;

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
    }

    /**
     * Tests creating
     */
    @Test
    public void createTest() {

        userService.create(user1);
        userService.create(user2);

        // do we find righ ammount of activities? 
        List<User> foundSportActivityList = userService.findAll();
        assertTrue(foundSportActivityList.size() >= 2);

        // do we have right activities?
        assertTrue(foundSportActivityList.contains(user1));
        assertTrue(foundSportActivityList.contains(user2));
    }

    /**
     * Tests creating with null argument
     */
    @Test(expectedExceptions = IllegalArgumentException.class)
    public void createNullTest() {
        userService.create(null);
    }

    /**
     * Tests finding by id
     */
    @Test
    public void findByIdTest() {
        userService.create(user1);
        boolean isRightUserFound
                = user1.equals(userService.findById(user1.getId()));

        assertTrue(isRightUserFound);
    }

    /**
     * Tests finding by null id
     */
    @Test(expectedExceptions = IllegalArgumentException.class)
    public void findByIdNullTest() {
        userService.findById(null);
    }

    /**
     * Tests updating
     */
    @Test
    public void updateTest() {
        userService.create(user1);
        user1.setSex(Gender.FEMALE);
        userService.update(user1);
        User userFound = userService.findById(user1.getId());

        assertEquals(userFound, user1);
    }

    /**
     * Tests updating with null argument
     */
    @Test(expectedExceptions = IllegalArgumentException.class)
    public void updateNullTest() {
        userService.update(null);
    }

    /**
     * Tests deleting
     */
    @Test
    public void deleteTest() {

        userService.create(user1);
        Long burdnedCaloriesId = user1.getId();

        boolean containsBeforeDelete = userService.findById(burdnedCaloriesId) != null;
        userService.delete(user1);
        boolean containsAfterDelete = userService.findById(burdnedCaloriesId) != null;

        System.out.print("POL " + containsAfterDelete);
        assertNotEquals(containsBeforeDelete, containsAfterDelete);
    }

    /**
     * Tests deleting with null argument
     */
    @Test(expectedExceptions = IllegalArgumentException.class)
    public void deleteNullTest() {
        userService.delete(null);
    }

    /**
     * Tests finding all records
     */
    @Test
    public void findAllTest() {
        userService.create(user1);
        userService.create(user2);

        // do we find righ ammount of activities?
        List<User> foundRecordsList = userService.findAll();
        assertTrue(foundRecordsList.size() >= 2);

        // do we have right activities?
        assertTrue(foundRecordsList.contains(user1));
        assertTrue(foundRecordsList.contains(user2));
    }

    /**
     * Tests finding by parameters
     */
//    @Test
//    public void findByParametersTest() {
//        userService.create(user1);
//        userService.create(user2);
//
//        UserFilter userFilter = new UserFilter();
//        userFilter.setMinAge(user1.getAge() - 1);
//        userFilter.setMaxAge(user2.getAge() + 1);
//        
//        System.out.println("BEKL "+userService.findByParameters(userFilter).size());
//
//        assertTrue(userService.findByParameters(userFilter).size() == 2);
//
//    }

    // TODO
    /**
     * Tests finding all records
     */
//    @Test
//    public void authenticateTest() {
//        userService.create(user1);
//        assertTrue(userService.authenticate(user1.getId(), user1.getPasswordHash()));
//    }
}
