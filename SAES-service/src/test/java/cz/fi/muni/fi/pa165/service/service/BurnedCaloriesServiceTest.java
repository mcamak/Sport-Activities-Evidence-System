package cz.fi.muni.fi.pa165.service.service;

import cz.muni.fi.pa165.saes.dao.BurnedCaloriesDao;
import cz.muni.fi.pa165.saes.entity.BurnedCalories;
import cz.muni.fi.pa165.service.BurnedCaloriesService;
import cz.muni.fi.pa165.service.mapping.ServiceConfiguration;
import javax.inject.Inject;
import org.hibernate.service.spi.ServiceException;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.ContextConfiguration;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

/**
 *
 * @author Marian Camak
 */
@ContextConfiguration(classes=ServiceConfiguration.class)
public class BurnedCaloriesServiceTest {
    
    @Mock
    private BurnedCaloriesDao productDao;
    
    @Inject
    @InjectMocks
    private BurnedCaloriesService burCalService;

    @BeforeClass
    public void setup() throws ServiceException
    {
        MockitoAnnotations.initMocks(this);
    }
    
    private BurnedCalories burCalTest;
    
    @BeforeMethod
    public void prepareTestProduct(){
    	burCalTest = new BurnedCalories();
        burCalTest.setBodyWeight(81);
        burCalTest.setCaloriesBurned(200);
//        Price price = new Price();
//        price.setCurrency(Currency.EUR);
//        price.setValue(new BigDecimal(10));
//        testProduct.setCurrentPrice(price);
//        
//        when(exchangeService.getCurrencyRate(Currency.EUR, Currency.CZK)).thenReturn(BigDecimal.valueOf(27));
    }
}
