package htwberlin.mau_mau.view_management.view;

import htwberlin.mau_mau.card_management.data.Card;
import htwberlin.mau_mau.card_management.data.Deck;
import htwberlin.mau_mau.card_management.data.Suit;
import htwberlin.mau_mau.player_management.data.Player;
import htwberlin.mau_mau.rules_management.data.GameRulesId;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Scanner;

/**
 * Provides actions for the player to interact with the game and handles game responds to the player.
 */
@Component
public class View {
    private final Scanner scanner;

    private static final Logger LOGGER = LogManager.getLogger(View.class);

    private static final String USER_INPUT_EXCEPTION = "User input exception: ";
    private static final String INCORRECT_INPUT = "Incorrect input.";

    /**
     * Instantiates a new View.
     */
    public View() {
        scanner = new Scanner(System.in);
    }

    /**
     * Show start greeting.
     */
    public void showStartGreeting() {
        System.out.println("Welcome to the Mau-Mau Palace! Here we offer you your favourite Mau-Mau game against virtual " +
                "players with standard and special rules.");
    }

    /**
     * Shows the new game greeting and rules info.
     *
     * @param name                   the name
     * @param numberOfVirtualPlayers the number of virtual players
     * @param gameRulesId            the game rules id
     */
    public void showNewGameGreeting(String name, int numberOfVirtualPlayers, GameRulesId gameRulesId) {
        System.out.println("OK, " + name + ", let's play Mau-Mau game with " + numberOfVirtualPlayers + " other players, using "
                + gameRulesId.toString().toLowerCase() + " rules!\n");
        System.out.println("" +
                "Mau-Mau is a card came for 2-4 players. The first player with no cards left wins.\n" +
                "The card you play must match the open card on the top of the playing stack.\n" +
                "Either suit or rank have to match! You can choose a card to play from your hand,\n" +
                "entering a number of card. If you have no suitable card to play, you must draw a card\n" +
                " - enter 'D'. The turn of a player ends directly after drawing a card. \n" +
                "Notice, that you must announce your last card saying 'Mau!' or you get 2 penalty cards.\n" +
                "Similarly, you must declare your win with 'Mau-Mau!' before you play your last card\nR" +
                "or game continues with 2 penalty cards for you and this win is not accepted.\n" +
                "You enter 'M' to say 'Mau!' or 'MM' to say 'Mau-Mau!' To quit the game prematurely, enter 'Q'.");
        if (gameRulesId == GameRulesId.SPECIAL) {
            System.out.println("\nSpecial rules:\n" +
                    "* A SEVEN forces the next player to take two cards from the drawing stack \n" +
                    "- unless he can counter the attack with his own SEVEN. If SEVEN is played again,  \n" +
                    "next player must play SEVEN too or draw four cards. Enter 'D' once to draw and you \n" +
                    "will be given a required number of cards. After that, the game continues as usual.\n\n" +
                    "* An EIGHT means: skip a round, entering 'S' - unless you can counter \n" +
                    "the attack with your own EIGHT and let the following player skip. This rule is no longer \n" +
                    "valid if anyone skips a round or if a second player counters with an EIGHT.\n\n" +
                    "* A NINE means: The direction of play changes. If there are only two players, the card means \n" +
                    "a one-time skipping a round for the next player. \n\n" +
                    "* A JACK is a wish card, it gives a player the right to request a suit of card.\n" +
                    "The next player have to play with the wished suit or draw 2 cards. Moreover, JACK can be played \n" +
                    "on any card regardless of rank or suit. But JACK on JACK is forbidden. JACK does not neutralise \n" +
                    "a SEVEN or an EIGHT.");
        }
    }

    /**
     * Shows game table to the real player, incl. cards in the real player's hand, number of the cards
     * per each virtual player and the open card in the playing stack.
     *
     * @param hand     Deck object representing cards in the real player's hand
     * @param openCard the open card on the top of the playing stack
     * @param players  the players
     */
    public void showTable(Deck hand, Card openCard, List<Player> players) {
        System.out.println("\nPlayers' cards: ");
        for (int i = 1; i < players.size(); i++) {

            System.out.println(players.get(i).getName() + " has "
                    + players.get(i).getHand().getCards().size() + " cards.");
        }
        System.out.println("\nYOUR TURN NOW: \nCards in your hand: ");
        int num = 1;
        for (Card card : hand.getCards()) {
            System.out.println(num + " - " + getCardText(card));
            num++;
        }

        System.out.println("\nOpen card: " + getCardText(openCard));
    }

    /**
     * Gets the text message that consists of suit and rank of the card
     *
     * @param card
     * @return the text message describing a card
     */
    private String getCardText(Card card) {
        return card.getRank().toString() + " of " + card.getSuit().toString();
    }

    /**
     * Show virtual player turn.
     *
     * @param name the name
     */
    public void showVirtualPlayerTurn(String name) {
        System.out.println("\n" + name + " MAKES THE MOVE: ");
    }

    /**
     * Show message that the player drew a card.
     *
     * @param name the name of the player
     * @param card the card of the real player
     */
    public void showDrawCard(String name, Card card) { //100
        if (name.equalsIgnoreCase("You")) {
            System.out.println(name + " drew a card. It's " + getCardText(card));
        } else {
            System.out.println(name + " drew a card.");
        }
    }

    /**
     * Show the message about played card.
     *
     * @param success  boolean true, if the card is successfully played
     * @param name     the player's name
     * @param message  text
     * @param card     the played card
     * @param openCard the open card
     */
    public void showPlayedCard(boolean success, String name, String message, Card card, Card openCard) {
        if (success) {

            System.out.println(name + " played " + getCardText(card)
                    + " against " + getCardText(openCard) + ".\n" + message);
        } else {
            System.out.println(message);
        }
    }


    /**
     * Show that player said "Mau!".
     *
     * @param name the player's name
     */
    public void showMau(String name) { //200
        if (name.equalsIgnoreCase("You")) {
            System.out.println(name + " said 'Mau!' The end of the game is approaching!\n" +
                    "Now, play the card which matches the open card.");
        } else {
            System.out.println(name + " said 'Mau!' The end of the game is approaching! It means, \n" +
                    "there are only two cards left in the player's hand and one of them is played now.");
        }
    }

    /**
     * Show that player said "Mau-Mau!".
     *
     * @param name the player's  name
     */
    public void showMauMau(String name) { //300
        if (name.equalsIgnoreCase("You")) {
            System.out.println("\n" + name + " said 'Mau-Mau!' Luck is on your side. \n" +
                    "Now, play your last card which matches the open card and celebrate your victory!");
        } else {
            System.out.println("\n" + name + " said 'Mau-Mau!' and celebrates the discarding of the last card.");
        }
    }

    /**
     * Show that player said "Mau!" but have too many cards left.
     */
    public void showIncorrectMau() { //200
        System.out.println("You said 'Mau!', but it's not the time yet - you have more than two cards.");
    }

    /**
     * Show that player said "Mau-Mau!" but have too many cards left.
     */
    public void showIncorrectMauMau() { //300
        System.out.println("You said 'Mau-Mau!', but it's not the time yet - you have more than one card.");
    }

    /**
     * Show Mau or Mau-Mau not said.
     *
     * @param mau the mau
     */
    public void showNotSaid(String mau) {
        System.out.println("\nYou forgot to say " + mau + " Sorry, you take 2 penalty cards.");
    }

    /**
     * Show the victory message for the real player.
     *
     * @param name the name of the real player.
     */
    public void showVictoryRealPlayer(String name) {
        System.out.println("\n*** CONGRATULATIONS, " + name + "! ***\n *** YOU HAVE WON! ***");
    }

    /**
     * Show the message for the virtual player's victory.
     *
     * @param name           the name
     * @param nameRealPlayer the name real player
     */
    public void showVictoryVirtualPlayer(String name, String nameRealPlayer) {
        System.out.println("\n*** " + name + " HAS WON! ***\n" +
                "Good luck next time, " + nameRealPlayer + "!");
    }

    /**
     * Show message that player decided to quit the game.
     *
     * @param name the name
     */
    public void showQuit(String name) { //400
        System.out.println("\n So, you decided to end the game, " + name + "! Well, if you want to play " +
                "\n another time, the Mau-Mau Palace is waiting for you. May the luck be with you!\n");
    }

    /**
     * Show that player skipped a round.
     *
     * @param name the player's name
     */
    public void showSkip(String name) {  //500
        System.out.println(name + " skipped this round. ");
    }

    public void showWish(String name, Suit wish) {
        System.out.println(name + " requested  " + wish.toString() + ".");
    }

    /**
     * Requests a player's name from user.
     *
     * @return name string
     */
    public String requestPlayerName() {
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
            } catch (NameIsEmptyException e) {
                System.out.println("Incorrect input. Empty name entered.");
            } catch (Exception e) {
                LOGGER.debug(String.format(USER_INPUT_EXCEPTION + " %s", e.getMessage()));
                System.out.println(INCORRECT_INPUT);
            }
        }
        while (!success);

        System.out.println("Hello, " + name + "!");
        return name;
    }

    /**
     * Request user to press enter.
     */
    public void requestEnter() {
        System.out.println("Press enter to finish.");
        scanner.nextLine();
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
     *
     * @param message text of the message with a request from the user
     * @param min     minimal value
     * @param max     maximal value
     * @return num requested number
     */
    private int requestLimitedNumber(String message, int min, int max) {
        boolean success = false;
        String input = "";
        int num = 1;

        do {
            try {
                System.out.println(message);
                input = scanner.nextLine();
                if (isNumeric(input)) {
                    num = Integer.parseInt(input);
                    if (num < min || num > max) {
                        throw new NumberOutOfLimitsException(num, min, max);
                    }
                } else {
                    throw new NotANumberException(input, min, max);
                }
                success = true;
            } catch (NotANumberException | NumberOutOfLimitsException e) {
                LOGGER.error(String.format(USER_INPUT_EXCEPTION + " %s", e.toString()));
                System.out.println("Incorrect input. Expected value between " + min + " and " + max
                        + ", but received " + input + ".");
            } catch (Exception e) {
                LOGGER.error(String.format(USER_INPUT_EXCEPTION + " %s", e.getMessage()));
                System.out.println(INCORRECT_INPUT);
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
     * Request player move, returns card position in player's hand or code for other operations -
     * draw card, say mau, etc.
     *
     * @param min           first card number in player's hand
     * @param max           last card number in player's hand
     * @param isEightPlayed true if 'eight' rule is enabled
     * @return int card position in player's hand or code for other operations
     */
    public int requestPlayerMove(int min, int max, boolean isEightPlayed) {
        boolean success = false;
        int num = 1;
        String move = "";
        String specialMessage;
        if (isEightPlayed) {
            specialMessage = "'S' - to skip the round, ";
        } else {
            specialMessage = "'D' - to draw a card, ";
        }
        do {
            try {
                System.out.println("\nEnter (" + min + "-" + max + ") to play a card, " + specialMessage +
                        "'M' - to say 'Mau!', \n'MM' - to say 'Mau-Mau!', 'Q' - to quit the game.");
                //S - skip the round,
                move = scanner.nextLine();
                if (isNumeric(move)) {
                    num = Integer.parseInt(move);
                    if (!(num >= min && num <= max)) {
                        throw new PlayerMoveDrawException(move, min, max);
                    }
                    success = true;
                } else {
                    num = parsePlayerMoveInput(move, isEightPlayed, min, max);
                    success = true;
                }
            } catch (PlayerMoveDrawException | PlayerMoveSkipException e) {
                LOGGER.error(String.format(USER_INPUT_EXCEPTION + " %s", e.getMessage()));
                System.out.printf(INCORRECT_INPUT + " %s%n", e.getMessage());
            } catch (Exception e) {
                LOGGER.error(String.format(USER_INPUT_EXCEPTION + " %s", e.getMessage()));
                System.out.println(INCORRECT_INPUT);
            }
        }
        while (!success);

        return num;
    }

    private int parsePlayerMoveInput(String move, boolean isEightPlayed, int min, int max)
            throws PlayerMoveSkipException, PlayerMoveDrawException {
        int num = 0;

        if (move.equalsIgnoreCase("d") && !isEightPlayed) {
            num = 100;
        } else if (move.equalsIgnoreCase("m")) {
            num = 200;
        } else if (move.equalsIgnoreCase("mm")) {
            num = 300;
        } else if (move.equalsIgnoreCase("q")) {
            num = 400;
        } else if (move.equalsIgnoreCase("s") && isEightPlayed) {
            num = 500;
        } else if (isEightPlayed) {
            throw new PlayerMoveSkipException(move, min, max);
        } else {
            throw new PlayerMoveDrawException(move, min, max);
        }

        return num;
    }

    public Suit requestWish() {
        boolean success = false;
        String input = "";
        Suit wishedSuit = Suit.SPADES;
        do {
            try {
                System.out.println("Please choose a suit. Enter 'S' for SPADES, 'C' for CLUBS, \n" +
                        "'H' for HEARTS, 'D' for DIAMONDS.");
                input = scanner.nextLine();

                if (input.equalsIgnoreCase("s")) {
                    wishedSuit = Suit.SPADES;
                } else if (input.equalsIgnoreCase("c")) {
                    wishedSuit = Suit.CLUBS;
                } else if (input.equalsIgnoreCase("h")) {
                    wishedSuit = Suit.HEARTS;
                } else if (input.equalsIgnoreCase("d")) {
                    wishedSuit = Suit.DIAMONDS;
                } else {
                    throw new WishedSuitException(input);
                }
                success = true;
            } catch (WishedSuitException e) {
                System.out.printf(INCORRECT_INPUT + " %s\n", e.getMessage());
            } catch (Exception e) {
                LOGGER.debug(String.format(USER_INPUT_EXCEPTION + " %s", e.getMessage()));
                System.out.println(INCORRECT_INPUT);
            }
        }
        while (!success);

        return wishedSuit;
    }

    /**
     * Frees resources used by View.
     */
    public void close() {
        scanner.close();
    }


    /**
     * Checks if String is an integer
     *
     * @param move String
     * @return true, if integer
     */
    public static boolean isNumeric(String move) {
        if (move == null) {
            return false;
        }
        try {
            Integer.parseInt(move);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }
}
