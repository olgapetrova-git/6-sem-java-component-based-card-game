package htwberlin.mau_mau.virtual_player_management.service;

import htwberlin.mau_mau.virtual_player_management.data.VirtualPlayer;

/**
 * The interface VirtualPlayerService provides operations related to the virtual player.
 */
public interface VirtualPlayerService {

    /**
     * Creates virtual player with a random name from the predefined list.
     *
     * @return the virtual player object
     */
    VirtualPlayer createVirtualPlayer();


}
