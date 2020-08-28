package htwberlin.mau_mau.view_management.view;

import htwberlin.mau_mau.card_management.data.Card;
import htwberlin.mau_mau.card_management.data.Deck;
import htwberlin.mau_mau.game_management.data.GameData;
import htwberlin.mau_mau.player_management.data.Player;
import htwberlin.mau_mau.rules_management.data.GameRulesId;
import htwberlin.mau_mau.virtual_player_management.data.VirtualPlayer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Provides actions for the player to interact with the game.
 */
@Component
public class UI
{
    private Scanner scanner;

    private static final Logger LOGGER = LogManager.getLogger(UI.class);

    /**
     * Instantiates a new UI.
     */
    public UI() {
        scanner = new Scanner(System.in);
    }

    /**
     * Show start greeting.
     */
    public void showStartGreeting(){
        System.out.println("Welcome to the Mau Mau Palace! Here we offer you your favorite Mau-Mau game against virtual " +
                "players with standard and special rules.");
    }

    /**
     * Show new game greeting.
     *
     * @param name                   the name
     * @param numberOfVirtualPlayers the number of virtual players
     * @param gameRulesId            the game rules id
     */
    public void showNewGameGreeting(String name, int numberOfVirtualPlayers, GameRulesId gameRulesId)
    {
        System.out.println("OK, "+name+", let's play Mau-Mau game with "+numberOfVirtualPlayers+" other players, using "
                +gameRulesId.toString().toLowerCase()+ " rules!\n");
        System.out.println("" +
                "Mau-Mau is a card came for 2-4 players. The first player with no cards left wins.\n" +
                "The card you play must match the open card on the top of the playing stack.\n" +
                "Either suit or rank have to match! You can choose a card to play from your hand,\n" +
                "entering a number of card. If you have no suitable card to play, you must draw a card\n" +
                " - enter 100. The turn of a player ends directly after drawing a card. \n" +
                "Notice, that you must announce your last card saying 'Mau!' or you get 2 penalty cards.\n" +
                "Similarly, you must declare your win with 'Mau-Mau!' before you play your last card\n" +
                "or game continues with 2 penalty cards for you and this win is not accepted.\n" +
                "You enter 200 to say 'Mau!' or 300 to say 'Mau-Mau!' To quit the game prematurely, enter 400.");
        if (gameRulesId == GameRulesId.SPECIAL) {
            System.out.println("Special rules:\n" +
                    "* A SEVEN forces the next player to take two cards from the drawing stack \n" +
                    "- unless he can counter the attack with his own SEVEN. After that, the game continues as usual.\n" +
                    "* An EIGHT means: skip a round - unless you can counter the attack with your own eight \n" +
                    "and let the following player skip. This does not continue after that.\n" +
                    "* A JACK is a wish card, it gives a player the right to wish a suit of card.\n" +
                    "The next player have to play with wished suit or take 2 cards. Moreover, JACK can be played \n" +
                    "on any card. But JACK on JACK is forbidden.");
        }
    }

    /**
     * Show game table to the real player, incl. cards in the real player's hand, number of the cards
     * per each virtual player and the open card in the playing stack.
     *
     * @param hand     the real player's hand
     * @param openCard the open card on the top of the playing stack
     * @param players  the players
     */
    public void showTable(Deck hand, Card openCard, ArrayList<Player> players)
    {
        System.out.println("Cards in your hand: ");
        int num = 1;
        for(Card card : hand.getCards()) {
            System.out.println(num + " - " + getCardText(card));
            num++;
        }
        System.out.println("Players' cards: ");
        for (int i = 1; i<players.size(); i++) {

                System.out.println(players.get(i).getName() + " has "
                        +players.get(i).getHand().getCards().size() + " cards.");
            }
        System.out.println("Open card: " + getCardText(openCard));
    }

    private String getCardText(Card card){
        return card.getRank().toString()+ " of " + card.getSuit().toString();
    }

    /**
     * Show the victory message for the real player.
     *
     * @param name the name of the real player.
     */
    public void showVictoryRealPlayer(String name){
        System.out.println("*** CONGRATULATIONS, " + name + "! ***\n *** YOU HAVE WON! ***");
    }

    /**
     * Show the message for the virtual player's victory.
     *
     * @param name
     * @param nameRealPlayer
     */
    public void showVictoryVirtualPlayer(String name, String nameRealPlayer){
        System.out.println("*** " +name+" HAS WON! ***\n" +
                "Good luck next time, " + nameRealPlayer +"!");
    }

    /**
     * Requests a player's name from user.
     *
     * @return name string
     */
    public String requestPlayerName(){
        boolean success = false;
        String name = "";
        do {
            try {
                System.out.println("Please enter your name: ");
                name = scanner.nextLine();
                if (name.isEmpty()) {
                    throw new NameIsEmptyException();
                }
                success = true;
            } catch (Exception e) {
                LOGGER.debug("User input exception: " + e.getMessage());
                System.out.println("Incorrect input.");
            }
        }
        while (!success);

        System.out.println("Hello, "+name+"!");
        return name;
    }

    /**
     * Requests a specific number of virtual players from user.
     *
     * @return num requested number
     */
    public int requestNumberOfVirtualPlayers() {
        return requestLimitedNumber("Enter number of virtual players (1-3):", 1, 3);
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
            } catch (InputMismatchException e) {
                LOGGER.error("User input exception: " + e.toString());
                System.out.println("Incorrect input.");
                scanner.nextLine();
            }
            catch (Exception e) {
                LOGGER.error("User input exception: " + e.getMessage());
                System.out.println("Incorrect input.");
                scanner.nextLine();
            }
        }
        while (!success);

        return num;
    }

    /**
     * Requests user to choose a set of game rules.
     *
     * @return gameRulesId game rules id
     */
    public GameRulesId requestGameRules() {
        GameRulesId gameRulesId;

        int num = requestLimitedNumber("Select game rules: 1 - standard rules, 2 - special rules: ", 1, 2);

        if (num == 2) {
            gameRulesId = GameRulesId.SPECIAL;
        } else {
            gameRulesId = GameRulesId.STANDARD;
        }

        return gameRulesId;
    }

    /**
     * Request player move int.
     *
     * @param min the min
     * @param max the max
     * @return the int
     */
    public int requestPlayerMove(int min, int max) {
        boolean success = false;
        int num = 1;
        do {
            try {
                System.out.println("Enter (" + min + "-" + max + ") to play a card, 100 - to draw a card, 200 - to say" +
                        " 'Mau!', 300 - to say 'Mau-Mau!', 400 - to quit the game.");
                num = scanner.nextInt();

                if (!((num >= min && num <= max) || num == 100 || num == 200 || num == 300 || num == 400)) {
                    throw new PlayerMoveException(num,min,max);
                }
                success = true;
            } catch (InputMismatchException e) {
                LOGGER.error("User input exception: " + e.toString());
                System.out.println("Incorrect input.");
                scanner.nextLine();
            }
            catch (Exception e) {
                LOGGER.error("User input exception: " + e.getMessage());
                System.out.println("Incorrect input.");
                scanner.nextLine();
            }
        }
        while (!success);

        return num;
    }

    /**
     * Frees resources used by UI.
     */
    public void close(){
        scanner.close();
    }


}
