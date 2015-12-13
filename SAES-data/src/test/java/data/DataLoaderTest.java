package data;

import cz.muni.fi.pa165.data.DataLoader;
import cz.muni.fi.pa165.data.SaesDataConfiguration;
import cz.muni.fi.pa165.saes.dao.ActivityRecordDao;
import cz.muni.fi.pa165.saes.dao.BurnedCaloriesDao;
import cz.muni.fi.pa165.saes.dao.SportActivityDao;
import cz.muni.fi.pa165.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import java.io.IOException;

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
    public BurnedCaloriesDao userDao;

    @Inject
    public UserService userService;

    @Inject
    public DataLoader dataLoader;

    @PersistenceContext
    private EntityManager em;

    @Test
    public void createData() throws IOException {
        logger.debug("starting test");

        Assert.assertTrue(activityDao.findAll().size() > 0, "No sport activities. ");
//        Assert.assertTrue(recordDao.findAll().size() > 0, "No activity records. ");
        Assert.assertTrue(caloriesDao.findAll().size() > 0, "No burned calories. ");
        Assert.assertTrue(userDao.findAll().size() > 0, "No no users. ");

        // TODO
    }

}
