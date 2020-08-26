package htwberlin.mau_mau.configuration_management.controller;

import org.springframework.context.ConfigurableApplicationContext;

/**
 * The interface Configuration controller provides an instance of Spring dependency injection container.
 */
public interface ConfigurationController {
    /**
     * Context configurable application context (DI container)
     *
     * @return the configurable application context
     */
    public ConfigurableApplicationContext context();
}
