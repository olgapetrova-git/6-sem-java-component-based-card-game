package htwberlin.mau_mau.game_management.controller;

import htwberlin.mau_mau.game_management.model.GameData;
import htwberlin.mau_mau.real_player_management.model.RealPlayer;
import htwberlin.mau_mau.rules_management.model.GameRulesId;

/**
 * The interface Game controller.
 */
public interface GameController {

    /**
     * Sets up new game.
     *
     * @param realPlayer     the real player
     * @param virtualPlayers the virtual players
     * @param gameRulesId    the game rules id
     * @return gameData the new game
     */
    GameData setupNewGame(RealPlayer realPlayer, int virtualPlayers, GameRulesId gameRulesId);

    /**
     * Add real player to game data.
     *
     * @param gameData the game data
     * @param name     the name of the real player he entered in View
     * @return the game data
     */
    GameData addRealPlayerToGame (GameData gameData, String name);


    /**
     * Add virtual player to game data.
     *
     * @param gameData the game data
     * @return the game data
     */
    GameData addVirtualPlayerToGame(GameData gameData);

    /**
     * Deal cards to players, i.e. save cards in Hand for each Player and store it in gameData.
     *
     * @param gameData the game data
     * @return the game data
     */
    GameData dealCardsToPlayers(GameData gameData);

    /**
     * Make real player move and store it in the game data.
     *
     * @param cardPosition the card position in the player's hand, selected by the player in ui
     * @param gameData     the game data
     * @return the game data
     */
    GameData makeRealPlayerMove(int cardPosition, GameData gameData);

    /**
     * Make virtual player move and store it in the game data.
     *
     * @param gameData the game data
     * @return the game data
     */
    GameData makeVirtualPlayerMove(GameData gameData);
}
