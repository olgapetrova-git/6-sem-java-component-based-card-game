package htwberlin.mau_mau.configuration_management.service;

import org.springframework.context.ConfigurableApplicationContext;

/**
 * The interface Configuration Service provides an instance of Spring dependency injection container.
 */
public interface ConfigurationService {
    /**
     * Configurable application context (DI container)
     *
     * @return the configurable application context
     */
    public ConfigurableApplicationContext context();

}
