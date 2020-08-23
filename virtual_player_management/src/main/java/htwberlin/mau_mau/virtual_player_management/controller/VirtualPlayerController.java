package htwberlin.mau_mau.virtual_player_management.controller;

import htwberlin.mau_mau.card_management.model.Card;
import htwberlin.mau_mau.card_management.model.Deck;

/**
 * The interface Virtual player controller provides operations performed by the virtual player.
 */
public interface VirtualPlayerController {

    /**
     * Make virtual player move, i.e. select a card from the virtual player's hand which can be played against
     * the topmost card of the playing stack.
     *
     * @param topmostCard the topmost card
     * @param hand        the hand
     * @return the card
     */
    Card makeVirtualPlayerMove(Card topmostCard, Deck hand);
}
