package cz.muni.fi.pa165.service.mapping;

import cz.muni.fi.pa165.saes.SportActivitySystemApplicationContext;
import cz.muni.fi.pa165.service.SportActivityService;
import cz.muni.fi.pa165.service.facade.SportActivityFacadeImpl;
import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.dozer.loader.api.BeanMappingBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(SportActivitySystemApplicationContext.class)
@ComponentScan(basePackageClasses = {SportActivityService.class, SportActivityFacadeImpl.class})
public class ServiceConfiguration {

    @Bean
    public Mapper dozer() {
        DozerBeanMapper dozer = new DozerBeanMapper();
        dozer.addMapping(new DozerCustomConfig());
        return dozer;
    }

    /**
     * Custom config for Dozer if needed
     */
    public class DozerCustomConfig extends BeanMappingBuilder {

        @Override
        protected void configure() {
//            mapping(Category.class, CategoryDTO.class);
        }
    }

}
