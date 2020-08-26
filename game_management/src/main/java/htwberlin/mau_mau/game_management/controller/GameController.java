package htwberlin.mau_mau.game_management.controller;

import htwberlin.mau_mau.card_management.model.Card;
import htwberlin.mau_mau.card_management.model.Deck;
import htwberlin.mau_mau.game_management.model.GameData;
import htwberlin.mau_mau.rules_management.model.GameRulesId;

/**
 * The interface Game controller.
 */
public interface GameController {

    /**
     * Sets up new game.
     *
     * @param name                   the name of the real player that he entered in view
     * @param numberOfVirtualPlayers the virtual players
     * @param gameRulesId            the game rules id
     * @return gameData the new game
     */
    GameData setupNewGame(String name, int numberOfVirtualPlayers, GameRulesId gameRulesId);

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
     * @return the boolean
     */
    boolean makeGameMoveForRealPlayer(int cardPosition, GameData gameData);


    /**
     * Make virtual player move and store it in the game data, i.e. select a card from the virtual player's hand
     * which can be played against the topmost card of the playing stack.
     *
     * @param topmostCard the topmost card
     * @param hand        the hand
     * @return the boolean
     */
    boolean makeGameMoveForVirtualPlayer(Card topmostCard, Deck hand);
}
