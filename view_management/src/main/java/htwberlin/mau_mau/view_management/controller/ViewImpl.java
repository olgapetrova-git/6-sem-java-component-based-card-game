package htwberlin.mau_mau.view_management.controller;


import htwberlin.mau_mau.game_management.controller.GameController;
import htwberlin.mau_mau.game_management.controller.GameControllerImpl;
import htwberlin.mau_mau.game_management.model.GameData;
import htwberlin.mau_mau.player_management.model.Player;
import htwberlin.mau_mau.real_player_management.model.RealPlayer;
import htwberlin.mau_mau.rules_management.model.GameRulesId;

public class ViewImpl implements View{
    @Override
    public void newGameStarted(String name, int virtualPlayers, GameRulesId gameRulesId) {
        Player player = new RealPlayer(name);
        GameController gameController = new GameControllerImpl();
        GameData gameData = gameController.setupNewGame((RealPlayer) player, virtualPlayers, gameRulesId);
        controlGameFlow(gameData, gameController);
    }


    void controlGameFlow(GameData gameData, GameController gameController) {

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
    boolean doGameTurn(GameData gameData, Player player) {
        // TODO: if the player is real,
        // request player move,
        // *	if real Player pressed the button "Quit", we call endGame ()
        // player move is a position of card in player's Hand (returned by View)
        // call getCardByPositionFromHand(int position) from DeckCardController, it returns player's Card
        // call getTopmostCardFromPlayingStack(gameData), it returns open card to validate player move

        // validate this player's Card vs. open Card via GameRules - call validatePlayerMove(card, card)
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
