package htwberlin.mau_mau.real_player_management.service;

import htwberlin.mau_mau.real_player_management.data.RealPlayer;

/**
 * The interface RealPlayerService provides operations relating to the real player's attributes.
 */
public interface RealPlayerService {
    /**
     * Gets value of saidMau boolean.
     *
     * @param player the real player object
     * @return the boolean: true,  if the real player has said 'Mau!', when it is his turn and he only has two cards
     */
    public boolean isSaidMau(RealPlayer player);

    /**
     * Sets value of saidMau boolean.
     *
     * @param saidMau the boolean: true,  if the real player has said 'Mau!'
     * @param player  the real player object
     */
    public void setSaidMau(boolean saidMau, RealPlayer player);

    /**
     * Gets value of saidMauMau boolean.
     *
     * @param player the real player object
     * @return the boolean: true,  if the real player has said 'Mau-Mau!', if he has only the last card
     */
    public boolean isSaidMauMau(RealPlayer player);

    /**
     * Sets value of saidMauMau boolean
     *
     * @param saidMauMau the boolean: true,  if the real player has said 'Mau Mau!'
     * @param player the real player object
     */
    public void setSaidMauMau(boolean saidMauMau, RealPlayer player);

    /**
     * Creates real player data object using given name.
     *
     * @param name the name
     * @return the real player object
     */
    public RealPlayer createRealPlayer(String name);


}
