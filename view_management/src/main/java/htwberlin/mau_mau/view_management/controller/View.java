package htwberlin.mau_mau.view_management.controller;

import htwberlin.mau_mau.rules_management.model.GameRulesId;

/**
 * The interface View provides actions for player to interact with the game.
 */
public interface View {
    /**
     * Action to start the new game.
     *
     * @param name           the player's name
     * @param numberOfVirtualPlayers number of the virtual players, that real player has selected
     * @param gameRulesId    the game rules id
     */
    void newGameStarted(String name, int numberOfVirtualPlayers, GameRulesId gameRulesId);

   /*

     * Action to select the card from player's hand.
     *
     * @param cardPosition the card position

    void cardSelected(int cardPosition);

    void mauSaid();

    void maumauSaid();

    void gameEnded();
    */
}
