package htwberlin.mau_mau.game_management.service;

import htwberlin.mau_mau.card_management.data.Card;
import htwberlin.mau_mau.card_management.data.Deck;
import htwberlin.mau_mau.game_management.data.GameData;
import htwberlin.mau_mau.rules_management.data.GameRulesId;

/**
 * The interface Game Service provides operations to control the main game flow.
 */
public interface GameService {

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
     * Deals cards to players, i.e. save cards in Hand for each Player and store it in gameData.
     *
     * @param gameData the game data
     * @return the game data
     */
    GameData dealCardsToPlayers(GameData gameData);

    /**
     * Makes real player move and store it in the game data.
     *
     * @param cardPosition the card position in the player's hand, selected by the player in ui
     * @param gameData     the game data
     * @return the boolean
     */
    boolean makeGameMoveForRealPlayer(int cardPosition, GameData gameData);


    /**
     * Makes virtual player move and store it in the game data, i.e. select a card from the virtual player's hand
     * which can be played against the topmost card of the playing stack.
     *
     * @param topmostCard the topmost card
     * @param hand        the hand
     * @return the boolean
     */
    boolean makeGameMoveForVirtualPlayer(Card topmostCard, Deck hand);
}
