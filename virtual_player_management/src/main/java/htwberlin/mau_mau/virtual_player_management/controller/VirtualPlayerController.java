package htwberlin.mau_mau.virtual_player_management.controller;

import htwberlin.mau_mau.virtual_player_management.model.VirtualPlayer;

/**
 * The interface Virtual player controller provides operations related to the virtual player.
 */
public interface VirtualPlayerController {

    /**
     * Create virtual player with a random name from the predefined list.
     *
     * @return the virtual player
     */
    VirtualPlayer createVirtualPlayer();


}
