package htwberlin.mau_mau.configuration_management.service;


import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

@Configuration
public class ConfigurationServiceImpl implements ConfigurationService {
    @Override
    public ConfigurableApplicationContext context(){
        return new AnnotationConfigApplicationContext("htwberlin"); //type of Context (DI container)
    }
/*    @Bean(name = "entityManager")
    private static EntityManager createEntityManager() {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("mau_mau");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        return entityManager;
    }*/
}
