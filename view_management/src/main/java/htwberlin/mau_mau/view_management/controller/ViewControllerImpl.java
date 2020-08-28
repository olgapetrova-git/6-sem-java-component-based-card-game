package htwberlin.mau_mau.view_management.controller;

import htwberlin.mau_mau.card_management.service.CardService;
import htwberlin.mau_mau.card_management.service.EmptyDrawingStackException;
import htwberlin.mau_mau.card_management.service.EmptyPlayingStackException;
import htwberlin.mau_mau.game_management.data.GameData;
import htwberlin.mau_mau.game_management.service.GameService;
import htwberlin.mau_mau.player_management.data.Player;
import htwberlin.mau_mau.real_player_management.data.RealPlayer;
import htwberlin.mau_mau.rules_management.data.GameRulesId;
import htwberlin.mau_mau.rules_management.service.RulesProvider;
import htwberlin.mau_mau.view_management.view.UI;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * The type View controller.
 */
@Component
public class ViewControllerImpl implements ViewController {
    private static final Logger LOGGER = LogManager.getLogger(ViewController.class);

    @Autowired
    private UI ui;

    @Autowired
    private GameService gameService;

    @Autowired
    private RulesProvider rulesProvider;

    @Autowired
    private CardService cardService;

    @Override
    public void run(){
        ui.showStartGreeting();

        String name = ui.requestPlayerName();
        int numberOfVirtualPlayers = ui.requestNumberOfVirtualPlayers();
        GameRulesId gameRulesId = ui.requestGameRules();
        GameData gameData = gameService.setupNewGame(name, numberOfVirtualPlayers, gameRulesId);
        rulesProvider.chooseRules(gameData.getGameRulesId());

        ui.showNewGameGreeting(name, numberOfVirtualPlayers, gameRulesId);

        processMainFlow(gameData);

        ui.close();
    }

    /**
     * Process main game flow in a console cycle.
     *
     * @param gameData current game object
     */
    private void processMainFlow(GameData gameData) {
        boolean win = false;

        do {
            ui.showTable(gameData.getPlayers().get(0).getHand(), gameData.getOpenCard(), gameData.getPlayers());

            for (Player player : gameData.getPlayers()) {
                gameData.setCurrentPlayer(player);
                win = processMove(gameData, player);
                if(win) { break; }
            }
        } while (!win);
        gameData.setWin(win);
        if (gameData.getCurrentPlayer() instanceof RealPlayer) {
            ui.showVictoryRealPlayer(gameData.getCurrentPlayer().getName());
        } else {
            ui.showVictoryVirtualPlayer(gameData.getCurrentPlayer().getName(), gameData.getPlayers().get(0).getName());
        }

        LOGGER.debug("*** " + gameData.getCurrentPlayer().getName() + " HAS WON! ***");

    //TODO: ??? call endTheGame()
    }

    /**
     * Process one game move for one real or virtual player.
     *
     * @param gameData the game data
     * @param player   the player
     * @return the boolean: true, if the player has won, else false
     */

    boolean processMove(GameData gameData, Player player) {

        boolean win = false;

        if (player instanceof RealPlayer) {

            LOGGER.debug("*** PLAYING STACK SIZE: " + gameData.getPlayingStack().getCards().size());
            LOGGER.debug("*** DRAWING STACK SIZE: " + gameData.getDrawingStack().getCards().size());

            int move = ui.requestPlayerMove(1, player.getHand().getCards().size());

            // Draw card
            if (move >= 1 && move <= player.getHand().getCards().size()) {
                gameService.makeGameMoveForRealPlayer(move, gameData);
            } else if (move == 100) {
                try {
                    cardService.drawCard(gameData.getDrawingStack(), gameData.getPlayingStack(), player.getHand());
                } catch (EmptyPlayingStackException | EmptyDrawingStackException e) {
                    LOGGER.error(e.getMessage());
                }
            } else if (move == 200) {
                // Say mau
                ((RealPlayer) player).setSaidMau(true);
            } else if (move == 300) {
                // Say mau-mau
                ((RealPlayer) player).setSaidMauMau(true);
                win = true;
            } else if (move == 400) {
                //TODO: player quits the game
            }

            LOGGER.debug("*** MOVE: " + move);
        }
        // TODO: if the player is real,
        // request player move,
        // *  if real Player pressed the button "Quit", we call endGame ()
        // player move is a position of card in player's Hand (returned by ViewController)
        // call getCardByPositionFromHand(int position) from DeckCardController, it returns player's Card
        // call getTopmostCardFromPlayingStack(gameData), it returns open card to validate player move

        // validate this player's Card vs. open Card via RulesService - call validatePlayerMove(card, card)
        // if true, call playCard,
        // if false, call addCardToHandFromDrawingStack from DeckCardController

        // if the player is virtual,
        // check for every card in his Hand if the move is possible - anyway, call validatePlayerMove,
        // if true, call playCard,
        // if false, call addCardToHand from DeckCardController
        // then check if the game is won - call validateWin,

        // if validateWin returned true, then checK:

        // if the player is real - check, if has he said "mau" or "mau mau" (boolean),
        // if mau" or "mau mau" boolean is true - return TRUE,
        // if false - call addCardToHand and return FALSE
        // if the player is virtual, display the message "mau" or "mau mau" and return TRUE,

        // if validateWin returned false, return FALSE

        return win;
    }

}
