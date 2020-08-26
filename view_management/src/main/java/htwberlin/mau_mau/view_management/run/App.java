package htwberlin.mau_mau.view_management.run;

import htwberlin.mau_mau.configuration_management.controller.ConfigurationController;
import htwberlin.mau_mau.configuration_management.controller.ConfigurationControllerImpl;
import htwberlin.mau_mau.rules_management.model.GameRulesId;
import htwberlin.mau_mau.view_management.controller.View;
import htwberlin.mau_mau.view_management.controller.ViewImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * Manages the flow of the game.
 *
 */
public class App 
{
    private static final Logger LOGGER = LogManager.getLogger(App.class);
    @Autowired
    private static View view;

    public static void main(String[] args) {
        LOGGER.debug("App started");
        System.out.println("Hello World! Call View and Set up new Game goes here");
        LOGGER.debug("App finished");
        ConfigurationController configurationManagement = new ConfigurationControllerImpl();
        ConfigurableApplicationContext context = configurationManagement.context();
        view = context.getBean(ViewImpl.class);
        view.newGameStarted("name", 5, GameRulesId.STANDARD);

    }
}
