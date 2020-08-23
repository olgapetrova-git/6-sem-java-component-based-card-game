package htwberlin.mau_mau.view_management.run;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Manages the flow of the game.
 *
 */
public class App 
{
    private static final Logger LOGGER = LogManager.getLogger(App.class);

    public static void main(String[] args) {
        LOGGER.debug("App started");
        System.out.println("Hello World! Call View and Set up new Game goes here");
        LOGGER.debug("App finished");
    }
}
