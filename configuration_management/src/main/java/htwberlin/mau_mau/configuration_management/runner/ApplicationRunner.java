package htwberlin.mau_mau.configuration_management.runner;

import htwberlin.mau_mau.configuration_management.service.ConfigurationService;
import htwberlin.mau_mau.configuration_management.service.ConfigurationServiceImpl;
import htwberlin.mau_mau.view_management.controller.ViewController;
import htwberlin.mau_mau.view_management.controller.ViewControllerImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.ConfigurableApplicationContext;
/**
 * https://github.com/olgapetrova-git/mau_mau/
 */

/**
 * Provides an entry point to start a program.
 */
public class ApplicationRunner
{
    private static final Logger LOGGER = LogManager.getLogger(ApplicationRunner.class);

    /**
     * The entry point of application. Starts the application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        LOGGER.debug("ApplicationRunner started");
        ConfigurationService configurationManagement = new ConfigurationServiceImpl();
        ConfigurableApplicationContext context = configurationManagement.context();
        ViewController viewController = context.getBean(ViewControllerImpl.class);
        viewController.run();
        LOGGER.debug("ApplicationRunner finished");
    }
}
