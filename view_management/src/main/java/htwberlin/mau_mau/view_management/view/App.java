package htwberlin.mau_mau.view_management.view;

import htwberlin.mau_mau.configuration_management.service.ConfigurationService;
import htwberlin.mau_mau.configuration_management.service.ConfigurationServiceImpl;
import htwberlin.mau_mau.rules_management.data.GameRulesId;
import htwberlin.mau_mau.view_management.controller.ViewController;
import htwberlin.mau_mau.view_management.controller.ViewControllerImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.Scanner;

/**
 * Provides actions for the player to interact with the game.
 */
public class App
{
    private Scanner scanner;
    private static final Logger LOGGER = LogManager.getLogger(App.class);
    @Autowired
    private ViewController viewController;

    /**
     * Instantiates a new App.
     */
    public App() {
        scanner = new Scanner(System.in);
    }

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        LOGGER.debug("App started");
        App app = new App();
        app.run();
        LOGGER.debug("App finished");
    }

    /**
     * Starts interaction with the user.
     */
    private void run(){
        System.out.println("Welcome to the Mau Mau Palace! Here we offer you your favorite Mau-Mau game against virtual " +
                "players with standard and special rules.");
        ConfigurationService configurationManagement = new ConfigurationServiceImpl();
        ConfigurableApplicationContext context = configurationManagement.context();
        viewController = context.getBean(ViewControllerImpl.class);
        String name = requestPlayerName();
        System.out.println("Hello, "+name+"!");
        int numberOfVirtualPlayers = requestNumberOfVirtualPlayers();
        GameRulesId gameRulesId = requestGameRules();
        viewController.newGameStarted(name, numberOfVirtualPlayers, gameRulesId);
        scanner.nextLine();
        System.out.println("OK, "+name+", let's play Mau-Mau game with "+numberOfVirtualPlayers+" other players, using "
                +gameRulesId.toString().toLowerCase()+ " rules!");
        //call controlgameflow //parametersOfGameEntered
        //System.out.println("Press enter to start the game.....");
        //App calls these methods from ViewController?
        scanner.close();

    }

    /**
     * Requests a player's name from user.
     * @return name
     */
    private String requestPlayerName(){
        System.out.println("Please enter your name: ");
        return scanner.nextLine();
    }

    /**
     * Requests a specific number of virtual players from user.
     * @return num requested number
     */
    private int requestNumberOfVirtualPlayers() {
        return requestLimitedNumber("Enter number of virtual players (1-4):", 1, 4);
    }

    /**
     * Requests a number from user and validates it according to the given limits.
     * @param message text of the message with a request from the user
     * @param min minimal value
     * @param max maximal value
     * @return num requested number
     */
    private int requestLimitedNumber(String message, int min, int max) {
        boolean success = false;
        int num = 1;
        do {
            try {
                System.out.println(message);
                num = scanner.nextInt();
                if (num < min || num > max) {
                    throw new NumberOutOfLimitsException(num,min,max);
                }
                success = true;
            } catch (Exception exception) {
                LOGGER.debug("User input exception: " + exception.getMessage());
                System.out.println("Incorrect input.");
                scanner.nextLine();
            }
        }
        while (!success);

        return num;
    }

    /**
     * Requests user to choose a set of game rules.
     * @return gameRulesId
     */
    private GameRulesId requestGameRules() {
        GameRulesId gameRulesId;

        int num = requestLimitedNumber("Select game rules: 1 - standard rules, 2 - special rules: ", 1, 2);

        if (num == 2) {
            gameRulesId = GameRulesId.SPECIAL;
        } else {
            gameRulesId = GameRulesId.STANDARD;
        }

        return gameRulesId;
    }


}
