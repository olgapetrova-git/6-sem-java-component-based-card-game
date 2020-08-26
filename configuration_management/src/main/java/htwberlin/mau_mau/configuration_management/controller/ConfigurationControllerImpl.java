package htwberlin.mau_mau.configuration_management.controller;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConfigurationControllerImpl implements ConfigurationController {
    @Override
    public ConfigurableApplicationContext context(){
        return new AnnotationConfigApplicationContext("htwberlin"); //type of Context (DI container)
    }
}
