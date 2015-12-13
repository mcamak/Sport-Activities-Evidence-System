package cz.muni.fi.pa165.data;

import cz.muni.fi.pa165.service.mapping.ServiceConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import java.io.IOException;

/**
 * Created by Marian Camak (inQool) on 12. 12. 2015.
 */
@Configuration
@Import(ServiceConfiguration.class)
@ComponentScan(basePackageClasses = {DataLoaderImpl.class})
public class SaesDataConfiguration {

    final static Logger logger = LoggerFactory.getLogger(SaesDataConfiguration.class);

    @Inject
    DataLoader dataLoader;

    @PostConstruct
    public void loadData() throws IOException {
        logger.info("loading data ...");
        dataLoader.loadData();
    }
}
