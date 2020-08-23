package htwberlin.mau_mau.real_player_management.controller;

import htwberlin.mau_mau.real_player_management.model.RealPlayer;

/**
 * The interface Real player controller provides operations over real player's attributes.
 */
public interface RealPlayerController {
    /**
     * Gets value of saidMau boolean.
     *
     * @param player the player
     * @return the boolean
     */
    public boolean isSaidMau(RealPlayer player);

    /**
     * Sets value of saidMau boolean.
     *
     * @param saidMau the boolean
     * @param player  the player
     */
    public void setSaidMau(boolean saidMau, RealPlayer player);

    /**
     * Gets value of saidMauMau boolean.
     *
     * @param player the player
     * @return the boolean
     */
    public boolean isSaidMauMau(RealPlayer player);

    /**
     * Sets value of saidMauMau boolean
     *
     * @param saidMauMau the boolean
     * @param player     the player
     */
    public void setSaidMauMau(boolean saidMauMau, RealPlayer player);
}
