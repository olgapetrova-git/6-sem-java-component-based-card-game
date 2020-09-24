package htwberlin.mau_mau.game_management.service;

import htwberlin.mau_mau.card_management.data.Card;
import htwberlin.mau_mau.card_management.data.Deck;
import htwberlin.mau_mau.game_management.data.GameData;
import htwberlin.mau_mau.rules_management.data.GameRulesId;
import htwberlin.mau_mau.rules_management.data.PostAction;
import htwberlin.mau_mau.rules_management.data.RulesResult;

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
     */
    void dealCardsToPlayers(GameData gameData);

    /**
     * Makes real player move and store it in the game data.
     *
     * @param cardPosition the card position in the player's hand, selected by the player in ui
     * @param gameData     the game data
     * @return RulesResult object that contains rules validation result
     */
    RulesResult makeGameMoveForRealPlayer(int cardPosition, GameData gameData);


    /**
     * Makes virtual player move and store it in the game data, i.e. select a card from the virtual player's hand
     * which can be played against the open card at the top of the playing stack.
     *
     * @param openCard the open card at the top of the playing stack
     * @param hand     the virtual player's hand
     * @param gameData the game data
     * @return RulesResult object that contains rules validation result
     */
    RulesResult makeGameMoveForVirtualPlayer(Card openCard, Deck hand, GameData gameData);


    /**
     * Gets post action from RulesService if player can not make a move.
     *
     * @param rulesResult object containing rules validation state and result
     * @return the post action
     */
    PostAction getPostAction(RulesResult rulesResult);

    /**
     * Saves gameData object to persistent storage.
     * @param gameData
     */
    void saveToDB(GameData gameData);
}
