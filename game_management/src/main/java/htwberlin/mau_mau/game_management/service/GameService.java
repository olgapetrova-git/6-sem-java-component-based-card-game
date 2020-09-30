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
     * @param name the name of the real player that this player entered in ui
     * @param numberOfVirtualPlayers the virtual players
     * @param gameRulesId the game rules id
     * @return gameData new game data object representing current game state
     */
    GameData setupNewGame(String name, int numberOfVirtualPlayers, GameRulesId gameRulesId);

    /**
     * Deals cards to players, i.e. save cards in Hand for each Player and store it in gameData.
     *
     * @param gameData the game data object representing current game state
     */
    void dealCardsToPlayers(GameData gameData);

    /**
     * Makes real player move and store it in the game data.
     *
     * @param cardPosition the card position in the player's hand, selected by the player in ui
     * @param gameData     the game data object representing current game state
     * @return RulesResult object that contains rules validation result
     */
    RulesResult makeGameMoveForRealPlayer(int cardPosition, GameData gameData);


    /**
     * Makes virtual player move and store it in the game data, i.e. select a card from the virtual player's hand
     * which can be played against the open card at the top of the playing stack.
     *
     * @param openCard the open card at the top of the playing stack
     * @param hand     Deck object representing cards in the virtual player's hand
     * @param gameData the game data object representing current game state
     * @return RulesResult object that contains rules validation result
     */
    RulesResult makeGameMoveForVirtualPlayer(Card openCard, Deck hand, GameData gameData);


    /**
     * Gets post action from RulesService if the player can not make a move.
     *
     * @param rulesResult object containing rules validation state and result
     * @return the post action object indicating an action to be done in case the move can't be made
     */
    PostAction getPostAction(RulesResult rulesResult);

    /**
     * Counts the number of penalty cards.
     *
     * @param postAction the post action object indicating an action to be done in case the move can't be made
     * @return the int number of penalty cards that should be dealt to the player
     */
    int countPenaltyCards(PostAction postAction);

    /**
     * Saves gameData object to persistent storage.
     *
     * @param gameData the game data object representing current game state
     */
    void saveToDB(GameData gameData);
}
