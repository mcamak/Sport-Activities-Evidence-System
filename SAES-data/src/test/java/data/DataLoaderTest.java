package data;

import cz.muni.fi.pa165.data.DataLoader;
import cz.muni.fi.pa165.data.SaesDataConfiguration;
import cz.muni.fi.pa165.saes.dao.ActivityRecordDao;
import cz.muni.fi.pa165.saes.dao.BurnedCaloriesDao;
import cz.muni.fi.pa165.saes.dao.SportActivityDao;
import cz.muni.fi.pa165.saes.dao.UserDao;
import cz.muni.fi.pa165.saes.entity.SportActivity;
import cz.muni.fi.pa165.saes.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.testng.annotations.Test;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.io.IOException;

import static org.testng.Assert.assertTrue;

/**
 * Created by Marian Camak (inQool) on 12. 12. 2015.
 */
@ContextConfiguration(classes = {SaesDataConfiguration.class})
@TestExecutionListeners(TransactionalTestExecutionListener.class)
@Transactional
public class DataLoaderTest extends AbstractTestNGSpringContextTests {

    final static Logger logger = LoggerFactory.getLogger(DataLoaderTest.class);

    @Inject
    public SportActivityDao activityDao;

    @Inject
    public ActivityRecordDao recordDao;

    @Inject
    public BurnedCaloriesDao caloriesDao;

    @Inject
    public UserDao userDao;

    @Inject
    public DataLoader dataLoader;

    @PersistenceContext
    private EntityManager em;

    @Test
    public void createData() throws IOException {
        logger.debug("Starting test...");

        assertTrue(activityDao.findAll().size() > 0, "No sport activities. ");
        assertTrue(recordDao.findAll().size() > 0, "No activity records. ");
        assertTrue(caloriesDao.findAll().size() > 0, "No burned calories. ");
        assertTrue(userDao.findAllUsers().size() > 0, "No no users. ");

        for (User user : userDao.findAllUsers()) {
            assertTrue(recordDao.findRecordsByUser(user).size() > 0, "No records of user " + user.getUsername());
        }
        for (SportActivity activity : activityDao.findAll()) {
            assertTrue(caloriesDao.findBySportActivity(activity).size() > 0, "No burned calories of activity " + activity.getName());
        }

        logger.debug("Test successfully finished.");
    }

}
