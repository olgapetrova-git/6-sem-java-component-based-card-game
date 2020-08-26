package htwberlin.mau_mau.real_player_management.service;

import htwberlin.mau_mau.real_player_management.data.RealPlayer;

/**
 * The interface Real player Service provides operations relating to the real player's attributes.
 */
public interface RealPlayerService {
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
     * @param saidMau the boolean, if the real player has said "Mau" when it is his turn and he only has two cards
     *      *                  left in his hand, at least one of which can be played
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
     * @param saidMauMau the boolean, if the real player has said "Mau Mau", if he can also discard the last card.
     * @param player     the player
     */
    public void setSaidMauMau(boolean saidMauMau, RealPlayer player);

    /**
     * Creates real player data object using given name.
     *
     * @param name the name
     * @return the real player
     */
    public RealPlayer createRealPlayer(String name);


}
