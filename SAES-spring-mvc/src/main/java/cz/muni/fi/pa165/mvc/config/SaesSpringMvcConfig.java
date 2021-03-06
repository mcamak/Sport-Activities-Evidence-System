package cz.muni.fi.pa165.mvc.config;

import cz.muni.fi.pa165.data.SaesDataConfiguration;
import cz.muni.fi.pa165.facade.SportActivityFacade;
import cz.muni.fi.pa165.facade.UserFacade;
import cz.muni.fi.pa165.mvc.security.SecurityConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.format.FormatterRegistry;
import org.springframework.validation.Validator;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import javax.inject.Inject;

/**
 * Created by Marian Camak on 20. 12. 2015.
 */
@Configuration
@EnableWebMvc
@Import({SaesDataConfiguration.class, SecurityConfig.class})
@ComponentScan(basePackages = "cz.muni.fi.pa165.mvc")
public class SaesSpringMvcConfig extends WebMvcConfigurerAdapter {

    @Inject
    SportActivityFacade activityFacade;

    @Inject
    UserFacade userFacade;

    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(new SportActivityConverter(activityFacade));
        registry.addConverter(new UserConverter(userFacade));
    }

    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }

    @Bean
    public ViewResolver viewResolver() {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("/WEB-INF/jsp/");
        viewResolver.setSuffix(".jsp");
        return viewResolver;
    }

    @Bean
    public Validator validator() {
        return new LocalValidatorFactoryBean();
    }
}
