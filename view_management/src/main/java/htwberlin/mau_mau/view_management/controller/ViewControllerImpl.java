package htwberlin.mau_mau.view_management.controller;


import htwberlin.mau_mau.game_management.service.GameService;
import htwberlin.mau_mau.game_management.data.GameData;
import htwberlin.mau_mau.player_management.data.Player;
import htwberlin.mau_mau.real_player_management.data.RealPlayer;
import htwberlin.mau_mau.rules_management.data.GameRulesId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ViewControllerImpl implements ViewController {
    @Autowired
    private GameService gameService;

    @Override
    public void newGameStarted(String name, int numberOfVirtualPlayers, GameRulesId gameRulesId) {
        Player player = new RealPlayer(name);

        GameData gameData = gameService.setupNewGame(name, numberOfVirtualPlayers, gameRulesId);
        processMainFlow(gameData, gameService);
    }


    void processMainFlow(GameData gameData, GameService gameService) {


        // call dealCardsToPlayers
        // TODO: once, for each player in ArrayList:
        // call doGameTurn
        // if TRUE, announce victory for the Player (print message) and call endGame()
        // if FALSE, continue for the next player,
        // if all players are checked and none of them has won,
        // start cycle doGameTurn again until win and  call endTheGame()
    }


    /**
     * Do game turn for one real or virtual player.
     *
     * @param gameData the game data
     * @param player   the player
     * @return the boolean: true, if the player has won, else false
     */
    boolean processMove(GameData gameData, Player player) {
        // TODO: if the player is real,
        // request player move,
        // *	if real Player pressed the button "Quit", we call endGame ()
        // player move is a position of card in player's Hand (returned by ViewController)
        // call getCardByPositionFromHand(int position) from DeckCardController, it returns player's Card
        // call getTopmostCardFromPlayingStack(gameData), it returns open card to validate player move

        // validate this player's Card vs. open Card via GameRulesService - call validatePlayerMove(card, card)
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

        return false;
    }

}
