package cz.muni.fi.pa165.saes;

import cz.muni.fi.pa165.saes.dao.UserDao;
import org.hibernate.jpa.HibernatePersistenceProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

/**
 * Configuration for application.
 * @author Marian Camak
 */
@Configuration
@EnableTransactionManagement
@EnableJpaRepositories
@ComponentScan(basePackageClasses = {UserDao.class})
public class SportActivitySystemApplicationContext {

    @Bean
    public JpaTransactionManager transactionManager() {
        return new JpaTransactionManager(entityManagerFactory().getObject());
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean entityManagerFactoryBean =
                new LocalContainerEntityManagerFactoryBean();
        entityManagerFactoryBean.setDataSource(this.dataSource());
        entityManagerFactoryBean.setPersistenceProvider(new HibernatePersistenceProvider());
        return entityManagerFactoryBean;
    }

    @Bean
    public DataSource dataSource() {
        final EmbeddedDatabaseBuilder databaseBuilder = new EmbeddedDatabaseBuilder();
        databaseBuilder.setType(EmbeddedDatabaseType.DERBY);
        return databaseBuilder.build();
    }
}